

package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.dto.Alumno;
import modelo.dto.Materia;


public class AlumnoDAO {

    private final Conexion conectaDb;
    private String message;

    public AlumnoDAO(Conexion conectaDb) {
        this.conectaDb = new Conexion();
    }


    public String alumnoIns(Alumno alumno) {

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

            ps.setString(1, alumno.getNombre());
            ps.setString(1, alumno.getApellido());
            ps.setString(1, alumno.getDni());
            ps.setString(1, alumno.getGenero());
            
    

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
