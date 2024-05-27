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
public class EstadoNaoSelecionadoLocacao extends EstadoListaLocacao
{

    public EstadoNaoSelecionadoLocacao(ListaLocacaoPresenter objeto)
    {
        super(objeto);
        objeto.carregarTabela();
        objeto.getView().getBtnExcluir().setEnabled(false);
        objeto.getView().getBtnVisualizar().setEnabled(false);
    }

    @Override
    public void visualizar()
    {
        //faz nada pq nao pode
    }

    @Override
    public void excluir()
    {
        //faz nada pq nao pode
    }
    
    
}
