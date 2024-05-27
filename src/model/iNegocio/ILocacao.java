/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.iNegocio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Danilo
 */
public interface ILocacao extends Serializable
{

    ICliente getCliente();

    int getCodLocacao();

    String getDataLocacao();

    char getEmAberto();

    ArrayList<IItemLocacao> getItens();

    double getValorTotal();

    void setCliente(ICliente cliente);

    void setCodLocacao(int codLocacao);

    void setDataLocacao(String dataLocacao);

    void setEmAberto(char emAberto);

    void setItens(ArrayList<IItemLocacao> itens);

    void setValorTotal(double valorTotal);

    String toString();
    
}
