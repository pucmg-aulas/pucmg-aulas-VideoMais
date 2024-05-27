/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.locacao;

import command.listagem.locacao.ExcluirLocacaoCommand;
import command.listagem.locacao.VisualizarLocacaoCommand;
import presenter.ListaLocacaoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoSelecionadoLocacao extends EstadoListaLocacao
{

    public EstadoSelecionadoLocacao(ListaLocacaoPresenter objeto)
    {
        super(objeto);
        objeto.getView().getBtnExcluir().setEnabled(true);
        objeto.getView().getBtnVisualizar().setEnabled(true);
    }

    
    @Override
    public void visualizar()
    {
        new VisualizarLocacaoCommand(objeto).execute();
    }

    @Override
    public void excluir()
    {
        new ExcluirLocacaoCommand(objeto).execute();
    }
    
}
