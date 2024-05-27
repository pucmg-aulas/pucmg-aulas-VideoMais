/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaPagamentos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import model.iNegocio.IPagamento;
import presenter.ListaPagamentoPresenter;

/**
 *
 * @author Danilo
 */
public class ListagemMesPagamento implements IChainPagamento
{
    private IChainPagamento proximo;
    private ListaPagamentoPresenter controller;

    public ListagemMesPagamento(ListaPagamentoPresenter controller)
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
        try
        {
            ArrayList<IPagamento> listaFiltrada;
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPagamento = null;

            if (controller.getView().getJcMes().getSelectedIndex() == 0)
            {
                listaFiltrada = new ArrayList<IPagamento>(listaAnterior);
            } else
            {
                listaFiltrada = new ArrayList<IPagamento>();
                Iterator<IPagamento> it = listaAnterior.iterator();

                while (it.hasNext())
                {
                    IPagamento p = it.next();
                    dataPagamento = f.parse(p.getDataFaturamento());
                    
                    if (dataPagamento.getMonth() == (controller.getView().getJcMes().getSelectedIndex() - 1))
                    {
                        listaFiltrada.add(p);
                    }
                }

            }
            
            return proximo.processo(listaFiltrada);
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(controller.getView(), e.getMessage());
            return null;
        }
    }
    
}
