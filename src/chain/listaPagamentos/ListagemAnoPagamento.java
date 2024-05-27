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
public class ListagemAnoPagamento implements IChainPagamento
{

    private IChainPagamento proximo;
    private ListaPagamentoPresenter controller;

    public ListagemAnoPagamento(ListaPagamentoPresenter controller)
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

            if (controller.getView().getJcAno().getSelectedIndex() == 0)
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
                    int ano = Integer.parseInt(controller.getView().getJcAno().getSelectedItem().toString()) - 1900;

                    if (dataPagamento.getYear() == ano)
                    {
                        listaFiltrada.add(p);
                    }
                }

            }
            //ultimoDaCadeia
            return listaFiltrada;
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(controller.getView(), e.getMessage());
            return null;
        }
    }
}
