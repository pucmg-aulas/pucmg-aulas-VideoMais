/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import chain.calculoDataEntrega.CalculaDataEntregaFilme;
import chain.calculoDataEntrega.CalculaDataEntregaLivro;
import chain.calculoDataEntrega.IChainDataEntrega;
import chain.calculoValorLocacao.CalcularFilme;
import chain.calculoValorLocacao.CalcularLivro;
import chain.calculoValorLocacao.IChainLocacao;
import presenter.InclusaoLocacaoPresenter;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.iNegocio.IItemLocacao;
import model.iNegocio.IProduto;
import model.negocio.ItemLocacao;

/**
 *
 * @author Danilo
 */
public class AddItemCommandLocacao extends AbstractCommandLocacao
{

    public AddItemCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {
        if (receptor.getCliente() == null)
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Entre com Um ICliente Primeiro");
        } else
        {
            if (receptor.getView().getTbProdutos().getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(receptor.getView(), "Selecione Um Item Primeiro");
            } else
            {

                int lin = receptor.getView().getTbProdutos().getSelectedRow();

                int pCodInserir = Integer.parseInt(receptor.getView().getTbProdutos().getValueAt(lin, 0).toString());

                IProduto p = receptor.getProdutos().buscarProdutoPorCodigo(pCodInserir);

                if (p.getIndicadorLocacao() == 0)
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "Produto já Alugado!");

                } else
                {
                    int flag = -1;

                    for (int i = 0; i < receptor.getTmSelected().getRowCount(); i++)
                    {
                        int pCodExistente = Integer.parseInt(receptor.getView().getTbProdSelecionados().getValueAt(i, 0).toString());

                        if (pCodInserir == pCodExistente)
                        {
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == -1)
                    {
                        Vector v = new Vector();

                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 0));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 1));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 2));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 3));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 4));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 5));
                        v.add(receptor.getView().getTbProdutos().getValueAt(lin, 6));

                        receptor.getTmSelected().addRow(v);
                        receptor.getView().getTbProdSelecionados().setModel(receptor.getTmSelected());
                        //precisamos dizer q ele "esta alugado"
                        p.setIndicadorLocacao(0);
                        inserirListaItens(p);
                    } else
                    {
                        JOptionPane.showMessageDialog(receptor.getView(), "Item já Incluso!");
                    }
                }
            }
        }
    }

    private void inserirListaItens(IProduto pProduto)
    {

        IChainDataEntrega lv = new CalculaDataEntregaLivro();
        IChainDataEntrega fi = new CalculaDataEntregaFilme();
        
        lv.setProximo(fi);
        
        IChainLocacao Lv = new CalcularLivro();
        IChainLocacao Fi = new CalcularFilme();
        
        Lv.setProximo(Fi);
        
        IItemLocacao it = new ItemLocacao(pProduto, Lv.calcular(pProduto), lv.calcular(pProduto));

        receptor.getItensSelecionados().add(it);
        
        receptor.atualizaCampoValorTotal(it.getValorUnitario());
    }
}
