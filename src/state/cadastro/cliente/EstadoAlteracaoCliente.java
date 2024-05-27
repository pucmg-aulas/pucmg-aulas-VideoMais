/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.cliente;

import command.inclusao.cliente.AlterarClienteCommand;
import presenter.InclusaoClientePresenter;

/**
 *
 * @author Danilo
 */
public class EstadoAlteracaoCliente extends EstadoClienteView
{

    public EstadoAlteracaoCliente(InclusaoClientePresenter objeto)
    {
        super(objeto);

        liberarTelaPraEdição();
        objeto.getView().getBtnNovo().setEnabled(false);

        objeto.getView().getBtnSalvar().setText("Salvar");

        objeto.getView().getBtnSair().setText("Cancelar");


        objeto.getView().getJtNome().requestFocus();
    }

    @Override
    public void salvar()
    {
        new AlterarClienteCommand(objeto).execute();
    }

    @Override
    public void fechar()
    {
        objeto.setEstado(new EstadoVisualizacaoCliente(objeto));
    }


    @Override
    public void novo()
    {
        //faz nada pq nao pode
    }
}
