/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IEndereco;

/**
 *
 * @author Danilo
 */
public class Endereco implements IEndereco {
    
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String uf;

    public Endereco(String rua, String bairro, String numero, String cidade, String uf) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Override
    public String getRua() {
        return rua;
    }

    @Override
    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String getBairro() {
        return bairro;
    }

    @Override
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String getCidade() {
        return cidade;
    }

    @Override
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String getUf() {
        return uf;
    }

    @Override
    public void setUf(String uf) {
        this.uf = uf;
    }

    
    
    
    
}
