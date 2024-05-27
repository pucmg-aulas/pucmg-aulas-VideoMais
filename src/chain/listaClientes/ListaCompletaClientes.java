/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaClientes;

import model.iNegocio.ICliente;
import presenter.ListaClientePresenter;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public class ListaCompletaClientes implements IChainClientesList
{

    private IChainClientesList proximo;
    private ListaClientePresenter controller;

    public ListaCompletaClientes(ListaClientePresenter controller)
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
        ArrayList<ICliente> listafiltrada = new ArrayList<ICliente>(listaAnterior);

        if (controller.getView().getJtPesquisar().getText().equals("") && controller.getView().getJcSexo().getSelectedIndex() == 0) {
            return listafiltrada;
        }
        return proximo.processo(listaAnterior);
        
        
    }
    
}
