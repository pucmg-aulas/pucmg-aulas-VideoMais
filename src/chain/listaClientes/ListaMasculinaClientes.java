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
public class ListaMasculinaClientes implements IChainClientesList
{

    private IChainClientesList proximo;
    private ListaClientePresenter controller;

    public ListaMasculinaClientes(ListaClientePresenter controller)
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

        if (controller.getView().getJcSexo().getSelectedIndex() == 2)
        {
            listafiltrada = new ArrayList<ICliente>(listaAnterior);
            
        } else
        {
            listafiltrada = new ArrayList<ICliente>();
            Iterator<ICliente> it = listaAnterior.iterator();
            
            while (it.hasNext())
            {
                ICliente cliente = it.next();
                
                if (cliente.getSexo() == 'M')
                {
                    listafiltrada.add(cliente);
                }
            }
        }
        
        return proximo.processo(listafiltrada);
    }
    
}
