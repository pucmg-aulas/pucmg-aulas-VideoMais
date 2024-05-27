/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.IDevolucoes;
import java.util.ArrayList;
import java.util.List;
import model.iNegocio.IDevolucaoItem;

/**
 *
 * @author Danilo
 */
public class Devolucoes extends AbstractDAO implements IDevolucoes
{

    private List devolucoes;
    private static Devolucoes instancia;

    private Devolucoes()
    {

        devolucoes = new ArrayList<IDevolucaoItem>();
        leitura();
    }

    public static IDevolucoes getInstancia()
    {
        if (instancia == null)
        {
            instancia = new Devolucoes();
            return instancia;
            
        } else
        {
            return instancia;
        }
    }

    @Override
    public void grava()
    {
        super.grava("src/serializacao/Devolucoes.dat", devolucoes);
    }

    @Override
    public void leitura()
    {
        devolucoes = super.leitura("src/serializacao/Devolucoes.dat");
    }

    @Override
    public IDevolucaoItem buscarDevolucaoItemPorCliente(String pCPF)
    {

        IDevolucaoItem retorno = null;
        for (int i = 0; i < devolucoes.size(); i++)
        {
            IDevolucaoItem l = (IDevolucaoItem) devolucoes.get(i);
            if (l.getLocacao().getCliente().getCpf().equals(pCPF))
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ArrayList<IDevolucaoItem> getDevolucoes()
    {
        return (ArrayList<IDevolucaoItem>) devolucoes;
    }

    @Override
    public void adiciona(IDevolucaoItem p)
    {
        devolucoes.add(p);
        grava();
    }

    @Override
    public void excluirDevolucaoItem(IDevolucaoItem p)
    {
        devolucoes.remove(p);
        grava();
    }
    
}
