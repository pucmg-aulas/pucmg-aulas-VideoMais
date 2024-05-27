/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.pagamento;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import presenter.InclusaoPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public abstract class EstadoPagamentoView
{
    protected InclusaoPagamentoPresenter objeto;

    public EstadoPagamentoView(InclusaoPagamentoPresenter objeto)
    {
        this.objeto = objeto;
        limpaListeners();
        objeto.getView().getJlValorMulta().setText("0.00");
        objeto.getView().getJlValorTotal().setText("0.00");
        objeto.getView().getJtDesconto().setText("0.00");
        
        Date data = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        objeto.getView().getJlCurrentDate().setText(form.format(data));
    }
    
    
    public void sair()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }
    
    public abstract void pagar();
    
    public abstract void multas();
    
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
