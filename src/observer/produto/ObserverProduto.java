/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer.produto;

import java.util.ArrayList;
import model.iNegocio.IProduto;

/**
 *
 * @author mateus
 */
public interface ObserverProduto {
    
     public void update(ArrayList<IProduto>generos);
    
}
