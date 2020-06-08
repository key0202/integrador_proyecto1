
package run;

import modelo.dao.impl.DaoProfesorImpl;
import modelo.dto.Profesor;


public class textProfesor {
    
    
    public static void main(String[] args) {
        DaoProfesorImpl daoUsuarios = new DaoProfesorImpl();
        
        try {
            Profesor u = daoUsuarios.validarSesion("profesora", "123456");
            if (u != null) {
                System.out.println(u.getNombre());
            } else {
                System.err.println("error");
                System.out.println(u.getPassword());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
