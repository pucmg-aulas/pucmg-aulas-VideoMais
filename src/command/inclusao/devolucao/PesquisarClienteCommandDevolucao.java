/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.devolucao;

import dao.iPersistence.IClientes;
import dao.iPersistence.ILocacoes;
import dao.persistence.Clientes;
import dao.persistence.Locacoes;
import model.iNegocio.ICliente;
import presenter.InclusaoDevolucaoPresenter;
import java.util.Iterator;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class PesquisarClienteCommandDevolucao extends AbstractCommandDevolucao
{

    IClientes clientes;
    ILocacoes locacoes;

    public PesquisarClienteCommandDevolucao(InclusaoDevolucaoPresenter receptor)
    {
        super(receptor);
        locacoes  = Locacoes.getInstancia();
        clientes = Clientes.getInstancia();
    }

    @Override
    public void execute()
    {

        try
        {

            if (receptor.getView().getJtCliente().getText().isEmpty())
            {
                throw new Exception("Entre com um Cliente Primeiro");
                
            } else
            {
                ICliente cliente = clientes.buscarClientePorNome(receptor.getView().getJtCliente().getText());

                if (JOptionPane.showConfirmDialog(receptor.getView(), "Confirma Cliente Encontrado:" + cliente.getNome()) == JOptionPane.YES_OPTION)
                {
                    receptor.setCliente(cliente);
                    receptor.getView().getJtCliente().setText(cliente.getNome());

                    if (haItensNaoEntreguesDoCliente(cliente))
                    {
                        receptor.carregarLocacoes();
                        
                    } else
                    {
                        receptor.setCliente(null);
                        throw new Exception("Não Há Entregas Pendentes Para o Cliente");
                    }
                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(receptor.getView(), e.getMessage());
        }
    }

    private boolean haItensNaoEntreguesDoCliente(ICliente c)
    {
        for (Iterator it = locacoes.getLocacoes().iterator(); it.hasNext();)
        {
            ILocacao locacao = (ILocacao) it.next();

            if (locacao.getCliente().getNome().equals(c.getNome()))
            {
                for (Iterator<IItemLocacao> it1 = locacao.getItens().iterator(); it1.hasNext();)
                {
                    IItemLocacao itemLocacao = it1.next();
                     //zero indica produto alugado (nao devolvido)
                    if (itemLocacao.getItem().getIndicadorLocacao() == 0)
                    {
                        return true;
                    }   
                }
            }
        }
        return false;
    }
}
