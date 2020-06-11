
package modelo.dao;


public interface DaoNotas {
    
    public String insertarNotasFK(String idalumno, String imateria);

    
    public void getNotasMateria(int idmateria);
}
