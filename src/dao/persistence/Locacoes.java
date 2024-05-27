/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.ILocacoes;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import observer.locacao.ObserverLocacao;

/**
 *
 * @author Danilo
 */
public class Locacoes extends AbstractDAO implements ILocacoes
{

    private List locacoes;
    private static ILocacoes instancia;
    protected List observadores;

    private Locacoes()
    {
        observadores = new ArrayList<ILocacao>();
        locacoes = new ArrayList<ILocacao>();
        leitura();
    }

    public static ILocacoes getInstancia()
    {
        if (instancia == null)
        {
            instancia = new Locacoes();
            return instancia;
            
        } else
        {
            return instancia;
        }
    }

    @Override
    public void grava()
    {
        super.grava("src/serializacao/Locacoes.dat", locacoes);
        notifyObservers();
    }

    @Override
    public void leitura()
    {
        locacoes = super.leitura("src/serializacao/Locacoes.dat");
    }

    @Override
    public ILocacao buscarLocacaoPorCliente(String pNomeCliente)
    {

        ILocacao retorno = null;
        for (int i = 0; i < locacoes.size(); i++)
        {
            ILocacao l = (ILocacao) locacoes.get(i);
            if (l.getCliente().getNome().toLowerCase().equals(pNomeCliente.toLowerCase()))
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ILocacao buscarLocacaoPorCodigo(int pCod)
    {
        ILocacao retorno = null;
        for (int i = 0; i < locacoes.size(); i++)
        {
            ILocacao l = (ILocacao) locacoes.get(i);
            if (l.getCodLocacao() == pCod)
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ArrayList<ILocacao> getLocacoes()
    {
        return (ArrayList<ILocacao>) locacoes;
    }

    @Override
    public boolean adiciona(ILocacao p)
    {
        if (locacoes.add(p))
        {
            grava();
            return true;
        }
        return false;
    }

    @Override
    public void excluir(ILocacao locacao)
    {
        locacoes.remove(locacao);
        grava();
    }

    @Override
    public int getUltimoCodigo()
    {
        int uCod = 0;

        if (locacoes.size() > 0)
        {

            ILocacao l = (ILocacao) locacoes.get(locacoes.size() - 1);
            uCod = l.getCodLocacao() + 1;

        } else
        {
            uCod = 1;
        }

        return uCod;

    }

    @Override
    public boolean altera(ILocacao NovaLocacao, int pCodLocacaoAntiga) throws IOException
    {
        try
        {

            ArrayList<ILocacao> listaTemp = new ArrayList<ILocacao>();

            for (Iterator<ILocacao> it = locacoes.iterator(); it.hasNext();)
            {
                ILocacao locacao = it.next();
                if (locacao.getCodLocacao() != pCodLocacaoAntiga)
                {
                    listaTemp.add(locacao);

                } else
                {
                    listaTemp.add(NovaLocacao);
                }
            }

            locacoes.removeAll(locacoes);
            locacoes.addAll(listaTemp);
            grava();

            return true;

        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public void setItemLocacaoEntregue(ILocacao locacao, int codItemEntregue) throws IOException
    {
        ArrayList<IItemLocacao> listaTemp = new ArrayList<IItemLocacao>();

        for (Iterator<IItemLocacao> it = locacao.getItens().iterator(); it.hasNext();)
        {
            IItemLocacao itemLocacao = it.next();

            if (itemLocacao.getItem().getCodProduto() != codItemEntregue)
            {
                listaTemp.add(itemLocacao);

            } else
            {
                itemLocacao.getItem().setIndicadorLocacao(-1);
                listaTemp.add(itemLocacao);
            }
        }
        
        locacao.getItens().removeAll(locacao.getItens());
        locacao.getItens().addAll(listaTemp);
        
        altera(locacao, locacao.getCodLocacao());

    }

    @Override
    public boolean temMulta(ILocacao locacao)
    {

        boolean retorno = false;
        Date dataAtual = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            for (int i = 0; i < locacao.getItens().size(); i++)
            {
                String pData = locacao.getItens().get(i).getDataPrevistaDevolucao();
                Date dataPrevista = f.parse(pData);

                if (dataAtual.after(dataPrevista))
                {
                    retorno = true;
                    break;
                }
            }
            return retorno;
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    @Override
    public void positivarLocacao(int pCodLoc)
    {
        try
        {
            for (Iterator it = locacoes.iterator(); it.hasNext();)
            {
                ILocacao locacao = (ILocacao) it.next();

                if (locacao.getCodLocacao() == pCodLoc)
                {
                    locacao.setEmAberto('N');
                    altera(locacao, locacao.getCodLocacao());
                    break;
                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void addObserver(ObserverLocacao observer) {
         observadores.add(observer);
        
    }

    @Override
    public void notifyObservers() {
        
        for (Iterator<ObserverLocacao> it = observadores.iterator(); it.hasNext();) 
        {
            ObserverLocacao obs = it.next();
            obs.update(this.getLocacoes());
        }
    }
}
