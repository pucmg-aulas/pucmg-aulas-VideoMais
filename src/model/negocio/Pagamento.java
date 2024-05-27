/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.ILocacao;
import model.iNegocio.IPagamento;

/**
 *
 * @author Danilo
 */
public class Pagamento implements IPagamento{
    
    private ILocacao locacao;
    private double valorTotal;
    private String dataFaturamento;
    private double desconto;

    public Pagamento(ILocacao locacao, double valorTotal, String dataFaturamento, double desconto) {
        this.locacao = locacao;
        this.valorTotal = valorTotal;
        this.dataFaturamento = dataFaturamento;
        this.desconto = desconto;
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
    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String getDataFaturamento() {
        return dataFaturamento;
    }

    @Override
    public void setDataFaturamento(String dataFaturamento) {
        this.dataFaturamento = dataFaturamento;
    }

    @Override
    public double getDesconto() {
        return desconto;
    }

    @Override
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return locacao.getCliente().getNome() + "%" + valorTotal + "%" + dataFaturamento + "%" + desconto;
    }
    
    
    
    
    
}
