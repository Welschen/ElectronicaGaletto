package com.mycompany.electronicagaletto.vista;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.Pago;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;


public class CuentasClientes extends javax.swing.JPanel {
    Date fechaActual = new Date();
    private TableRowSorter<DefaultTableModel> sorter2;
    ControladoraLogica control = null;
    Usuario usr;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    Cliente cliente=null;
    String sald; 
    DecimalFormat df = new DecimalFormat("0.00");
    public CuentasClientes( Usuario usr) {
        control = new ControladoraLogica();
        initComponents();
        initStyles();
        this.usr=usr;
        cargarTablaClientes();
        
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = f.format(fechaActual);
        lblSaldoFecha.setText("Saldo al "+fecha+":");
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtCliente.putClientProperty("JTextField.placeholderText", "Ingrese el nombre, apellido o DNI del cliente");
        txtSaldoTotal.putClientProperty("JTextField.placeholderText", "$");
    }
    private void cargarTablaClientes(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador", "Apellido", "Nombre", "DNI"};
    datosTabla.setColumnIdentifiers(titulos);
     //traer datos desde la base
    List <Cliente> listaCliente = control.traerClientesActivo();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaCliente!=null){
        for (Cliente cli : listaCliente) {
            Object[] objeto = {cli.getIdCliente(), cli.getApellido(), cli.getNombre(), cli.getDni()};
            
            datosTabla.addRow(objeto);
        }   
    }
    tablaClientes.setModel(datosTabla);
    
    JTableHeader thead = tablaClientes.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tablaClientes.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        //tablaClientes.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        //tablaClientes.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
        tablaClientes.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
    tablaClientes.setAutoCreateRowSorter(true);
    sorter2 = new TableRowSorter<>(datosTabla);
    tablaClientes.setRowSorter(sorter2);
    }
    
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Fecha", "Identificador", "Descripción", "Debe ($)", "Haber ($)"};
    datosTabla.setColumnIdentifiers(titulos);
     //traer datos desde la base
    List <Venta> listaVentas = control.traerVentas(cliente);
    List <Pago> listaPagos = control.traerPagos(cliente);
    List <ItemDevolucion> listaItemDevoluciones = control.traerItemDevoluciones(cliente);
    double debe=0; 
    double haber=0; 
    double saldo;
        //recorrer la lista y mostrar datos en la tabla
        if (!listaVentas.isEmpty()){
            for (Venta ven : listaVentas) {
               Date fecha1= ven.getFechaVenta();
               SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
               String fecha = f.format(fecha1);
               String con =df.format(ven.getTotal());
               String sin=con.replace(',', '.');
                Object[] objeto = {fecha, ven.getIdVenta(), "Venta", sin,
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
               String con =df.format(pa.getMonto());
               String sin=con.replace(',', '.');
                Object[] objeto = {fecha, pa.getIdPago(), "Pago", " ",
                sin};
                haber=haber+pa.getMonto();
            datosTabla.addRow(objeto);
            }
        }
       if (!listaItemDevoluciones.isEmpty()){
            for (ItemDevolucion idev: listaItemDevoluciones) {
               Date fecha1= idev.getDevo().getFechaDevolucion();
               SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
               String fecha = f.format(fecha1);
               String con =df.format(idev.getPrecioDevol());
               String sin=con.replace(',', '.');
                Object[] objeto = {fecha, idev.getDevo().getIdDevolucion(), "Devolución"," ",
                sin};
                haber=haber+idev.getPrecioDevol();
            datosTabla.addRow(objeto);
            }     
        }
        saldo=debe-haber;
      //  sald = String.valueOf(saldo);
        String con =df.format(saldo);
               sald=con.replace(',', '.');
        txtSaldoTotal.setText("$ "+sald);
    tablaCuenta.setModel(datosTabla);
    
    JTableHeader thead = tablaCuenta.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tablaCuenta.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
        tablaCuenta.getColumnModel().getColumn(1).setCellRenderer(alinearDerecha);
       // tablaCuenta.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
        tablaCuenta.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tablaCuenta.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tablaCuenta.setAutoCreateRowSorter(true);
    //sorter = new TableRowSorter<>(datosTabla);
    //tablaCuenta.setRowSorter(sorter);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuenta = new javax.swing.JTable();
        btnPago = new javax.swing.JButton();
        lblSaldoFecha = new javax.swing.JLabel();
        txtSaldoTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        btnGenePDF = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Cuentas corrientes de clientes ");

        tablaCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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
        tablaCuenta.setRowHeight(22);
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

        lblSaldoFecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSaldoFecha.setForeground(new java.awt.Color(0, 0, 0));
        lblSaldoFecha.setText("Saldo al (fecha):");

        txtSaldoTotal.setEditable(false);
        txtSaldoTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSaldoTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtSaldoTotal.setFocusable(false);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Cliente:");

        txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        tablaClientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaClientes);

        btnGenePDF.setBackground(new java.awt.Color(13, 71, 161));
        btnGenePDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGenePDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenePDF.setText("Generar reporte PDF");
        btnGenePDF.setBorder(null);
        btnGenePDF.setBorderPainted(false);
        btnGenePDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenePDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addGap(489, 489, 489))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblSaldoFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSaldoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPago, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnGenePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente)))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtSaldoTotal)
                    .addComponent(lblSaldoFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPago, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
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
        ShowJPanel(new PagoCuentaCliente(usr, cliente,sald));
    }//GEN-LAST:event_btnPagoActionPerformed
       private void filtrar2(){
        String texto = txtCliente.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter2.setRowFilter(RowFilter.regexFilter(filtro));
    }
    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        int f = tablaClientes.getSelectedRow();
        
        String client = tablaClientes.getValueAt(f, 0).toString()+" - "+
        tablaClientes.getValueAt(f, 1).toString()+" "+
        tablaClientes.getValueAt(f, 2).toString()+" - "+
        tablaClientes.getValueAt(f, 3).toString();
        txtCliente.setText(client);
        
        int id =(int) tablaClientes.getValueAt(f, 0);
        cliente = control.traerCliente(id);
        cargarTabla();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        filtrar2();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnGenePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenePDFActionPerformed
        Document documento = new Document();
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = f.format(fechaActual);
            
            try{
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reportes/CuentaCliente.pdf"));
                
                Image header = Image.getInstance("src/main/resources/img/LogoGalettoPNG.png");
                header.scaleToFit(350, 800);
                header.setAlignment(Chunk.ALIGN_CENTER);
                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
               // parrafo.add("Fecha: "+fecha+"\n\n");
                parrafo.setFont(FontFactory.getFont("Tahoma",18,Font.BOLD,BaseColor.DARK_GRAY));
                parrafo.add("\nCuenta corriente de: "+cliente.getNombre()+" "+cliente.getApellido()+"\n\n");
                
                
                documento.open();
                
                documento.add(header);
                documento.add(parrafo);
                
                PdfPTable tabla = new PdfPTable(4);
                 tabla.addCell("Fecha");
                 tabla.addCell("Descripción");
                 tabla.addCell("Debe ($)" );
                 tabla.addCell("Haber ($)");
                 
                 DefaultTableModel modelo = (DefaultTableModel)tablaCuenta.getModel();
               
                for(int i=0; i<modelo.getRowCount(); i++) {
                    tabla.addCell(modelo.getValueAt(i, 0).toString());
                    tabla.addCell(modelo.getValueAt(i, 2).toString());
                    tabla.addCell(modelo.getValueAt(i, 3).toString());
                    tabla.addCell(modelo.getValueAt(i, 4).toString());
                }
                documento.add(tabla);
                Paragraph parra = new Paragraph();
                parra.setAlignment(Paragraph.ALIGN_RIGHT);
                 parra.setFont(FontFactory.getFont("Tahoma",16,Font.BOLD,BaseColor.BLACK));
                parra.add("\nSaldo al "+fecha+": "+txtSaldoTotal.getText()+"\n\n");
                documento.add(parra);
                
                documento.close();
                JOptionPane.showMessageDialog(null, "Reporte creado");
           
            }catch (DocumentException | FileNotFoundException e){
              System.out.println("Error en PDF " + e);
        }catch (IOException e){
            System.out.println("Error en la imagen " + e);
        }
    }//GEN-LAST:event_btnGenePDFActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenePDF;
    private javax.swing.JButton btnPago;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblSaldoFecha;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaCuenta;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtSaldoTotal;
    // End of variables declaration//GEN-END:variables

}
