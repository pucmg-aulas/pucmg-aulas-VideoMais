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
public interface IEndereco extends Serializable
{

    String getBairro();

    String getCidade();

    String getNumero();

    String getRua();

    String getUf();

    void setBairro(String bairro);

    void setCidade(String cidade);

    void setNumero(String numero);

    void setRua(String rua);

    void setUf(String uf);
    
}
