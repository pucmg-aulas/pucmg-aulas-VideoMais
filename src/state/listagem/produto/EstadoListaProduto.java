/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.produto;

import presenter.ListaProdutoPresenter;

/**
 *
 * @author mateus
 */
public abstract class EstadoListaProduto {
    
     protected ListaProdutoPresenter objeto;

    public EstadoListaProduto(ListaProdutoPresenter objeto) {
        
        this.objeto = objeto;
        objeto.carregaComboGenero();
    }

    public void exclui() {
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
    

