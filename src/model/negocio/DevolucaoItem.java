/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IDevolucaoItem;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class DevolucaoItem implements IDevolucaoItem {
    
    private ILocacao locacao; //pq o item da locacao vai estar dentro do Array de cada locacao
    private String multa;
    private String dataDevolucao;

    public DevolucaoItem() {
    }

    public DevolucaoItem(ILocacao locacao, String multa, String dataDevolucao, String statusDevolucao) {
        this.locacao = locacao;
        this.multa = multa;
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public ILocacao getLocacao() {
        return locacao;
    }

    @Override
    public void setLocacao(ILocacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public String getMulta() {
        return multa;
    }

    @Override
    public void setMulta(String multa) {
        this.multa = multa;
    }

    @Override
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    
}

