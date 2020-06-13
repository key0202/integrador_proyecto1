package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DaoAsistencia;
import modelo.dto.Asistencia;
import vista.MenuPrincipal;
import vista.Registro_Asistencia;

/**
 *
 * @author DAVID
 */
public class ControladorAsistencia implements ActionListener, MouseListener {

    private DaoAsistencia daoAsistencia;
    private Registro_Asistencia vista_asistencia;
    private Asistencia asistencia = new Asistencia();

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    //obtenemos nombre de usuario
    String docente = Controlador.docente;

    public ControladorAsistencia(DaoAsistencia daoAsistencia, Registro_Asistencia vista_asistencia) {
        this.daoAsistencia = daoAsistencia;
        this.vista_asistencia = vista_asistencia;
        eventos();
    }

    //metodo para iniciar la vista del formulario Registro de Asistencia
    public void iniciar() {
        vista_asistencia.setVisible(true);
        vista_asistencia.setLocationRelativeTo(null);
        vista_asistencia.setResizable(false);
        //enviar lista de materias del profesor
        daoAsistencia.comboBoxMaterias(vista_asistencia, docente);
        //indicar fecha actual al iniciar jframe
        Date fecha = new Date();
        vista_asistencia.fechaAsistencia.setDate(fecha);
        //imputs no editables
        vista_asistencia.txtNombre.setEditable(false);
        vista_asistencia.txtApellidos.setEditable(false);
        vista_asistencia.txtDNI.setEditable(false);
    }

    //Iniciar los eventos de los botones del formulario Registro de Asistencia
    public void eventos() {
        this.vista_asistencia.btnGuardar.addActionListener(this);
        this.vista_asistencia.btnRegresar.addActionListener(this);
        this.vista_asistencia.comboMateria.addActionListener(this);
        //eventos del mouse
        this.vista_asistencia.tablaAsistencia.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista_asistencia.btnGuardar) {
            // obtener fecha del jframe
            LocalDate fecha = new java.sql.Date(vista_asistencia.fechaAsistencia.getDate().getTime()).toLocalDate();
            String materia = (String) vista_asistencia.comboMateria.getSelectedItem();
            String nombre = vista_asistencia.txtNombre.getText();
            String apellido = vista_asistencia.txtApellidos.getText();
            String dni = vista_asistencia.txtDNI.getText();
            String estado = (String) vista_asistencia.comboAsistencia.getSelectedItem();
            if (nombre.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione un alumno");
            } else {
                //JOptionPane.showMessageDialog(null, materia + "\n" + strFecha + "\n" + nombre + "\n" + apellido + "\n" + dni + "\n" + estado);
                asistencia.setFecha(fecha);
                asistencia.setMateria(materia);
                asistencia.setNombre(nombre);
                asistencia.setApellidos(apellido);
                asistencia.setDni(dni);
                asistencia.setTipo(estado);
                //Agregar para archivo txt
                daoAsistencia.agregarAsistencia(asistencia, vista_asistencia, docente);
            }

        }
        if (e.getSource() == vista_asistencia.btnRegresar) {
            //regresamos a MenuPrincipal
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vista_asistencia.dispose();
        }
        /*Elegir una materia del combobox*/
        if (e.getSource() == vista_asistencia.comboMateria) {
            String materia = (String) vista_asistencia.comboMateria.getSelectedItem();
            //JOptionPane.showMessageDialog(null, materia);
            daoAsistencia.listarAlumnosMateria(vista_asistencia, materia);
        }
    }

    //doble click en un alumno
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            int seleccion = vista_asistencia.tablaAsistencia.getSelectedRow();
            String nombre, apellido, dni;
            //EN LOS JTABLE COMIENZAN DESDE INDICE 0
            nombre = String.valueOf(vista_asistencia.tablaAsistencia.getValueAt(seleccion, 0));
            apellido = String.valueOf(vista_asistencia.tablaAsistencia.getValueAt(seleccion, 1));
            dni = String.valueOf(vista_asistencia.tablaAsistencia.getValueAt(seleccion, 2));

            vista_asistencia.txtNombre.setText(nombre);
            vista_asistencia.txtApellidos.setText(apellido);
            vista_asistencia.txtDNI.setText(dni);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
