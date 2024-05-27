/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.ListaMultaView;
import model.iNegocio.ICliente;
import chain.calculoValorLocacao.CalcularFilme;
import chain.calculoValorLocacao.CalcularLivro;
import chain.calculoValorLocacao.IChainLocacao;
import dao.iPersistence.ILocacoes;
import dao.persistence.Locacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.IItemLocacao;
import model.iNegocio.ILocacao;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class ListaMultaPresenter
{
    ListaMultaView view;
    ICliente cliente;
    ILocacoes locacoes = Locacoes.getInstancia();

    public ListaMultaPresenter(ICliente cliente)
    {
        view = new ListaMultaView();
        
       
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fechar();
            }
        });
        
       
        carregarTabelaMultas();
        view.setVisible(true);
       
    }
    
    private void fechar()
    {
        view.setVisible(false);
        view.dispose();
    }
    
    private void carregarTabelaMultas()
    {
        try
        {
            double diasAtraso = 0;
            Date dataAtual = new Date();
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPrevista = null;
            
            DefaultTableModel tm;
            Object colunas1[] = {"Cod.ILocacao", "IProduto", "Valor Unitario", "Dias em Atraso"};

            tm = new DefaultTableModel(colunas1, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
            tm.setNumRows(0);
            

            for(int i = 0; i < locacoes.getLocacoes().size(); i++)
            {
                ILocacao l = locacoes.getLocacoes().get(i);
                String texto = String.valueOf(l.getCodLocacao()) + "%";
                
                if(l.getEmAberto() == 'S' && l.getCliente().getCpf().equals(cliente.getCpf()))
                {
                    for(int j = 0; j < l.getItens().size(); j++)
                    {
                        IItemLocacao il = l.getItens().get(j);
                        
                        dataPrevista = f.parse(il.getDataPrevistaDevolucao());
                        
                        IProduto p = il.getItem();
                        Double valorProd = null;
                        
                        IChainLocacao livro = new CalcularLivro();
                        IChainLocacao filme = new CalcularFilme();
                        livro.setProximo(filme);
                        valorProd = livro.calcular(p);
                        
                        
                        if(dataAtual.after(dataPrevista)) //eh pq tem multa
                        {
                            
                            long atraso = dataAtual.getTime() - dataPrevista.getTime();
                            diasAtraso = (atraso /1000) / 60 / 60 /24;
                            
                            texto += String.valueOf(l.getItens().get(j).getItem().getNomeProduto()) + "%";
                            texto += String.valueOf(valorProd) + "%";
                            texto += il.getDataPrevistaDevolucao() + "%";
                            texto += String.valueOf(diasAtraso);
                            String linha[] = texto.split("%");
                            tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3]});
                            
                        }
                    }
                }
            }
            view.getTbMultas().setModel(tm);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    } 
}
