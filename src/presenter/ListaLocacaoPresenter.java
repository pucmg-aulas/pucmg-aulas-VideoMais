/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import view.ListaLocacaoView;
import chain.listaLocacoes.IChainLocacao;
import chain.listaLocacoes.ListaCompletaLocacao;
import chain.listaLocacoes.ListaDataFimLocacao;
import chain.listaLocacoes.ListaDataInicioLocacao;
import chain.listaLocacoes.ListaPorNomeLocacao;
import dao.iPersistence.ILocacoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import model.iNegocio.ILocacao;
import dao.persistence.Locacoes;
import java.util.ArrayList;
import observer.locacao.ObserverLocacao;
import state.listagem.locacao.EstadoListaLocacao;
import state.listagem.locacao.EstadoNaoSelecionadoLocacao;
import state.listagem.locacao.EstadoSelecionadoLocacao;

/**
 *
 * @author Danilo
 */
public class ListaLocacaoPresenter implements ObserverLocacao
{
    private ListaLocacaoView view;
    private ILocacoes locacoes;
    private EstadoListaLocacao estado;

    public ListaLocacaoPresenter()
    {
        view = new ListaLocacaoView();
        locacoes = Locacoes.getInstancia();
        locacoes.addObserver(this);
        setEstado(new EstadoNaoSelecionadoLocacao(this));
        view.setVisible(true);
        
        //botoes
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                sair();
            }
        });
        view.getBtnExcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                excluir();
            }
        });
        view.getBtnVisualizar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                visualizar();
            }
        });
        view.getBtnLimpar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                limpar();
            }
        });
        //textboxes
        view.getJtCliente().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                        carregarTabela();
                }
            }

        });    
        view.getJtInicio().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                        carregarTabela();
                }    
            }
            
        });
        view.getJtFim().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                        carregarTabela();
                }                
            }

        });
        //tabela
        view.getTbLocacoes().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e)
            {
                setEstado(new EstadoSelecionadoLocacao(ListaLocacaoPresenter.this));
            }
        });
        
    }
    
    public void sair()
    {
        estado.sair();
    }
    
    public void visualizar()
    {
        estado.visualizar();
    }
    
    public void excluir()
    {
        estado.excluir();
    }
    
    private void limpar()
    {
        view.getJtCliente().setText("");
        view.getJtFim().setText("");
        view.getJtInicio().setText("");
        carregarTabela();
    }
    
    public void carregarTabela()
    {
        DefaultTableModel tm;
        Object colunas[] = {"Cod.", "ICliente", "Valor Total", "Data Loc.", "Em Aberto"};
        tm = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        tm.setNumRows(0);

        IChainLocacao listaCompleta = new ListaCompletaLocacao(this);
        IChainLocacao listaNome = new ListaPorNomeLocacao(this);
        IChainLocacao ListaDataIni = new ListaDataInicioLocacao(this);
        IChainLocacao listaDataFim = new ListaDataFimLocacao(this);

        listaCompleta.setProximo(listaNome);
        listaNome.setProximo(ListaDataIni);
        ListaDataIni.setProximo(listaDataFim);

        Iterator<ILocacao> it = listaCompleta.processo(locacoes.getLocacoes()).iterator();

        while (it.hasNext())
        {
            ILocacao locacao =  it.next();
            String c1 = locacao.toString();
            String linha[] = c1.split("%");
            tm.addRow(new Object[]{linha[0], linha[1], linha[2], linha[3], linha[4]});
        }

        view.getTbLocacoes().setModel(tm);
    }
    
    
    
    
    //================ GETTERS AND SETTERS =========================================================
    //===============================================================================================

    public ListaLocacaoView getView()
    {
        return view;
    }

    public void setView(ListaLocacaoView view)
    {
        this.view = view;
    }

    public ILocacoes getLocacoes()
    {
        return locacoes;
    }

    public void setLocacoes(ILocacoes locacoes)
    {
        this.locacoes = locacoes;
    }

    public EstadoListaLocacao getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoListaLocacao estado)
    {
        this.estado = estado;
    }

    @Override
    public void update(ArrayList<ILocacao> locacoes)
    {
        carregarTabela();
    }
    
    
    
    
    
}
