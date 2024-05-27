/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaProdutos;

import presenter.ListaProdutoPresenter;
import java.util.ArrayList;
import java.util.Iterator;
import model.iNegocio.IProduto;

/**
 *
 * @author mateus
 */
public class ListagemPorStatus implements CadeiaListagemProdutos
{

    private CadeiaListagemProdutos proximo;
    private ListaProdutoPresenter controller;

    public ListagemPorStatus(ListaProdutoPresenter controller)
    {
        this.controller = controller;
    }

    @Override
    public void setProximo(CadeiaListagemProdutos proximo)
    {
        this.proximo = proximo;

    }

    @Override
    public ArrayList<IProduto> processo(ArrayList<IProduto> listaAnterior)
    {
        ArrayList<IProduto> listafiltrada;

        if (controller.getView().getJcStatus().getSelectedIndex() == 0)
        {
            listafiltrada = new ArrayList<IProduto>(listaAnterior);

        } else
        {
            listafiltrada = new ArrayList<IProduto>();
            Iterator<IProduto> it = listaAnterior.iterator();

            while (it.hasNext())
            {
                IProduto produto = it.next();
                if(produto.getStatus().equals(controller.getView().getJcStatus().getSelectedItem().toString()))
                {
                    listafiltrada.add(produto);

                }
            }
        }

        return proximo.processo(listafiltrada);
    }
}
