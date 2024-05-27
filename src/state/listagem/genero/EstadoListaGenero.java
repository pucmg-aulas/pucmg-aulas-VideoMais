/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.genero;

import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */
public abstract class EstadoListaGenero {
    
     protected ListaGeneroPresenter objeto;

    public EstadoListaGenero(ListaGeneroPresenter objeto) {
        
        this.objeto = objeto;
    }

    public void exclui() {
    }
     public void novo() {
    }

    public void editar() {
    }

    public void seleciona() {
    }

    ;

    public void deseleciona() {
    }
;
}
    

