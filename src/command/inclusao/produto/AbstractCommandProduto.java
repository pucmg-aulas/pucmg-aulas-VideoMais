/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.inclusao.produto;

import command.Command;
import dao.iPersistence.IGeneros;
import dao.persistence.Generos;
import presenter.InclusaoProdutoPresenter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.iNegocio.IGenero;
import model.iNegocio.IProduto;
import model.negocio.Filme;
import model.negocio.Livro;

/**
 *
 * @author Danilo
 */
public abstract class AbstractCommandProduto implements Command
{

    protected InclusaoProdutoPresenter receptor;

    public AbstractCommandProduto(InclusaoProdutoPresenter receptor)
    {
        this.receptor = receptor;
    }

    @Override
    public abstract void execute();

    protected void valida() throws IOException, Exception
    {


        if (receptor.getView().getJtDescProd().getText().isEmpty())
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Campo Descrição Vazio");
            
        } else if (receptor.getView().getJtAnoLanc().getText().equals("    "))
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Campo Ano Lançamento Vazio");
            
        } else if (receptor.getView().getJcCategoria().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Campo Categoria Vazio");
            
        } else if (receptor.getView().getJcGenero().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Campo IGenero Vazio");
            
        } else if (receptor.getView().getJcStatusFilme().getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(receptor.getView(), "Campo Status Vazio");
            
        } else
        {
            IGeneros generos = Generos.getInstancia();

            IGenero g = generos.buscarGeneroPorNome(receptor.getView().getJcGenero().getSelectedItem().toString());
            
            IProduto p = null;

                if(receptor.getView().getJcCategoria().getSelectedIndex() == 1)
                {
                    p = new Filme(Integer.parseInt(receptor.getView().getJlCodProd().getText()), receptor.getView().getJtDescProd().getText(), receptor.getView().getJtAnoLanc().getText(), receptor.getView().getJcStatusFilme().getSelectedItem().toString(), g);
                }
                else if(receptor.getView().getJcCategoria().getSelectedIndex() == 2)
                {
                    p = new Livro(Integer.parseInt(receptor.getView().getJlCodProd().getText()), receptor.getView().getJtDescProd().getText(), receptor.getView().getJtAnoLanc().getText(), receptor.getView().getJcStatusFilme().getSelectedItem().toString(), g);
                }

            if (!p.getNomeProduto().contains(","))
            {
                if (salva(p))
                {
                    JOptionPane.showMessageDialog(receptor.getView(), "Pessoa já existente");
                } else
                {
                    receptor.setProduto(p);
                    JOptionPane.showMessageDialog(receptor.getView(), receptor.getView().getJtDescProd().getText() + " Cadastrado com sucesso!");
                    receptor.limpaCampos();
                    receptor.getView().getJtDescProd().requestFocus();
                }
            } else
            {
                throw new Exception("Nome não pode conter vírgula!");
            }
        }
    }

    protected abstract boolean salva(IProduto produto) throws IOException;
}
