/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.cliente;

import presenter.InclusaoClientePresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Danilo
 */
public abstract class EstadoClienteView
{

    protected InclusaoClientePresenter objeto;

    public EstadoClienteView(InclusaoClientePresenter objeto)
    {
        this.objeto = objeto;
        limpaListeners();
    }

    public abstract void salvar();

    public abstract void fechar();
    
    public abstract void novo();

    protected void limpaListeners()
    {

        for (Component c : objeto.getView().getContentPane().getComponents())
        {
            if (c instanceof JButton)
            {
                for (ActionListener al : ((JButton) c).getActionListeners())
                {
                    ((JButton) c).removeActionListener(al);
                }
            }
        }
    }
    
        
    protected void liberarTelaPraEdição()
    {
        objeto.getView().getJcEstado().setEnabled(true);
        objeto.getView().getJcSexo().setEnabled(true);

        objeto.getView().getJtBairro().setEnabled(true);
        objeto.getView().getJtCidade().setEnabled(true);
        objeto.getView().getJtDataNasc().setEnabled(true);
        objeto.getView().getJtEmail().setEnabled(true);
        objeto.getView().getJtNome().setEnabled(true);
        objeto.getView().getJtNumero().setEnabled(true);
        objeto.getView().getJtRenda().setEnabled(true);
        objeto.getView().getJtRua().setEnabled(true);
        objeto.getView().getJtTelefone().setEnabled(true);
        objeto.getView().getJtcpf().setEnabled(true);

    }
    
    protected void bloquearTelaPraEdição()
    {
        objeto.getView().getJcEstado().setEnabled(false);
        objeto.getView().getJcSexo().setEnabled(false);
        
        objeto.getView().getJtBairro().setEnabled(false);
        objeto.getView().getJtCidade().setEnabled(false);
        objeto.getView().getJtDataNasc().setEnabled(false);
        objeto.getView().getJtEmail().setEnabled(false);
        objeto.getView().getJtNome().setEnabled(false);
        objeto.getView().getJtNumero().setEnabled(false);
        objeto.getView().getJtRenda().setEnabled(false);
        objeto.getView().getJtRua().setEnabled(false);
        objeto.getView().getJtTelefone().setEnabled(false);
        objeto.getView().getJtcpf().setEnabled(false);
        
    }
}
