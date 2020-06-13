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
import modelo.dto.Alumno;
import vista.Lista_Alumnos;
import vista.MenuPrincipal;
import vista.Registro_Alumno;

public class ControladorAlumno implements ActionListener,ItemListener, MouseListener {

    private DaoAlumno daoAlumno;//dao
    private Registro_Alumno vista_alumno;//vista
    
    //VISTA  LISTA_ALUMNOS PARA LISTAR TODOS LOS ALUMNOS    
    private Lista_Alumnos listaAlumnos = new Lista_Alumnos();

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
        this.vista_alumno.btnLimpiar.addActionListener(this);
        this.vista_alumno.comboMateria.addItemListener(this);
        
        //Ir al Jdialog Lista ALumnos
        this.vista_alumno.btnListaAlumnos.addActionListener(this); 
        
        //setear los datos de ls tabla de la vista Lista Alumnos a Registro Alumnos
        this.listaAlumnos.tablaListaAlumnos.addMouseListener(this);
    }

    //Limpiar casillas
    public void limpiar() {
        vista_alumno.txtNombre.setText("");
        vista_alumno.txtApellidos.setText("");
        vista_alumno.txtDNI.setText("");
    }
    
    

    
    //EVENTOS DE LOS BOTONES DE REGISTRO ALUMNO
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

              //  System.out.println("IDAlumno es" + idalumno);
               // System.out.println("IDMateria es" + idmateria);

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
        
        //BOTON LISTAR TODOS ALUMNOS
        if(e.getSource() == vista_alumno.btnListaAlumnos){
            
             
            listaAlumnos.setVisible(true);
            System.out.println("llamando");
            vista_alumno.setEnabled(false);
  
            daoAlumno= new DaoAlumnoImpl();
            
            daoAlumno.listarTodosAlumnos(listaAlumnos.tablaListaAlumnos); 
            
        }
        
        
        
        //BOTON LIMPIAR
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
    
    
    
        //EVENTO DEL COMBO BOX
    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getSource()== vista_alumno.comboMateria){
            
           // JOptionPane.showMessageDialog(null, "selecionado primero" + vista_alumno.comboMateria.getSelectedItem().toString());
            daoAlumno = new DaoAlumnoImpl();
           // daoAlumno.prueba(vista_alumno.tablaAlumnos);
            String mes =daoAlumno.listarAlumnos(vista_alumno.comboMateria.getSelectedItem().toString(), vista_alumno.tablaAlumnos);
           // JOptionPane.showMessageDialog(null, mes);
           
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
       /* if(e.getSource() == listaAlumnos.tablaListaAlumnos ){
            System.out.println("Tabla click");
        }*/
       
       if(e.getClickCount() > 1){
          // JOptionPane.showMessageDialog(null, "Evento click");
           
           int seleccion = listaAlumnos.tablaListaAlumnos.getSelectedRow();
          // JOptionPane.showMessageDialog(null, seleccion);
           String nombre,apellido,dni;
          //EN LOS JTABLE COMIENZAN DESDE INDICE 0
           nombre=String.valueOf(listaAlumnos.tablaListaAlumnos.getValueAt(seleccion, 1)) ;
           apellido=String.valueOf(listaAlumnos.tablaListaAlumnos.getValueAt(seleccion, 2)) ;
           dni=String.valueOf(listaAlumnos.tablaListaAlumnos.getValueAt(seleccion, 3)) ;
           
           //JOptionPane.showMessageDialog(null, nombre);
           listaAlumnos.dispose();
           
           vista_alumno.setEnabled(true);
           
           vista_alumno.txtNombre.setText(nombre);
           vista_alumno.txtApellidos.setText(apellido);
           vista_alumno.txtDNI.setText(dni);
           
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
