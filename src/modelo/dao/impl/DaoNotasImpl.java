package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.dao.DaoNotas;
import modelo.dto.Alumno;

public class DaoNotasImpl implements DaoNotas {

    private Conexion conexion;
    private String message;

    public DaoNotasImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public String insertarNotasFK(String idalumno, String idmateria) {

        int ida = Integer.parseInt(idalumno);
        int idm = Integer.parseInt(idmateria);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO notas( ")
                .append("idalumno,")
                .append("idmateria ")
                .append(") VALUES (?,?) ");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, ida);
            ps.setInt(2, idm);

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "cero filas insertadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();// si hay error
        }

        return message;
    }

    @Override
    public void getNotasMateria(int idmateria) {

    }

    @Override
    public String comboMateria(JComboBox cbMateria) {

        cbMateria.removeAllItems();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("nombremateria ")
                .append("FROM materia ");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = (rs.getString("nombremateria"));
                cbMateria.addItem(nombre);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String listarAlumnos(String materia, JTable tablaAlumnos, String tipoExamen) {

        limpiarTabla(tablaAlumnos);

        List<Alumno> list = null;

        DefaultTableModel model = null;
        String[] columnas = {"NOMBRE", "APELLIDOS", "DNI", "NOTA"};

        model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("* ")
                //  .append("a.id, ")
                //   .append("a.nombre,")
                //   .append("a.apellidos,")
                //   .append("a.dni ")
                .append("FROM alumno a ")
                .append("INNER JOIN notas n on (a.id = n.idalumno) ")
                .append("INNER JOIN materia m on (n.idmateria= m.id) ")
                .append("WHERE m.nombremateria=? ");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, materia);

            try (ResultSet rs = ps.executeQuery();) {

                list = new ArrayList<>();

             //   System.out.println("conectando");

                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt(1));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellidos(rs.getString("apellidos"));
                    alumno.setDni(rs.getString("dni"));
                    String PC1 = String.valueOf(rs.getDouble("PC1"));
                    String PC2 = String.valueOf(rs.getDouble("PC2"));
                    String PC3 = String.valueOf(rs.getDouble("PC3"));
                    String ExamenFinal = String.valueOf(rs.getDouble("ExamenFinal"));
                  //  String[] datos = new String[3];
                 //   System.out.println("conectando 2");
                    System.out.println(PC1 + " " + PC2 + " " + PC3);
                    String nota="";
                    
                        if( tipoExamen.equalsIgnoreCase("PC1")){
                            nota=PC1;
                        }else if(tipoExamen.equalsIgnoreCase("PC2")){
                            nota=PC2;
                        }else if(tipoExamen.equalsIgnoreCase("PC3")){
                            nota=PC3;
                        }else if(tipoExamen.equalsIgnoreCase("ExamenFinal")){
                            nota=ExamenFinal;
                        }
        
                  //  System.out.println("Entra al tbla " + datos);

                    String[] datos1 = {alumno.getNombre(), alumno.getApellidos(), alumno.getDni(), nota};
                    // System.out.println(String.valueOf(alumno.getId()) + " " + alumno.getNombre());
                    
                   // System.out.println(datos1);
                    list.add(alumno);
                    model.addRow(datos1);

                    // System.out.println("datos");
                    tablaAlumnos.setModel(model);
                }

            } catch (Exception e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return "error notas " + message;

    }

    public void limpiarTabla(JTable tabla) {
        String[] columnas = {"NOMBRE", "APELLIDOS", "DNI", "NOTA"};
        DefaultTableModel model = new DefaultTableModel(null, columnas);

        tabla.setModel(model);
        int numDatos = model.getRowCount();
        while (numDatos > 1) {
            model.removeRow(1);
        }

    }

    /*UPDATE notas set pc3 =11 
    where idalumno=4 and idmateria=9;
     */
    //INSERTAR PC1,PC2,PC O EX SEGÚN MATERIA Y ALUMNO
    @Override
    public String insertarNotasDatos(String idalumno, String idmateria, String tipoExamen, double nota) {
        int ida = Integer.parseInt(idalumno);
        int idm = Integer.parseInt(idmateria);
        StringBuilder sql = new StringBuilder();
        //sql.append("");

        if (tipoExamen.equalsIgnoreCase("PC1")) {
            sql.append("UPDATE notas SET ")
                    .append("PC1 = ? ")
                    .append("WHERE idalumno = ? AND idmateria = ? ");

        } else if (tipoExamen.equalsIgnoreCase("PC2")) {
            sql.append("UPDATE notas SET ")
                    .append("PC2 = ? ")
                    .append("WHERE idalumno = ? AND idmateria = ? ");

        } else if (tipoExamen.equalsIgnoreCase("PC3")) {
            sql.append("UPDATE notas SET ")
                    .append("PC3 = ? ")
                    .append("WHERE idalumno = ? AND idmateria = ? ");

        } else if (tipoExamen.equalsIgnoreCase("ExamenFinal")) {
            sql.append("UPDATE notas SET ")
                    .append("ExamenFinal = ? ")
                    .append("WHERE idalumno = ? AND idmateria = ? ");
        }

        try (Connection cn = conexion.conexionDB()) {

            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setDouble(1, nota);
            ps.setInt(2, ida);
            ps.setInt(3, idm);

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            message = e.getMessage() + " erro notas";
        }

        return message;
    }

}
