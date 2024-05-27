/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.ListaPagamentoView;
import chain.listaPagamentos.IChainPagamento;
import chain.listaPagamentos.ListaPagamentoCompleta;
import chain.listaPagamentos.ListagemAnoPagamento;
import chain.listaPagamentos.ListagemDiaPagamento;
import chain.listaPagamentos.ListagemMesPagamento;
import dao.iPersistence.IPagamentos;
import dao.persistence.Pagamentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IPagamento;
import observer.pagamento.ObserverPagamento;

/**
 *
 * @author Danilo
 */
public class ListaPagamentoPresenter implements ObserverPagamento
{
    private ListaPagamentoView view;
    private IPagamentos pagamentos = Pagamentos.getInstancia();

    public ListaPagamentoPresenter()
    {
        view = new ListaPagamentoView();
        
        view.getJcbListarTodos().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                bloquearLiberarCombos();
                carregarPagamentos();
            }
        });
        view.getJcAno().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                carregarPagamentos();
            }
        });
        view.getJcDia().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                carregarPagamentos();
            }
        });
        view.getJcMes().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                carregarPagamentos();
            }
        });
        
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.setVisible(false);
                view.dispose();
            }
        });
        view.setVisible(true);
        carregarPagamentos();
    }
    
    
    public void carregarPagamentos()
    {
        DefaultTableModel tm;
        Object colunas1[] = {"ICliente", "Valor Total", "Data", "Desconto"};
        
        tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);
        
        double valorTotal = 0;
        double valorDesconto = 0;
        
        IChainPagamento completo = new ListaPagamentoCompleta(this);
        IChainPagamento dia = new ListagemDiaPagamento(this);
        IChainPagamento mes = new ListagemMesPagamento(this);
        IChainPagamento ano = new ListagemAnoPagamento(this);
        
        completo.setProximo(dia);
        dia.setProximo(mes);
        mes.setProximo(ano);
        
        Iterator<IPagamento> it = completo.processo(pagamentos.getPagamentos()).iterator();

        while (it.hasNext())
        {
            IPagamento pag = it.next();
            String c1 = pag.toString();
            String linha[] = c1.split("%");
            tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3]});
            valorTotal += pag.getValorTotal();
            valorDesconto += pag.getDesconto();
        }
        
        view.getJlbValorTotal().setText(String.valueOf(valorTotal - valorDesconto));
        view.getTbPagamentos().setModel(tm);
    }
    
    public void bloquearLiberarCombos()
    {
        if(view.getJcbListarTodos().isSelected())
        {
            view.getJcAno().setEnabled(false);
            view.getJcDia().setEnabled(false);
            view.getJcMes().setEnabled(false);
        }
        else
        {
            view.getJcAno().setEnabled(true);
            view.getJcDia().setEnabled(true);
            view.getJcMes().setEnabled(true);
            
        }
    }

    public ListaPagamentoView getView()
    {
        return view;
    }

    public void setView(ListaPagamentoView view)
    {
        this.view = view;
    }

    @Override
    public void update(ArrayList<IPagamento> locacoes)
    {
        carregarPagamentos();
    }
    
    
}
