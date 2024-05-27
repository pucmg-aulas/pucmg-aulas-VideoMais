/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.ListaProdutoView;
import chain.listaProdutos.CadeiaListagemProdutos;
import chain.listaProdutos.ListagemCompleta;
import chain.listaProdutos.ListagemPorCategoria;
import chain.listaProdutos.ListagemPorGenero;
import chain.listaProdutos.ListagemPorNome;
import chain.listaProdutos.ListagemPorStatus;
import dao.iPersistence.IGeneros;
import dao.iPersistence.IProdutos;
import dao.persistence.Generos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IGenero;
import model.iNegocio.IProduto;
import observer.produto.ObserverProduto;
import dao.persistence.Produtos;
import state.listagem.produto.EstadoListaProduto;

import state.listagem.produto.EstadoNaoSelecionadoProduto;
import state.listagem.produto.EstadoSelecionadoProduto;

/**
 *
 * @author mateus
 */
public class ListaProdutoPresenter implements ObserverProduto
{

    private ListaProdutoView view;
    private IProduto produto;
    private DefaultTableModel tm;
    private IProdutos produtos;
    private IGeneros generos;
    private EstadoListaProduto estado;

    public ListaProdutoPresenter() throws FileNotFoundException, IOException
    {
        view = new ListaProdutoView();
        this.produtos = Produtos.getInstancia();
        produtos.addObserver(this);

        carregarTabela();
        
        view.getTbProdutos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTbProdutos().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                setEstado(new EstadoSelecionadoProduto(ListaProdutoPresenter.this));
            }
        });
        view.getBtnExcluir().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                exclui();
            }
        });
        view.getBtnEditar().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editar();
            }
        });
        view.getBtnSair().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.dispose();
            }
        });
        view.getJtPesquisar().addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                carregarTabela();
            }
        });
        view.getJcCategoria().addPopupMenuListener(new PopupMenuListener()
        {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                carregarTabela();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
            }
        });
        view.getJcGenero().addPopupMenuListener(new PopupMenuListener()
        {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                carregarTabela();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
            }
        });
        view.getJcStatus().addPopupMenuListener(new PopupMenuListener()
        {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                carregarTabela();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
            }
        });

        setEstado(new EstadoNaoSelecionadoProduto(this));

        view.setVisible(true);
    }

    public void carregaComboGenero()
    {
        generos = Generos.getInstancia();
        view.getJcGenero().removeAllItems();
        view.getJcGenero().addItem((String) "<IGenero>");

        for (int i = 0; i < generos.getGeneros().size(); i++)
        {
            IGenero g = generos.getGeneros().get(i);
            view.getJcGenero().addItem((String) g.getNomeGenero());
        }
    }

    private void carregarTabela()
    {
        DefaultTableModel tm;
        Object colunas[] =
        {
            "Cod.", "Descrição", "Lançamento", "Status", "IGenero", "Categoria"
        };
        tm = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);

        CadeiaListagemProdutos listaCompleta = new ListagemCompleta(this);
        CadeiaListagemProdutos listaCategoria = new ListagemPorCategoria(this);
        CadeiaListagemProdutos listaGenero = new ListagemPorGenero(this);
        CadeiaListagemProdutos listaNome = new ListagemPorNome(this);
        CadeiaListagemProdutos listaStatus = new ListagemPorStatus(this);

        listaCompleta.setProximo(listaCategoria);
        listaCategoria.setProximo(listaGenero);
        listaGenero.setProximo(listaStatus);
        listaStatus.setProximo(listaNome);

        Iterator<IProduto> it = listaCompleta.processo(produtos.getProdutos()).iterator();

        while (it.hasNext())
        {
            IProduto produto = it.next();
            String linha = produto.toString();
            String campos[] = linha.split("%");
            tm.addRow(new Object[]
                    {
                        campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]
                    });
        }

        view.getTbProdutos().setModel(tm);

    }

    public DefaultTableModel getTm()
    {
        return tm;
    }

    public void setTm(DefaultTableModel tm)
    {
        this.tm = tm;
    }

    public void exclui()
    {
        estado.exclui();
    }

    public void editar()
    {
        estado.editar();
    }

    public ListaProdutoView getView()
    {
        return view;
    }

    public EstadoListaProduto getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoListaProduto estado)
    {
        this.estado = estado;
    }

    public void setView(ListaProdutoView view)
    {
        this.view = view;
    }

    public IProduto getProduto()
    {
        return produto;
    }

    public void setProduto(IProduto produto)
    {
        this.produto = produto;
    }

    public IProdutos getProdutos()
    {
        return produtos;
    }

    public void setProdutos(IProdutos produtos)
    {
        this.produtos = produtos;
    }

    @Override
    public void update(ArrayList<IProduto> generos)
    {
        carregarTabela();
        estado.deseleciona();
    }
}
