/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Datos;
import clases.Peticiones;
import clases.Utilidades;
import static formularios.frmVenta.txtBusquedap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GLARA
 */
public class buscar_productos extends javax.swing.JInternalFrame {

    static int CompruebaP;
    
    /**
     * Variables para realizar las transacciones con la base de datos
     */
    String nombreTabla = "producto";
    String[] titulos = {"Id", "Código", "Descripción Producto", "Costo", "Precio Venta", "Precio Mayoreo", "Unidad", "Categoria", "Estado", "Fecha Reg",};
    String campos = "codigo, nombre, idunidad, idCategoria, observacion, "
            + "fec_reg, estado, ubicacion, invminimo, existencia, preciocoste, precioventa, preciomayoreo";
    String nombreId = "idproducto";

    public Hashtable<String, String> hashUnidad = new Hashtable<>();
    public Hashtable<String, String> hashUnidad2 = new Hashtable<>();
    public Hashtable<String, String> hashCategoria = new Hashtable<>();
    public Hashtable<String, String> hashCategoria2 = new Hashtable<>();

    DefaultTableModel modelP;
    Datos datos = new Datos();
    Peticiones peticiones = new Peticiones();
    boolean editar = false;

    /**
     * Creates new form Productos
     */
    public buscar_productos() {
        initComponents();
        //llenarcombobox();
        //txtBusqueda.requestFocus();
        /**
         * Oculta la primer columna de la tala, la que contiene el Id
         */
        tableResultadosP.getColumnModel().getColumn(0).setMaxWidth(0);
        tableResultadosP.getColumnModel().getColumn(0).setMinWidth(0);
        tableResultadosP.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

//    private void llenarcombobox() {
//
//        try {
//
//            /* Instaciamos un objeto de la clase Opcion para cargar el combo box
//             de los proveedores  */
//            Opcion op = new Opcion("0", " ");
//
//            /* Añadimos el primer elemento al combo box */
//            comboUnidad.addItem(op);
//
//            /* Llamos a la funcion consultaUnidad la cual nos devuelve todas las
//             Unidades que hay, esos datos los guardamos en un ResultSet para luego
//             llenar el combo box con todas las Unidades */
//            ResultSet rs = peticiones.consultaUnidad("");
//
//            /* Hacemos un while que mientras hallan registros en rs, sobreescrira
//             al objeto de la clase opcion con los datos del objeto rs, y los añada
//             al combo box */
//            int count = 0;
//            while (rs.next()) {
//                count++;
//                op = new Opcion(
//                        rs.getString("idUnidad"),
//                        rs.getString("nombre"));
//                comboUnidad.addItem(op);
//                hashUnidad.put(rs.getString("nombre"), "" + count);
//                hashUnidad2.put(rs.getString("idUnidad"), rs.getString("nombre"));
//            }
//
//            /* Instaciamos un objeto de la clase Opcion para cargar el combo box
//             de los servicios  */
//            Opcion op2 = new Opcion("0", " ");
//
//            /* Añadimos el primer elemento al combo box */
//            comboCategoria.addItem(op2);
//
//            /* Llamos a la funcion getServicios la cual nos devuelve todos los
//             Servicios que hay, esos datos los guardamos en un ResultSet para luego
//             llenar el combo box con todos los Servicios */
//            ResultSet rsSer = peticiones.consultaCategoria("");
//
//            /* Hacemos un while que mientras hallan registros en rs, sobreescrira
//             al objeto de la clase opcion con los datos del objeto rs, y los añada
//             al combo box */
//            int count2 = 0;
//            while (rsSer.next()) {
//                count2++;
//                op2 = new Opcion(
//                        rsSer.getString("idCategoria"),
//                        rsSer.getString("nombre"));
//                comboCategoria.addItem(op2);
//                hashCategoria.put(rsSer.getString("nombre"), "" + count2);
//                hashCategoria2.put(rsSer.getString("idCategoria"), rsSer.getString("nombre"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Problema con: " + e.getMessage());
//        }
//    }
//    /**
//     * Prepara el formulario y jtable para crear un nuevo producto (Habilita y
//     * limpia los campos correspondientes
//     */
//    private void nuevo() {
//        Utilidades.setEditableTexto(this.tbPane2, true, null, true, "");
//        //Utilidades.setEditableTexto(this.panelBusqueda, false, null, true, "");
//        Utilidades.setEditableTexto(this.panelResultados, false, null, true, "");
//        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
//        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);
//
//        modelP.setRowCount(0);
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

    /* Funcion para llenar la tabla cuando se busque un producto en especifico
     por el código, nombre, nit  */
    private void llenarTabla(String nombre) {

        try {
            /* Limpiamos la tabla */
            modelP.setRowCount(0);

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
                modelP.addRow(registro);
            }
            tableResultadosP.setModel(modelP);
            Utilidades.ajustarAnchoColumnas(tableResultadosP);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
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
        ResultSet rs = peticiones.consultaRegistrosId(nombreTabla,
                (String) tableResultadosP.getValueAt(s, 0), nombreId);
        //System.out.print((String) tableResultados.getValueAt(s, 0) + "codigo1---: \n");
        /* Hacemos un while que mientras en rs hallan datos el ira agregando
        filas a la tabla. */
        String codigob = "" + tableResultadosP.getValueAt(s, 1);
        //            while (rs.next()) {
        if (CompruebaP == 1) {
            txtBusquedap.setText(codigob);
            txtBusquedap.requestFocus();
            //txtBusquedap.requestFocus(false);
            txtBusquedap.nextFocus(); 
            //txtBusquedap.setText(rs.getString("codigo"));
            //frmVenta form = new frmVenta();
            //System.out.print(txtBusquedap.getText() + "codigo2---: \n");

            //form.buscarProducto_codigo(codigob);
        }
//            }

        this.dispose();
    }

//    /**
//     * Realiza la transacción para guardar los recistros de un nuevo producto
//     */
//    private void Guardar() {
//
//        if (Utilidades.esObligatorio(this.panelFormulario, true)) {
//
//            Utilidades.esObligatorio(this.panelFormulario1, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (Utilidades.esObligatorio(this.panelFormulario1, true)) {
//
//            Utilidades.esObligatorio(this.panelFormulario, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        Object[] producto = {
//            txtCodigo.getText(), txtNombre.getText(),
//            Integer.parseInt(((Opcion) comboUnidad.getSelectedItem()).getValor()),
//            Integer.parseInt(((Opcion) comboCategoria.getSelectedItem()).getValor()),
//            txtObservacion.getText(), getFecha(),
//            peticiones.selected(rbEstado), txtUbicacion.getText(), Validar(txtMinimo.getText()),
//            Validar(txtExistencia.getText()), Validar(txtCosto.getText()), Validar(txtVenta.getText()),
//            Validar(txtMayoreo.getText())
//        };
//
//        /* Llamamos a la funcion guardarRegistrosId la cual recibe como parametro
//         el nombre de la tabla, los campos y los valores a insertar del producto */
//        int id = peticiones.guardarRegistrosId(nombreTabla, campos, producto);
//
//        if (id != 0) {
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
//
//            Utilidades.esObligatorio(this.panelFormulario1, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (Utilidades.esObligatorio(this.panelFormulario1, true)) {
//
//            Utilidades.esObligatorio(this.panelFormulario, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));
//
//        Object[] producto = {
//            txtCodigo.getText(), txtNombre.getText(),
//            Integer.parseInt(((Opcion) comboUnidad.getSelectedItem()).getValor()),
//            Integer.parseInt(((Opcion) comboCategoria.getSelectedItem()).getValor()),
//            txtObservacion.getText(), getFecha(),
//            peticiones.selected(rbEstado), txtUbicacion.getText(), Validar(txtMinimo.getText()),
//            Validar(txtExistencia.getText()), Validar(txtCosto.getText()), Validar(txtVenta.getText()),
//            Validar(txtMayoreo.getText()), id
//        };
//
//        if (peticiones.actualizarRegistroId(nombreTabla, campos, producto, nombreId)) {
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
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

        /* Guardamos el ID de dla fila selecciona en la variable s*/
        s = tableResultadosP.getSelectedRow();

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
        bnBuscar = new javax.swing.JButton();
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
        tableResultadosP = new javax.swing.JTable();
        panelEncabezado = new jcMousePanel.jcMousePanel();
        labelEncabezado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(905, 401));
        setName("buscar_producto"); // NOI18N
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(905, 401));

        panelImage.setBackground(new java.awt.Color(255, 255, 255));
        panelImage.setLayout(null);

        panelBotones.setBackground(java.awt.SystemColor.controlHighlight);
        panelBotones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBotones.setFocusable(false);

        bnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        bnBuscar.setText("Buscar");
        bnBuscar.setToolTipText("");
        bnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnBuscarActionPerformed(evt);
            }
        });

        bnCrear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        bnCrear.setText("Crear");
        bnCrear.setToolTipText("");
        bnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnCrearActionPerformed(evt);
            }
        });

        bnSuprimir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bnSuprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        bnSuprimir.setText("Suprimir");
        bnSuprimir.setToolTipText("");
        bnSuprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnSuprimirActionPerformed(evt);
            }
        });

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
        bnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnBuscar)
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
                    .addComponent(bnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        labelBusqueda.setText("Buscar Producto");
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

        tableResultadosP.setAutoCreateRowSorter(true);
        tableResultadosP.setModel(modelP = new DefaultTableModel(null, titulos)
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
            tableResultadosP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            tableResultadosP.setFocusCycleRoot(true);
            tableResultadosP.setRowHeight(24);
            tableResultadosP.setSurrendersFocusOnKeystroke(true);
            tableResultadosP.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tableResultadosPKeyPressed(evt);
                }
            });
            scrollpaneResultados.setViewportView(tableResultadosP);

            panelResultados.add(scrollpaneResultados, java.awt.BorderLayout.CENTER);

            panelImage.add(panelResultados);
            panelResultados.setBounds(0, 169, 890, 201);

            panelEncabezado.setColor1(new java.awt.Color(102, 153, 255));
            panelEncabezado.setColor2(new java.awt.Color(255, 255, 255));
            panelEncabezado.setModo(3);

            labelEncabezado.setFont(new java.awt.Font("Decker", 1, 17)); // NOI18N
            labelEncabezado.setForeground(new java.awt.Color(255, 255, 255));
            labelEncabezado.setText("BUSCAR PRODUCTOS");

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
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            panelImage.add(panelEncabezado);
            panelEncabezado.setBounds(0, 0, 890, 22);

            getContentPane().add(panelImage, java.awt.BorderLayout.CENTER);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void bnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnBuscarActionPerformed
        // TODO add your handling code here:
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        Utilidades.setEditableTexto(this.panelBusqueda, true, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, true, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        modelP.setRowCount(0);
        txtBusqueda.requestFocus();
    }//GEN-LAST:event_bnBuscarActionPerformed

    private void bnSuprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnSuprimirActionPerformed
        // TODO add your handling code here:
        int resp;
        resp = JOptionPane.showInternalConfirmDialog(this, "¿Desea Eliminar el Registro?", "Pregunta", 0);
        if (resp == 0) {
            int s = 0;

            /* Guardamos el ID de dla fila selecciona en la variable s */
            s = tableResultadosP.getSelectedRow();

            /* Validamos que hallan seleccionado */
            if (s < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
                return;
            }

            String id = Utilidades.objectToString(tableResultadosP.getValueAt(s, 0));

            if ((peticiones.eliminarRegistro(nombreTabla, "estado", nombreId, id)) > 0) {
                JOptionPane.showMessageDialog(rootPane, "El registro ha sido Eliminado correctamente ");
                //nuevo();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se ha podido Eliminar el registro, por favor verifique los datos");
            }
        }
    }//GEN-LAST:event_bnSuprimirActionPerformed

    private void bnDeudoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeudoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bnDeudoresActionPerformed

    private void bnEstadocuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnEstadocuentaActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_bnEstadocuentaActionPerformed

    private void bnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnCrearActionPerformed
        // TODO add your handling code here:  
        editar = false;
        //nuevo();

    }//GEN-LAST:event_bnCrearActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla(txtBusqueda.getText());
        //Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        //Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, false, editar);

    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void bnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnEditarActionPerformed

        int s = 0;

        /* Guardamos el ID de dla fila selecciona en la variable s */
        s = tableResultadosP.getSelectedRow();

        /* Validamos que hallan seleccionado */
        if (s < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
            return;
        }
        //tableMouseClicked();
        //Utilidades.setEditableTexto(this.tbPane2, true, null, false, "");
        // Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);
        editar = true;
    }//GEN-LAST:event_bnEditarActionPerformed

    private void tableResultadosPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableResultadosPKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            tableMouseClicked();
        }
    }//GEN-LAST:event_tableResultadosPKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnBuscar;
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
    private javax.swing.JTable tableResultadosP;
    public static elaprendiz.gui.textField.TextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
