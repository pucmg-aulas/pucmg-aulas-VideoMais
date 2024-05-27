/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.IProdutos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.iNegocio.IItemLocacao;
import model.iNegocio.IProduto;
import observer.produto.ObserverProduto;

/**
 *
 * @author Danilo
 */
public class Produtos extends AbstractDAO implements IProdutos {

    private List produtos;
    private static IProdutos instancia;
    private ArrayList<ObserverProduto> observadores = new ArrayList<ObserverProduto>();

    private Produtos() {

        produtos = new ArrayList<IProduto>();
        leitura();
    }

    public static IProdutos getInstancia() {
        if (instancia == null) 
        {
            instancia = new Produtos();
            return instancia;
            
        } else {
            return instancia;
        }
    }

    @Override
    public void grava() {
        
        super.grava("src/serializacao/Produtos.dat", produtos);
        notifyObservers();
    }

    @Override
    public void leitura() {
        
        produtos = super.leitura("src/serializacao/Produtos.dat");
    }

    @Override
    public IProduto buscarProdutoPorNome(String pNome) {

        IProduto retorno = null;
        for (int i = 0; i < produtos.size(); i++) {
            IProduto l = (IProduto) produtos.get(i);
            if (l.getNomeProduto().toLowerCase().equals(pNome.toLowerCase())) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public boolean remove(String nome) throws IOException {
        boolean remove = false;
        for (Iterator<IProduto> it = produtos.iterator(); it.hasNext();) {
            IProduto produto = it.next();
            if (produto.getNomeProduto().equals(nome)) {
                produtos.remove(produto);
                grava();
                remove = true;
                break;
            }
        }

        return remove;
    }

    @Override
    public IProduto buscarProdutoPorParteDeNome(String pNome) {

        IProduto retorno = null;
        for (int i = 0; i < produtos.size(); i++) {
            IProduto l = (IProduto) produtos.get(i);
            if (l.getNomeProduto().toLowerCase().contains(pNome.toLowerCase())) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public IProduto buscarProdutoPorCodigo(int pCod) {
        IProduto retorno = null;
        for (int i = 0; i < produtos.size(); i++) {
            IProduto l = (IProduto) produtos.get(i);
            if (l.getCodProduto() == pCod) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ArrayList<IProduto> getProdutos() {
        return (ArrayList<IProduto>) produtos;
    }

    @Override
    public boolean adiciona(IProduto p) {
        if (produtos.add(p)) {
            grava();
            return true;
        }
        return false;
    }

    @Override
    public void excluirProduto(IProduto p) {
        produtos.remove(p);
        grava();
    }

    @Override
    public int getUltimoCodigo() {
        int uCod = 0;

        if (this.produtos.size() > 0) {

            IProduto p = (IProduto) produtos.get(produtos.size() - 1);
            uCod = p.getCodProduto() + 1;

        } else {
            uCod = 1;
        }

        return uCod;

    }

    @Override
    public boolean altera(IProduto exixtente, String nomeAnterior) throws IOException {
        try {

            ArrayList<IProduto> listaTemp = new ArrayList<IProduto>();

            for (Iterator<IProduto> it = produtos.iterator(); it.hasNext();) {
                IProduto produto = it.next();
                if (!produto.getNomeProduto().equals(nomeAnterior)) {
                    listaTemp.add(produto);

                } else {
                    listaTemp.add(exixtente);
                }
            }

            produtos.removeAll(produtos);
            produtos.addAll(listaTemp);
            grava();
            leitura();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void alterarDisponibilidadeProduto(List itensAlterados) throws IOException 
    {
        for (int i = 0; i < itensAlterados.size(); i++) 
        {
            IProduto prod = (IProduto) itensAlterados.get(i);
            altera(prod, prod.getNomeProduto());
        }
    }
    
    @Override
    public void setarProdutosAlugado(List itensAlterados) throws IOException
    {
        ArrayList<IItemLocacao> itens = new ArrayList<IItemLocacao>(itensAlterados);
        List alterados = new ArrayList();
        for (int i = 0; i < itens.size(); i++) 
        {
            IProduto prod = itens.get(i).getItem();
            prod.setIndicadorLocacao(0);
            alterados.add(prod);
        }
        alterarDisponibilidadeProduto(alterados);
        
    }
    
    @Override
    public void setarProdutoAlugado(IProduto produto)
    {
        try
        {
            //0 indica produto Alugado
            produto.setIndicadorLocacao(0);
            altera(produto, produto.getNomeProduto());
            
        } catch (IOException ex)
        {
            Logger.getLogger(IProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void setarProdutoDisponivel(IProduto produto)
    {
        try
        {
            //-1 indica produto disponivel
            produto.setIndicadorLocacao(-1);
            altera(produto, produto.getNomeProduto());
            
        } catch (IOException ex)
        {
            Logger.getLogger(IProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setarProdutosDisponiveis(List itensAlterados) throws IOException
    {
        ArrayList<IItemLocacao> itens = new ArrayList<IItemLocacao>(itensAlterados);
        List alterados = new ArrayList();
        for (int i = 0; i < itens.size(); i++) 
        {
            IProduto prod = itens.get(i).getItem();
            prod.setIndicadorLocacao(-1);
            alterados.add(prod);
        }
        alterarDisponibilidadeProduto(alterados);
        
    }

    @Override
    public void addObserver(ObserverProduto observer) {
        observadores.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Iterator<ObserverProduto> it = observadores.iterator(); it.hasNext();) {
            ObserverProduto observerGenero = it.next();
            observerGenero.update(this.getProdutos());
        }
    }


}
