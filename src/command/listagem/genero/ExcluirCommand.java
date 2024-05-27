/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.genero;

import presenter.ListaGeneroPresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ExcluirCommand extends AbstractCommand {

    public ExcluirCommand(ListaGeneroPresenter receptor) {
        super(receptor);
    }

    @Override
    public void execute() {

        try {
            if (receptor.getGenero() != null) {
                if (JOptionPane.showConfirmDialog(receptor.getView(), "Deseja realmente excluir o registro?",
                        receptor.getGenero().getNomeGenero(), JOptionPane.YES_NO_OPTION) == 0) {
                    receptor.getGeneros().remove(receptor.getGenero().getNomeGenero());


                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(ListaGeneroPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
