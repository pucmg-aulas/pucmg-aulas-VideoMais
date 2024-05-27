/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.iPersistence.IGeneros;
import dao.persistence.Generos;
import view.InclusaoGeneroView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.iNegocio.IGenero;
import state.cadastro.genero.EstadoGeneroView;
import state.cadastro.genero.EstadoInclusaoGenero;
import state.cadastro.genero.EstadoVisualizacaoGenero;

/**
 *
 * @author Danilo
 */
public class InclusaoGeneroPresenter
{

    private InclusaoGeneroView view;
    private IGeneros generos;
    private IGenero genero;
    private String nomeAnterior;
    private EstadoGeneroView estado;

    public InclusaoGeneroPresenter(IGenero genero)
    {
        this.generos = Generos.getInstancia();

        view = new InclusaoGeneroView();
        view.setVisible(true);

        setGenero(genero);

        if (genero == null)
        {
            setEstado(new EstadoInclusaoGenero(this));

        } else
        {
            setEstado(new EstadoVisualizacaoGenero(this));
        }
        
        view.getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                salvar();
            }
        });
        
        view.getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                cancelar();
            }
        });
    }

    public void salvar()
    {
        estado.salvar();
    }
    
    public void cancelar()
    {
        estado.cancelar();
    }

    public InclusaoGeneroView getView()
    {
        return view;
    }

    public void limpaCampos()
    {
        view.getJtNomeGenero().setText("");
        view.getJlCOD().setText(String.valueOf(generos.getUltimoCodigo()));
    }

    public IGenero getGenero()
    {
        return genero;
    }

    public IGeneros getGeneros()
    {
        return generos;
    }

    public void setGenero(IGenero genero)
    {
        this.genero = genero;
    }

    public void setGeneros(IGeneros generos)
    {
        this.generos = generos;
    }

    public void setEstado(EstadoGeneroView estado)
    {
        this.estado = estado;
    }

    public void setNomeAnterior(String nomeAnterior)
    {
        this.nomeAnterior = nomeAnterior;
    }

    public String getNomeAnterior()
    {
        return nomeAnterior;
    }
}
