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
public class CalculaValorFilme implements ICalculoProduto{

    @Override
    public double calcular(IProduto p) {
        
        if(p.getStatus().equals("Faixa Verde"))
        {
            return 3;
        }
        else if(p.getStatus().equals("Normal"))
        {
            return 3.50;
        }
        else if(p.getStatus().equals("Lançamento"))
        {
            return 4;
        }
        else if(p.getStatus().equals("Super Lançamento"))
        {
            return 4.50;
        }
        else
        {
            return -1;
        }
        
        
    }



}
