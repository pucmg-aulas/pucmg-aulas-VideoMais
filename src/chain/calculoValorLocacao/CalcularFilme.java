/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.calculoValorLocacao;

import strategy.caculos.produto.CalculaValorFilme;
import model.iNegocio.IFilme;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class CalcularFilme implements IChainLocacao
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
        if (produto instanceof IFilme)
        {
            return new CalculaValorFilme().calcular(produto);

        }

        return proximo.calcular(produto);
    }
}
