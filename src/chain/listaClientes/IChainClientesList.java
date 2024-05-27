/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaClientes;

import model.iNegocio.ICliente;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public interface IChainClientesList
{
    
    public  void setProximo(IChainClientesList proximo);
    
    public  ArrayList<ICliente> processo(ArrayList<ICliente> listaAnterior);
    
}
