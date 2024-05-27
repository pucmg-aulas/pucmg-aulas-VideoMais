/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.cliente;

import model.iNegocio.ICliente;
import command.Command;
import presenter.InclusaoClientePresenter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.iNegocio.IEndereco;
import model.negocio.Cliente;
import model.negocio.Endereco;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandCliente implements Command
{

    protected InclusaoClientePresenter receptor;

    public AbstractCommandCliente(InclusaoClientePresenter receptor)
    {
        this.receptor = receptor;
    }

    @Override
    public abstract void execute();

    protected void valida() throws IOException, Exception
    {

        if (receptor.getView().getJtNome().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
        } else if (receptor.getView().getJcSexo().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Sexo Vazio!");
        } else if (receptor.getView().getJtcpf().getText().equals("   .   .   -  "))
        {
            JOptionPane.showMessageDialog(null, "Campo CPF Vazio!");
        } else if (receptor.getView().getJtDataNasc().getText().equals("  /  /    "))
        {
            JOptionPane.showMessageDialog(null, "Campo Data Nascimento Vazio!");
        } else if (receptor.getView().getJtRenda().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Renda Vazio!");
        } else if (receptor.getView().getJtTelefone().getText().equals("(  )     -    "))
        {
            JOptionPane.showMessageDialog(null, "Campo Telefone Vazio!");
        } else if (receptor.getView().getJtEmail().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo E-mail Vazio!");
        } else if (receptor.getView().getJtRua().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Rua Vazio!");
        } else if (receptor.getView().getJtNumero().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Numero Vazio!");
        } else if (receptor.getView().getJtBairro().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Bairro Vazio!");
        } else if (receptor.getView().getJtCidade().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Cidade Vazio!");
        } else if (receptor.getView().getJcEstado().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Estado Vazio!");
        }
        else
        {
            IEndereco endereco = new Endereco(receptor.getView().getJtRua().getText(), receptor.getView().getJtBairro().getText(), receptor.getView().getJtNumero().getText(), receptor.getView().getJtCidade().getText(), receptor.getView().getJcEstado().getSelectedItem().toString());

            ICliente cliente = new Cliente(receptor.getView().getJtNome().getText(), receptor.getView().getJcSexo().getSelectedItem().toString().charAt(0), receptor.getView().getJtcpf().getText(), receptor.getView().getJtDataNasc().getText(), Double.parseDouble(receptor.getView().getJtRenda().getText()), receptor.getView().getJtTelefone().getText(), receptor.getView().getJtEmail().getText(), endereco);

            if (!cliente.getNome().contains(","))
            {
                if (salva(cliente))
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "Pessoa já existente");
                } else
                {
                    receptor.setCliente(cliente);
                    JOptionPane.showMessageDialog(receptor.getView(), receptor.getView().getJtNome().getText() + " Cadastrado com sucesso!");
                    receptor.limpaCampos();
                    receptor.getView().getJtNome().requestFocus();
                }
            } else
            {
                throw new Exception("Nome não pode conter vírgula!");
            }
        }
    }

    protected abstract boolean salva(ICliente cliente) throws IOException;
}
