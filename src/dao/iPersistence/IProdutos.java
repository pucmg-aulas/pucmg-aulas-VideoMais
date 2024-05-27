/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.iNegocio.IProduto;
import observer.produto.ObserverProduto;

/**
 *
 * @author Danilo
 */
public interface IProdutos
{

    void addObserver(ObserverProduto observer);

    boolean adiciona(IProduto p);

    boolean altera(IProduto exixtente, String nomeAnterior) throws IOException;

    IProduto buscarProdutoPorCodigo(int pCod);

    IProduto buscarProdutoPorNome(String pNome);

    IProduto buscarProdutoPorParteDeNome(String pNome);

    void excluirProduto(IProduto p);

    ArrayList<IProduto> getProdutos();

    int getUltimoCodigo();

    void grava();

    void leitura();

    void notifyObservers();

    boolean remove(String nome) throws IOException;

    void setarProdutoAlugado(IProduto produto);

    void setarProdutoDisponivel(IProduto produto);

    void setarProdutosAlugado(List itensAlterados) throws IOException;

    void setarProdutosDisponiveis(List itensAlterados) throws IOException;
    
}
