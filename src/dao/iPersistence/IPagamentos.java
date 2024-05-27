/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.util.ArrayList;
import model.iNegocio.IPagamento;
import observer.pagamento.ObserverPagamento;

/**
 *
 * @author Danilo
 */
public interface IPagamentos
{

    void adiciona(IPagamento p);

    IPagamento buscarPagamentosPorCliente(String pCPF);

    void excluirPagamento(IPagamento p);

    ArrayList<IPagamento> getPagamentos();

    void grava();

    void leitura();
    
    void notifyObservers();
    
    void addObserver(ObserverPagamento observer);
    
}
