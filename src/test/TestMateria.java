package test;

import controlador.ControlMateria;
import modelo.dao.DaoMateria;
import modelo.dao.impl.DaoMateriaImpl;
import vista.Registro_Materia;

/**
 *
 * @author DAVID
 */
public class TestMateria {
    public static void main(String[] args) {        
        DaoMateria dm = new DaoMateriaImpl();
        Registro_Materia vistaMateria = new Registro_Materia();
        
        ControlMateria control = new ControlMateria(vistaMateria, dm);
        control.iniciar();
    }
}
