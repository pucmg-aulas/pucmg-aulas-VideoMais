/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.ListaClienteView;
import model.iNegocio.ICliente;
import chain.listaClientes.IChainClientesList;
import chain.listaClientes.ListaCompletaClientes;
import chain.listaClientes.ListaMasculinaClientes;
import chain.listaClientes.ListaNomeClientes;
import chain.listaClientes.ListagemFemininaClientes;
import dao.iPersistence.IClientes;
import dao.persistence.Clientes;
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
import javax.swing.table.DefaultTableModel;
import state.listagem.cliente.EstadoListagemCliente;
import state.listagem.cliente.EstadoNaoSelecionado;

/**
 *
 * @author Danilo
 */
public class ListaClientePresenter implements observer.cliente.ObserverCliente
{

    private ListaClienteView view;
    private EstadoListagemCliente estado;
    private ICliente cliente;
    private IClientes clientes;
    private DefaultTableModel tm;

    public ListaClientePresenter() throws FileNotFoundException, IOException
    {
        this.clientes = Clientes.getInstancia();
        clientes.addObserver(this);

        view = new ListaClienteView();
        view.setVisible(true);
        setEstado(new EstadoNaoSelecionado(this));

        view.getJtPesquisar().addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                carregarTabela();
            }
        });

        view.getJcSexo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                carregarTabela();
            }
        });

        view.getBtnSair().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fechar();
            }
        });

        view.getTabela().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                seleciona();
            }

        });
        
        view.getBtnExcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                exclui();
            }
        });
        
        view.getBtnVisualizar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                visualiza();
            }
        });

    }

    private void fechar()
    {
        estado.fechar();
    }

    public void exclui()
    {
        estado.exclui();
    }

    public void visualiza()
    {
        estado.visualiza();
    }

    public void deseleciona()
    {
        estado.deseleciona();
    }

    public void seleciona()
    {
        estado.seleciona();
    }

    public void setEstado(EstadoListagemCliente estado)
    {
        this.estado = estado;
    }

    public EstadoListagemCliente getEstado()
    {
        return estado;
    }

    public ListaClienteView getView()
    {
        return view;
    }

    public IClientes getClientes()
    {
        return clientes;
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public void setCliente(ICliente cliente)
    {
        this.cliente = cliente;
    }

    public void carregarTabela()
    {
        Object colunas[] = {"Nome", "CPF", "Nascimento", "Renda", "Telefone", "E-mail"};
        tm = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);

        IChainClientesList todos = new ListaCompletaClientes(this);
        IChainClientesList nomes = new ListaNomeClientes(this);
        IChainClientesList homem = new ListaMasculinaClientes(this);
        IChainClientesList mulher = new ListagemFemininaClientes(this);

        todos.setProximo(nomes);
        nomes.setProximo(homem);
        homem.setProximo(mulher);

        Iterator<ICliente> it = todos.processo(clientes.getClientes()).iterator();

        while (it.hasNext())
        {
            ICliente produto = it.next();
            String linha = produto.toString();
            String campos[] = linha.split("%");
            tm.addRow(new Object[]
                    {
                        campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]
                    });
        }

        view.getJtClientes().setModel(tm);

    }

    @Override
    public void update(ArrayList<ICliente> pessoas)
    {
        carregarTabela();
    }
}