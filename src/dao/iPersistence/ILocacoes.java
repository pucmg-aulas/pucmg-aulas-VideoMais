/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.io.IOException;
import java.util.ArrayList;
import model.iNegocio.ILocacao;
import observer.locacao.ObserverLocacao;

/**
 *
 * @author Danilo
 */
public interface ILocacoes
{

    boolean adiciona(ILocacao p);

    boolean altera(ILocacao NovaLocacao, int pCodLocacaoAntiga) throws IOException;

    ILocacao buscarLocacaoPorCliente(String pNomeCliente);

    ILocacao buscarLocacaoPorCodigo(int pCod);

    void excluir(ILocacao locacao);

    ArrayList<ILocacao> getLocacoes();

    int getUltimoCodigo();

    void grava();

    void leitura();

    void positivarLocacao(int pCodLoc);

    void setItemLocacaoEntregue(ILocacao locacao, int codItemEntregue) throws IOException;

    boolean temMulta(ILocacao locacao);
    
    void notifyObservers();
    
    void addObserver(ObserverLocacao observer);
    
}
