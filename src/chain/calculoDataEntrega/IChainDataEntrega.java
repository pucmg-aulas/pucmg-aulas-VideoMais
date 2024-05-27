/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.calculoDataEntrega;

import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public interface IChainDataEntrega
{
    public abstract void setProximo(IChainDataEntrega proximoNaCadeia);
    
    public abstract String calcular(IProduto produto);
}
