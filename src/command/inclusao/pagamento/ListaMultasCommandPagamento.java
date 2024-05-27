/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.pagamento;

import javax.swing.JOptionPane;
import presenter.InclusaoPagamentoPresenter;
import presenter.ListaMultaPresenter;

/**
 *
 * @author Danilo
 */
public class ListaMultasCommandPagamento extends AbstractCommandPagamento
{

    public ListaMultasCommandPagamento(InclusaoPagamentoPresenter receptor)
    {
        super(receptor);
    }

    
    @Override
    public void execute()
    {
        if(receptor.getLocacoes().temMulta(receptor.getLocacao()))
        {
            new ListaMultaPresenter(receptor.getCliente());
        }
        else
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Não Há Multas!");
        }
    }
    
}
