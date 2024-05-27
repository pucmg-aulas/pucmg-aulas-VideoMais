/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.calculoValorLocacao;


import strategy.caculos.produto.CalcularValorLivro;
import model.iNegocio.ILivro;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class CalcularLivro implements IChainLocacao
{

    private IChainLocacao proximo;
    
    @Override
    public void setProximo(IChainLocacao proximoNaCadeia)
    {
        this.proximo = proximoNaCadeia;
    }

    @Override
    public double calcular(IProduto produto)
    {
        if(produto instanceof ILivro)
        {
            return new CalcularValorLivro().calcular(produto);
            
        }

        return proximo.calcular(produto);
    }
    
}
