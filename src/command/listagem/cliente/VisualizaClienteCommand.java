/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.cliente;

import model.iNegocio.ICliente;
import presenter.InclusaoClientePresenter;
import presenter.ListaClientePresenter;

/**
 *
 * @author Global
 */
public class VisualizaClienteCommand extends AbstractCommandCliente
{

    public VisualizaClienteCommand(ListaClientePresenter receptor)
    {
        super(receptor);

    }

    @Override
    public void execute()
    {
        if (receptor.getCliente() != null)
        {
            ICliente cliente = receptor.getCliente();
            new InclusaoClientePresenter(cliente);
        }
    }
}
