package com.mycompany.electronicagaletto.vista;

import com.mycompany.electronicagaletto.ElectronicaGaletto;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.Pago;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CuentasClientes extends javax.swing.JPanel {
    private TableRowSorter<DefaultTableModel> sorter;
    ControladoraLogica control = null;
    Usuario usr;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    
    public CuentasClientes( Usuario usr) {
        control = new ControladoraLogica();
        initComponents();
        initStyles();
        this.usr=usr;
        getClienteCmb(cmbCliente);
        autocompletarCmb();
        cargarTabla();
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
       
    }
      private void getClienteCmb(JComboBox cmbCliente){
        controlPersis.getClienteCmb(cmbCliente);
    }
     private void autocompletarCmb() {
        AutoCompleteDecorator.decorate(cmbCliente);
    }
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Fecha", "Identificador", "Descripción", "Debe", "Haber"};
    datosTabla.setColumnIdentifiers(titulos);
    //Cliente elegido
    Cliente idCliente = (Cliente) cmbCliente.getSelectedItem();
     //traer datos desde la base
    List <Venta> listaVentas = control.traerVentas(idCliente);
    List <Pago> listaPagos = control.traerPagos(idCliente);
    List <ItemDevolucion> listaItemDevoluciones = control.traerItemDevoluciones(idCliente);
    double debe=0; 
    double haber=0; 
    double saldo;
        //recorrer la lista y mostrar datos en la tabla
        if (!listaVentas.isEmpty()){
            for (Venta ven : listaVentas) {
               Date fecha1= ven.getFechaVenta();
               SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
               String fecha = f.format(fecha1);
                Object[] objeto = {fecha, ven.getIdVenta(), "Venta", ven.getTotal(),
                " "};
                debe=debe+ven.getTotal();
            datosTabla.addRow(objeto);
            }     
        }
        if (!listaPagos.isEmpty()){
            for (Pago pa : listaPagos) {
               Date fecha1= pa.getFechaPago();
               SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
               String fecha = f.format(fecha1);
                Object[] objeto = {fecha, pa.getIdPago(), "Pago", " ",
                pa.getMonto()};
                haber=haber+pa.getMonto();
            datosTabla.addRow(objeto);
            }
        }
        saldo=debe-haber;
        String sald = String.valueOf(saldo);
        txtSaldoTotal.setText(sald);
    tablaCuenta.setModel(datosTabla);
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tablaCuenta.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
        tablaCuenta.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
        tablaCuenta.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
        tablaCuenta.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tablaCuenta.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
    tablaCuenta.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tablaCuenta.setRowSorter(sorter);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuenta = new javax.swing.JTable();
        btnPago = new javax.swing.JButton();
        cmbCliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtSaldoTotal = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Cuentas corrientes de clientes ");

        tablaCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaCuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCuenta);

        btnPago.setBackground(new java.awt.Color(13, 71, 161));
        btnPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPago.setForeground(new java.awt.Color(255, 255, 255));
        btnPago.setText("Registrar pago");
        btnPago.setBorder(null);
        btnPago.setBorderPainted(false);
        btnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoActionPerformed(evt);
            }
        });

        cmbCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Saldo:");

        txtSaldoTotal.setEditable(false);
        txtSaldoTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSaldoTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtSaldoTotal.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addGap(515, 515, 515))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPago, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSaldoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtSaldoTotal)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
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

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
        //Verificar q la tabla no este vacia
       /* if (tablaCuenta.getRowCount()>0){
            //verificar q haya seleccionado 1
            if (tablaCuenta.getSelectedRow()!=-1){
              //llamar a nuevo jpanel pasando el id del objeto seleccionado
              int id = Integer.parseInt(String.valueOf(tablaCuenta.getValueAt(tablaCuenta.getSelectedRow(),0)));
          
        control.editarEstadoCliente(id);
                
        JOptionPane optionPane = new JOptionPane("Se modificó correctamente");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Guardado Exitoso");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        ElectronicaGaletto.ShowJPanel(new CuentasClientes( usr));
            }
        }*/
    }//GEN-LAST:event_btnPagoActionPerformed

    private void cmbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClienteActionPerformed
        cargarTabla();
    }//GEN-LAST:event_cmbClienteActionPerformed
    /*private void filtrar(){
        String texto = txtBuscar.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPago;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuenta;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtSaldoTotal;
    // End of variables declaration//GEN-END:variables

}
