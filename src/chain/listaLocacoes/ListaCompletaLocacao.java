/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaLocacoes;

import presenter.ListaLocacaoPresenter;
import java.util.ArrayList;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class ListaCompletaLocacao implements IChainLocacao
{

    private IChainLocacao proximo;
    private ListaLocacaoPresenter controller;

    public ListaCompletaLocacao(ListaLocacaoPresenter controller)
    {
        this.controller = controller;
    }

    @Override
    public void setProximo(IChainLocacao proximo)
    {
        this.proximo = proximo;
    }

    @Override
    public ArrayList<ILocacao> processo(ArrayList<ILocacao> listaAnterior)
    {
        ArrayList<ILocacao> listafiltrada = new ArrayList<ILocacao>(listaAnterior);

        if (controller.getView().getJtCliente().getText().isEmpty() && controller.getView().getJtInicio().getText().equals("  /  /    ") && controller.getView().getJtFim().getText().equals("  /  /    "))
        {
            return listafiltrada;
        }
        return proximo.processo(listaAnterior);

    }
}
