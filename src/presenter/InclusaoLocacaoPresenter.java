/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.InclusaoLocacaoView;
import model.iNegocio.ICliente;
import command.inclusao.locacao.AddItemCommandLocacao;
import command.inclusao.locacao.BuscarClienteCommandLocacao;
import command.inclusao.locacao.BuscarProdutoCommandLocacao;
import command.inclusao.locacao.ExcluirItemCommandLocacao;
import command.inclusao.locacao.SalvarCommandLocacao;
import dao.iPersistence.IClientes;
import dao.iPersistence.ILocacoes;
import dao.iPersistence.IProdutos;
import dao.persistence.Clientes;
import dao.persistence.Locacoes;
import dao.persistence.Produtos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IItemLocacao;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class InclusaoLocacaoPresenter
{

    private InclusaoLocacaoView view;
    private IClientes clientes;
    private IProdutos produtos;
    private ILocacoes locacoes;
    private ICliente cliente;
    private ArrayList<IItemLocacao> itensSelecionados;
    private DefaultTableModel tmSelected;
    private DefaultTableModel tmProdutos;

    public InclusaoLocacaoPresenter()
    {
        clientes = Clientes.getInstancia();
        produtos = Produtos.getInstancia();
        locacoes = Locacoes.getInstancia();
        itensSelecionados = new ArrayList<IItemLocacao>();

        view = new InclusaoLocacaoView();
        view.getJlQtd_Loc().setText("0");
        view.getJlValorCred().setText("0");

        carregarTabelaProdutos();
        carregarTabelaSelecionados();

        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                sair();
            }
        });
        
        view.getBtnADD().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                addItem();
            }
        });
        
        view.getBtnExcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                excluirItem();
            }
        });
        
        view.getBtnBuscarCliente().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                buscarCliente();
            }
        });
        
        view.getBtnBuscarProduto().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                buscarProduto();
            }
        });
        
        view.getBtnLimparCamposBusca().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                limparCamposProduto();
            }
        });
        
        view.getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                salvar();
            }
        });
        
        view.setVisible(true);
    }

    public void addItem()
    {
        new AddItemCommandLocacao(this).execute();
    }

    public void excluirItem()
    {
        new ExcluirItemCommandLocacao(this).execute();
    }

    public void sair()
    {
        view.setVisible(false);
        view.dispose();
    }

    public void salvar()
    {
        new SalvarCommandLocacao(this).execute();
    }

    public void buscarCliente()
    {
        new BuscarClienteCommandLocacao(this).execute();
    }

    public void buscarProduto()
    {
        new BuscarProdutoCommandLocacao(this).execute();
    }

    public void limparCamposProduto()
    {
        view.getJtCodProduto().setText("");
        view.getJtTituloProd().setText("");
        carregarTabelaProdutos();
    }

    public InclusaoLocacaoView getView()
    {
        return view;
    }

    public void limpaCampos()
    {
        view.getJtCPFCliente().setText("");
        view.getJtCodProduto().setText("");
        view.getJtTituloProd().setText("");
        view.getJtNomeCliente().setText("");
        view.getJtSALDO().setText("");
        view.getJtTOTAL().setText("0.00");
        view.getJlQtd_Loc().setText("0");
        view.getJlValorCred().setText("0");
    }

    private void carregarTabelaProdutos()
    {
        Object colunas[] = {"Cod.", "Descrição", "Lançamento", "Status", "IGenero", "Disponivel", "Categoria"};
        tmProdutos = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tmProdutos.setNumRows(0);
        
        for (int i = 0; i < produtos.getProdutos().size(); i++)
        {
            IProduto produto = produtos.getProdutos().get(i);

            String c1 = produto.toString2();
            String linha[] = c1.split("%");
            tmProdutos.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]});
                
        }
        view.getTbProdutos().setModel(tmProdutos);

    }

    private void carregarTabelaSelecionados()
    {
        Object colunas[] = {"Cod.", "Descrição", "Lançamento", "Status", "IGenero", "Disponivel", "Categoria"};


        tmSelected = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tmSelected.setNumRows(0);
        
        for (int i = 0; i < itensSelecionados.size(); i++)
        {
            IProduto produto = itensSelecionados.get(i).getItem();

            String c1 = produto.toString2();
            String linha[] = c1.split("%");
            tmSelected.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]});
                
        }
        view.getTbProdSelecionados().setModel(tmSelected);
        
    }
    
    public void setarProdutosAlugados() throws IOException
    {
        produtos.setarProdutosAlugado(itensSelecionados);
    }
    
    public void atualizaCampoValorTotal(Double valor)
    {
        
        double old = Double.parseDouble(view.getJtTOTAL().getText());
        double novo = old + valor;
        
        view.getJtTOTAL().setText(String.valueOf(novo));
    }

    public void fecharTela()
    {
        view.setVisible(false);
        view.dispose();
    }

    public ILocacoes getLocacoes()
    {
        return locacoes;
    }

    public void setLocacoes(ILocacoes locacoes)
    {
        this.locacoes = locacoes;
    }
    
    public ArrayList<IItemLocacao> getItensSelecionados()
    {
        return itensSelecionados;
    }

    public void setItensSelecionados(ArrayList<IItemLocacao> itensSelecionados)
    {
        this.itensSelecionados = itensSelecionados;
    }

    public DefaultTableModel getTmSelected()
    {
        return tmSelected;
    }

    public void setTmSelected(DefaultTableModel tmSelected)
    {
        this.tmSelected = tmSelected;
    }

    public DefaultTableModel getTmProdutos()
    {
        return tmProdutos;
    }

    public void setTmProdutos(DefaultTableModel tmProdutos)
    {
        this.tmProdutos = tmProdutos;
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public void setCliente(ICliente cliente)
    {
        this.cliente = cliente;
    }

    public IClientes getClientes()
    {
        return clientes;
    }

    public void setClientes(IClientes clientes)
    {
        this.clientes = clientes;
    }

    public IProdutos getProdutos()
    {
        return produtos;
    }

    public void setProdutos(IProdutos produtos)
    {
        this.produtos = produtos;
    }
    
    
    
}
