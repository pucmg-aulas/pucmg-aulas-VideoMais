/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IGenero;
import model.iNegocio.ILivro;

/**
 *
 * @author Danilo
 */
public class Livro extends Produto implements ILivro {

    public Livro(int codProduto, String nomeProduto, String dataLancamento, String status, IGenero genero) {
        super(codProduto, nomeProduto, dataLancamento, status, genero);
    }

    @Override
    public String toString() {
        return super.toString() + "%" + "ILivro";
        
    }
    
    @Override
    public String toString2()
    {
        return super.toString2() + "%" + "ILivro";
    }
    
    
    
    
}
