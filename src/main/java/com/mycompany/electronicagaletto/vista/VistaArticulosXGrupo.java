package com.mycompany.electronicagaletto.vista;

import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.JTableHeader;

public class VistaArticulosXGrupo extends javax.swing.JPanel{
    Date fechaActual = new Date();
    
    ControladoraLogica control =null;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    private TableRowSorter<DefaultTableModel> sorter;
    private TableRowSorter<DefaultTableModel> sorter2;
    Usuario usr;
    Grupo grupo;
    DecimalFormat df = new DecimalFormat("0.00"); 
    double suma=0;
    public VistaArticulosXGrupo(Usuario usr,Grupo gru) {
        control = new ControladoraLogica();
        this.usr=usr;
        initComponents();
        initStyles();
        this.grupo=gru;
        txtGrupo.setText(gru.getNombreGrupo());
        cargarTabla();
        
        llenarTabla();
        //tblArticulos.getColumnModel().getColumn(4).setCellRenderer(new RenderPintar());
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtGrupo.putClientProperty("JTextField.placeholderText", "Ingrese el grupo");
    
        txtArtic.putClientProperty("JTextField.placeholderText", "Ingrese el artículo a buscar");
    }
    
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false;
            } 
          
    };
     String titulos[] = {"Identificador", "Nombre", "Beneficio", "Stock mínimo"};
        datosTabla.setColumnIdentifiers(titulos);
    //traer datos desde la base
    List <Grupo> listaGrupo = control.traerGrupos();
      if (listaGrupo!=null){
        for (Grupo gru : listaGrupo) {
            if(gru.isEstado()){
             String con =df.format(gru.getBeneficio());
            String sin=con.replace(',', '.');
            Object[] objeto = {gru.getIdGrupo(),gru.getNombreGrupo(),sin,
             gru.getBajoStock()};
            
            datosTabla.addRow(objeto);
            }
        }   
    }
        
    tablaGrupo.setModel(datosTabla);
      
    JTableHeader thead = tablaGrupo.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
     DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
       
        // Aplicar las alineaciones a las columnas específicas
        tablaGrupo.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
       // tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tablaGrupo.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tablaGrupo.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
   
        
    tablaGrupo.setAutoCreateRowSorter(true);
    sorter2 = new TableRowSorter<>(datosTabla);
    tablaGrupo.setRowSorter(sorter2);
    }
  
    
     private void llenarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador","Nombre", "Costo", "Precio", "Stock", "Grupo", 
        "Código de barras", "Estado"};
    datosTabla.setColumnIdentifiers(titulos);
  
    List <Articulo> listaArticulos = control.traerArticulos(grupo);
        //recorrer la lista y mostrar datos en la tabla
    if (listaArticulos!=null){
        for (Articulo arti : listaArticulos) {
            String con =df.format(arti.getCosto());
            String sin=con.replace(',', '.');
            String con1 =df.format(arti.getPrecio());
            String sin1=con1.replace(',', '.');
            Object[] objeto = {arti.getIdArticulo(), arti.getNombreArticulo(), sin,
                sin1, arti.getStock(),
            arti.getGrupo().getNombreGrupo(), arti.getCodBarra(), arti.getEstado()};
            
            datosTabla.addRow(objeto);
        }   
    }
    tblArticulos.setModel(datosTabla);
        JTableHeader thead = tblArticulos.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
      
        // Aplicar las alineaciones a las columnas específicas
        tblArticulos.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        //tablaArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblArticulos.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
       
    
      
    tblArticulos.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tblArticulos.setRowSorter(sorter);
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        txtArtic = new javax.swing.JTextField();
        btnGenerarPDF = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaGrupo = new javax.swing.JTable();
        txtGrupo = new javax.swing.JTextField();
        btnLimpTodo = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(817, 528));
        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(817, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(817, 528));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Ver artículos por grupo");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Grupo:");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Artículo:");

        tblArticulos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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
        jScrollPane2.setViewportView(tblArticulos);

        txtArtic.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtArtic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArticKeyReleased(evt);
            }
        });

        btnGenerarPDF.setBackground(new java.awt.Color(13, 71, 161));
        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar reporte PDF");
        btnGenerarPDF.setBorder(null);
        btnGenerarPDF.setBorderPainted(false);
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        tablaGrupo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tablaGrupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaGrupo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGrupoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaGrupo);

        txtGrupo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        btnLimpTodo.setBackground(new java.awt.Color(13, 71, 161));
        btnLimpTodo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLimpTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpTodo.setText("Volver");
        btnLimpTodo.setBorder(null);
        btnLimpTodo.setBorderPainted(false);
        btnLimpTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addGap(664, 664, 664))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtGrupo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtArtic))
                            .addComponent(jScrollPane2))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArtic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
            Document documento = new Document();
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = f.format(fechaActual);
            
            try{
                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reportes/Articulos_grupo.pdf"));
                
                Image header = Image.getInstance("src/main/resources/img/LogoGalettoPNG.png");
                header.scaleToFit(350, 800);
                header.setAlignment(Chunk.ALIGN_CENTER);
                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_LEFT);
                parrafo.setFont(FontFactory.getFont("Tahoma",18,Font.BOLD,BaseColor.DARK_GRAY));
                parrafo.add("\n\nGrupo: "+grupo.getNombreGrupo()+"\n\n");
                
                
                documento.open();
                
                documento.add(header);
                documento.add(parrafo);
                
                PdfPTable tabla = new PdfPTable(4);
                 tabla.addCell("Nombre");
                 tabla.addCell("Costo($)");
                 tabla.addCell("Precio($)");
                 tabla.addCell("Stock");
                 
                 DefaultTableModel modelo = (DefaultTableModel)tblArticulos.getModel();
           
                for(int i=0; i<modelo.getRowCount(); i++) {
                    tabla.addCell(modelo.getValueAt(i, 1).toString());
                    tabla.addCell(modelo.getValueAt(i, 2).toString());
                    tabla.addCell(modelo.getValueAt(i, 3).toString());
                    tabla.addCell(modelo.getValueAt(i, 4).toString());
                }documento.add(tabla);
                Paragraph parra = new Paragraph();
                parra.setAlignment(Paragraph.ALIGN_BOTTOM);
                parra.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.DARK_GRAY));
                parra.add("\nFecha: "+fecha+"\n");
                documento.add(parra);
                documento.close();
                JOptionPane.showMessageDialog(null, "Reporte creado");
           
            }catch (DocumentException | FileNotFoundException e){
              System.out.println("Error en PDF " + e);
        }catch (IOException e){
            System.out.println("Error en la imagen " + e);
        }
        
    }//GEN-LAST:event_btnGenerarPDFActionPerformed
    
    private void txtArticKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticKeyReleased
        filtrar();
    }//GEN-LAST:event_txtArticKeyReleased
    
    private void tablaGrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaGrupoMouseClicked
        int f = tablaGrupo.getSelectedRow();
        String grup = tablaGrupo.getValueAt(f, 1).toString();
                
        txtGrupo.setText(grup);
        int id =(int) tablaGrupo.getValueAt(f, 0);
        grupo = control.traerGrupo(id);
        llenarTabla();
    }//GEN-LAST:event_tablaGrupoMouseClicked

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        filtrar2();
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void btnLimpTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpTodoActionPerformed
         ShowJPanel(new VistaGrupos(usr));
    }//GEN-LAST:event_btnLimpTodoActionPerformed
    
   
    private void filtrar(){
        String texto = txtArtic.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
      private void filtrar2(){
        String texto = txtGrupo.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter2.setRowFilter(RowFilter.regexFilter(filtro));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnLimpTodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaGrupo;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtArtic;
    private javax.swing.JTextField txtGrupo;
    // End of variables declaration//GEN-END:variables

   
}
