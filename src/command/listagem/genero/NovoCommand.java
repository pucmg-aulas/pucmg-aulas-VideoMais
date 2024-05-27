/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.genero;

import presenter.InclusaoGeneroPresenter;
import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */
public class NovoCommand extends AbstractCommand
{

    public NovoCommand(ListaGeneroPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        new InclusaoGeneroPresenter(null);
    }
}
