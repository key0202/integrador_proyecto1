package controlador;

import static controlador.Controlador.docente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.dao.DaoAlumno;
import modelo.dao.DaoMateria;
import modelo.dao.DaoNotas;
import modelo.dao.impl.DaoAlumnoImpl;
import modelo.dao.impl.DaoMateriaImpl;
import modelo.dao.impl.DaoNotasImpl;
import modelo.dto.Alumno;
import vista.MenuPrincipal;
import vista.Registro_Alumno;

public class ControladorAlumno implements ActionListener {

    private DaoAlumno daoAlumno;//dao
    private Registro_Alumno vista_alumno;//vista

    //DAOS PARA CREAR LA RELACION ENTRE MATERIA Y NOTAS
    private DaoMateria daoMateria;
    private DaoNotas daoNotas;
    public String materia;
    Alumno alumno = null;

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    public ControladorAlumno(Registro_Alumno vista_alumno, DaoAlumno daoAlumno) {
        this.daoAlumno = daoAlumno;
        this.vista_alumno = vista_alumno;
        eventos();

    }

    //metodo para iniciar la vista del formulario Registro de Alumno y listar combo box
    public void iniciar() {
        vista_alumno.setVisible(true);
        vista_alumno.setLocationRelativeTo(null);
        vista_alumno.setResizable(false);
        daoAlumno.comboBoxMaterias(vista_alumno, docente);
    }

    //Iniciar los eventos de los botones del formulario Registro de Alumno    
    public void eventos() {
        this.vista_alumno.btnVolver.addActionListener(this);
        this.vista_alumno.btnAgregar.addActionListener(this);

    }

    //Limpiar casillas
    public void limpiar() {
        vista_alumno.txtNombre.setText("");
        vista_alumno.txtApellidos.setText("");
        vista_alumno.txtDNI.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Boton agregar de la vista Registrar Alumno
        if (e.getSource() == vista_alumno.btnAgregar) {
            alumno = new Alumno();
            alumno.setNombre(vista_alumno.txtNombre.getText().trim());
            alumno.setApellidos(vista_alumno.txtApellidos.getText().trim());
            alumno.setDni(vista_alumno.txtDNI.getText().trim());
            alumno.setGenero(vista_alumno.comboGenero.getSelectedItem().toString());
            //  alumno.setFechaNacimiento(vista_alumno.fechaNacimiento.getDate());
            
            //obtiene la materia del combobox de la vista Registro_Alumno
            materia = vista_alumno.comboMateria.getSelectedItem().toString();
            daoMateria = new DaoMateriaImpl();

            if (daoAlumno.existeAlumno(alumno)) { 
                
                daoNotas = new DaoNotasImpl();
                //Ahora se debe crear la relación entre Alumno y Materia a través de la tabla Notas
                String idalumno = daoAlumno.getIdAlumno(alumno.getDni());
                String idmateria = daoMateria.getIdMateria(materia);

                String message = daoNotas.insertarNotasFK(idalumno, idmateria);

                System.out.println("IDAlumno es" + idalumno);
                System.out.println("IDMateria es" + idmateria);

                JOptionPane.showMessageDialog(null, "Alumno existente Agregado");

            } else {
                
                String res = daoAlumno.insertarAlumno(alumno);
               // System.out.println("Es " + res);
              //  System.out.println("Materia " + materia);

                //Si res es nulo significa que ha sido insertado de manera correcta 
                if (res == null) {
                    //daoMateria y daoNotas para realizar la tabla Notas 
                    daoMateria = new DaoMateriaImpl();
                    daoNotas = new DaoNotasImpl();
                    //Ahora se debe crear la relación entre Alumno y Materia a través de la tabla Notas
                    String idalumno = daoAlumno.getIdAlumno(alumno.getDni());
                    String idmateria = daoMateria.getIdMateria(materia);

                    String message = daoNotas.insertarNotasFK(idalumno, idmateria);

                    System.out.println("IDAlumno es" + idalumno);
                    System.out.println("IDMateria es" + idmateria);

                    JOptionPane.showMessageDialog(null, "Alumno Agregado");

                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar");
                }
            }

        }

        
        if (e.getSource() == vista_alumno.btnLimpiar) {
            limpiar();
        }

        //Boton Volver de la vista Registro Alumno     
        if (e.getSource() == vista_alumno.btnVolver) {
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vista_alumno.dispose();
        }
    }

}
