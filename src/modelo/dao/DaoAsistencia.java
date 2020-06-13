package modelo.dao;

import java.util.List;
import modelo.dto.Alumno;
import modelo.dto.Asistencia;
import vista.Registro_Asistencia;

public interface DaoAsistencia {

    //listar alumnos de una materia
    //public List<Alumno> listarAlumnos(String materia);
    public void listarAlumnosMateria(Registro_Asistencia vista_asistencia, String materia);

    //llebar combo box materias de la vista Registro_Alumno
    public void comboBoxMaterias(Registro_Asistencia vista_asistencia, String docente);

    public void agregarAsistencia(Asistencia asistencia, Registro_Asistencia vistaAsistencia, String docente);
}
