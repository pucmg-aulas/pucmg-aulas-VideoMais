/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.iPersistence.IGeneros;
import dao.iPersistence.IProdutos;
import dao.persistence.Generos;
import view.InclusaoProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import model.iNegocio.IGenero;
import model.iNegocio.IProduto;
import dao.persistence.Produtos;
import state.cadastro.produto.EstadoInclusaoProduto;
import state.cadastro.produto.EstadoProdutoView;
import state.cadastro.produto.EstadoVisualizacaoProduto;

/**
 *
 * @author Danilo
 */
public class InclusaoProdutoPresenter
{

    private InclusaoProdutoView view;
    private IProdutos produtos;
    private IProduto produto;
    private String nomeAnterior;
    private EstadoProdutoView estado;
    private IGeneros generos;

    public InclusaoProdutoPresenter(IProduto produto)
    {
        this.produtos = Produtos.getInstancia();
        setProduto(produto);
        view = new InclusaoProdutoView();

        if (produto == null)
        {
            setEstado(new EstadoInclusaoProduto(this));

        } else
        {
            setEstado(new EstadoVisualizacaoProduto(this));
        }
        
        view.getBtnAddGenero().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                addGenero();
            }
        });
        view.getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                salvar();
            }
        });
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                cancelar();
            }
        });
        //fazer o observer cuidar desse evento aki embaixo...
        view.getJcGenero().addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                 carregaComboGenero();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
            }
        });
        view.setVisible(true);
    }


    public void addGenero()
    {
        estado.addGenero();
    }
    
    public void salvar()
    {
        estado.salvar();
    }
    
    public void cancelar()
    {
        estado.cancelar();
    }

    
    public void limpaCampos()
    {
        view.getJcCategoria().setSelectedIndex(0);
        view.getJcGenero().setSelectedIndex(0);
        view.getJcStatusFilme().setSelectedIndex(0);
 
        view.getJtAnoLanc().setText("");
        view.getJtDescProd().setText("");

        view.getJlCodProd().setText(String.valueOf(produtos.getUltimoCodigo()));
        carregaComboGenero();
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
    
    public InclusaoProdutoView getView()
    {
        return view;
    }
    
    public IProduto getProduto()
    {
        return produto;
    }

    public IProdutos getProdutos()
    {
        return produtos;
    }

    public void setProduto(IProduto produto)
    {
        this.produto = produto;
    }

    public void setProdutos(IProdutos produtos)
    {
        this.produtos = produtos;
    }

    public void setEstado(EstadoProdutoView estado)
    {
        this.estado = estado;
    }

    public void setNomeAnterior(String nomeAnterior)
    {
        this.nomeAnterior = nomeAnterior;
    }

    public String getNomeAnterior()
    {
        return nomeAnterior;
    }

    
}
