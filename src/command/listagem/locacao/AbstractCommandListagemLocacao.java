/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.locacao;

import presenter.ListaLocacaoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandListagemLocacao implements command.Command
{

    protected ListaLocacaoPresenter receptor;

    @Override
    public abstract void execute();

    public AbstractCommandListagemLocacao(ListaLocacaoPresenter receptor)
    {
        this.receptor = receptor;
    }
}
