
package vista;


public class Registro_Alumno extends javax.swing.JDialog {


    public Registro_Alumno(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Registro_Alumno() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboMateria = new javax.swing.JComboBox<>();
        btnExportarTabla = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnListaAlumnos = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de Alumnos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 70));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaAlumnos.setBackground(new java.awt.Color(0, 204, 255));
        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "DNI"
            }
        ));
        tablaAlumnos.setGridColor(new java.awt.Color(0, 0, 0));
        tablaAlumnos.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tablaAlumnos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 580, 200));

        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 72, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 104, 130, -1));

        jLabel3.setText("Apellidos");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 157, -1, -1));
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 177, 135, -1));

        jLabel4.setText("DNI");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 227, -1, -1));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 247, 135, -1));

        jLabel6.setText("Genero");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        jPanel1.add(comboGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 110, -1));

        btnLimpiar.setText("Limpiar");
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 140, 38));

        btnAgregar.setText("Agregar");
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 140, 37));

        jLabel7.setText("Curso");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        comboMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMateriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 110, -1));

        btnExportarTabla.setText("Exportar Tabla");
        jPanel1.add(btnExportarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 140, 40));

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar.png"))); // NOI18N
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 110, 70));

        btnListaAlumnos.setText("...");
        jPanel1.add(btnListaAlumnos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 30, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 830, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMateriaActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro_Alumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_Alumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_Alumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_Alumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Registro_Alumno dialog = new Registro_Alumno(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnExportarTabla;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnListaAlumnos;
    public javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> comboGenero;
    public javax.swing.JComboBox<String> comboMateria;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaAlumnos;
    public javax.swing.JTextField txtApellidos;
    public javax.swing.JTextField txtDNI;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
