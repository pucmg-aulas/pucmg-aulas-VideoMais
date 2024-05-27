/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.produto;

import presenter.InclusaoGeneroPresenter;
import presenter.InclusaoProdutoPresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Danilo
 */
public abstract class EstadoProdutoView
{
    protected InclusaoProdutoPresenter objeto;

    public EstadoProdutoView(final InclusaoProdutoPresenter objeto)
    {
        this.objeto = objeto;
        limpaListeners();

        objeto.getView().getBtnSair().setText("Fechar");
        objeto.carregaComboGenero();

    }

    public void salvar()
    {
    }

    public void cancelar()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }
    
    public void addGenero()
    {
        new InclusaoGeneroPresenter(null);
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
