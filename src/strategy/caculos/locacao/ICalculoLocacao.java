/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.caculos.locacao;

import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public interface ICalculoLocacao
{
    public double calcular(ILocacao locacao);
    
}
