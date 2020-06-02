package controlador;

import modelo.dao.ProfesorDAO;
import vista.Login;
import vista.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        Login login = new Login();
        ProfesorDAO profesorDao = new ProfesorDAO();
        MenuPrincipal mp = new MenuPrincipal();
        
        Controlador con = new Controlador(login, profesorDao, mp);
        
        login.setVisible(true);
        
    }
}
