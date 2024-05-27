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
public interface IProduto extends Serializable
{

    int getCodProduto();

    String getDataLancamento();

    IGenero getGenero();

    int getIndicadorLocacao();

    String getNomeProduto();

    String getStatus();

    void setCodProduto(int codProduto);

    void setDataLancamento(String dataLancamento);

    void setGenero(IGenero genero);

    void setIndicadorLocacao(int indicadorLocacao);

    void setNomeProduto(String nomeProduto);

    void setStatus(String status);

    String toString();

    String toString2();
    
}
