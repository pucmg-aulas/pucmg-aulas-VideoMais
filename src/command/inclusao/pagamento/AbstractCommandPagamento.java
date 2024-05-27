/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.pagamento;

import command.Command;
import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandPagamento implements Command
{
    protected InclusaoPagamentoPresenter receptor;

    public AbstractCommandPagamento(InclusaoPagamentoPresenter receptor)
    {
        this.receptor = receptor;
    }
    
    @Override
    public abstract void execute();
}
