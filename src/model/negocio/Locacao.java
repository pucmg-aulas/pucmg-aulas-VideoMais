/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.negocio;

import model.iNegocio.ILocacao;
import java.util.ArrayList;
import model.iNegocio.ICliente;
import model.iNegocio.IItemLocacao;

/**
 *
 * @author Danilo
 */
public class Locacao implements ILocacao{
    
    private int codLocacao;
    private ICliente cliente;
    private double valorTotal;
    private String dataLocacao;
    private char emAberto;
    private ArrayList<IItemLocacao> itens;

    public Locacao(int codLocacao, ICliente cliente, double valorTotal, String dataLocacao, char emAberto, ArrayList<IItemLocacao> itens) {
        this.codLocacao = codLocacao;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.dataLocacao = dataLocacao;
        this.emAberto = emAberto;
        this.itens = itens;
    }


    @Override
    public int getCodLocacao() {
        return codLocacao;
    }

    @Override
    public void setCodLocacao(int codLocacao) {
        this.codLocacao = codLocacao;
    }

    @Override
    public ICliente getCliente() {
        return cliente;
    }

    @Override
    public void setCliente(ICliente cliente) {
        this.cliente = cliente;
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
    public String getDataLocacao() {
        return dataLocacao;
    }

    @Override
    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    @Override
    public char getEmAberto() {
        return emAberto;
    }

    @Override
    public void setEmAberto(char emAberto) {
        this.emAberto = emAberto;
    }

    @Override
    public ArrayList<IItemLocacao> getItens() {
        return itens;
    }

    @Override
    public void setItens(ArrayList<IItemLocacao> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return codLocacao + "%" + cliente.getNome() + "%" + valorTotal + "%" + dataLocacao + "%" + emAberto;
    }
    
    
    
    
    
    
}
