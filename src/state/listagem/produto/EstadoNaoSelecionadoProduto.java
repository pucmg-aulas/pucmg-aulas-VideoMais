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

    public class EstadoNaoSelecionadoProduto extends EstadoListaProduto {

    public EstadoNaoSelecionadoProduto(ListaProdutoPresenter objeto) {
        super(objeto);
        objeto.setProduto(null);
        objeto.getView().getBtnExcluir().setEnabled(false);
        objeto.getView().getBtnEditar().setEnabled(false);
       
    }

    @Override
    public void seleciona() {
        objeto.setEstado(new EstadoSelecionadoProduto(objeto));
    }
    
     
}
