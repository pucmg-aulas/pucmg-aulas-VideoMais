/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.cliente;

import command.inclusao.cliente.IncluirClienteCommand;
import presenter.InclusaoClientePresenter;

/**
 *
 * @author Danilo
 */
public class EstadoInclusaoCliente extends EstadoClienteView
{

    public EstadoInclusaoCliente(InclusaoClientePresenter objeto) 
    {
        super(objeto);
        
        //objeto.getView().getBtnNovo().setEnabled(false);
        objeto.limpaCampos();
        liberarTelaPraEdição();
        objeto.getView().getBtnSalvar().setText("Salvar");
        objeto.getView().getJtNome().requestFocus();
        objeto.getView().getBtnNovo().setEnabled(false);
        objeto.getView().getBtnSair().setText("Cancelar");
    }

    @Override
    public void salvar() 
    {
        new IncluirClienteCommand(objeto).execute();
    }


    @Override
    public void fechar()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }

    @Override
    public void novo()
    {
        //faz nada.. pq nao pode
    }
 
    
}
