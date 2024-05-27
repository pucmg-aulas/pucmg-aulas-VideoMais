/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaLocacoes;

import presenter.ListaLocacaoPresenter;
import java.util.ArrayList;
import java.util.Iterator;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class ListaPorNomeLocacao implements IChainLocacao
{

    private IChainLocacao proximo;
    private ListaLocacaoPresenter controller;

    public ListaPorNomeLocacao(ListaLocacaoPresenter controller)
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
        ArrayList<ILocacao> listafiltrada;

        if (controller.getView().getJtCliente().getText().isEmpty())
        {
            listafiltrada = new ArrayList<ILocacao>(listaAnterior);

        } else
        {
            listafiltrada = new ArrayList<ILocacao>();
            Iterator<ILocacao> it = listaAnterior.iterator();

            while (it.hasNext())
            {
                ILocacao locacao = it.next();
                if (locacao.getCliente().getNome().toLowerCase().contains(controller.getView().getJtCliente().getText().toLowerCase()))
                {
                    listafiltrada.add(locacao);

                }
            }
        }

        return proximo.processo(listafiltrada);

    }
}
