/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.pagamento;

import command.inclusao.pagamento.ListaMultasCommandPagamento;
import command.inclusao.pagamento.RegistrarPagamentoCommand;
import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoSelecionadoClientePagamento extends EstadoPagamentoView
{

    public EstadoSelecionadoClientePagamento(InclusaoPagamentoPresenter objeto)
    {
        super(objeto);
        objeto.carregarLocacoes();
        objeto.getView().getJtCliente().setText(objeto.getCliente().getNome());
        objeto.getView().getBtnPagar().setEnabled(true);
        objeto.getView().getBtnVisualizarMultas().setEnabled(true);
        objeto.getView().getJtDesconto().setEnabled(true);
        
    }

    @Override
    public void pagar()
    {
        new RegistrarPagamentoCommand(objeto).execute();
    }

    @Override
    public void multas()
    {
        new ListaMultasCommandPagamento(objeto).execute();
    }
    
}
