/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.devolucao;

import command.inclusao.devolucao.PagarCommandDevolucao;
import command.inclusao.devolucao.PesquisarClienteCommandDevolucao;
import presenter.InclusaoDevolucaoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoClienteSelecionadoDevolucao extends EstadoDevolucaoView
{

    public EstadoClienteSelecionadoDevolucao(InclusaoDevolucaoPresenter objeto)
    {
        super(objeto);
        new PesquisarClienteCommandDevolucao(objeto).execute();
        
        objeto.getView().getBtnDevolver().setEnabled(false);
        objeto.getView().getBtnPagar().setEnabled(true);
        
        //nao tem cliente selecionado
        if(objeto.getCliente() == null)
        {
            objeto.setEstado(new EstadoNaoSelecionadoDevolucao(objeto));
        }
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
        //nao posso devolver
    }
    
    
}
