/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.locacao;

import presenter.InclusaoLocacaoPresenter;
import javax.swing.JOptionPane;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class BuscarProdutoCommandLocacao extends AbstractCommandLocacao
{

    public BuscarProdutoCommandLocacao(InclusaoLocacaoPresenter receptor)
    {
        super(receptor);
    }

    @Override
    public void execute()
    {

        if (!receptor.getView().getJtCodProduto().getText().isEmpty() || !receptor.getView().getJtTituloProd().getText().isEmpty())
        {
            receptor.getTmProdutos().setNumRows(0);

            for (int i = 0; i < receptor.getProdutos().getProdutos().size(); i++)
            {
                IProduto produto = receptor.getProdutos().getProdutos().get(i);

                if (receptor.getView().getJtTituloProd().getText().isEmpty())
                {
                    if (produto.getCodProduto() == Integer.parseInt(receptor.getView().getJtCodProduto().getText()))
                    {
                        String c1 = produto.toString2();
                        String linha[] = c1.split("%");
                        receptor.getTmProdutos().addRow(new Object[]
                                {
                                    linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]
                                });
                    }
                } else if (receptor.getView().getJtCodProduto().getText().isEmpty())
                {
                    if (produto.getNomeProduto().toLowerCase().contains(receptor.getView().getJtTituloProd().getText().toLowerCase()))
                    {
                        String c1 = produto.toString2();
                        String linha[] = c1.split("%");
                        receptor.getTmProdutos().addRow(new Object[]
                                {
                                    linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]
                                });
                    }
                }
            }
            receptor.getView().getTbProdutos().setModel(receptor.getTmProdutos());
        }
        else
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Entre com o Código ou Nome do IProduto Primeiro!");
        }
    }
}
