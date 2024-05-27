/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer.genero;

import java.util.ArrayList;
import model.iNegocio.IGenero;

/**
 *
 * @author Danilo
 */
public interface ObserverGenero
{
    public void update(ArrayList<IGenero>generos);
}
