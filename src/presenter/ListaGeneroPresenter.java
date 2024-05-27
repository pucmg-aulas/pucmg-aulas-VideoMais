/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

*/

package presenter;

import dao.iPersistence.IGeneros;
import dao.persistence.Generos;
import view.ListaGeneroView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IGenero;
import observer.genero.ObserverGenero;
import state.listagem.genero.EstadoListaGenero;
import state.listagem.genero.EstadoNaoSelecionadoGenero;
import state.listagem.genero.EstadoSelecionadoGenero;

/**
 *
 * @author mateus
 */
public class ListaGeneroPresenter implements ObserverGenero
{
    private ListaGeneroView view;
    private IGenero genero;
    private DefaultTableModel tm;
    private IGeneros generos; 
    private  EstadoListaGenero estado;
    
    
  
    public ListaGeneroPresenter( ) throws FileNotFoundException, IOException {
        
        view = new ListaGeneroView();
        this.generos = Generos.getInstancia(); 
        carregarTabela();
        generos.addObserver(this);
        view.getTbGeneros().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTbGeneros().addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                setEstado(new EstadoSelecionadoGenero(ListaGeneroPresenter.this));
            }
        });

        

        view.getBtnExcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exclui();
            }
        });

        view.getBtnEditar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        view.getBtnIncluirNovo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                novo();
            }
        });
        
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               view.dispose();
            }
        });

        setEstado(new EstadoNaoSelecionadoGenero(this));

        view.setVisible(true);
    }

    public ListaGeneroView getView() {
        return view;
    }
    
     public void carregarTabela() {
         
         ArrayList<IGenero>gen = generos.getGeneros();

        Object colunas[] = {"Cod.", "Descrição"};
        tm = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);

        Iterator<IGenero> it = gen.iterator();
        while (it.hasNext()) {
            IGenero g = it.next();
            String linha = g.toString();
            String campos[] = linha.split("%");
            tm.addRow(new Object[] {campos[0], campos[1]});
        }


        view.getTbGeneros().setModel(tm);

    }

    public EstadoListaGenero getEstado() {
        return estado;
    }

    public void setEstado(EstadoListaGenero estado) {
        this.estado = estado;
    }

    public IGeneros getGeneros() {
        return generos;
    }

    public void setGeneros(IGeneros generos) {
        this.generos = generos;
    }

    public void setView(ListaGeneroView view) {
        this.view = view;
    }

    public IGenero getGenero() {
        return genero;
    }

    public void setGenero(IGenero genero) {
        this.genero = genero;
    }

   

    public DefaultTableModel getTm() {
        return tm;
    }

    public void setTm(DefaultTableModel tm) {
        this.tm = tm;
    }
    
     public void exclui() {
        estado.exclui();
    }

    public void editar() {
        estado.editar();
    }
     public void novo() {
        estado.novo();
    }

    @Override
    public void update(ArrayList<IGenero> generos) {
  
       carregarTabela();
        estado.deseleciona();
    }
    

    
    
    
}
