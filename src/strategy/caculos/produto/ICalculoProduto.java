/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.caculos.produto;

import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public interface ICalculoProduto {
    
    public double calcular(IProduto p);
    
}
