/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.produto;

import model.iNegocio.IProduto;
import presenter.InclusaoProdutoPresenter;
import presenter.ListaProdutoPresenter;

/**
 *
 * @author mateus
 */
public class EditarCommand extends AbstractCommand
{

    public EditarCommand(ListaProdutoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        int linha = receptor.getView().getTbProdutos().getSelectedRow();
        String cod = (String) receptor.getView().getTbProdutos().getValueAt(linha, 0);
        int pCod =Integer.parseInt(cod);
        
        IProduto produto = receptor.getProdutos().buscarProdutoPorCodigo(pCod);
        receptor.setProduto(produto);
        
        new InclusaoProdutoPresenter(produto);

    }
}
