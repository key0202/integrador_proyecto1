

package modelo.dto;

public class Notas {

    private int id;
    private double nota1;
    private double nota2;
    private double nota3;
    private double ef;
    private double promedio;
    private String cod_profesor;
    private String cod_alumno;
    
    

    public Notas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getEf() {
        return ef;
    }

    public void setEf(double ef) {
        this.ef = ef;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    

    @Override
    public String toString() {
        return "Notas{" + "id=" + id + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + ", ef=" + ef + ", promedio=" + promedio + '}';
    }

    
   
    
    
    
    
    
}
