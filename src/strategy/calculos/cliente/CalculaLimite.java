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
public class CalculaLimite implements ICalculoCliente{

    @Override
    public double calcular(ICliente c) {
        
        return c.getRenda() * 0.05;
        
    }
    
}
