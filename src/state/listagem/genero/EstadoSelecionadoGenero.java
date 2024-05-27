/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.genero;

import command.listagem.genero.EditarCommand;
import command.listagem.genero.ExcluirCommand;
import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */
public class EstadoSelecionadoGenero extends EstadoListaGenero{
    
    
     public EstadoSelecionadoGenero(ListaGeneroPresenter objeto) {
        super(objeto);
        objeto.setGenero(null);
        int linha = objeto.getView().getTbGeneros().getSelectedRow();
        String nome = (String) objeto.getView().getTbGeneros().getValueAt(linha, 1);
        objeto.setGenero(objeto.getGeneros().buscarGeneroPorNome(nome));
        objeto.getView().getBtnEditar().setEnabled(true);
        objeto.getView().getBtnExcluir().setEnabled(true);
        objeto.getView().getBtnIncluirNovo().setEnabled(false);
    }

    @Override
    public void deseleciona() {
        objeto.setEstado(new EstadoNaoSelecionadoGenero(objeto));
    }

    @Override
    public void exclui() {
        new ExcluirCommand(objeto).execute();
    }

    @Override
    public void editar() {
        new EditarCommand(objeto).execute();
    }
    
}
