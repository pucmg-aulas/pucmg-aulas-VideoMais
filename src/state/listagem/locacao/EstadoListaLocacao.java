/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.locacao;

import presenter.ListaLocacaoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class EstadoListaLocacao
{
    protected ListaLocacaoPresenter objeto;

    public EstadoListaLocacao(ListaLocacaoPresenter objeto)
    {
        this.objeto = objeto;
    }
    
    
    public void sair()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }
    
    public abstract void visualizar();
    
    public abstract void excluir();
    
    
}
