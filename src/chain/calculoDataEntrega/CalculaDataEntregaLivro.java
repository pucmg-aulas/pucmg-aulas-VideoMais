/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.calculoDataEntrega;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.iNegocio.ILivro;
import model.iNegocio.IProduto;

/**
 *
 * @author Danilo
 */
public class CalculaDataEntregaLivro implements IChainDataEntrega
{

    private IChainDataEntrega proximo;

    @Override
    public void setProximo(IChainDataEntrega proximoNaCadeia)
    {
        this.proximo = proximoNaCadeia;
    }

    @Override
    public String calcular(IProduto produto)
    {

        if (produto instanceof ILivro)
        {

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            if (produto.getStatus().equals("Faixa Verde"))
            {
                data.setDate(data.getDate() + 3);
                
            } else if (produto.getStatus().equals("Normal"))
            {
                data.setDate(data.getDate() + 2);
                
            } else
            {
                data.setDate(data.getDate() + 1);
            }
            
            String d = formatador.format(data);
            return d;
        }
        
        return proximo.calcular(produto);
    }
}
