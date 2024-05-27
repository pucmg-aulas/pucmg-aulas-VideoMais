/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.listagem.cliente;

import presenter.ListaClientePresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;



/**
 *
 * @author Global
 */
public abstract class EstadoListagemCliente {
    
protected ListaClientePresenter objeto;
    
    public EstadoListagemCliente(final ListaClientePresenter objeto) {
      
        this.objeto = objeto;
        limpaListeners();
        
    }
    
    public abstract void exclui();
    
    public abstract void visualiza();

    public abstract void seleciona();

    public abstract void deseleciona();
    
    public abstract void fechar();
    
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


}
