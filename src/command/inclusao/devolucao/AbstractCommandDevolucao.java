/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.devolucao;

import command.Command;
import presenter.InclusaoDevolucaoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandDevolucao implements Command
{
    protected InclusaoDevolucaoPresenter receptor;

    public AbstractCommandDevolucao(InclusaoDevolucaoPresenter receptor)
    {
        this.receptor = receptor;
    }
    
    
    
    @Override
    public abstract void execute();
}
