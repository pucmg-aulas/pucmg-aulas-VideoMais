/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.devolucao;

import presenter.InclusaoDevolucaoPresenter;
import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class PagarCommandDevolucao extends AbstractCommandDevolucao
{

    public PagarCommandDevolucao(InclusaoDevolucaoPresenter receptor)
    {
        super(receptor);
    }

    
    @Override
    public void execute()
    {
        new InclusaoPagamentoPresenter(receptor.getCliente());
        
    }
    
}
