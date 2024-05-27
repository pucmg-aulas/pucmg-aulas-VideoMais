/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.calculos.cliente;

import model.iNegocio.ICliente;


/**
 *
 * @author Danilo
 */
public interface ICalculoCliente {
    
    public double calcular(ICliente c);
    
}
