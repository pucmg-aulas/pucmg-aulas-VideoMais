/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state.cadastro.cliente;

import presenter.InclusaoClientePresenter;

/**
 *
 * @author Danilo
 */
public class EstadoVisualizacaoCliente extends EstadoClienteView
{
        public EstadoVisualizacaoCliente(final InclusaoClientePresenter objeto) {
            
        super(objeto);
        
        objeto.getView().setTitle("Visualização");

        objeto.setNomeAnterior(objeto.getCliente().getNome());

        preencherTela();
        bloquearTelaPraEdição(); 
        
        objeto.getView().getBtnSair().setText("Sair");

        objeto.getView().getBtnSalvar().setEnabled(true);
        objeto.getView().getBtnSalvar().setText("Editar");
        
        objeto.getView().getBtnSalvar().requestFocus();
    
        objeto.getView().getBtnNovo().setEnabled(true);

        
    }

    private void preencherTela()
    {
        if(objeto.getCliente().getSexo() == 'M')
            {
                objeto.getView().getJcSexo().setSelectedIndex(1);
            }
            else
            {
                objeto.getView().getJcSexo().setSelectedIndex(2);
            }
            
            int uf = 0;
            if(objeto.getCliente().getEndereco().getUf().equals("ES"))
            {
                uf = 1;
            }
            else if(objeto.getCliente().getEndereco().getUf().equals("MG"))
            {
                uf = 2;
            }
            else if(objeto.getCliente().getEndereco().getUf().equals("RJ"))
            {
                uf = 3;
            }
            else if(objeto.getCliente().getEndereco().getUf().equals("SP"))
            {
                uf = 4;
            }
            objeto.getView().getJcEstado().setSelectedIndex(uf);
            

            objeto.getView().getJtRua().setText(objeto.getCliente().getEndereco().getRua());
            objeto.getView().getJtNumero().setText(objeto.getCliente().getEndereco().getNumero());
            objeto.getView().getJtBairro().setText(objeto.getCliente().getEndereco().getBairro());
            objeto.getView().getJtCidade().setText(objeto.getCliente().getEndereco().getCidade());
            
            objeto.getView().getJtNome().setText(objeto.getCliente().getNome());
            objeto.getView().getJtcpf().setText(objeto.getCliente().getCpf());
            objeto.getView().getJtDataNasc().setText(objeto.getCliente().getDataNascimento());
            objeto.getView().getJtTelefone().setText(objeto.getCliente().getTelefone());
            objeto.getView().getJtEmail().setText(objeto.getCliente().geteMail());
            objeto.getView().getJtRenda().setText(String.valueOf(objeto.getCliente().getRenda()));
    }
    


    @Override
    public void salvar()
    {
        objeto.setEstado(new EstadoAlteracaoCliente(objeto));
    }

    @Override
    public void fechar()
    {
        objeto.getView().setVisible(false);
        objeto.getView().dispose();
    }

    @Override
    public void novo()
    {
        objeto.setEstado(new EstadoInclusaoCliente(objeto));
    }

}
