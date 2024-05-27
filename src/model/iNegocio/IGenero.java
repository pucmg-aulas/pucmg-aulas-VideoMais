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
public interface IGenero extends Serializable
{

    int getCodGenero();

    String getNomeGenero();

    void setCodGenero(int codGenero);

    void setNomeGenero(String nomeGenero);

    String toString();
    
}
