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
public class ListagemPorGenero implements CadeiaListagemProdutos
{

    private CadeiaListagemProdutos proximo;
    private ListaProdutoPresenter controller;

    public ListagemPorGenero(ListaProdutoPresenter controller)
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

        if (controller.getView().getJcGenero().getSelectedIndex() == 0)
        {
            listafiltrada = new ArrayList<IProduto>(listaAnterior);

        } else
        {
            listafiltrada = new ArrayList<IProduto>();
            Iterator<IProduto> it = listaAnterior.iterator();

            while (it.hasNext())
            {
                IProduto produto = it.next();
                if (produto.getGenero().getNomeGenero().equals(controller.getView().getJcGenero().getSelectedItem().toString()))
                {
                    listafiltrada.add(produto);

                }
            }
        }

        return proximo.processo(listafiltrada);
    }
}
