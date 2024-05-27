/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IGenero;

/**
 *
 * @author Danilo
 */
public class Genero implements IGenero {
    
    private int codGenero;
    private String nomeGenero;

    public Genero(int codGenero, String nomeGenero) {
        this.codGenero = codGenero;
        this.nomeGenero = nomeGenero;
    }

    @Override
    public int getCodGenero() {
        return codGenero;
    }

    @Override
    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
    }

    @Override
    public String getNomeGenero() {
        return nomeGenero;
    }

    @Override
    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    @Override
    public String toString() {
        return codGenero + "%" + nomeGenero;
    }

    
    
    
    
}
