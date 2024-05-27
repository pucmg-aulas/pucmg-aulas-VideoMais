/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IFilme;
import model.iNegocio.IGenero;

/**
 *
 * @author Danilo
 */
public class Filme extends Produto implements IFilme {

    public Filme(int codProduto, String nomeProduto, String dataLancamento, String status, IGenero genero) {
        super(codProduto, nomeProduto, dataLancamento, status, genero);
    }

    @Override
    public String toString() {
        return super.toString() + "%" + "IFilme";
    }
    
    @Override
    public String toString2()
    {
        return super.toString2() + "%" + "IFilme";
    }
    
    
    
    
}
