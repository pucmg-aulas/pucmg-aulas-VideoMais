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
public class ListagemPorNome implements CadeiaListagemProdutos
{

    private CadeiaListagemProdutos proximo;
    private ListaProdutoPresenter controller;

    public ListagemPorNome(ListaProdutoPresenter controller)
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

        if (controller.getView().getJtPesquisar().getText().isEmpty())
        {
            listafiltrada = new ArrayList<IProduto>(listaAnterior);

        } else
        {
            listafiltrada = new ArrayList<IProduto>();
            Iterator<IProduto> it = listaAnterior.iterator();

            while (it.hasNext())
            {
                IProduto produto = it.next();
                if (produto.getNomeProduto().contains(controller.getView().getJtPesquisar().getText()))
                {
                    listafiltrada.add(produto);

                }
            }
        }

        return listafiltrada;
    }
}
