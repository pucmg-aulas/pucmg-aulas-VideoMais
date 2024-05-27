

package state.listagem.cliente;

import presenter.ListaClientePresenter;

public class EstadoNaoSelecionado extends EstadoListagemCliente {

    public EstadoNaoSelecionado(final ListaClientePresenter objeto) 
    {
        super(objeto);
        objeto.setCliente(null);
         
        objeto.getView().getBtnExcluir().setEnabled(false);
        objeto.getView().getBtnVisualizar().setEnabled(false);
        
        objeto.carregarTabela();
    }

    @Override
    public void seleciona() 
    {
        objeto.setEstado(new EstadoSelecionado(objeto));
    }

    @Override
    public void exclui()
    {
        //faz nada pq nao pode
    }

    @Override
    public void visualiza()
    {
        //faz nada pq nao pode
    }

    @Override
    public void deseleciona()
    {
        //faz nada pq nao pode
    }

    @Override
    public void fechar()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }

    
}
