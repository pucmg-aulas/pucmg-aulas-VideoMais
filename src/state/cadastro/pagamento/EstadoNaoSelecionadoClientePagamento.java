/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.pagamento;

import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoNaoSelecionadoClientePagamento extends EstadoPagamentoView
{

    public EstadoNaoSelecionadoClientePagamento(InclusaoPagamentoPresenter objeto)
    {
        super(objeto);
        objeto.setCliente(null);
        objeto.getView().getBtnPagar().setEnabled(false);
        objeto.getView().getBtnVisualizarMultas().setEnabled(false);
        objeto.getView().getJtDesconto().setEnabled(false);
        objeto.getView().getJtCliente().setText("");
    }

    @Override
    public void pagar()
    {
        //nem pode
    }

    @Override
    public void multas()
    {
        //nem pode
    }
    
    
    
}
