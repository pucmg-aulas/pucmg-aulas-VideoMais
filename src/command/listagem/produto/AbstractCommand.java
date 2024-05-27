/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.produto;

import command.Command;
import presenter.ListaProdutoPresenter;

/**
 *
 * @author mateus
 */
public abstract class AbstractCommand implements Command
{

    protected ListaProdutoPresenter receptor;

    @Override
    public abstract void execute();

    public AbstractCommand(ListaProdutoPresenter receptor)
    {
        this.receptor = receptor;
    }
}
