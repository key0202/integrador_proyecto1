/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author DAVID
 */
public class Materias {

    private String nombreMateria;

    public Materias() {
    }

    public Materias(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

}
