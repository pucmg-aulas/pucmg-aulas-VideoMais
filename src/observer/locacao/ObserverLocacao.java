/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer.locacao;

import java.util.ArrayList;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public interface ObserverLocacao
{
    public void update(ArrayList<ILocacao> locacoes);
}
