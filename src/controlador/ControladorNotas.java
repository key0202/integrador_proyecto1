
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.dao.DaoNotas;
import modelo.dao.impl.DaoNotasImpl;
import vista.MenuPrincipal;
import vista.Registro_Nota;


public class ControladorNotas implements ActionListener,ItemListener, MouseListener{
    
    DaoNotas daoNotas;
    
    Registro_Nota vistaRegistroNota;
    
     //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    public ControladorNotas(DaoNotas daoNotas, Registro_Nota registroNota) {
        this.daoNotas = daoNotas;
        this.vistaRegistroNota = registroNota;
        eventos();
    }
    
    public void iniciar(){   
        vistaRegistroNota.setVisible(true);
        vistaRegistroNota.setLocationRelativeTo(null);
        vistaRegistroNota.setResizable(false);
        
        vistaRegistroNota.txtNombre.setEditable(false);
        vistaRegistroNota.txtApellidos.setEditable(false);
        vistaRegistroNota.txtdni.setEditable(false);
        
        //LLENAR COMBOBOX
        daoNotas.comboMateria(vistaRegistroNota.comboMateria);
        
        
        
    }
    
    public void eventos(){
        this.vistaRegistroNota.btnRegresar.addActionListener(this);
        this.vistaRegistroNota.btnGuardar.addActionListener(this);
        this.vistaRegistroNota.comboMateria.addItemListener(this);
        
        this.vistaRegistroNota.tablaAsistencia.addMouseListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTON GUARDAR
        if(e.getSource()== vistaRegistroNota.btnGuardar){
            System.err.println("Prueba btn ");
        }
        
        //BOTON VOLVER
        if(e.getSource()== vistaRegistroNota.btnRegresar ){
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vistaRegistroNota.dispose();
        }
            
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        
        if(e.getSource()==vistaRegistroNota.comboMateria){
            
            daoNotas = new DaoNotasImpl();
            
            String res = daoNotas.listarAlumnos(vistaRegistroNota.comboMateria.getSelectedItem().toString(), vistaRegistroNota.tablaAsistencia);
            System.out.println("res");
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        if(e.getClickCount()>1){
            
            int seleccion = vistaRegistroNota.tablaAsistencia.getSelectedRow();
             String nombre,apellido,dni;
             
             
             nombre=String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 0)) ;
             apellido=String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 1)) ;
           dni=String.valueOf(vistaRegistroNota.tablaAsistencia.getValueAt(seleccion, 2)) ;
             
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
