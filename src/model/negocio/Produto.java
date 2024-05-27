/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IGenero;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class Produto implements IProduto{
    
    private int codProduto;
    private String nomeProduto;
    private String dataLancamento;
    private String status;
    private IGenero genero;
    private int indicadorLocacao;

    public Produto(int codProduto, String nomeProduto, String dataLancamento, String status, IGenero genero) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.dataLancamento = dataLancamento;
        this.status = status;
        this.genero = genero;
        this.indicadorLocacao = -1;
    }
    
    @Override
    public int getCodProduto() {
        return codProduto;
    }

    @Override
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    @Override
    public String getNomeProduto() {
        return nomeProduto;
    }

    @Override
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    @Override
    public String getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public IGenero getGenero() {
        return genero;
    }

    @Override
    public void setGenero(IGenero genero) {
        this.genero = genero;
    }

    @Override
    public int getIndicadorLocacao() {
        return indicadorLocacao;
    }

    @Override
    public void setIndicadorLocacao(int indicadorLocacao) {
        this.indicadorLocacao = indicadorLocacao;
    }
    
    
    

    @Override
    public String toString() {
        return codProduto + "%" + nomeProduto + "%" + dataLancamento + "%" + status + "%" + genero.getNomeGenero();
    }
    
    
    @Override
    public String toString2() {
        
        String indice = "";
        if(this.indicadorLocacao == -1)
        {
            indice = "Sim";
        }
        else
        {
            indice = "Não";
        }
        return codProduto + "%" + nomeProduto + "%" + dataLancamento + "%" + status + "%" + genero.getNomeGenero() + "%" + indice;
    }
    
    
    
    
    
    
    
}
