
package modelo.dao;


import java.util.List;
import javax.swing.JTable;
import modelo.dto.Alumno;
import vista.Registro_Alumno;

public interface DaoAlumno {
    
    public String insertarAlumno(Alumno alumno) ;
    
    //listar alumnos según materia
    public String listarAlumnos(String materia, JTable tablaAlumnos);
    
    //llebar combo box materias de la vista Registro_Alumno
    public void comboBoxMaterias(Registro_Alumno vista_alumno, String docente);
    
    //Obtener el id de un alumno a través de su DNI(en la tabla es unique)
    public String getIdAlumno(String dni);
    
    
   //Saber si el alumno ya fue instanciado
    public boolean existeAlumno(Alumno alumno);
    
    
    public String listarTodosAlumnos(JTable tabla);
    
    
}
