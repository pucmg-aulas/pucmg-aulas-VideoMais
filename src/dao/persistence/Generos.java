/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.persistence;

import dao.abstractClass.AbstractDAO;
import dao.iPersistence.IGeneros;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.iNegocio.IGenero;
import observer.genero.ObserverGenero;

/**
 *
 * @author Danilo
 */
public class Generos extends AbstractDAO implements IGeneros{
    
        private List generos;
        private static IGeneros instancia;
        protected List observadores;

    private Generos() {
        
        generos = new ArrayList<IGenero>();
        observadores = new ArrayList<ObserverGenero>();
        leitura();
    }
    
    public static IGeneros getInstancia()
    {
        if(instancia == null)
        {
           instancia = new Generos();
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
        super.grava("src/serializacao/Generos.dat", generos);
        notifyObservers();
    }

    @Override
    public void leitura() 
    {
        generos = super.leitura("src/serializacao/Generos.dat");
    }

    
    @Override
    public IGenero buscarGeneroPorNome(String pNome) {
        
        IGenero retorno = null;
        for (int i = 0; i < generos.size(); i++) 
        {
            IGenero l = (IGenero) generos.get(i);
            if (l.getNomeGenero().toLowerCase().equals(pNome.toLowerCase())) 
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }
    
    @Override
   public IGenero buscarGeneroPorCodigo(int pCod) {
        
        IGenero retorno = null;
        for (int i = 0; i < generos.size(); i++) 
        {
            IGenero l = (IGenero) generos.get(i);
            if (l.getCodGenero() == pCod) 
            {
                retorno = l;
                break;
            }
        }
        return retorno;
    }

    @Override
    public  ArrayList<IGenero> getGeneros() 
    {
        return (ArrayList<IGenero>) generos;
    }

    @Override
    public boolean adiciona(IGenero p) 
    {
        if(generos.add(p))
        {
            grava();
            return true;
        }
        return false;
    }
    
    @Override
    public void excluirGenero(IGenero p)
    {
        generos.remove(p);
        grava();
    }
    
    @Override
    public boolean remove(String nome) throws IOException {
        boolean remove=false;
        for (Iterator<IGenero> it = generos.iterator(); it.hasNext();) {
            IGenero genero = it.next();
            if (genero.getNomeGenero().equals(nome)) {
               generos.remove(genero);
               grava();
               remove=true;
               break;
            }
        }
        
        return remove;
    }
    
    @Override
    public int getUltimoCodigo()
    {
        int uCod = 0;
        
        if(generos.size() > 0)
        {
         
            IGenero g  = (IGenero) generos.get(generos.size() - 1);
            uCod = g.getCodGenero() + 1;
            
        }
        else
        {
            uCod = 1;
        }
        
        return uCod;
        
    }
    
    @Override
    public boolean altera(IGenero novoGenero, String nomeAnterior) throws IOException
    {
        try
        {

            ArrayList<IGenero> listaTemp = new ArrayList<IGenero>();

            for (Iterator<IGenero> it = generos.iterator(); it.hasNext();)
            {
                IGenero genero = it.next();
                if (!genero.getNomeGenero().equals(nomeAnterior))
                {
                    listaTemp.add(genero);

                } else
                {
                    listaTemp.add(novoGenero);
                }
            }

            generos.removeAll(generos);
            generos.addAll(listaTemp);
            grava();

            return true;
            
        } catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public void addObserver(ObserverGenero observer) {
         observadores.add(observer);
        
    }

    @Override
    public void notifyObservers() {
        
        for (Iterator<ObserverGenero> it = observadores.iterator(); it.hasNext();) 
        {
            ObserverGenero observerGenero = it.next();
            observerGenero.update(this.getGeneros());
        }
    }
    
}
