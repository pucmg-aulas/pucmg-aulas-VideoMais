/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaPagamentos;

import java.util.ArrayList;
import model.iNegocio.IPagamento;

/**
 *
 * @author Danilo
 */
public interface IChainPagamento
{
    public  void setProximo(IChainPagamento proximo);
    
    public  ArrayList<IPagamento> processo(ArrayList<IPagamento> listaAnterior);
}
