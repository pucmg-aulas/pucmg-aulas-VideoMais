/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaLocacoes;

import presenter.ListaLocacaoPresenter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class ListaDataInicioLocacao implements IChainLocacao
{

    private IChainLocacao proximo;
    private ListaLocacaoPresenter controller;

    public ListaDataInicioLocacao(ListaLocacaoPresenter controller)
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

        try
        {
            ArrayList<ILocacao> listafiltrada;
            Date dataLoc = null;
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");

            if (controller.getView().getJtInicio().getText().equals("  /  /    "))
            {
                listafiltrada = new ArrayList<ILocacao>(listaAnterior);

            } else
            {
                listafiltrada = new ArrayList<ILocacao>();
                Iterator<ILocacao> it = listaAnterior.iterator();

                Date dataIni = (Date) form.parse(controller.getView().getJtInicio().getText());

                while (it.hasNext())
                {
                    ILocacao locacao = it.next();
                    dataLoc = (Date) form.parse(locacao.getDataLocacao());

                    if (dataLoc.after(dataIni) || dataLoc.equals(dataIni))
                    {
                        listafiltrada.add(locacao);
                    }
                }
            }
            return proximo.processo(listafiltrada);
            
        } catch (ParseException ex)
        {
            Logger.getLogger(ListaDataInicioLocacao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
