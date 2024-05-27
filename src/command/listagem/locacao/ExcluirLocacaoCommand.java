/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.locacao;

import dao.iPersistence.IProdutos;
import presenter.ListaLocacaoPresenter;
import java.util.Iterator;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import dao.persistence.Produtos;

/**
 *
 * @author Danilo
 */
public class ExcluirLocacaoCommand extends AbstractCommandListagemLocacao
{

    public ExcluirLocacaoCommand(ListaLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try 
        {

            int linha = receptor.getView().getTbLocacoes().getSelectedRow();
            String pCodLoc;

            if (linha == -1) 
            {
                throw new Exception("Selecione Uma Linha Primeiro!");
            }

            int x = JOptionPane.showConfirmDialog(receptor.getView(), "Deseja Realmente Excluir?");
            
            if (x == JOptionPane.YES_OPTION) 
            {
                pCodLoc = (String) receptor.getView().getTbLocacoes().getValueAt(linha, 0);
                
                ILocacao locacao = receptor.getLocacoes().buscarLocacaoPorCodigo(Integer.parseInt(pCodLoc));
             
                setarItensComoDisponivel(locacao);
                receptor.getLocacoes().excluir(locacao);
                JOptionPane.showMessageDialog(receptor.getView(), "Locação Excluida com Sucesso!");
                
                receptor.carregarTabela();
            }
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
        
    private void setarItensComoDisponivel(ILocacao locacao)
    {
        IProdutos produtos = Produtos.getInstancia();
        
        for (Iterator<IItemLocacao> it = locacao.getItens().iterator(); it.hasNext();)
        {
            IItemLocacao itemLocacao = it.next();
            produtos.setarProdutoDisponivel(itemLocacao.getItem());
        }
        
    }
    
}
