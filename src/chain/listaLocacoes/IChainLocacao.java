/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.listaLocacoes;

import java.util.ArrayList;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public interface IChainLocacao
{
    public  void setProximo(IChainLocacao proximo);
    
    public  ArrayList<ILocacao> processo(ArrayList<ILocacao> listaAnterior);
}
