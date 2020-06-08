package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.dao.DaoMateria;
import modelo.dto.Materia;
import vista.Registro_Materia;

public class ControlMateria implements ActionListener {

    private DaoMateria daoMateria;//dao
    private Registro_Materia vista_materia;//vista
    private Materia materia = new Materia();//dto
    
    String docente = JOptionPane.showInputDialog(null, "Bienvenidos al modulo de registro de materias, registre su nombre").toLowerCase();

    public ControlMateria(Registro_Materia vista_materia, DaoMateria daoMateria) {
        this.vista_materia = vista_materia;
        this.vista_materia.btnAgregar.addActionListener(this);
        this.vista_materia.btnRegresar.addActionListener(this);
        this.daoMateria = daoMateria;

        this.vista_materia.setVisible(true);
    }

    //metodo para iniciar la aplicacion
    public void iniciar() {
        daoMateria.leer(vista_materia, docente);
    }
    //estos son los botones del registro de materias
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista_materia.btnAgregar) {
            String curso = vista_materia.jTextField1.getText();
            if (curso.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe agregar una materia");
            } else {
                materia.setNombreMateria(curso);
                daoMateria.agregarMateria(materia, vista_materia, docente);
            }
        }
        if(e.getSource() == vista_materia.btnRegresar){
            System.exit(0);
        }
    }

}
