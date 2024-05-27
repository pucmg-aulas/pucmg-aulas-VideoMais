/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer.pagamento;

import java.util.ArrayList;
import model.iNegocio.IPagamento;

/**
 *
 * @author Danilo
 */
public interface ObserverPagamento
{
    public void update(ArrayList<IPagamento> locacoes);
}
