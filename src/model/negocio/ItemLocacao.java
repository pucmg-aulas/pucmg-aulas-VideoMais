/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.IItemLocacao;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class ItemLocacao implements IItemLocacao{
    
    private IProduto item;
    private double valorUnitario;
    private String dataPrevistaDevolucao;

    public ItemLocacao(IProduto item, double valorUnitario, String dataPrevistaDevolucao) {
        this.item = item;
        this.valorUnitario = valorUnitario;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    @Override
    public IProduto getItem() {
        return item;
    }

    @Override
    public void setItem(IProduto item) {
        this.item = item;
    }

    @Override
    public double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    @Override
    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    @Override
    public String toString() {
        
        String devolvido = "-";
        
        if(item.getIndicadorLocacao() == 0)
        {
            devolvido = "Não";
        }
        else if(item.getIndicadorLocacao() == -1)
        {
            devolvido = "Sim";
        }
        return item.getCodProduto() + "%" + item.getNomeProduto() + "%" +valorUnitario + "%" + dataPrevistaDevolucao + "%" + devolvido;
    }
   
    
    
    
}
