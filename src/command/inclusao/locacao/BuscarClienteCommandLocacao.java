/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import model.iNegocio.ICliente;
import strategy.calculos.cliente.CalculaLimite;
import strategy.calculos.cliente.ICalculoCliente;
import dao.iPersistence.ILocacoes;
import dao.persistence.Locacoes;
import presenter.InclusaoLocacaoPresenter;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class BuscarClienteCommandLocacao extends AbstractCommandLocacao
{

    public BuscarClienteCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        ICliente cliente = null;

        if (receptor.getView().getJtCPFCliente().getText().isEmpty() && receptor.getView().getJtNomeCliente().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Entre com o Código ou Nome do ICliente Primeiro");
            
        } else if (receptor.getView().getJtCPFCliente().getText().equals("   .   .   -  "))
        {
            cliente = receptor.getClientes().buscarClientePorParteDeNome(receptor.getView().getJtNomeCliente().getText());
            
            if (cliente == null)
            {
                JOptionPane.showMessageDialog(receptor.getView(), "Nenhum ICliente Encontrado");
            }
        } else if (receptor.getView().getJtNomeCliente().getText().isEmpty())
        {
            cliente = receptor.getClientes().buscarClientePorCPF(receptor.getView().getJtCPFCliente().getText());
            
            if (cliente == null)
            {
                JOptionPane.showMessageDialog(receptor.getView(), "Nenhum ICliente Encontrado");
            }
        }

        int x = JOptionPane.showConfirmDialog(receptor.getView(), "Confirma ICliente Encontrado: " + cliente.getNome() + " ?");

        if (x == JOptionPane.YES_OPTION)
        {
            receptor.getView().getJtCPFCliente().setText(cliente.getCpf());
            receptor.getView().getJtNomeCliente().setText(cliente.getNome());

            receptor.getView().getJlQtd_Loc().setText(qtdLocacoesMes(cliente));
            receptor.getView().getJtSALDO().setText(TrazValorSaldoAtualizadoCliente(cliente));

            ICalculoCliente cl = new CalculaLimite();
            
            receptor.getView().getJlValorCred().setText(String.valueOf(cl.calcular(cliente)));
            receptor.getView().getJtTOTAL().setText("0.00");
            receptor.setCliente(cliente);
        } else
        {
            receptor.getView().getJtCPFCliente().setText("");
            receptor.getView().getJtNomeCliente().setText("");
        }
    }

    private String qtdLocacoesMes(ICliente c)
    {

        ILocacoes locacoes = Locacoes.getInstancia();
        int qtd = 0;

        for (int i = 0; i < locacoes.getLocacoes().size(); i++)
        {
            StringTokenizer s1 = new StringTokenizer(locacoes.getLocacoes().get(i).getDataLocacao(), "/");
            String dia, mes, ano;
            dia = s1.nextToken();
            mes = s1.nextToken();
            ano = s1.nextToken();

            Date data = new Date();
            int pMes = data.getMonth() + 1;

            if (locacoes.getLocacoes().get(i).getCliente().getNome().equals(c.getNome()) && pMes == Integer.parseInt(mes))
            {
                qtd++;
            }
        }
        return String.valueOf(qtd);
    }

    private String TrazValorSaldoAtualizadoCliente(ICliente c)
    {
        
        ILocacoes locacoes = Locacoes.getInstancia();
        double saldoTotal = 0;

        for (int i = 0; i < locacoes.getLocacoes().size(); i++)
        {
            ILocacao l = locacoes.getLocacoes().get(i);
            if (l.getCliente().getNome().equals(c.getNome()) && l.getEmAberto() == 'S')
            {
                saldoTotal += l.getValorTotal();
            }
        }

        CalculaLimite calc = new CalculaLimite();
        double lim = calc.calcular(c);

        double saldoAtual = lim - saldoTotal;

        return String.valueOf(saldoAtual);
    }
}
