/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Datos;
import clases.Peticiones;
import clases.Utilidades;
import static formularios.frmVenta.bnBuscar;
import static formularios.frmVenta.txtBusquedap;
import static formularios.frmVenta.txtDireccion;
import static formularios.frmVenta.txtNit;
import static formularios.frmVenta.txtNombrecliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static formularios.frmVenta.valorIdcliente;

/**
 *
 * @author GLARA
 */
public class buscar_cliente extends javax.swing.JInternalFrame {

    static int CompruebaC;
    /**
     * Variables para realizar las transacciones con la base de datos
     */
    String nombreTabla = "clientes";
    String[] titulos = {"Id", "Código", "Nombre Cliente", "Dirección", "Nit", "Limite Créd"};
    String campos = "codigo, nombre, direccion, correo, nit, telefono, fec_reg, lim_cred, estado";
    String nombreId = "idClientes";

    DefaultTableModel model;
    Datos datos = new Datos();
    Peticiones peticiones = new Peticiones();
    boolean editar = false;

    /**
     * Creates new form Cliente
     */
    public buscar_cliente() {
        initComponents();

        /**
         * Oculta la primer columna de la tala, la que contiene el Id
         */
        tableResultados.getColumnModel().getColumn(0).setMaxWidth(0);
        tableResultados.getColumnModel().getColumn(0).setMinWidth(0);
        tableResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    /**
     * Prepara el formulario y jtable para crear un nuevo cliente (Habilita y
     * limpia los campos correspondientes
     */
//    public void nuevo() {
//        Utilidades.setEditableTexto(this.panelFormulario, true, null, true, "");
//        //Utilidades.setEditableTexto(this.panelBusqueda, false, null, true, "");
//        Utilidades.setEditableTexto(this.panelResultados, false, null, true, "");
//        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
//        model.setRowCount(0);
//        txtCodigo.requestFocus();
//    }
    private String Validar(String x) {
        String y;
        if (x.equals("")) {
            y = "0";
            return y;
        } else {
            y = x;
            return y;
        }
    }

//    /**
//     * Obtiene la fecha de un JDateChooser, y devuelve la fecha como un string
//     *
//     * @return fecha
//     */
//    private String getFecha() {
//
//        try {
//            String fecha;
//            int años = dateFecha.getCalendar().get(Calendar.YEAR);
//            int dias = dateFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
//            int mess = dateFecha.getCalendar().get(Calendar.MONTH) + 1;
//            int hours = dateFecha.getCalendar().get(Calendar.HOUR_OF_DAY);
//            int minutes = dateFecha.getCalendar().get(Calendar.MINUTE);
//            int seconds = dateFecha.getCalendar().get(Calendar.SECOND);
//
//            fecha = "" + años + "-" + mess + "-" + dias + " " + hours + ":" + minutes + ":" + seconds;
//            return fecha;
//        } catch (Exception e) {
//            JOptionPane.showInternalMessageDialog(this, "Verifique la fecha");
//
//        }
//        return null;
//    }

    /* Funcion para llenar la tabla cuando se busque un cliente en especifico
     por el código, nombre, nit  */
    public void llenarTabla(String nombre) {

        try {
            /* Limpiamos la tabla */
            model.setRowCount(0);

            /* Llamamos a la funcion consultaClientes la cual nos devuelve todos 
             los clientes relaciones con el valor a buscar en la base de datos. 
            
             - Los datos recibidos lo guardamos en el objeto ResulSet para luego
             llenar la tabla con los registros.
            
             */
            ResultSet rs = peticiones.consultaClientes(nombre);
            Object[] registro = new Object[7];

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {

                registro[0] = rs.getString("idClientes");
                registro[1] = rs.getString("codigo");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("direccion");
                registro[4] = rs.getString("nit");
                registro[5] = rs.getString("lim_cred");
                //registro[6] = rs.getBoolean("estado"); //getString("lim_cred");
                model.addRow(registro);
            }
            tableResultados.setModel(model);
            Utilidades.ajustarAnchoColumnas(tableResultados);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }
    }

    /* Funcion para llenar la tabla cuando se busque un cliente en especifico
     por el Id */
    public void llenarFormulario(int s) {

        try {
            /* Llamamos a la funcion consultaRegistrosId la cual nos devuelve todos
            los empleados relaciones con el id a buscar en la base de datos.
            
            - Los datos recibidos lo guardamos en el objeto ResulSet para luego
            llenar la tabla con los registros.
            
             */
//            String id;
//            String cod;
//            String nombre;
//            id = tableResultados.getValueAt(s, 0).toString();
//            cod = tableResultados.getValueAt(s, 4).toString();
//            nombre = tableResultados.getValueAt(s, 2).toString();

//            if (Comprueba == 1) {
//                
//                valorIdcliente = id;
//                txtNit.setText(cod);
//                txtNombrecliente.setText(nombre);
//                
//            }
//            if (Comprueba == 2) {
////            frmVenta.txtNit.setText(cod);
////            frmVenta.txtNombrecliente.setText(nombre);
//            }
            ResultSet rs = peticiones.consultaRegistrosId(nombreTabla,
                    (String) tableResultados.getValueAt(s, 0), nombreId);

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
            filas a la tabla. */
            while (rs.next()) {
                if (CompruebaC == 1) {
                    valorIdcliente = (rs.getString("idClientes"));
                    txtNit.setText(rs.getString("nit"));
                    txtNombrecliente.setText(rs.getString("nombre"));
                    txtDireccion.setText(rs.getString("direccion"));
                }
                if (CompruebaC == 2) {
                }
            }
            bnBuscar.setEnabled(true);
            txtBusquedap.requestFocus();
            txtBusquedap.setEditable(true);

            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
            //Logger.getLogger(buscar_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    /**
//     * Realiza la transacción para guardar los recistros de un nuevo cliente
//     */
//    private void Guardar() {
//
//        if (Utilidades.esObligatorio(this.panelFormulario, true)) {
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        Object[] cliente = {
//            txtCodigo.getText(), txtNombre.getText(), txtDireccion.getText(),
//            txtCorreo.getText(), txtNit.getText(),
//            txtTelefono.getText(), getFecha(), Validar(txtLimitecredito.getText()),
//            peticiones.selected(rbEstado)
//        };
//
//        /* Llamamos a la funcion guardarRegistros la cual recibe como parametro
//         el nombre de la tabla, los campos y los valores a insertar del cliente */
//        if (peticiones.guardarRegistros(nombreTabla, campos, cliente)) {
//            JOptionPane.showMessageDialog(rootPane, "El registro ha sido Guardado correctamente ");
//            nuevo();
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "No se ha podido Guardar el registro, por favor verifique los datos");
//        }
//    }
//    /**
//     * Modifica el registro seleccionado
//     */
//    private void Modificar() {
//        int s = 0;
//
//        /* Guardamos el ID de dla fila selecciona en la variable s */
//        s = tableResultados.getSelectedRow();
//
//        /* Validamos que hallan seleccionado */
//        if (s < 0) {
//            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
//            return;
//        }
//
//        if (Utilidades.esObligatorio(this.panelFormulario, true)) {
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son"
//                    + " Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));
//
//        Object[] cliente = {
//            txtCodigo.getText(), txtNombre.getText(), txtDireccion.getText(),
//            txtCorreo.getText(), txtNit.getText(),
//            txtTelefono.getText(), getFecha(), Validar(txtLimitecredito.getText()),
//            peticiones.selected(rbEstado), id
//        };
//
//        if (peticiones.actualizarRegistroId(nombreTabla, campos, cliente, nombreId)) {
//            JOptionPane.showMessageDialog(rootPane, "El registro ha sido Modificado correctamente ");
//            nuevo();
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "No se ha podido Modificar"
//                    + " registro, por favor verifique los datos");
//        }
//    }
    /**
     * Al dar clic sobre la tabla, llenará el formulario con el registro
     * seleccionado
     */
    private void tableMouseClicked() {

        /* Variable que contendra el ID de la fila seleccionada */
        int s = 0;

        /* Limpiamos los campos del formulario */
        //Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

        /* Guardamos el ID de dla fila selecciona en la variable s*/
        s = tableResultados.getSelectedRow();

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

        panelImage = new elaprendiz.gui.panel.PanelImage();
        panelBotones = new javax.swing.JPanel();
        bnBuscarC = new javax.swing.JButton();
        bnCrear = new javax.swing.JButton();
        bnSuprimir = new javax.swing.JButton();
        bnDeudores = new javax.swing.JButton();
        bnEstadocuenta = new javax.swing.JButton();
        bnEditar = new javax.swing.JButton();
        panelBusqueda = new javax.swing.JPanel();
        labelBusqueda = new javax.swing.JLabel();
        txtBusqueda = new elaprendiz.gui.textField.TextField();
        panelResultados = new javax.swing.JPanel();
        scrollpaneResultados = new javax.swing.JScrollPane();
        tableResultados = new javax.swing.JTable();
        panelEncabezado = new jcMousePanel.jcMousePanel();
        labelEncabezado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(905, 401));
        setName("buscar_clientes"); // NOI18N
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(905, 401));

        panelImage.setBackground(new java.awt.Color(255, 255, 255));
        panelImage.setLayout(null);

        panelBotones.setBackground(java.awt.SystemColor.controlHighlight);
        panelBotones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBotones.setFocusable(false);

        bnBuscarC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnBuscarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        bnBuscarC.setText("Buscar");
        bnBuscarC.setToolTipText("");
        bnBuscarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnBuscarCActionPerformed(evt);
            }
        });

        bnCrear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        bnCrear.setText("Crear");
        bnCrear.setToolTipText("");

        bnSuprimir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnSuprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        bnSuprimir.setText("Suprimir");
        bnSuprimir.setToolTipText("");

        bnDeudores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnDeudores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deudores.png"))); // NOI18N
        bnDeudores.setText("Deudores");
        bnDeudores.setToolTipText("");
        bnDeudores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeudoresActionPerformed(evt);
            }
        });

        bnEstadocuenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnEstadocuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reports.png"))); // NOI18N
        bnEstadocuenta.setText("Estado Cuenta");
        bnEstadocuenta.setToolTipText("");
        bnEstadocuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnEstadocuentaActionPerformed(evt);
            }
        });

        bnEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update.png"))); // NOI18N
        bnEditar.setText("Editar");
        bnEditar.setToolTipText("");

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnBuscarC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnSuprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnDeudores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnEstadocuenta)
                .addContainerGap(264, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnBuscarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnSuprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnDeudores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnEstadocuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImage.add(panelBotones);
        panelBotones.setBounds(0, 22, 890, 68);

        panelBusqueda.setBackground(java.awt.SystemColor.activeCaption);
        panelBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBusqueda.setPreferredSize(new java.awt.Dimension(890, 82));
        panelBusqueda.setLayout(null);

        labelBusqueda.setFont(new java.awt.Font("Decker", 1, 22)); // NOI18N
        labelBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBusqueda.setText("Buscar Cliente");
        panelBusqueda.add(labelBusqueda);
        labelBusqueda.setBounds(240, 10, 380, 28);

        txtBusqueda.setOpaque(true);
        txtBusqueda.setPreferredSize(new java.awt.Dimension(250, 27));
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        panelBusqueda.add(txtBusqueda);
        txtBusqueda.setBounds(240, 40, 390, 27);

        panelImage.add(panelBusqueda);
        panelBusqueda.setBounds(0, 88, 890, 82);

        panelResultados.setBackground(new java.awt.Color(255, 255, 255));
        panelResultados.setPreferredSize(new java.awt.Dimension(786, 402));
        panelResultados.setLayout(new java.awt.BorderLayout());

        scrollpaneResultados.setBackground(new java.awt.Color(255, 255, 255));
        scrollpaneResultados.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableResultados.setModel(model = new DefaultTableModel(null, titulos)
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
            tableResultados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            tableResultados.setFocusCycleRoot(true);
            tableResultados.setRowHeight(24);
            tableResultados.setSurrendersFocusOnKeystroke(true);
            tableResultados.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tableResultadosKeyPressed(evt);
                }
            });
            scrollpaneResultados.setViewportView(tableResultados);

            panelResultados.add(scrollpaneResultados, java.awt.BorderLayout.CENTER);

            panelImage.add(panelResultados);
            panelResultados.setBounds(0, 169, 890, 201);

            panelEncabezado.setColor1(new java.awt.Color(102, 153, 255));
            panelEncabezado.setColor2(new java.awt.Color(255, 255, 255));
            panelEncabezado.setModo(3);

            labelEncabezado.setFont(new java.awt.Font("Decker", 1, 17)); // NOI18N
            labelEncabezado.setForeground(new java.awt.Color(255, 255, 255));
            labelEncabezado.setText("CLIENTES");

            javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
            panelEncabezado.setLayout(panelEncabezadoLayout);
            panelEncabezadoLayout.setHorizontalGroup(
                panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelEncabezadoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(123, Short.MAX_VALUE))
            );
            panelEncabezadoLayout.setVerticalGroup(
                panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelEncabezadoLayout.createSequentialGroup()
                    .addComponent(labelEncabezado)
                    .addGap(0, 1, Short.MAX_VALUE))
            );

            panelImage.add(panelEncabezado);
            panelEncabezado.setBounds(0, 0, 890, 22);

            getContentPane().add(panelImage, java.awt.BorderLayout.CENTER);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void bnBuscarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnBuscarCActionPerformed
        // TODO add your handling code here:
        //Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        Utilidades.setEditableTexto(this.panelBusqueda, true, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, true, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        model.setRowCount(0);
        txtBusqueda.requestFocus();
    }//GEN-LAST:event_bnBuscarCActionPerformed

    private void bnDeudoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeudoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bnDeudoresActionPerformed

    private void bnEstadocuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnEstadocuentaActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_bnEstadocuentaActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla(txtBusqueda.getText());
        //Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void tableResultadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableResultadosKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            tableMouseClicked();
        }
    }//GEN-LAST:event_tableResultadosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnBuscarC;
    private javax.swing.JButton bnCrear;
    private javax.swing.JButton bnDeudores;
    private javax.swing.JButton bnEditar;
    private javax.swing.JButton bnEstadocuenta;
    private javax.swing.JButton bnSuprimir;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLabel labelEncabezado;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBusqueda;
    private jcMousePanel.jcMousePanel panelEncabezado;
    private elaprendiz.gui.panel.PanelImage panelImage;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JScrollPane scrollpaneResultados;
    private javax.swing.JTable tableResultados;
    private elaprendiz.gui.textField.TextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
