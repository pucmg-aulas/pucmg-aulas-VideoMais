/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.genero;

import command.inclusao.genero.AlterarGeneroCommand;
import presenter.InclusaoGeneroPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Danilo
 */
public class EstadoAlteracaoGenero extends EstadoGeneroView
{

    public EstadoAlteracaoGenero(InclusaoGeneroPresenter objeto)
    {
        super(objeto);

        liberarTelaPraEdição(objeto);

        objeto.getView().getBtnSalvar().setText("Salvar");

        objeto.getView().getBtnCancelar().setText("Cancelar");

        objeto.getView().getJtNomeGenero().requestFocus();
    }

    @Override
    public void salvar()
    {
        new AlterarGeneroCommand(objeto).execute();
    }

    @Override
    public void cancelar()
    {
        objeto.setEstado(new EstadoVisualizacaoGenero(objeto));
    }

    public void liberarTelaPraEdição(InclusaoGeneroPresenter objeto)
    {
        objeto.getView().getJtNomeGenero().setEditable(true);
    }
}
