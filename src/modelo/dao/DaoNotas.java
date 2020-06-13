
package modelo.dao;

import javax.swing.JComboBox;
import javax.swing.JTable;


public interface DaoNotas {
    
    public String insertarNotasFK(String idalumno, String imateria);

    
    
    public void getNotasMateria(int idmateria);
    
    //LLEVAR COMBO BOX DE LAS MATERIAS;
    public String comboMateria(JComboBox cbMateria);
    
    
    //LISTAR ALUMNOS SEGÚN MATERIA
    public String listarAlumnos(String materia, JTable tablaAlumnos);
}
