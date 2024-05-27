/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.pagamento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import model.iNegocio.IPagamento;
import model.negocio.Pagamento;
import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class RegistrarPagamentoCommand extends AbstractCommandPagamento
{

    public RegistrarPagamentoCommand(InclusaoPagamentoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            if (receptor.getView().getJtCliente().getText().isEmpty())
            {
                throw new Exception("Selecione Um Cliente Primeiro");

            } else if (receptor.getView().getTbLocAbertas().getSelectedRow() == -1)
            {
                throw new Exception("Selecione Uma Linha Primeiro!");
            } else
            {
                int linha = receptor.getView().getTbLocAbertas().getSelectedRow();
                String codloc = receptor.getView().getTbLocAbertas().getValueAt(linha, 0).toString();

                Date data = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                ILocacao loc = receptor.getLocacoes().buscarLocacaoPorCodigo(Integer.parseInt(codloc));
                
                if(!foiDevolvida(loc))
                {
                    throw new Exception("Itens ainda não foram devolvidos!");
                }

                if (JOptionPane.showConfirmDialog(receptor.getView(), "Confirma Pagamento?") == JOptionPane.YES_OPTION)
                {
                    IPagamento pagamento = new Pagamento(loc, Double.parseDouble(receptor.getView().getJlValorTotal().getText()), format.format(data), Double.parseDouble(receptor.getView().getJtDesconto().getText()));
                    receptor.getPagamentos().adiciona(pagamento);
                    receptor.getLocacoes().positivarLocacao(Integer.parseInt(codloc));

                    JOptionPane.showMessageDialog(receptor.getView(), "Pagamento Registrado com Sucesso!");
                    receptor.carregarLocacoes();
                    limparCampos();

                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(receptor.getView(), e.getMessage());
        }
    }

    public void limparCampos()
    {
        receptor.getView().getJlValorMulta().setText("0.00");
        receptor.getView().getJlValorTotal().setText("0.00");
        receptor.getView().getJtDesconto().setText("0.00");

    }

    private boolean foiDevolvida(ILocacao locacao)
    {
        boolean retorno = false;

        for (Iterator<IItemLocacao> it = locacao.getItens().iterator(); it.hasNext();)
        {
            IItemLocacao iItemLocacao = it.next();

            if (iItemLocacao.getItem().getIndicadorLocacao() == -1)
            {
                retorno = true;
            } else
            {
                return false;
            }
        }
        return retorno;
    }
}
