/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaClientes;

import model.iNegocio.ICliente;
import presenter.ListaClientePresenter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Danilo
 */
public class ListaNomeClientes implements IChainClientesList
{

    private IChainClientesList proximo;
    private ListaClientePresenter controller;

    public ListaNomeClientes(ListaClientePresenter controller)
    {
        this.controller = controller;
    }

    @Override
    public void setProximo(IChainClientesList proximo)
    {
        this.proximo = proximo;
    }

    @Override
    public ArrayList<ICliente> processo(ArrayList<ICliente> listaAnterior)
    {
        ArrayList<ICliente> listafiltrada = null;

        if (controller.getView().getJtPesquisar().getText().isEmpty())
        {
            listafiltrada = new ArrayList<ICliente>(listaAnterior);

        } else
        {
            listafiltrada = new ArrayList<ICliente>();
            Iterator<ICliente> it = listaAnterior.iterator();

            while (it.hasNext())
            {
                ICliente cliente = it.next();

                if (cliente.getNome().toLowerCase().contains(controller.getView().getJtPesquisar().getText().toLowerCase()))
                {
                    listafiltrada.add(cliente);
                }
            }
        }

        return proximo.processo(listafiltrada);
    }
    
}
