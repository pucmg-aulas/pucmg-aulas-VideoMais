/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaProdutos;

import presenter.ListaProdutoPresenter;
import java.util.ArrayList;
import model.iNegocio.IProduto;

/**
 *
 * @author mateus
 */
public class ListagemCompleta implements CadeiaListagemProdutos {

    private CadeiaListagemProdutos proximo;
    private ListaProdutoPresenter controller;

    public ListagemCompleta(ListaProdutoPresenter controller)
    {
        this.controller = controller;
    }

    
    @Override
    public void setProximo(CadeiaListagemProdutos proximo) {
        this.proximo = proximo;
    }

    @Override
    public ArrayList<IProduto> processo(ArrayList<IProduto> listaAnterior) {
        
        ArrayList<IProduto> listafiltrada = new ArrayList<IProduto>(listaAnterior);

        if (controller.getView().getJtPesquisar().getText().equals("") && controller.getView().getJcGenero().getSelectedIndex() == 0 && controller.getView().getJcCategoria().getSelectedIndex() == 0 && controller.getView().getJcStatus().getSelectedIndex() == 0) {
            return listafiltrada;
        }
        return proximo.processo(listaAnterior);
    }
}
