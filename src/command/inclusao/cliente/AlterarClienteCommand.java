/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.cliente;

import model.iNegocio.ICliente;
import presenter.InclusaoClientePresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import state.cadastro.cliente.EstadoVisualizacaoCliente;

/**
 *
 * @author Danilo
 */
public class AlterarClienteCommand extends AbstractCommandCliente
{

    public AlterarClienteCommand(InclusaoClientePresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            valida();
            receptor.setEstado(new EstadoVisualizacaoCliente(receptor));
            
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(AlterarClienteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected boolean salva(ICliente cliente) throws IOException
    {
        return !receptor.getClientes().altera(cliente, receptor.getNomeAnterior());
    }
}
