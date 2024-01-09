package com.mycompany.electronicagaletto.igu;

import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CargarGrupo extends javax.swing.JFrame {
    ControladoraLogica control = new ControladoraLogica();
    public CargarGrupo() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBene = new javax.swing.JTextField();
        txtBajoStock = new javax.swing.JTextField();
        txtNomGrupo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Electronica Galetto");

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre del grupo:");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel2.setText("Beneficio (Ej: 0.5 = 50%):");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jLabel3.setText("Stock bajo:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Cargar nuevo grupo");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));
        bg.add(txtBene, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 60, -1));
        bg.add(txtBajoStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 90, -1));
        bg.add(txtNomGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 310, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        bg.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 250, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String txt0 = txtBene.getText();
        double bene = Double.parseDouble(txt0);
        String txt2 = txtBajoStock.getText();
        int bstock = Integer.parseInt(txt2);
       
        control.guardar(bene, bstock,txtNomGrupo.getText());
        
        JOptionPane optionPane = new JOptionPane("Se guardo correctamente");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Guardado Exitoso");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        
        txtBajoStock.setText("");
        txtBene.setText("");
        txtNomGrupo.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtBajoStock;
    private javax.swing.JTextField txtBene;
    private javax.swing.JTextField txtNomGrupo;
    // End of variables declaration//GEN-END:variables
}
