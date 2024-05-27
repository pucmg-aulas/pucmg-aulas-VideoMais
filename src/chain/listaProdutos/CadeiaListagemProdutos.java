/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaProdutos;

import java.util.ArrayList;
import model.iNegocio.IProduto;

/**
 *
 * @author mateus
 */
public interface CadeiaListagemProdutos {
    
     
    public  void setProximo(CadeiaListagemProdutos proximo);
    
    public  ArrayList<IProduto> processo(ArrayList<IProduto> listaAnterior);
    
}
