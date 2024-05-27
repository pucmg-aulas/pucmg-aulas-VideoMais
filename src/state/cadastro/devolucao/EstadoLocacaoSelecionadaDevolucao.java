/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.devolucao;

import command.inclusao.devolucao.EfetuarDevolucaoCommand;
import command.inclusao.devolucao.PagarCommandDevolucao;
import presenter.InclusaoDevolucaoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoLocacaoSelecionadaDevolucao extends EstadoDevolucaoView
{

    public EstadoLocacaoSelecionadaDevolucao(InclusaoDevolucaoPresenter objeto)
    {
        super(objeto);
        objeto.carregarItensLocacao();
        objeto.getView().getBtnDevolver().setEnabled(true);
        objeto.getView().getBtnPagar().setEnabled(true);
    }

    @Override
    public void pesquisar()
    {
        objeto.setEstado(new EstadoClienteSelecionadoDevolucao(objeto));
    }

    @Override
    public void pagar()
    {
        new PagarCommandDevolucao(objeto).execute();
    }

    @Override
    public void devolver()
    {
        new EfetuarDevolucaoCommand(objeto).execute();
        objeto.carregarItensLocacao();
    }
    
    
}
