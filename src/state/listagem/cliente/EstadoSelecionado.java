package state.listagem.cliente;

import command.listagem.cliente.ExcluirClienteCommand;
import command.listagem.cliente.VisualizaClienteCommand;
import presenter.ListaClientePresenter;

public class EstadoSelecionado extends EstadoListagemCliente
{

    public EstadoSelecionado(ListaClientePresenter objeto)
    {
        super(objeto);
        
        objeto.setCliente(null);
        
        seleciona();
        objeto.getView().getBtnExcluir().setEnabled(true);
        objeto.getView().getBtnVisualizar().setEnabled(true);
         

    }

    @Override
    public void seleciona()
    {
        int linha = objeto.getView().getTabela().getSelectedRow();
        String nome = (String) objeto.getView().getTabela().getValueAt(linha, 0);
        objeto.setCliente(objeto.getClientes().buscarClientePorNome(nome));
        
    }
    
    @Override
    public void deseleciona()
    {
        //criar command pra fazer isso.. 
        objeto.setEstado(new EstadoNaoSelecionado(objeto));
    }

    @Override
    public void exclui()
    {
        new ExcluirClienteCommand(objeto).execute();
    }

    @Override
    public void visualiza()
    {
        new VisualizaClienteCommand(objeto).execute();
    }

    @Override
    public void fechar()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }
}
