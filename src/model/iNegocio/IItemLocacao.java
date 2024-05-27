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
public interface IItemLocacao extends Serializable
{

    String getDataPrevistaDevolucao();

    IProduto getItem();

    double getValorUnitario();

    void setDataPrevistaDevolucao(String dataPrevistaDevolucao);

    void setItem(IProduto item);

    void setValorUnitario(double valorUnitario);

    String toString();
    
}
