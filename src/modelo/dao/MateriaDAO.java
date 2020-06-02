
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.dto.Materia;


public class MateriaDAO {
    
    private final Conexion conectaDb;
    private String message;

    public MateriaDAO(Conexion conectaDb) {
        this.conectaDb = new Conexion();
    }


    public String materiaIns(Materia materia) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO perros( ")
                .append("nombre,")
                .append("dni,")
                .append("fecha_nacimiento,")
                .append("peso,")
                .append("raza ")
                .append(") VALUES (?,?,?,?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setString(1, materia.getNombreMateria());
    

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "cero filas insertadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();// si hay error
        }

        return message;
    }
}
