package modelo.dao;

import modelo.dto.Materia;
import vista.Registro_Materia;


public interface DaoMateria {

    public void leer(Registro_Materia vistaMateria, String docente);

    public void agregarMateria(Materia materia, Registro_Materia vistaMateria, String docente);
    
    public String getIdMateria(String nombreMateria);
}
