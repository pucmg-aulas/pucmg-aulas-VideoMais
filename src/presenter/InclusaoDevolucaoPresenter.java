/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.iPersistence.IDevolucoes;
import dao.iPersistence.ILocacoes;
import dao.persistence.Devolucoes;
import dao.persistence.Locacoes;
import view.InclusaoDevolucaoView;
import model.iNegocio.ICliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import state.cadastro.devolucao.EstadoDevolucaoView;
import state.cadastro.devolucao.EstadoLocacaoSelecionadaDevolucao;
import state.cadastro.devolucao.EstadoNaoSelecionadoDevolucao;

/**
 *
 * @author Danilo
 */
public class InclusaoDevolucaoPresenter
{

    private InclusaoDevolucaoView view;
    private IDevolucoes devolucoes;
    private ICliente cliente;
    private ILocacao locacao;
    private EstadoDevolucaoView estado;
    ILocacoes locacoes = Locacoes.getInstancia();

    public InclusaoDevolucaoPresenter()
    {
        this.devolucoes = Devolucoes.getInstancia();
        view = new InclusaoDevolucaoView();
        
        setEstado(new EstadoNaoSelecionadoDevolucao(this));
        
        view.getBtnPesquisar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                pesquisar();
            }
        });
        view.getBtnDevolver().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                devolver();
            }
        });
        view.getBtnPagar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                pagar();
            }
        });
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                sair();
            }
        });
        
        view.getTbLocacaoCliente().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e)
            {
                setEstado(new EstadoLocacaoSelecionadaDevolucao(InclusaoDevolucaoPresenter.this));
            }
        });
        
        view.setVisible(true);
    }

    public void pesquisar()
    {
        estado.pesquisar();
    }

    public void pagar()
    {
        estado.pagar();
    }

    public void devolver()
    {
        estado.devolver();
    }

    public void sair()
    {
        estado.sair();
    }

    public void carregarLocacoes()
    {
        try
        {
            if (view.getJtCliente().getText().isEmpty())
            {
                throw new Exception("Nenhum Cliente Selecionado!");
            }

            DefaultTableModel tm;
            Object colunas1[] =
            {
                "Cod.", "Cliente", "Valor Total", "Data Loc.", "Em Aberto"
            };

            tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};;
            tm.setNumRows(0);


            for (Iterator<ILocacao> it = locacoes.getLocacoes().iterator(); it.hasNext();)
            {
                ILocacao locacao = it.next();
                boolean flag = false;

                if (locacao.getCliente().getNome().toLowerCase().contains(view.getJtCliente().getText().toLowerCase()))
                {
                    for (int j = 0; j < locacao.getItens().size(); j++)
                    {
                        //0 = produto locado
                        if (locacao.getItens().get(j).getItem().getIndicadorLocacao() == 0)
                        {
                            flag = true;
                            break;
                        }
                    }
                    //se tiver produtos nao devolvidos, eu exibo..
                    if (flag)
                    {
                        String c1 = locacao.toString();
                        String linha[] = c1.split("%");
                        tm.addRow(new Object[]
                                {
                                    linha[0], linha[1], linha[2], linha[3], linha[4]
                                });
                    }
                }
            }
            view.getTbLocacaoCliente().setModel(tm);
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void carregarItensLocacao()
    {
        try
        {
            if(view.getTbLocacaoCliente().getSelectedRow() == -1)
            {
                throw new Exception("Nenhuma Locação Selecionada!");
            }
            int row = view.getTbLocacaoCliente().getSelectedRow();
            int pCod = Integer.parseInt(view.getTbLocacaoCliente().getValueAt(row, 0).toString());
            
            this.locacao = locacoes.buscarLocacaoPorCodigo(pCod);
            
            
            DefaultTableModel tm;
            Object colunas1[] = {"Cod.", "IProduto", "Valor Unitario", "Data Prevista Devolução", "Devolvido"};

            tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
            tm.setNumRows(0);

            for (Iterator<IItemLocacao> it1 = locacao.getItens().iterator(); it1.hasNext();)
            {
                IItemLocacao itemLocacao = it1.next();
                String c1 = itemLocacao.toString();
                String linha[] = c1.split("%");
                tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4]});
            }
            view.getTbItensLocacao().setModel(tm);
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    


    //==============================================================================
    //==============================================================================
    public InclusaoDevolucaoView getView()
    {
        return view;
    }

    public void setView(InclusaoDevolucaoView view)
    {
        this.view = view;
    }

    public ILocacoes getLocacoes()
    {
        return locacoes;
    }
    
    
    public IDevolucoes getDevolucoes()
    {
        return devolucoes;
    }

    public void setDevolucoes(IDevolucoes devolucoes)
    {
        this.devolucoes = devolucoes;
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public void setCliente(ICliente cliente)
    {
        this.cliente = cliente;
    }

    public ILocacao getLocacao()
    {
        return locacao;
    }

    public void setLocacao(ILocacao locacao)
    {
        this.locacao = locacao;
    }

    public EstadoDevolucaoView getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoDevolucaoView estado)
    {
        this.estado = estado;
    }
}
