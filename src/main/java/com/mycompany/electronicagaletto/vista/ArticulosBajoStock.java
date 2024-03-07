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
import com.mycompany.electronicagaletto.ElectronicaGaletto;
import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Devolucion;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ArticulosBajoStock extends javax.swing.JPanel {
    Date fechaActual = new Date();
    private TableRowSorter<DefaultTableModel> sorter2;
    ControladoraLogica control = null;
    Usuario usr;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    Cliente cliente=null;
    String sald; 
    DecimalFormat df = new DecimalFormat("0.00");
    public ArticulosBajoStock( Usuario usr) {
        control = new ControladoraLogica();
        initComponents();
        initStyles();
        this.usr=usr;
       cargarTabla();
    }

    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtCliente.putClientProperty("JTextField.placeholderText", "Ingrese el artículo");
        
    }
   
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false;
            } 
          
    };
     String titulos[] = {"Identificador", "Nombre", "Precio" ,"Costo","Código de barras","Stock", "Stock mínimo"};
        datosTabla.setColumnIdentifiers(titulos);
    //traer datos desde la base
    List <Articulo> listaArticulos = control.traerArticulos();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaArticulos!=null){
        
        for (Articulo arti : listaArticulos) {
            if(arti.isEstado() && arti.getStock()<=arti.getGrupo().getBajoStock()){
             String con =df.format(arti.getPrecio());
            String sin=con.replace(',', '.');
            String con1 =df.format(arti.getCosto());
            String sin1=con.replace(',', '.');
            Object[] objeto = {arti.getIdArticulo(), arti.getNombreArticulo(),sin,sin1,arti.getCodBarra(), arti.getStock(),
             arti.getGrupo().getBajoStock()};
            
            datosTabla.addRow(objeto);
            }
        }   
    }
    
    tblArticulos.setModel(datosTabla);
      
     DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
       
        // Aplicar las alineaciones a las columnas específicas
        tblArticulos.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
       // tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblArticulos.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(5).setCellRenderer(alinearDerecha);
        
    tblArticulos.setAutoCreateRowSorter(true);
    sorter2 = new TableRowSorter<>(datosTabla);
    tblArticulos.setRowSorter(sorter2);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnGenePDF = new javax.swing.JButton();
        btnCargarstock = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Artículos con bajo stock");

        tblArticulos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblArticulos.setRowHeight(22);
        jScrollPane1.setViewportView(tblArticulos);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Artículo:");

        txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

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

        btnCargarstock.setBackground(new java.awt.Color(13, 71, 161));
        btnCargarstock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCargarstock.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarstock.setText("Ingreso de mercadería");
        btnCargarstock.setBorder(null);
        btnCargarstock.setBorderPainted(false);
        btnCargarstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarstockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliente))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnCargarstock, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarstock, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
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
       private void filtrar2(){
        String texto = txtCliente.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter2.setRowFilter(RowFilter.regexFilter(filtro));
    }
    private void btnGenePDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenePDFActionPerformed
        Document documento = new Document();
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = f.format(fechaActual);
            
            try{
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reportes/ReporteStock.pdf"));
                
                Image header = Image.getInstance("src/main/resources/img/LogoGalettoPNG.png");
                header.scaleToFit(350, 800);
                header.setAlignment(Chunk.ALIGN_CENTER);
                Paragraph parrafo = new Paragraph();
                Paragraph parra = new Paragraph();
                parra.setAlignment(Paragraph.ALIGN_RIGHT);
                 parra.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.BLACK));
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parra.add("\n Fecha: "+fecha+"\n\n");
                parrafo.setFont(FontFactory.getFont("Tahoma",18,Font.BOLD,BaseColor.DARK_GRAY));
                parrafo.add("\nArtículos con bajo stock\n\n");
                
                
                documento.open();
                
                documento.add(header);
                documento.add(parra);
                documento.add(parrafo);
                
                PdfPTable tabla = new PdfPTable(5);
                 tabla.addCell("Nombre");
                 tabla.addCell("Precio ($)");
                 tabla.addCell("Costo ($)");
               
                 tabla.addCell("Stock");
                 tabla.addCell("Stock minimo");
                 
                 DefaultTableModel modelo = (DefaultTableModel)tblArticulos.getModel();
               
                for(int i=0; i<modelo.getRowCount(); i++) {
                    tabla.addCell(modelo.getValueAt(i, 1).toString());
                    tabla.addCell(modelo.getValueAt(i, 2).toString());
                    tabla.addCell(modelo.getValueAt(i, 3).toString());
                   
                    tabla.addCell(modelo.getValueAt(i, 5).toString());
                    tabla.addCell(modelo.getValueAt(i, 6).toString());
                    
                }
                documento.add(tabla);
 
                documento.close();
                JOptionPane.showMessageDialog(null, "Reporte creado");
           
            }catch (DocumentException | FileNotFoundException e){
              System.out.println("Error en PDF " + e);
        }catch (IOException e){
            System.out.println("Error en la imagen " + e);
        }
    }//GEN-LAST:event_btnGenePDFActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        filtrar2();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnCargarstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarstockActionPerformed
        ShowJPanel(new IngresoMercaderia(usr));
    }//GEN-LAST:event_btnCargarstockActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarstock;
    private javax.swing.JButton btnGenePDF;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables

}
