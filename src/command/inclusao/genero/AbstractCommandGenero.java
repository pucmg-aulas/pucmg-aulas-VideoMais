/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.genero;

import command.Command;
import presenter.InclusaoGeneroPresenter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.iNegocio.IGenero;
import model.negocio.Genero;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandGenero implements Command
{
    protected InclusaoGeneroPresenter receptor;

    public AbstractCommandGenero(InclusaoGeneroPresenter receptor)
    {
        this.receptor = receptor;
    }

    @Override
    public abstract void execute();

    protected void valida() throws IOException, Exception
    {

        if (receptor.getView().getJtNomeGenero().getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Campo Nome IGenero Vazio!");
        } 
        else
        {
             
            IGenero genero = new Genero(Integer.parseInt(receptor.getView().getJlCOD().getText()), receptor.getView().getJtNomeGenero().getText());
            
            if (!genero.getNomeGenero().contains(","))
            {
                if (salva(genero))
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "IGenero já existente");
                } else
                {
                    receptor.setGenero(genero);
                    JOptionPane.showMessageDialog(receptor.getView(), receptor.getView().getJtNomeGenero().getText() + " Cadastrado com sucesso!");
                    receptor.limpaCampos();
                    receptor.getView().getJtNomeGenero().requestFocus();
                }
            } else
            {
                throw new Exception("Nome não pode conter vírgula!");
            }
        }
    }

    protected abstract boolean salva(IGenero genero) throws IOException;
}
