/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.listagem.genero;

import model.iNegocio.IGenero;
import presenter.InclusaoGeneroPresenter;
import presenter.ListaGeneroPresenter;

/**
 *
 * @author mateus
 */
public class EditarCommand extends AbstractCommand{

    public EditarCommand(ListaGeneroPresenter receptor) {
        super(receptor);
    }

    @Override
    public void execute() {

            if (receptor.getGenero()!= null) {
              IGenero genero = receptor.getGenero();
              new InclusaoGeneroPresenter(genero);  
            }

             

           
            
       
       
    }
    
}
