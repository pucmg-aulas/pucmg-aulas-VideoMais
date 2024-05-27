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
public interface ICliente extends Serializable
{

    String getCpf();

    String getDataNascimento();

    IEndereco getEndereco();

    String getNome();

    double getRenda();

    char getSexo();

    String getTelefone();

    String geteMail();

    void setCpf(String cpf);

    void setDataNascimento(String dataNascimento);

    void setEndereco(IEndereco endereco);

    void setNome(String nome);

    void setRenda(double renda);

    void setSexo(char sexo);

    void setTelefone(String telefone);

    void seteMail(String eMail);

    String toString();
    
}
