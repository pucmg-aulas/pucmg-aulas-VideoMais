/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.produto;




import command.listagem.produto.EditarCommand;
import command.listagem.produto.ExcluirCommand;
import presenter.ListaProdutoPresenter;

/**
 *
 * @author mateus
 */
public class EstadoSelecionadoProduto extends EstadoListaProduto{
    
    
     public EstadoSelecionadoProduto(ListaProdutoPresenter objeto) {
        
        super(objeto);
        
        objeto.getView().getBtnEditar().setEnabled(true);
        objeto.getView().getBtnExcluir().setEnabled(true);
        
    }

    @Override
    public void deseleciona() 
    {
        objeto.setEstado(new EstadoNaoSelecionadoProduto(objeto));
    }

    @Override
    public void exclui() {
        new ExcluirCommand(objeto).execute();
    }

    @Override
    public void editar() {
        new EditarCommand(objeto).execute();
    }
    
}
