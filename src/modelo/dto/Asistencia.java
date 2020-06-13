package modelo.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Asistencia {

    private int id;
    private int cod_alumno;
    private int cod_curso;
    private LocalDate fecha;
    private String tipo;
    private String materia;
    private String nombre;
    private String apellidos;
    private String dni;

    public Asistencia() {
    }

    public Asistencia(int id, int cod_alumno, int cod_curso, LocalDate fecha, String tipo) {
        this.id = id;
        this.cod_alumno = cod_alumno;
        this.cod_curso = cod_curso;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_alumno() {
        return cod_alumno;
    }

    public void setCod_alumno(int cod_alumno) {
        this.cod_alumno = cod_alumno;
    }

    public int getCod_curso() {
        return cod_curso;
    }

    public void setCod_curso(int cod_curso) {
        this.cod_curso = cod_curso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getStringFecha() {

        //dar formato a fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String strFecha = formato.format(fecha);

        return strFecha;
    }

}
