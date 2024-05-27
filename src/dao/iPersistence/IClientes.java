/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.io.IOException;
import java.util.ArrayList;
import model.iNegocio.ICliente;
import observer.cliente.ObserverCliente;

/**
 *
 * @author Danilo
 */
public interface IClientes 
{

    void addObserver(ObserverCliente observer);

    boolean adiciona(ICliente p);

    boolean altera(ICliente pessoaExistente, String nomeAnterior) throws IOException;

    ICliente buscarClientePorCPF(String pCPF);

    ICliente buscarClientePorNome(String pNome);

    ICliente buscarClientePorParteDeNome(String pNome);

    void excluirCliente(ICliente p);

    ArrayList<ICliente> getClientes();

    void grava();

    void leitura();

    void notifyObservers();

    boolean remove(String nome) throws IOException;
    
}
