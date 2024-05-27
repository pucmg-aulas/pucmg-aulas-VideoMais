/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import command.Command;
import presenter.InclusaoLocacaoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandLocacao implements Command
{
    
    protected InclusaoLocacaoPresenter receptor;

    public AbstractCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        this.receptor = receptor;
    }
    
        
    @Override
    public abstract void execute();
}
