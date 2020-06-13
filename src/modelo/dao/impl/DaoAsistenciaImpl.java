package modelo.dao.impl;

import controlador.ControladorMenu;
import static controlador.ControladorMenu.direccion;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.dao.DaoAsistencia;
import modelo.dto.Alumno;
import modelo.dto.Asistencia;
import vista.Registro_Asistencia;

public class DaoAsistenciaImpl implements DaoAsistencia {

    String username = ControladorMenu.username;
    String destino = ControladorMenu.destino;
    String direccion = ControladorMenu.direccion;

    private Conexion conexion;

    public DaoAsistenciaImpl() {
        this.conexion = new Conexion();
    }

    //obtener donde se guardaran los cambios
    public String midirectorio(String docente) {
        File directorio = new File(direccion + docente); // "C:/Users/DAVID/Desktop/materias_david"

        if (directorio.exists()) {//la carpeta del docente ya existe
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;

        } else {
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;
        }
    }

    //donde se guardaran los cambios
    public String directorioDestino(String docente, String materia, LocalDate fecha) {
        File directorio = new File(direccion + docente); // "C:/Users/DAVID/Desktop/materias_david"

        if (directorio.exists()) {//la carpeta del docente ya existe
            String midirectorio = direccion + docente + "/asistencia_" + materia + "_" + fecha + ".txt";
            return midirectorio;

        } else {
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/asistencia_" + materia + "_" + fecha + ".txt";
            return midirectorio;
        }
    }

    @Override
    public void comboBoxMaterias(Registro_Asistencia vista_asistencia, String docente) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String filename = (midirectorio(docente));
        File file = new File(filename);
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {

                String line;
                line = in.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                String curso;
                curso = st.nextToken();
                model.addElement(curso);
                vista_asistencia.comboMateria.setModel(model);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }
    }

    @Override
    public void listarAlumnosMateria(Registro_Asistencia vista_asistencia, String materia) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("nombre,")
                .append("apellidos,")
                .append("dni ")
                .append("FROM alumno a ")
                .append("INNER JOIN notas n ")
                .append("on (a.id = n.idalumno) ")
                .append("INNER JOIN materia m ")
                .append("on (n.idmateria= m.id) ")
                .append("WHERE m.nombremateria= '" + materia + "'");

        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            vista_asistencia.tablaAsistencia.setModel(modelo);
            Conexion conn = new Conexion();
            Connection con = conn.conexionDB();

            PreparedStatement ps = con.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            //resulset para la tabla
            ResultSetMetaData rsmd = rs.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();

            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("DNI");
            //modelo.addColumn("Asistencia");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }

                modelo.addRow(filas);
            }
        } catch (SQLException e) {
            System.err.println("error listarAlumnosMateria: " + e);
        }
    }

    @Override
    public void agregarAsistencia(Asistencia asistencia, Registro_Asistencia vistaAsistencia, String docente) {
        try {
            FileWriter file = new FileWriter(directorioDestino(docente, asistencia.getMateria(), asistencia.getFecha()), true);
            BufferedWriter bw = new BufferedWriter(file);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(asistencia.getStringFecha() + "|" + asistencia.getNombre() + " " + asistencia.getApellidos() + "|" + asistencia.getDni() + "|" + asistencia.getTipo());
            pw.close();

            JOptionPane.showMessageDialog(null, "Asistencia guardada en " + direccion + docente);
            //limpiar los campos
            vistaAsistencia.txtNombre.setText("");
            vistaAsistencia.txtApellidos.setText("");
            vistaAsistencia.txtDNI.setText("");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error agregarAsistencia(): " + error);
        }
    }

}
