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
public class CalcularValorLivro implements ICalculoProduto{

    @Override
    public double calcular(IProduto p) {
        
        if(p.getStatus().equals("Faixa Verde"))
        {
            return 8;
        }
        else if(p.getStatus().equals("Normal"))
        {
            return 10.50;
        }
        else if(p.getStatus().equals("Lançamento"))
        {
            return 15;
        }
        else if(p.getStatus().equals("Super Lançamento"))
        {
            return 20;
        }
        else
        {
            return -1;
        }
        
    }
    
}
