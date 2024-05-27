/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.produto;

import command.inclusao.produto.AlterarProdutoCommand;
import presenter.InclusaoProdutoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoAlteracaoProduto extends EstadoProdutoView
{
    
    public EstadoAlteracaoProduto(InclusaoProdutoPresenter objeto)
    {
        super(objeto);

        liberarTelaPraEdição(objeto);

        objeto.getView().getBtnSalvar().setText("Salvar");

        objeto.getView().getBtnSair().setText("Cancelar");


        objeto.getView().getJtDescProd().requestFocus();
    }

    @Override
    public void salvar()
    {
        new AlterarProdutoCommand(objeto).execute();
    }

    @Override
    public void cancelar()
    {
        objeto.setEstado(new EstadoVisualizacaoProduto(objeto));
    }

    public void liberarTelaPraEdição(InclusaoProdutoPresenter objeto)
    {
          
        objeto.getView().getJcCategoria().setEnabled(true);
        
        objeto.getView().getJcGenero().setEnabled(true);
        
        objeto.getView().getJcStatusFilme().setEnabled(true);
        
        objeto.getView().getJtAnoLanc().setEnabled(true);
        objeto.getView().getJtDescProd().setEnabled(true);
        
        objeto.getView().getBtnSalvar().setEnabled(true);
    }
    
}
