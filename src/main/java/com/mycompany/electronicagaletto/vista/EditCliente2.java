
package com.mycompany.electronicagaletto.vista;

import com.mycompany.electronicagaletto.ElectronicaGaletto;
import com.mycompany.electronicagaletto.ElectronicaGaletto1;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Localidad;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class EditCliente2 extends javax.swing.JPanel {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    ControladoraLogica control = null;
    int id;
    Cliente cli;
    Usuario usr;
    public EditCliente2(int id, Usuario usr) {
        control = new ControladoraLogica();
        this.id=id;
        initComponents();
        initStyles();
        getLocalidadCmb(cmbLocalidad);
        cargarDatos(id);
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        //txtBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el artículo a buscar.");
    }
     private void getLocalidadCmb(JComboBox cmbLocalidad){
        controlPersis.getLocalidadCmb(cmbLocalidad);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        txtDepto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPiso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumCalle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbLocalidad = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(817, 528));
        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(817, 528));
        jPanel1.setPreferredSize(new java.awt.Dimension(817, 528));

        title.setText("Editar cliente");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nombre");

        txtNom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Apellido");

        txtApe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeKeyTyped(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("DNI");

        txtDNI.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Email ");

        txtMail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMailKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Teléfono");

        txtTel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtDepto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Departamento");

        txtPiso.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPisoActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Piso ");

        txtNumCalle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNumCalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCalleKeyTyped(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Número");

        txtCalle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Calle");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Localidad");

        cmbLocalidad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(13, 71, 161));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(181, 181, 181))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(250, 250, 250))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(217, 217, 217))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addGap(250, 250, 250))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNom, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApe, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDNI, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMail, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTel, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(25, 25, 25)))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(250, 250, 250)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addGap(268, 268, 268))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(268, 268, 268))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(252, 252, 252))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbLocalidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNumCalle, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCalle, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPiso)
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtDepto))
                                .addGap(64, 64, 64)))
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator1)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addComponent(txtNumCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(2, 2, 2))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalleActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if(txtApe.getText().isEmpty() || txtCalle.getText().isEmpty() || txtDNI.getText().isEmpty() ||
                txtDepto.getText().isEmpty() || txtMail.getText().isEmpty() || txtNom.getText().isEmpty() ||
                txtNumCalle.getText().isEmpty() || txtPiso.getText().isEmpty() || txtTel.getText().isEmpty()){
            JOptionPane optionPane = new JOptionPane("Debe completar todos los campos!");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Atención");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);   
        }else{
        //direcc
        String calle = txtCalle.getText();
        String num = txtNumCalle.getText();
        int nume = Integer.parseInt(num);
        char dpto = txtDepto.getText().charAt(0);
        String pis = txtPiso.getText();
        int piso = Integer.parseInt(pis);
        //cliente
        String nom = txtNom.getText();
        String ape = txtApe.getText();
        String docu = txtDNI.getText();
        int doc = Integer.parseInt(docu);
        String tel = txtTel.getText();    
        String mail = txtMail.getText();
        
        control.editarCliente(cli,nom, ape, doc, tel, mail,calle, nume, dpto, piso, (Localidad) cmbLocalidad.getSelectedItem());
                
        JOptionPane optionPane = new JOptionPane("Se guardo correctamente");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Guardado Exitoso");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        
        ElectronicaGaletto1.ShowJPanel1(new VistaClientes1(usr));
         }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoActionPerformed

    private void txtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyTyped
         if(!(Character.isLetter(evt.getKeyChar())) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)){
            evt.consume();
        }
    }//GEN-LAST:event_txtNomKeyTyped

    private void txtApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeKeyTyped
         if(!(Character.isLetter(evt.getKeyChar())) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)){
            evt.consume();
        }
    }//GEN-LAST:event_txtApeKeyTyped

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
         int key= evt.getKeyChar();
        boolean numero = key>= 48 && key<=57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtNumCalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCalleKeyTyped
         int key= evt.getKeyChar();
        boolean numero = key>= 48 && key<=57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtNumCalleKeyTyped
    public boolean isEmail(String correo){
        Pattern pat=null;
        Matcher mat=null;
        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        mat = pat.matcher(correo);
        return mat.find();
    }
    private void txtMailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailKeyTyped
         if (isEmail(txtMail.getText())){
            
        }else{
            JOptionPane.showMessageDialog(null,"El email ingresado no es válido","Validar mail"
            ,JOptionPane.INFORMATION_MESSAGE);
            txtMail.requestFocus();
        }
    }//GEN-LAST:event_txtMailKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbLocalidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDepto;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNumCalle;
    private javax.swing.JTextField txtPiso;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos(int id) {
        this.cli = control.traerCliente(id);
        
        txtApe.setText(cli.getApellido());
        txtCalle.setText(cli.getDomi().getCalle());
        txtDNI.setText(Integer.toString(cli.getDni()));
        txtDepto.setText(Character.toString(cli.getDomi().getDepto()));
        txtMail.setText(cli.getEmail());
        txtNom.setText(cli.getNombre());
        txtNumCalle.setText(Integer.toString(cli.getDomi().getNumCalle()));
        txtPiso.setText(Integer.toString(cli.getDomi().getPiso()));
        txtTel.setText(cli.getTelefono());
        cmbLocalidad.setSelectedIndex(0);
    }

}
