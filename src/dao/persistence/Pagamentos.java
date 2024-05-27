/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.IPagamentos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.iNegocio.IPagamento;
import observer.pagamento.ObserverPagamento;
import observer.produto.ObserverProduto;

/**
 *
 * @author Danilo
 */
public class Pagamentos extends AbstractDAO implements IPagamentos {
    
    private List pagamentos;
    private static IPagamentos instancia;
    private List observadores;

    private Pagamentos() {
        
        observadores = new ArrayList<ObserverProduto>();
        pagamentos = new ArrayList<IPagamento>();
        leitura();
    }
    
    public static IPagamentos getInstancia()
    {
        if(instancia == null)
        {
            instancia = new Pagamentos();
            return instancia;
        }
        else
        {
            return instancia;
        }
    }
    
    @Override
    public void grava() 
    {
        super.grava("src/serializacao/Pagamentos.dat", pagamentos);
        notifyObservers();
    }

    @Override
    public void leitura() 
    {
        pagamentos = super.leitura("src/serializacao/Pagamentos.dat");
    }

    
    @Override
    public IPagamento buscarPagamentosPorCliente(String pCPF) {
        
        IPagamento retorno = null;
        for (int i = 0; i < pagamentos.size(); i++) 
        {
            IPagamento l = (IPagamento) pagamentos.get(i);
            if (l.getLocacao().getCliente().getCpf().equals(pCPF)) 
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }
    

    @Override
    public ArrayList<IPagamento> getPagamentos() 
    {
        return (ArrayList<IPagamento>) pagamentos;
    }

    @Override
    public void adiciona(IPagamento p) 
    {
        pagamentos.add(p);
        grava();
    }
    
    @Override
    public void excluirPagamento(IPagamento p)
    {
        pagamentos.remove(p);
        grava();
    }

    @Override
    public void notifyObservers()
    {
        for (Iterator<ObserverPagamento> it = observadores.iterator(); it.hasNext();) {
            ObserverPagamento observerGenero = it.next();
            observerGenero.update(this.getPagamentos());
        }
    }

    @Override
    public void addObserver(ObserverPagamento observer)
    {
        observadores.add(observer);
    }
    
}
