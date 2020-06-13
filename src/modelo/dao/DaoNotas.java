package modelo.dao;

import javax.swing.JComboBox;
import javax.swing.JTable;
import modelo.dto.Notas;
import vista.Registro_Nota;

public interface DaoNotas {

    public String insertarNotasFK(String idalumno, String idmateria);

    public void getNotasMateria(int idmateria);

    //LLEVAR COMBO BOX DE LAS MATERIAS;
    public String comboMateria(JComboBox cbMateria);

    //LISTAR ALUMNOS SEGÚN MATERIA
    public String listarAlumnos(String materia, JTable tablaAlumnos, String tipoExamen);

    //INSERTAR LAS NOTAS PC1-PC2-PC3
    public String insertarNotasDatos(String idalumno, String idmateria, String tipoExamen, double nota);

    public void exportarNotas(Notas notas, Registro_Nota vista_nota, String docente);
}
