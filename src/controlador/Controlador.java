

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;
import modelo.*;
import modelo.dao.ProfesorDAO;

public class Controlador implements ActionListener{

    Login login = new Login();
    ProfesorDAO profesorDao = new ProfesorDAO();
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    
    public  Controlador(Login login,ProfesorDAO profesorDao ,MenuPrincipal menuPrincipal){
        this.login = login;
        this.profesorDao = profesorDao;
        this.login.btnIngresar.addActionListener(this);
        this.menuPrincipal= menuPrincipal;
        
    }
  
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == login.btnIngresar){
           
            System.out.println("hola");
     
            
        }
        
        
        
    }

    
    
    
    
    
}
