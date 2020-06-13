package controlador;


import modelo.dao.DaoMateria;

import modelo.dao.impl.DaoMateriaImpl;
import modelo.dao.impl.DaoProfesorImpl;
import vista.Login;
import vista.MenuPrincipal;
import vista.Registro_Materia;

public class Main {

    public static void main(String[] args) {
 
        Login login = new Login();
        DaoProfesorImpl daoprofesor = new DaoProfesorImpl();
        DaoMateria daoMateria = new DaoMateriaImpl();
        Registro_Materia vistaMateria = new Registro_Materia();
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        
       // ControlMateria control = new ControlMateria(vistaMateria, dm);pppr
       // control.iniciar();
       
       
       Controlador controlador = new Controlador(login, daoprofesor, menuPrincipal);
        
       
    }
}
