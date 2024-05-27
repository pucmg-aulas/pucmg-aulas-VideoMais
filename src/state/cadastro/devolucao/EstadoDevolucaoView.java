/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.devolucao;

import presenter.InclusaoDevolucaoPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Danilo
 */
public abstract class EstadoDevolucaoView
{
    InclusaoDevolucaoPresenter objeto;

    public EstadoDevolucaoView(InclusaoDevolucaoPresenter objeto)
    {
        this.objeto = objeto;
        limpaListeners();
    }
    
    public abstract void pesquisar();

    public abstract void pagar();

    public abstract void devolver();
    
    public void sair()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }
    
   
    
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
