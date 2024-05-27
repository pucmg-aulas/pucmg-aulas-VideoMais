/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.devolucao;

import presenter.InclusaoDevolucaoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoNaoSelecionadoDevolucao extends EstadoDevolucaoView
{

    public EstadoNaoSelecionadoDevolucao(InclusaoDevolucaoPresenter objeto)
    {
        super(objeto);
        objeto.setCliente(null);
        objeto.getView().getBtnDevolver().setEnabled(false);
        objeto.getView().getBtnPagar().setEnabled(false);
        objeto.getView().getJtCliente().setText("");
    }

    @Override
    public void pesquisar()
    {
        objeto.setEstado(new EstadoClienteSelecionadoDevolucao(objeto));
    }

    @Override
    public void pagar()
    {
        //fazer nada.. nem pde
    }

    @Override
    public void devolver()
    {
        //faz nada.. pode nao
    }
    
    
}
