package controlador;

import static controlador.Controlador.docente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.dao.DaoAlumno;
import modelo.dao.DaoMateria;
import modelo.dao.DaoNotas;
import modelo.dao.impl.DaoAlumnoImpl;
import modelo.dao.impl.DaoMateriaImpl;
import modelo.dao.impl.DaoNotasImpl;
import modelo.dto.Notas;
import vista.MenuPrincipal;
import vista.Registro_Nota;

public class ControladorNotas implements ActionListener, ItemListener, MouseListener {

    DaoNotas daoNotas;

    Registro_Nota vistaRegistroNota;

    //DAOS PARA EL REGISTRO DE NOTAS
    DaoAlumno daoAlumno;
    DaoMateria daoMateria;

    //DTO
    Notas notas = null;

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    public ControladorNotas(DaoNotas daoNotas, Registro_Nota registroNota) {
        this.daoNotas = daoNotas;
        this.vistaRegistroNota = registroNota;
        eventos();
    }

    public void iniciar() {
        vistaRegistroNota.setVisible(true);
        vistaRegistroNota.setLocationRelativeTo(null);
        vistaRegistroNota.setResizable(false);

        vistaRegistroNota.txtNombre.setEditable(false);
        vistaRegistroNota.txtApellidos.setEditable(false);
        vistaRegistroNota.txtdni.setEditable(false);

        //LLENAR COMBOBOX
        daoNotas.comboMateria(vistaRegistroNota.comboMateria);

    }

    public void eventos() {
        this.vistaRegistroNota.btnRegresar.addActionListener(this);
        this.vistaRegistroNota.btnGuardar.addActionListener(this);
        this.vistaRegistroNota.comboMateria.addItemListener(this);
        this.vistaRegistroNota.btnExportarTabla.addActionListener(this);

        this.vistaRegistroNota.tablaAsistencia.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //BOTON GUARDAR
        if (e.getSource() == vistaRegistroNota.btnGuardar) {

            daoNotas = new DaoNotasImpl();
            daoMateria = new DaoMateriaImpl();
            daoAlumno = new DaoAlumnoImpl();

            String tipoExamen = vistaRegistroNota.comboTipoExamen.getSelectedItem().toString();
            String materia = vistaRegistroNota.comboMateria.getSelectedItem().toString();
            double nota = Double.parseDouble(vistaRegistroNota.txtNota.getText());
            String ida = daoAlumno.getIdAlumno(vistaRegistroNota.txtdni.getText());
            String idm = daoMateria.getIdMateria(materia);

            String res = daoNotas.insertarNotasDatos(ida, idm, tipoExamen, nota);
            if (res == null) {
                JOptionPane.showMessageDialog(null, "Nota registrada");
            }
            //  JOptionPane.showMessageDialog(null,"IdAlumno "+ ida +", IdMateria "+ idm +"Materia"+ materia);

            //    JOptionPane.showMessageDialog(null, vistaRegistroNota.txtNota.getText());
            //   JOptionPane.showMessageDialog(null, res);
            //   System.out.println(nota);
        }

        //BOTON VOLVER
        if (e.getSource() == vistaRegistroNota.btnRegresar) {
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vistaRegistroNota.dispose();
        }

        //BOTON EXPORTAR TABLA DE NOTAS DE UN CURSO
        if (e.getSource() == vistaRegistroNota.btnExportarTabla) {
            notas = new Notas();
            String materia = (String) vistaRegistroNota.comboMateria.getSelectedItem();
            String prueba = (String) vistaRegistroNota.comboTipoExamen.getSelectedItem();
            
            //si el jtable esta vacio
            if (vistaRegistroNota.tablaAsistencia.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "Elija una materia que tenga alumnos");
            } else {
                //JOptionPane.showMessageDialog(null, "Exportar tabla");
                notas.setMateria(materia);
                notas.setPrueba(prueba);
                daoNotas.exportarNotas(notas, vistaRegistroNota, docente);
            }

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //To change body of generated methods, choose Tools | Templates.

        if (e.getSource() == vistaRegistroNota.comboMateria) {

            daoNotas = new DaoNotasImpl();
            String tipoExamen = vistaRegistroNota.comboTipoExamen.getSelectedItem().toString();

            String res = daoNotas.listarAlumnos(vistaRegistroNota.comboMateria.getSelectedItem().toString(), vistaRegistroNota.tablaAsistencia, tipoExamen);
            //System.out.println(res);
            //JOptionPane.showMessageDialog(null, res);
            // JOptionPane.showMessageDialog(null, tipoExamen);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        if (e.getClickCount() > 1) {

            int seleccion = vistaRegistroNota.tablaAsistencia.getSelectedRow();
            String nombre, apellido, dni;

            nombre = String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 0));
            apellido = String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 1));
            dni = String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 2));

            System.out.println("item" + seleccion);

            vistaRegistroNota.txtNombre.setText(nombre);
            vistaRegistroNota.txtApellidos.setText(apellido);
            vistaRegistroNota.txtdni.setText(dni);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

}
