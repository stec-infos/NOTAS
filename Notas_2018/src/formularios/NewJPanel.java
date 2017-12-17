/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Datos;
import clases.Peticiones;
import clases.Utilidades;
import static formularios.buscar_productos.CompruebaP;
import static formularios.frmVenta.txtBusquedap;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 30234
 */
public class NewJPanel extends javax.swing.JPanel {
    
    frmVenta venta = new frmVenta();
    
    DefaultTableModel model2;
    String[] titulos = {"Id", "Código", "Descripción Producto", "Costo", "Precio Venta", "Precio Mayoreo", "Unidad", "Categoria", "Estado", "Fecha Reg",};
    
    Datos datos = new Datos();
    Peticiones peticiones = new Peticiones();
    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        initComponents();
    }

    public void prueba() {
        /* Variable que contendra el ID de la fila seleccionada */
        int s = 0;

        /* Limpiamos los campos del formulario */
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

        /* Guardamos el ID de dla fila selecciona en la variable s*/
        s = tableResultados2.getSelectedRow();

        venta.txtBusquedap.setText("" + tableResultados2.getValueAt(s, 1));
        venta.txtBusquedap.requestFocus();
    }
    
    /* Funcion para llenar la tabla cuando se busque un producto en especifico
     por el código, nombre, nit  */
    public void llenarTabla(String nombre) {

        try {
            /* Limpiamos la tabla */
            model2.setRowCount(0);

            /* Llamamos a la funcion consultaProducto la cual nos devuelve todos 
             los productos relaciones con el valor a buscar en la base de datos. 
            
             - Los datos recibidos lo guardamos en el objeto ResulSet para luego
             llenar la tabla con los registros.
            
             */
            ResultSet rs = peticiones.Buacar_Producto(nombre);
            Object[] registro = new Object[10];

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {

                registro[0] = rs.getString("producto.idproducto");
                registro[1] = rs.getString("producto.codigo");
                registro[2] = rs.getString("producto.nombre");
                registro[3] = rs.getString("producto.preciocoste");
                registro[4] = rs.getString("producto.precioventa");
                registro[5] = rs.getString("producto.preciomayoreo");
                registro[6] = rs.getString("unidad.nombre");
                registro[7] = rs.getString("categoria.nombre");

                if (rs.getString("estado").equals("1")) {
                    registro[8] = ("Activo");
                } else if (rs.getString("estado").equals("0")) {
                    registro[8] = ("Inactivo");
                }
                registro[9] = rs.getString("fec_reg");
                model2.addRow(registro);
            }
            tableResultados2.setModel(model2);
            Utilidades.ajustarAnchoColumnas(tableResultados2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
     
    /* Funcion para llenar la tabla cuando se busque un producto en especifico
     por el Id */
    private void llenarFormulario(int s) {

        /* Llamamos a la funcion consultaRegistrosId la cual nos devuelve todos
        los productos relaciones con el id a buscar en la base de datos.
        
        - Los datos recibidos lo guardamos en el objeto ResulSet para luego
        llenar la tabla con los registros.
        
         */
//        ResultSet rs = peticiones.consultaRegistrosId(nombreTabla,
//                (String) tableResultados2.getValueAt(s, 0), nombreId);
        //System.out.print((String) tableResultados.getValueAt(s, 0) + "codigo1---: \n");
        /* Hacemos un while que mientras en rs hallan datos el ira agregando
        filas a la tabla. */

        //            while (rs.next()) {
        if (CompruebaP == 1) {
            txtBusquedap.setText("" + tableResultados2.getValueAt(s, 1));
            txtBusquedap.requestFocus();
            //txtBusquedap.setText(rs.getString("codigo"));
        }
//            }
        //frmVenta form = new frmVenta();
        //System.out.print(txtBusquedap.getText() + "codigo2---: \n");

        venta.buscarProducto_codigo("" + tableResultados2.getValueAt(s, 1));
        
        //this.dispose();
    }
    
    /**
     * Al dar clic sobre la tabla, llenará el formulario con el registro
     * seleccionado
     */
    private void tableMouseClicked() {

        /* Variable que contendra el ID de la fila seleccionada */
        int s = 0;

        /* Limpiamos los campos del formulario */
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

        /* Guardamos el ID de dla fila selecciona en la variable s*/
        s = tableResultados2.getSelectedRow();

        /* Validamos que hallan seleccionado */
        if (s < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
            return;
        }

        llenarFormulario(s);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtBusqueda = new elaprendiz.gui.textField.TextField();
        scrollpaneResultados = new javax.swing.JScrollPane();
        tableResultados2 = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        jButton1.setText("jButton1");

        txtBusqueda.setOpaque(true);
        txtBusqueda.setPreferredSize(new java.awt.Dimension(250, 27));
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        scrollpaneResultados.setBackground(new java.awt.Color(255, 255, 255));
        scrollpaneResultados.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableResultados2.setAutoCreateRowSorter(true);
        tableResultados2.setModel(model2 = new DefaultTableModel(null, titulos)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column==6  ){
                        return true;
                    }else{
                        return false;}
                    //return false;
                }
            });
            tableResultados2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            tableResultados2.setFocusCycleRoot(true);
            tableResultados2.setRowHeight(24);
            tableResultados2.setSurrendersFocusOnKeystroke(true);
            tableResultados2.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tableResultados2KeyPressed(evt);
                }
            });
            scrollpaneResultados.setViewportView(tableResultados2);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollpaneResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(149, 149, 149)
                    .addComponent(scrollpaneResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla(txtBusqueda.getText());
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, false, editar);
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void tableResultados2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableResultados2KeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            tableMouseClicked();
        }
    }//GEN-LAST:event_tableResultados2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollpaneResultados;
    private javax.swing.JTable tableResultados2;
    private elaprendiz.gui.textField.TextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
