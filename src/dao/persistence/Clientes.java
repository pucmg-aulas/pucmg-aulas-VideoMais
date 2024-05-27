/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.IClientes;
import model.iNegocio.ICliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import observer.cliente.ObserverCliente;

/**
 *
 * @author Danilo
 */
public class Clientes  extends AbstractDAO implements IClientes {

    private List clientes;
    private static IClientes instancia;
    private ArrayList<ObserverCliente> observadores = new ArrayList<ObserverCliente>();

    private Clientes() {

        clientes = new ArrayList<ICliente>();
        leitura();
    }

    public static IClientes getInstancia() 
    {
        if (instancia == null) {
            instancia = new Clientes();
            return instancia;
        } else {
            return instancia;
        }
    }

    @Override
    public boolean remove(String nome) throws IOException {
        boolean remove = false;
        for (Iterator<ICliente> it = clientes.iterator(); it.hasNext();) {
            ICliente cliente = it.next();
            if (cliente.getNome().equals(nome)) {
                clientes.remove(cliente);
                grava();
                remove = true;
                break;
            }
        }

        return remove;
    }

    @Override
    public void grava() {
        
        super.grava("src/serializacao/Clientes.dat", clientes);
        notifyObservers();
        
    }

    @Override
    public void leitura() {
        
        clientes = super.leitura("src/serializacao/Clientes.dat");
    }

    @Override
    public ICliente buscarClientePorNome(String pNome) {

        ICliente retorno = null;
        for (int i = 0; i < clientes.size(); i++) {
            ICliente l = (ICliente) clientes.get(i);
            if (l.getNome().toLowerCase().contains(pNome.toLowerCase())) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ICliente buscarClientePorParteDeNome(String pNome) {

        ICliente retorno = null;
        for (int i = 0; i < clientes.size(); i++) {
            ICliente l = (ICliente) clientes.get(i);
            if (l.getNome().toLowerCase().contains(pNome.toLowerCase())) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ICliente buscarClientePorCPF(String pCPF) {

        ICliente retorno = null;
        for (int i = 0; i < clientes.size(); i++) {
            ICliente l = (ICliente) clientes.get(i);

            if (l.getCpf().equals(pCPF)) {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public ArrayList<ICliente> getClientes() {
        return (ArrayList<ICliente>) clientes;
    }

    @Override
    public boolean adiciona(ICliente p) {
        if (clientes.add(p)) {
            grava();
            return true;
        }
        return false;

    }

    @Override
    public void excluirCliente(ICliente p) {
        clientes.remove(p);
        grava();
    }

    @Override
    public boolean altera(ICliente pessoaExistente, String nomeAnterior) throws IOException {
        try {

            ArrayList<ICliente> listaTemp = new ArrayList<ICliente>();

            for (Iterator<ICliente> it = clientes.iterator(); it.hasNext();) {
                ICliente cliente = it.next();
                if (!cliente.getNome().equals(nomeAnterior)) {
                    listaTemp.add(cliente);

                } else {
                    listaTemp.add(pessoaExistente);
                }
            }

            clientes.removeAll(clientes);
            clientes.addAll(listaTemp);
            grava();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addObserver(ObserverCliente observer) {
        observadores.add(observer);
    }

    @Override
    public void notifyObservers() 
    {
        for (Iterator<ObserverCliente> it = observadores.iterator(); it.hasNext();) 
        {
            ObserverCliente observerCliente = it.next();
            observerCliente.update(this.getClientes());
        }
    }
}
