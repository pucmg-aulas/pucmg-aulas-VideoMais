/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaProdutos;

import presenter.ListaProdutoPresenter;
import java.util.ArrayList;
import java.util.Iterator;
import model.iNegocio.IFilme;
import model.iNegocio.ILivro;
import model.iNegocio.IProduto;

/**
 *
 * @author Global
 */
public class ListagemPorCategoria implements CadeiaListagemProdutos
{

    private CadeiaListagemProdutos proximo;
    private ListaProdutoPresenter controller;

    public ListagemPorCategoria(ListaProdutoPresenter controller)
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
        ArrayList<IProduto> listafiltrada = null;

        if (controller.getView().getJcCategoria().getSelectedIndex() == 0)
        {
            listafiltrada = new ArrayList<IProduto>(listaAnterior);
            
        } else
        {
            listafiltrada = new ArrayList<IProduto>();
            Iterator<IProduto> it = listaAnterior.iterator();
            
            while (it.hasNext())
            {
                IProduto produto = it.next();
                
                if (produto instanceof IFilme && controller.getView().getJcCategoria().getSelectedIndex() == 1)
                {
                    listafiltrada.add(produto);
                }
                else if(produto instanceof ILivro && controller.getView().getJcCategoria().getSelectedIndex() == 2)
                {
                    listafiltrada.add(produto);
                }
            }
        }
        
        return proximo.processo(listafiltrada);
    }
}
