/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.devolucao;

import strategy.caculos.locacao.CalcularMulta;
import dao.iPersistence.ILocacoes;
import dao.iPersistence.IProdutos;
import dao.persistence.Locacoes;
import presenter.InclusaoDevolucaoPresenter;
import javax.swing.JOptionPane;
import model.iNegocio.IProduto;
import dao.persistence.Produtos;

/**
 *
 * @author Danilo
 */
public class EfetuarDevolucaoCommand extends AbstractCommandDevolucao
{

    public EfetuarDevolucaoCommand(InclusaoDevolucaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        try
        {
            IProdutos produtos = Produtos.getInstancia();

            int linha2 = receptor.getView().getTbItensLocacao().getSelectedRow();
            if (linha2 == -1)
            {
                throw new Exception("Selecione um Item Primeiro!");

            }

            String cd = (String) receptor.getView().getTbItensLocacao().getValueAt(linha2, 0);
            int pCodProd = Integer.parseInt(cd);
            IProduto produto = produtos.buscarProdutoPorCodigo(pCodProd);

            produtos.setarProdutoDisponivel(produto);
            receptor.getLocacoes().setItemLocacaoEntregue(receptor.getLocacao(), pCodProd);

            JOptionPane.showMessageDialog(receptor.getView(), "Produto Devolvido Com Sucesso!");

            if (foiPaga(receptor.getLocacao().getCodLocacao()))
            {
                if (receptor.getLocacoes().temMulta(receptor.getLocacao()))
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "Há Multa pendente de R$" + new CalcularMulta().calcular(receptor.getLocacao()));
                }
            }
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(receptor.getView(), e.getMessage());
        }
    }



    private boolean foiPaga(int pCodLoc)
    {

        ILocacoes locacoes = Locacoes.getInstancia();
        
        boolean retorno = false;

        for (int i = 0; i < locacoes.getLocacoes().size(); i++)
        {
            if (locacoes.getLocacoes().get(i).getCodLocacao() == pCodLoc)
            {
                if (locacoes.getLocacoes().get(i).getEmAberto() == 'S')
                {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }
}
