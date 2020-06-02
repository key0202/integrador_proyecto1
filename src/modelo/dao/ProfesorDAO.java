
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import modelo.Conexion;
import modelo.dto.Profesor;


public class ProfesorDAO {

     private final Conexion conectaDb;
    private String message;

    public ProfesorDAO() {
        this.conectaDb = new Conexion();
    }
    
    
    public Profesor profesorGet(String usuario , String password) {
        Profesor profesor = new Profesor();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("apellidos,")
                .append("usuario,")
                .append("password,")
                .append("dni ")
                .append("FROM profesor ")
                .append("WHERE usuario = ? AND password=?");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, usuario);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    profesor.setId(rs.getInt(1));
                    profesor.setNombre(rs.getString(2));
                    profesor.setApellidos(rs.getString(3));
                    profesor.setUsuario(rs.getString(4));
                    profesor.setPassword(rs.getString(5));
                    profesor.setDni(rs.getString(6));

                } else {
                    profesor = null;
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return profesor;
    }

    
    
    
    
}
