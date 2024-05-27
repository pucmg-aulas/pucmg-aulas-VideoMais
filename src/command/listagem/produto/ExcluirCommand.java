/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.produto;

import presenter.ListaProdutoPresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ExcluirCommand extends AbstractCommand {

    public ExcluirCommand(ListaProdutoPresenter receptor) {
        super(receptor);
    }

    @Override
    public void execute() {
        try {
            if (receptor.getProduto() != null) {
                if (JOptionPane.showConfirmDialog(receptor.getView(), "Deseja realmente excluir o registro?",
                        receptor.getProduto().getNomeProduto(), JOptionPane.YES_NO_OPTION) == 0) {
                    receptor.getProdutos().remove(receptor.getProduto().getNomeProduto());
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(ListaProdutoPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
