/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.produto;

import presenter.InclusaoProdutoPresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.iNegocio.IProduto;
import state.cadastro.produto.EstadoInclusaoProduto;

/**
 *
 * @author Danilo
 */
public class IncluirProdutoCommand extends AbstractCommandProduto
{
     public IncluirProdutoCommand(InclusaoProdutoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            valida();
            receptor.setEstado(new EstadoInclusaoProduto(receptor));
            
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(IncluirProdutoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected boolean salva(IProduto produto) throws IOException
    {
        return !receptor.getProdutos().adiciona(produto);
    }
}
