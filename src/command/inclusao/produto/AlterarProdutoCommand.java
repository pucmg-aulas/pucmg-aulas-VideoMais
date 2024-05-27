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
import state.cadastro.produto.EstadoVisualizacaoProduto;

/**
 *
 * @author Danilo
 */
public class AlterarProdutoCommand extends AbstractCommandProduto
{
    public AlterarProdutoCommand(InclusaoProdutoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            valida();
            receptor.setEstado(new EstadoVisualizacaoProduto(receptor));
            
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(AlterarProdutoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected boolean salva(IProduto produto) throws IOException
    {
        return !receptor.getProdutos().altera(produto, receptor.getNomeAnterior());
    }
    
}
