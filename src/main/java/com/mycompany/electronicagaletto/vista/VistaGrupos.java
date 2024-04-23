package com.mycompany.electronicagaletto.vista;

import com.mycompany.electronicagaletto.ElectronicaGaletto;
import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class VistaGrupos extends javax.swing.JPanel {
    private TableRowSorter<DefaultTableModel> sorter;
    ControladoraLogica control = null;
    Usuario usr;
    DecimalFormat df = new DecimalFormat("0.00");
    public VistaGrupos( Usuario usr) {
        control = new ControladoraLogica();
        this.usr=usr;
        initComponents();
        initStyles();
        cargarTabla();
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el grupo a buscar.");
    }
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador","Nombre", "Stock mínimo", "Beneficio", "Estado"};
    datosTabla.setColumnIdentifiers(titulos);
     //traer datos desde la base
    List <Grupo> listaGrupos = control.traerGrupos();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaGrupos!=null){
        for (Grupo grupo : listaGrupos) {
            String con =df.format(grupo.getBeneficio());
            String sin=con.replace(',', '.');
            Object[] objeto = {grupo.getIdGrupo(),grupo.getNombreGrupo(), grupo.getBajoStock(), sin,
            grupo.getEstado()};
            
            datosTabla.addRow(objeto);
        }   
    }
    tablaGrupos.setModel(datosTabla);
    
    JTableHeader thead = tablaGrupos.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Segoe UI", Font.BOLD, 14));
    DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tablaGrupos.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
        tablaGrupos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tablaGrupos.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
        tablaGrupos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tablaGrupos.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);

    tablaGrupos.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tablaGrupos.setRowSorter(sorter);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGrupos = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        txtNuevo = new javax.swing.JButton();
        txtBajaAlta = new javax.swing.JButton();
        btnVerArtic = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Grupos");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tablaGrupos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tablaGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaGrupos);

        btnEditar.setBackground(new java.awt.Color(13, 71, 161));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.setBorder(null);
        btnEditar.setBorderPainted(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtNuevo.setBackground(new java.awt.Color(13, 71, 161));
        txtNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNuevo.setForeground(new java.awt.Color(255, 255, 255));
        txtNuevo.setText("Nuevo");
        txtNuevo.setBorder(null);
        txtNuevo.setBorderPainted(false);
        txtNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoActionPerformed(evt);
            }
        });

        txtBajaAlta.setBackground(new java.awt.Color(13, 71, 161));
        txtBajaAlta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtBajaAlta.setForeground(new java.awt.Color(255, 255, 255));
        txtBajaAlta.setText("Cambiar estado");
        txtBajaAlta.setBorder(null);
        txtBajaAlta.setBorderPainted(false);
        txtBajaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBajaAltaActionPerformed(evt);
            }
        });

        btnVerArtic.setBackground(new java.awt.Color(13, 71, 161));
        btnVerArtic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerArtic.setForeground(new java.awt.Color(255, 255, 255));
        btnVerArtic.setText("Ver artículos por grupo");
        btnVerArtic.setBorder(null);
        btnVerArtic.setBorderPainted(false);
        btnVerArtic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerArticActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(627, 627, 627))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBajaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerArtic, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBajaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerArtic, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //Verificar q la tabla no este vacia
        if (tablaGrupos.getRowCount()>0){
            //verificar q haya seleccionado 1
            if (tablaGrupos.getSelectedRow()!=-1){
              //llamar a nuevo jpanel pasando el id del objeto seleccionado
              int id = Integer.parseInt(String.valueOf(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(),0)));
              ElectronicaGaletto.ShowJPanel(new EditGrupo(id,usr));
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoActionPerformed
        ElectronicaGaletto.ShowJPanel(new AltasGrupos());
    }//GEN-LAST:event_txtNuevoActionPerformed

    private void txtBajaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBajaAltaActionPerformed
        //Verificar q la tabla no este vacia
        if (tablaGrupos.getRowCount()>0){
            //verificar q haya seleccionado 1
            if (tablaGrupos.getSelectedRow()!=-1){
              //llamar a nuevo jpanel pasando el id del objeto seleccionado
              int id = Integer.parseInt(String.valueOf(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(),0)));
              control.editarEstadoGrupo(id);
                
        JOptionPane optionPane = new JOptionPane("Se modificó correctamente");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Guardado Exitoso");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
              ElectronicaGaletto.ShowJPanel(new VistaGrupos(usr));
            }
        }
    }//GEN-LAST:event_txtBajaAltaActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnVerArticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerArticActionPerformed
         int fila = tablaGrupos.getSelectedRow();
        if(fila !=-1){
             int id =(int) tablaGrupos.getValueAt(fila, 0);
        Grupo grupo = control.traerGrupo(id);
        ShowJPanel(new VistaArticulosXGrupo(usr,grupo));
        }else{
             JOptionPane optionPane = new JOptionPane("Debe seleccionar un grupo");
        optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        }
        
    }//GEN-LAST:event_btnVerArticActionPerformed
     private void filtrar(){
        String texto = txtBuscar.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVerArtic;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaGrupos;
    private javax.swing.JLabel title;
    private javax.swing.JButton txtBajaAlta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JButton txtNuevo;
    // End of variables declaration//GEN-END:variables

}
