/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.iNegocio;

import java.io.Serializable;

/**
 *
 * @author Danilo
 */
public interface IDevolucaoItem extends Serializable
{

    String getDataDevolucao();

    ILocacao getLocacao();

    String getMulta();

    void setDataDevolucao(String dataDevolucao);

    void setLocacao(ILocacao locacao);

    void setMulta(String multa);
    
    
    
}
