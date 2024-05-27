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
public interface IPagamento extends Serializable
{

    String getDataFaturamento();

    double getDesconto();

    ILocacao getLocacao();

    double getValorTotal();

    void setDataFaturamento(String dataFaturamento);

    void setDesconto(double desconto);

    void setLocacao(ILocacao locacao);

    void setValorTotal(double valorTotal);

    String toString();
    
}
