/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.produto;

import command.inclusao.produto.IncluirProdutoCommand;
import presenter.InclusaoProdutoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoInclusaoProduto extends EstadoProdutoView
{
    public EstadoInclusaoProduto(InclusaoProdutoPresenter objeto)
    {
        super(objeto);
        objeto.limpaCampos();
        objeto.getView().getJtDescProd().requestFocus();
    }

    @Override
    public void salvar()
    {
        new IncluirProdutoCommand(objeto).execute();
    }
}
