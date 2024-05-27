/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaPagamentos;

import java.util.ArrayList;
import model.iNegocio.IPagamento;
import presenter.ListaPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class ListaPagamentoCompleta implements IChainPagamento
{
    private IChainPagamento proximo;
    private ListaPagamentoPresenter controller;

    public ListaPagamentoCompleta(ListaPagamentoPresenter controller)
    {
        this.controller = controller;
    }

    
    @Override
    public void setProximo(IChainPagamento proximo)
    {
        this.proximo = proximo;
    }

    @Override
    public ArrayList<IPagamento> processo(ArrayList<IPagamento> listaAnterior)
    {
        ArrayList<IPagamento> listaFiltrada;
        if(controller.getView().getJcbListarTodos().isSelected())
        {
            return listaAnterior;
        }
        else if(controller.getView().getJcAno().getSelectedIndex() == 0 && controller.getView().getJcDia().getSelectedIndex() == 0 && controller.getView().getJcMes().getSelectedIndex() == 0)
        {
            return listaAnterior;
        }
        else
        {
            listaFiltrada = new ArrayList<IPagamento>(listaAnterior);
        }
        return proximo.processo(listaFiltrada);
    }
    
}
