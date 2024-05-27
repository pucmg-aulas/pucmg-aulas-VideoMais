/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.genero;

import presenter.InclusaoGeneroPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Danilo
 */
public abstract class EstadoGeneroView
{
    protected InclusaoGeneroPresenter objeto;

    public EstadoGeneroView(InclusaoGeneroPresenter objeto)
    {
        this.objeto = objeto;
        limpaListeners();

        objeto.getView().getBtnCancelar().setText("Fechar");


    }

    public void salvar()
    {
    }


    public void cancelar()
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
