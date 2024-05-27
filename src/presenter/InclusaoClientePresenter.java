/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.iPersistence.IClientes;
import dao.persistence.Clientes;
import view.InclusaoClienteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.iNegocio.ICliente;
import state.cadastro.cliente.EstadoClienteView;
import state.cadastro.cliente.EstadoInclusaoCliente;
import state.cadastro.cliente.EstadoVisualizacaoCliente;

/**
 *
 * @author Danilo
 */
public class InclusaoClientePresenter
{

    //private static InclusaoClientePresenter instancia;
    private InclusaoClienteView view;
    private IClientes clientes;
    private ICliente cliente;
    private String nomeAnterior;
    private EstadoClienteView estado;

    public InclusaoClientePresenter(ICliente cliente)
    {
        this.clientes = Clientes.getInstancia();
        this.cliente = cliente;
        view = new InclusaoClienteView();
        view.setVisible(true);

        if (cliente == null)
        {
            setEstado(new EstadoInclusaoCliente(this));

        } else
        {
            setEstado(new EstadoVisualizacaoCliente(this));
        }

        view.getBtnSalvar().addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                salvar();
            }
        });

        view.getBtnSair().setText("Fechar");
        view.getBtnSair().addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fechar();
            }
        });

        view.getBtnNovo().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                novo();
            }
        });




    }

    public void novo()
    {
        estado.novo();
    }

    public void salvar()
    {
        estado.salvar();
    }


    public final void fechar()
    {
        estado.fechar();
    }

    public void limpaCampos()
    {

        view.getJcEstado().setSelectedIndex(0);
        view.getJcSexo().setSelectedIndex(0);
        view.getJtBairro().setText("");
        view.getJtCidade().setText("");
        view.getJtDataNasc().setText("");
        view.getJtEmail().setText("");
        view.getJtNome().setText("");
        view.getJtNumero().setText("");
        view.getJtRenda().setText("");
        view.getJtRua().setText("");
        view.getJtTelefone().setText("");
        view.getJtcpf().setText("");
    }

    public InclusaoClienteView getView()
    {
        return view;
    }

    public ICliente getCliente()
    {
        return cliente;
    }

    public IClientes getClientes()
    {
        return clientes;
    }

    public void setCliente(ICliente cliente)
    {
        this.cliente = cliente;
    }

    public void setEstado(EstadoClienteView estado)
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
