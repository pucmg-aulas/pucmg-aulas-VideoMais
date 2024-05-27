/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer.cliente;

import model.iNegocio.ICliente;
import java.util.ArrayList;

/**
 *
 * @author Global
 */
public interface ObserverCliente {
    
     public void update(ArrayList<ICliente> clientes);
}
