/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.iPersistence.ILocacoes;
import dao.persistence.Locacoes;
import view.ListaItemLocacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import observer.locacao.ObserverLocacao;

/**
 *
 * @author Danilo
 */
public class ListaItemLocacaoPresenter
{

    private ListaItemLocacaoView view;
    private ILocacao locacao;
    private ILocacoes locacoes;

    public ListaItemLocacaoPresenter(ILocacao locacao)
    {
        this.view = new ListaItemLocacaoView();
        this.locacao = locacao;
        this.locacoes = Locacoes.getInstancia();

        view.getLbCliente().setText(this.locacao.getCliente().getNome());
        view.getLbData().setText(this.locacao.getDataLocacao());

        carregarTabela();
        
        view.setVisible(true);
        
        view.getBtnVoltar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fechar();
            }
        });
    }

    private void fechar()
    {
        view.setVisible(false);
        view.dispose();
    }
    private void carregarTabela()
    {
        
        DefaultTableModel tm;
        Object colunas1[] = {"Cod.", "IProduto", "Valor Unitario", "Data Prevista Devolução", "Devolvido"};

        tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);

        for (int i = 0; i < locacao.getItens().size(); i++)
        {
            IItemLocacao itl  = locacao.getItens().get(i);
            String c1 = itl.toString();
            String linha[] = c1.split("%");
            tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4]});            
        }
        
        view.getTbItensLocacao().setModel(tm);
    }

    public ListaItemLocacaoView getView()
    {
        return view;
    }

    public void setView(ListaItemLocacaoView view)
    {
        this.view = view;
    }

    public ILocacao getLocacao()
    {
        return locacao;
    }

    public void setLocacao(ILocacao locacao)
    {
        this.locacao = locacao;
    }

    public ILocacoes getLocacoes()
    {
        return locacoes;
    }

    public void setLocacoes(ILocacoes locacoes)
    {
        this.locacoes = locacoes;
    }
    
    
}
