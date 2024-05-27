/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.cliente;

import presenter.ListaClientePresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Global
 */
public class ExcluirClienteCommand extends AbstractCommandCliente
{

    public ExcluirClienteCommand(ListaClientePresenter receptor)
    {
        super(receptor);

    }

    @Override
    public void execute()
    {
        try
        {
            if (receptor.getCliente() != null)
            {
                if (JOptionPane.showConfirmDialog(receptor.getView(), "Deseja realmente excluir o registro?",
                        receptor.getCliente().getNome(), JOptionPane.YES_NO_OPTION) == 0)
                {
                    receptor.getClientes().remove(receptor.getCliente().getNome());
                }
            }
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(ListaClientePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
