/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iPersistence;

import java.io.IOException;
import java.util.ArrayList;
import model.iNegocio.IGenero;
import observer.genero.ObserverGenero;
/**
 *
 * @author Danilo
 */
public interface IGeneros
{

    boolean adiciona(IGenero p);

    boolean altera(IGenero novoGenero, String nomeAnterior) throws IOException;

    IGenero buscarGeneroPorCodigo(int pCod);

    IGenero buscarGeneroPorNome(String pNome);

    void excluirGenero(IGenero p);

    ArrayList<IGenero> getGeneros();

    int getUltimoCodigo();

    void grava();

    void leitura();

    void notifyObservers();
    
    void addObserver(ObserverGenero observer);

    boolean remove(String nome) throws IOException;
    
}
