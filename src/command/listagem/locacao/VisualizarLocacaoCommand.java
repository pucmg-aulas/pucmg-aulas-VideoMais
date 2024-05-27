/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.locacao;

import dao.iPersistence.ILocacoes;
import dao.persistence.Locacoes;
import presenter.ListaItemLocacaoPresenter;
import presenter.ListaLocacaoPresenter;
import javax.swing.JOptionPane;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class VisualizarLocacaoCommand extends AbstractCommandListagemLocacao
{

    public VisualizarLocacaoCommand(ListaLocacaoPresenter receptor)
    {
        super(receptor);
    }

    
    @Override
    public void execute()
    {
        try
        {
            
            int line = receptor.getView().getTbLocacoes().getSelectedRow();

            if(line == -1)
            {
                throw new Exception("Selecione uma linha primeiro");
            }
            String cod = receptor.getView().getTbLocacoes().getValueAt(line, 0).toString();
            int pCod = Integer.parseInt(cod);
            
            ILocacoes locacoes = Locacoes.getInstancia();
            ILocacao locacao = locacoes.buscarLocacaoPorCodigo(pCod);
            
            new ListaItemLocacaoPresenter(locacao);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(receptor.getView(), e.getMessage());
        }
    }
    
}
