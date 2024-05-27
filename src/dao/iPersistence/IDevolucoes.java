/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.util.ArrayList;
import model.iNegocio.IDevolucaoItem;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public interface IDevolucoes
{

    void adiciona(IDevolucaoItem p);

    IDevolucaoItem buscarDevolucaoItemPorCliente(String pCPF);

    void excluirDevolucaoItem(IDevolucaoItem p);

    ArrayList<IDevolucaoItem> getDevolucoes();

    void grava();

    void leitura();
        
}
