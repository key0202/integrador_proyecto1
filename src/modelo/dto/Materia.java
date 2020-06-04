
package modelo.dto;


public class Materia {
    
    private int id;
    private String nombreMateria;

    public Materia() {
    }

    public Materia(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Materia{" + "id=" + id + ", nombreMateria=" + nombreMateria + '}';
    }   

}
