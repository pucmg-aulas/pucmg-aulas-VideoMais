/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.genero;

import command.inclusao.genero.IncluirGeneroCommand;
import presenter.InclusaoGeneroPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoInclusaoGenero extends EstadoGeneroView
{

    public EstadoInclusaoGenero(InclusaoGeneroPresenter objeto)
    {
        super(objeto);
        objeto.limpaCampos();
        objeto.getView().getJtNomeGenero().requestFocus();
    }

    @Override
    public void salvar()
    {
        new IncluirGeneroCommand(objeto).execute();
    }
}
