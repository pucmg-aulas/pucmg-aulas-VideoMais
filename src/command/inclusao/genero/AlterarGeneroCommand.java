/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.genero;

import presenter.InclusaoGeneroPresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.iNegocio.IGenero;
import state.cadastro.genero.EstadoVisualizacaoGenero;

/**
 *
 * @author Danilo
 */
public class AlterarGeneroCommand extends AbstractCommandGenero
{
    public AlterarGeneroCommand(InclusaoGeneroPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            valida();
            receptor.setEstado(new EstadoVisualizacaoGenero(receptor));
            
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(receptor.getView(), ex.getMessage());
            Logger.getLogger(AlterarGeneroCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected boolean salva(IGenero genero) throws IOException
    {
        return !receptor.getGeneros().altera(genero, receptor.getNomeAnterior());
    }
}
