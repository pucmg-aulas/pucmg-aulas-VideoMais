/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import dao.iPersistence.ILocacoes;
import dao.persistence.Locacoes;
import presenter.InclusaoLocacaoPresenter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.iNegocio.ILocacao;
import model.negocio.Locacao;

/**
 *
 * @author Danilo
 */
public class SalvarCommandLocacao extends AbstractCommandLocacao
{


    public SalvarCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            valida();
        } catch (IOException ex)
        {
            Logger.getLogger(SalvarCommandLocacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(SalvarCommandLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void valida() throws IOException, Exception
    {

        ILocacoes locacoes = receptor.getLocacoes();

        if (receptor.getView().getTbProdSelecionados().getModel().getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Selecione um item primeiro!");
            
        } else
        {

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            double total = Double.parseDouble(receptor.getView().getJtTOTAL().getText());

            int x = JOptionPane.showConfirmDialog(receptor.getView(), "Confirma Locação?");
            if (x == JOptionPane.YES_OPTION)
            {
                
                ILocacao locacao = new Locacao(locacoes.getUltimoCodigo(), receptor.getCliente(), total, formatador.format(data), 'S', receptor.getItensSelecionados());

                if (salva(locacao))
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "ILocacao já existente");

                } else
                {
                    //setar os itens como locados
                    receptor.setarProdutosAlugados();
                    JOptionPane.showMessageDialog(receptor.getView(), " Alugado com sucesso!");
                    receptor.fecharTela();
                    
                }
            } 
        }
    }

    private boolean salva(ILocacao locacao) throws IOException
    {
        return !Locacoes.getInstancia().adiciona(locacao);
    }
}
