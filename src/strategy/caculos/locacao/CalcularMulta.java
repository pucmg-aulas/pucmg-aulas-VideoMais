/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.caculos.locacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.iNegocio.ILocacao;

/**
 *
 * @author Danilo
 */
public class CalcularMulta implements ICalculoLocacao
{

    @Override
    public double calcular(ILocacao locacao)
    {
        
        double multa = 0;
        Date dataAtual = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        
        try
        {
            for(int j = 0; j < locacao.getItens().size(); j++)
            {

                Date dataPrevista = f.parse(locacao.getItens().get(j).getDataPrevistaDevolucao());

                if(dataAtual.before(dataPrevista))
                {
                    long atraso = dataAtual.getTime() - dataPrevista.getTime();
                    double diasAtraso = (atraso /1000) / 60 / 60 /24;

                    multa += (diasAtraso * 2);
                }
            }
            return multa;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
    
}
