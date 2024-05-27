/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.genero;

import command.listagem.genero.NovoCommand;
import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */

    public class EstadoNaoSelecionadoGenero extends EstadoListaGenero {

    public EstadoNaoSelecionadoGenero(ListaGeneroPresenter objeto) {
        super(objeto);
        objeto.setGenero(null);
        objeto.getView().getBtnExcluir().setEnabled(false);
        objeto.getView().getBtnEditar().setEnabled(false);
        objeto.getView().getBtnIncluirNovo().setEnabled(true);
    }

    @Override
    public void seleciona() {
        objeto.setEstado(new EstadoSelecionadoGenero(objeto));
    }
    
     @Override
     public void novo() 
     {
         new NovoCommand(objeto).execute();
     }
}
