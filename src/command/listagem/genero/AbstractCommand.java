/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.genero;

import command.Command;
import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */
public abstract class AbstractCommand implements Command{
        protected ListaGeneroPresenter receptor;

    @Override
    public abstract void execute();

    public AbstractCommand(ListaGeneroPresenter receptor) {
        this.receptor = receptor;
    }
}
