/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.genero;

import presenter.InclusaoGeneroPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Danilo
 */
public class EstadoVisualizacaoGenero extends EstadoGeneroView
{

    public EstadoVisualizacaoGenero(InclusaoGeneroPresenter objeto)
    {
        super(objeto);
        objeto.getView().setTitle("Visualização");

        objeto.setNomeAnterior(objeto.getGenero().getNomeGenero());

        preencherTela(objeto);
        bloquearTelaPraEdição(objeto);
        
        objeto.getView().getBtnSalvar().setText("Editar");
        objeto.getView().getBtnSalvar().requestFocus();

    }

    @Override
    public void salvar()
    {
        objeto.setEstado(new EstadoAlteracaoGenero(objeto));
    }

    private void preencherTela(InclusaoGeneroPresenter objeto)
    {
        objeto.getView().getJtNomeGenero().setText(objeto.getGenero().getNomeGenero());
        objeto.getView().getJlCOD().setText(String.valueOf(objeto.getGenero().getCodGenero()));
    }

    private void bloquearTelaPraEdição(InclusaoGeneroPresenter objeto)
    {
        objeto.getView().getJtNomeGenero().setEditable(false);

    }
}
