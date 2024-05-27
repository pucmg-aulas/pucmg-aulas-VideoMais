/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.produto;

import presenter.InclusaoProdutoPresenter;

/**
 *
 * @author Danilo
 */
public class EstadoVisualizacaoProduto extends EstadoProdutoView
{

    public EstadoVisualizacaoProduto(InclusaoProdutoPresenter objeto)
    {
        super(objeto);
        objeto.getView().setTitle("Visualização");
        

        objeto.setNomeAnterior(objeto.getProduto().getNomeProduto());

        preencherTela(objeto);
        bloquearTelaPraEdição(objeto);
        objeto.getView().getBtnSalvar().setText("Editar");
        objeto.getView().getBtnSalvar().setEnabled(true);
        objeto.getView().getBtnSalvar().requestFocus();

    }

    @Override
    public void salvar()
    {
        objeto.setEstado(new EstadoAlteracaoProduto(objeto));
    }

    private void preencherTela(InclusaoProdutoPresenter objeto)
    {
        objeto.getView().getJlCodProd().setText(String.valueOf(objeto.getProduto().getCodProduto()));
        objeto.getView().getJtDescProd().setText(objeto.getProduto().getNomeProduto());
        objeto.getView().getJtAnoLanc().setText(objeto.getProduto().getDataLancamento());

        if (objeto.getProduto().getClass().getName().equals("Negocio.ILivro"))
        {
            objeto.getView().getJcCategoria().setSelectedIndex(2);
            
        } else if (objeto.getProduto().getClass().getName().equals("Negocio.IFilme"))
        {
            objeto.getView().getJcCategoria().setSelectedIndex(1);
        }
        if (objeto.getProduto().getStatus().equals("Super Lançamento"))
        {
            objeto.getView().getJcStatusFilme().setSelectedIndex(1);
            
        } else if (objeto.getProduto().getStatus().equals("Lançamento"))
        {
            objeto.getView().getJcStatusFilme().setSelectedIndex(2);
            
        } else if (objeto.getProduto().getStatus().equals("Normal"))
        {
            objeto.getView().getJcStatusFilme().setSelectedIndex(3);
            
        } else if (objeto.getProduto().getStatus().equals("Faixa Verde"))
        {
            objeto.getView().getJcStatusFilme().setSelectedIndex(4);
        }

        objeto.getView().getJcGenero().setEditable(true);
        objeto.getView().getJcGenero().setSelectedItem((String)objeto.getProduto().getGenero().getNomeGenero());
        objeto.getView().getJcGenero().setEditable(false);
        
    }

    private void bloquearTelaPraEdição(InclusaoProdutoPresenter objeto)
    {
        objeto.getView().getJcCategoria().setEnabled(false);
        
        objeto.getView().getJcGenero().setEnabled(false);
        
        objeto.getView().getJcStatusFilme().setEnabled(false);

        objeto.getView().getJtAnoLanc().setEnabled(false);
        objeto.getView().getJtDescProd().setEnabled(false);

        objeto.getView().getBtnSalvar().setEnabled(false);

    }
}
