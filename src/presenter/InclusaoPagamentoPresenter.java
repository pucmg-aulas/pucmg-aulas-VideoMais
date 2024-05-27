/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.InclusaoPagamentoView;
import model.iNegocio.ICliente;
import strategy.caculos.locacao.CalcularMulta;
import dao.iPersistence.IClientes;
import dao.iPersistence.ILocacoes;
import dao.iPersistence.IPagamentos;
import dao.persistence.Clientes;
import dao.persistence.Locacoes;
import dao.persistence.Pagamentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.ILocacao;
import state.cadastro.pagamento.EstadoNaoSelecionadoClientePagamento;
import state.cadastro.pagamento.EstadoPagamentoView;
import state.cadastro.pagamento.EstadoSelecionadoClientePagamento;

/**
 *
 * @author Danilo
 */
public class InclusaoPagamentoPresenter
{
    private ILocacao locacao;
    private InclusaoPagamentoView view;
    private EstadoPagamentoView estado;
    private IClientes clientes = Clientes.getInstancia();
    private ILocacoes locacoes = Locacoes.getInstancia();
    private IPagamentos pagamentos = Pagamentos.getInstancia();
    private ICliente cliente;

    
    public InclusaoPagamentoPresenter(ICliente cliente)
    {
        this.cliente = cliente;
        this.view = new InclusaoPagamentoView();
        
        if(this.cliente == null)
        {
            setEstado(new EstadoNaoSelecionadoClientePagamento(this));
        }
        else
        {
            setEstado(new EstadoSelecionadoClientePagamento(this));
        }
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                sair();
            }
        });
        view.getBtnPesquisar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisarLocacaoCliente();
            }
        });
        view.getBtnPagar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                pagar();
            }
        });
        view.getBtnVisualizarMultas().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                multas();
            }
        });
        
        view.getJtDesconto().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    recalcularValorTotal();
                }
            }
            
            
        });
        view.getTbLocAbertas().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                atualizarLabelsPagamento();
            }
        });
        view.setVisible(true);
        
    }
    
    public void recalcularValorTotal() {
       
        if(view.getJtDesconto().getText().isEmpty())
        {
            int linha = view.getTbLocAbertas().getSelectedRow();
            int pCod = Integer.parseInt(view.getTbLocAbertas().getValueAt(linha, 0).toString());
            ILocacao l = locacoes.buscarLocacaoPorCodigo(pCod);
            
            view.getJlValorTotal().setText(String.valueOf(l.getValorTotal()));
        }
        else
        {
            double desc = Double.parseDouble(view.getJtDesconto().getText());
            double old = Double.parseDouble(view.getJlValorTotal().getText());

            double novo = old - desc;

            view.getJlValorTotal().setText(String.valueOf(novo));
        }
    }
        
    public void pesquisarLocacaoCliente()
    {
        try
        {
           if(view.getJtCliente().getText().isEmpty())
           {
               throw new Exception("Entre com um ICliente Primeiro");
           }
           else
           {
               ICliente c = clientes.buscarClientePorNome(view.getJtCliente().getText());
               
               if(JOptionPane.showConfirmDialog(view, "Confirma ICliente Encontrado:" + c.getNome()) == JOptionPane.YES_OPTION)
               {
                   view.getJtCliente().setText(c.getNome());
                   
                   if(haPagamentoPendentePara(c))
                   {
                      setCliente(c);
                      setEstado(new EstadoSelecionadoClientePagamento(this));
                   }
                   else
                   {
                       setEstado(new EstadoNaoSelecionadoClientePagamento(this));
                       throw new Exception("Não Há IPagamentos Pendentes Para o ICliente");
                   }
               }
           }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    
    public boolean haPagamentoPendentePara(ICliente c) 
    {
        boolean retorno = false;
        
        for(int i = 0; i < locacoes.getLocacoes().size(); i++)
        {
            if(locacoes.getLocacoes().get(i).getEmAberto() == 'S'
                    && locacoes.getLocacoes().get(i).getCliente().getNome().equals(c.getNome()))
            {
                retorno = true;
                break;
            }
        }
        return retorno;
    }
    
    public void carregarLocacoes()
    {
        DefaultTableModel tm;
        Object colunas1[] = {"Cod.", "ICliente", "Valor Total", "Data Loc.", "Em Aberto"};
        
        tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);
        
        for (int i = 0; i < locacoes.getLocacoes().size(); i++) 
        {
            ILocacao locacao =  locacoes.getLocacoes().get(i);
            
            if(locacao.getCliente().getCpf().equals(cliente.getCpf()) && locacao.getEmAberto() == 'S')
            {
                String c1 = locacao.toString();
                String linha[] = c1.split("%");
                tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4]});
            }
            
        }
        view.getTbLocAbertas().setModel(tm);
    }
    
    public void atualizarLabelsPagamento()
    {
        try
        {
            int linha = view.getTbLocAbertas().getSelectedRow();
            int pCodLoc = Integer.parseInt(view.getTbLocAbertas().getValueAt(linha, 0).toString());
            
            this.locacao = locacoes.buscarLocacaoPorCodigo(pCodLoc);
            
            if(locacoes.temMulta(locacao))
            {
                double pMulta = new CalcularMulta().calcular(locacao);
                view.getJlValorMulta().setText(String.valueOf(pMulta));
                view.getJlValorTotal().setText(String.valueOf(pMulta + locacao.getValorTotal()));
                
            }
            else
            {
                view.getJlValorTotal().setText(String.valueOf(locacao.getValorTotal()));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }   
    }
    
    public void sair()
    {
        estado.sair();
    }
    
    public void pagar()
    {
        estado.pagar();
    }
    
    public  void multas()
    {
        estado.multas();
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public void setCliente(ICliente cliente)
    {
        this.cliente = cliente;
    }

    public EstadoPagamentoView getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoPagamentoView estado)
    {
        this.estado = estado;
    }

    public ILocacao getLocacao()
    {
        return locacao;
    }

    public void setLocacao(ILocacao locacao)
    {
        this.locacao = locacao;
    }

    public InclusaoPagamentoView getView()
    {
        return view;
    }

    public void setView(InclusaoPagamentoView view)
    {
        this.view = view;
    }

    public IClientes getClientes()
    {
        return clientes;
    }

    public ILocacoes getLocacoes()
    {
        return locacoes;
    }

    public IPagamentos getPagamentos()
    {
        return pagamentos;
    }
    
    
}
