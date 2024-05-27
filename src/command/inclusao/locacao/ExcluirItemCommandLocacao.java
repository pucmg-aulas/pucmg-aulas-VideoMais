/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import presenter.InclusaoLocacaoPresenter;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;

/**
 *
 * @author Danilo
 */
public class ExcluirItemCommandLocacao extends AbstractCommandLocacao
{

    public ExcluirItemCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {

        try
        {
            int linha = receptor.getView().getTbProdSelecionados().getSelectedRow();
            String cod = receptor.getView().getTbProdutos().getValueAt(linha, 0).toString();
            int codItem = Integer.parseInt(cod);

            if (linha == -1)
            {
                throw new Exception("Selecione Uma Linha Primeiro!");
            }

            int x = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir?");

            if (x == JOptionPane.YES_OPTION)
            {
                receptor.getTmSelected().removeRow(linha);
                //Excluir o produto da lista
                removerListaItens(codItem);

            }

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void removerListaItens(int codItem)
    {              
        for(int i = 0; i < receptor.getItensSelecionados().size(); i++)       
        {
            IItemLocacao it = receptor.getItensSelecionados().get(i);
            if(it.getItem().getCodProduto() == codItem)
            {
                receptor.getItensSelecionados().remove(it);
                
                break;
            }
        }
    }
}
