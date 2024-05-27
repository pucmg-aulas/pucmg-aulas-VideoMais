/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.cliente;

import command.Command;
import presenter.ListaClientePresenter;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandCliente implements Command
{

    protected ListaClientePresenter receptor;
    
    public AbstractCommandCliente(ListaClientePresenter receptor)
    {
        this.receptor = receptor;
    }
    
    @Override
    public abstract void execute();
}
