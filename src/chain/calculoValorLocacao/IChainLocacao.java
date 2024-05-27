/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.calculoValorLocacao;

import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public interface IChainLocacao
{
    public abstract void setProximo(IChainLocacao proximoNaCadeia);
    
    public abstract double calcular(IProduto produto);
    
}
