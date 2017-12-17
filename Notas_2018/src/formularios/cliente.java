/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Datos;
import clases.FormatoDecimal;
import clases.Peticiones;
import clases.Utilidades;
import clases.VerificadorEntrada;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GLARA
 */
public class cliente extends javax.swing.JInternalFrame {

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
    public cliente() {
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
    public void nuevo() {
        Utilidades.setEditableTexto(this.panelFormulario, true, null, true, "");
        //Utilidades.setEditableTexto(this.panelBusqueda, false, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, false, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        model.setRowCount(0);
        txtCodigo.requestFocus();
    }

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

    /**
     * Obtiene la fecha de un JDateChooser, y devuelve la fecha como un string
     *
     * @return fecha
     */
    private String getFecha() {

        try {
            String fecha;
            int años = dateFecha.getCalendar().get(Calendar.YEAR);
            int dias = dateFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mess = dateFecha.getCalendar().get(Calendar.MONTH) + 1;
            int hours = dateFecha.getCalendar().get(Calendar.HOUR_OF_DAY);
            int minutes = dateFecha.getCalendar().get(Calendar.MINUTE);
            int seconds = dateFecha.getCalendar().get(Calendar.SECOND);

            fecha = "" + años + "-" + mess + "-" + dias + " " + hours + ":" + minutes + ":" + seconds;
            return fecha;
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(this, "Verifique la fecha");

        }
        return null;
    }

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
            ResultSet rs = peticiones.consultaRegistrosId(nombreTabla,
                    (String) tableResultados.getValueAt(s, 0), nombreId);

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {
                txtCodigo.setText(rs.getString("codigo"));
                txtNombre.setText(rs.getString("nombre"));
                txtDireccion.setText(rs.getString("direccion"));
                txtCorreo.setText(rs.getString("correo"));
                txtNit.setText(rs.getString("nit"));
                txtTelefono.setText(rs.getString("telefono"));
                dateFecha.setDate(rs.getDate("fec_reg"));
                txtLimitecredito.setText(rs.getString("lim_cred"));
                rbEstado.setSelected(rs.getBoolean("estado"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }

    }

    /**
     * Realiza la transacción para guardar los recistros de un nuevo cliente
     */
    private void Guardar() {

        if (Utilidades.esObligatorio(this.panelFormulario, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] cliente = {
            txtCodigo.getText(), txtNombre.getText(), txtDireccion.getText(),
            txtCorreo.getText(), txtNit.getText(),
            txtTelefono.getText(), getFecha(), Validar(txtLimitecredito.getText()),
            peticiones.selected(rbEstado)
        };

        /* Llamamos a la funcion guardarRegistros la cual recibe como parametro
         el nombre de la tabla, los campos y los valores a insertar del cliente */
        if (peticiones.guardarRegistros(nombreTabla, campos, cliente)) {
            JOptionPane.showMessageDialog(rootPane, "El registro ha sido Guardado correctamente ");
            nuevo();
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha podido Guardar el registro, por favor verifique los datos");
        }
    }

    /**
     * Modifica el registro seleccionado
     */
    private void Modificar() {
        int s = 0;

        /* Guardamos el ID de dla fila selecciona en la variable s */
        s = tableResultados.getSelectedRow();

        /* Validamos que hallan seleccionado */
        if (s < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
            return;
        }

        if (Utilidades.esObligatorio(this.panelFormulario, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son"
                    + " Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));

        Object[] cliente = {
            txtCodigo.getText(), txtNombre.getText(), txtDireccion.getText(),
            txtCorreo.getText(), txtNit.getText(),
            txtTelefono.getText(), getFecha(), Validar(txtLimitecredito.getText()),
            peticiones.selected(rbEstado), id
        };

        if (peticiones.actualizarRegistroId(nombreTabla, campos, cliente, nombreId)) {
            JOptionPane.showMessageDialog(rootPane, "El registro ha sido Modificado correctamente ");
            nuevo();
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha podido Modificar"
                    + " registro, por favor verifique los datos");
        }
    }

    /**
     * Al dar clic sobre la tabla, llenará el formulario con el registro
     * seleccionado
     */
    private void tableMouseClicked() {

        /* Variable que contendra el ID de la fila seleccionada */
        int s = 0;

        /* Limpiamos los campos del formulario */
        Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
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
        tableResultados = new javax.swing.JTable();
        panelFormulario = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        labelNit = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtNombre = new elaprendiz.gui.textField.TextField();
        txtDireccion = new elaprendiz.gui.textField.TextField();
        txtNit = new elaprendiz.gui.textField.TextField();
        txtTelefono = new elaprendiz.gui.textField.TextField();
        txtCorreo = new elaprendiz.gui.textField.TextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        panelBotonesformulario = new javax.swing.JPanel();
        bnGuardar = new javax.swing.JButton();
        bnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelCorreo1 = new javax.swing.JLabel();
        txtLimitecredito = new javax.swing.JFormattedTextField();
        rbEstado = new javax.swing.JRadioButton();
        labelCorreo2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        panelEncabezado = new jcMousePanel.jcMousePanel();
        labelEncabezado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("clientes"); // NOI18N
        setOpaque(true);

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
            tableResultados.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tableResultadosMouseClicked(evt);
                }
            });
            scrollpaneResultados.setViewportView(tableResultados);

            panelResultados.add(scrollpaneResultados, java.awt.BorderLayout.CENTER);

            panelImage.add(panelResultados);
            panelResultados.setBounds(0, 169, 890, 201);

            panelFormulario.setBackground(new java.awt.Color(255, 255, 255));
            panelFormulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            panelFormulario.setLayout(null);

            labelCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCodigo.setText("Código*");
            labelCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCodigo);
            labelCodigo.setBounds(30, 40, 120, 25);

            labelNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelNombre.setText("Nombre Completo*");
            labelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelNombre);
            labelNombre.setBounds(30, 80, 120, 25);

            labelDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelDireccion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelDireccion.setText("Dirección*");
            labelDireccion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelDireccion);
            labelDireccion.setBounds(30, 200, 120, 25);

            labelNit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelNit.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelNit.setText("Nit");
            labelNit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelNit);
            labelNit.setBounds(30, 120, 120, 25);

            labelTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelTelefono.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelTelefono.setText("Teléfono");
            labelTelefono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelTelefono);
            labelTelefono.setBounds(470, 40, 120, 25);

            labelCorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo.setText("Correo");
            labelCorreo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo);
            labelCorreo.setBounds(30, 160, 120, 25);

            labelFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelFecha.setText("Fecha*");
            labelFecha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelFecha);
            labelFecha.setBounds(470, 120, 120, 25);

            txtCodigo.setEditable(false);
            txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
            txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtCodigo.setEnabled(false);
            txtCodigo.setName("codigo"); // NOI18N
            txtCodigo.setOpaque(true);
            txtCodigo.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtCodigo);
            txtCodigo.setBounds(170, 40, 250, 25);

            txtNombre.setEditable(false);
            txtNombre.setBackground(new java.awt.Color(255, 255, 255));
            txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtNombre.setEnabled(false);
            txtNombre.setName("txtNombre"); // NOI18N
            txtNombre.setOpaque(true);
            txtNombre.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtNombre);
            txtNombre.setBounds(170, 80, 250, 25);

            txtDireccion.setEditable(false);
            txtDireccion.setBackground(new java.awt.Color(255, 255, 255));
            txtDireccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtDireccion.setEnabled(false);
            txtDireccion.setName("txtDireccion"); // NOI18N
            txtDireccion.setOpaque(true);
            txtDireccion.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtDireccion);
            txtDireccion.setBounds(170, 200, 570, 25);

            txtNit.setEditable(false);
            txtNit.setBackground(new java.awt.Color(255, 255, 255));
            txtNit.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNit.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtNit.setEnabled(false);
            txtNit.setOpaque(true);
            txtNit.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtNit);
            txtNit.setBounds(170, 120, 250, 25);

            txtTelefono.setEditable(false);
            txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
            txtTelefono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtTelefono.setEnabled(false);
            txtTelefono.setOpaque(true);
            txtTelefono.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtTelefono);
            txtTelefono.setBounds(610, 40, 130, 25);

            txtCorreo.setEditable(false);
            txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
            txtCorreo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCorreo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtCorreo.setEnabled(false);
            txtCorreo.setInputVerifier(new VerificadorEntrada(125,VerificadorEntrada.EMAIL));
            txtCorreo.setOpaque(true);
            txtCorreo.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtCorreo);
            txtCorreo.setBounds(170, 160, 250, 25);

            dateFecha.setDate(Calendar.getInstance().getTime());
            dateFecha.setDateFormatString("dd/MM/yyyy");
            dateFecha.setEnabled(false);
            dateFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            dateFecha.setIcon(null);
            dateFecha.setMaxSelectableDate(new java.util.Date(3093496470100000L));
            dateFecha.setMinSelectableDate(new java.util.Date(-62135744300000L));
            dateFecha.setPreferredSize(new java.awt.Dimension(120, 22));
            panelFormulario.add(dateFecha);
            dateFecha.setBounds(610, 120, 130, 25);

            panelBotonesformulario.setBackground(java.awt.SystemColor.activeCaption);
            panelBotonesformulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            panelBotonesformulario.setLayout(new java.awt.GridBagLayout());

            bnGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            bnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresar.png"))); // NOI18N
            bnGuardar.setText("Guardar");
            bnGuardar.setToolTipText("");
            bnGuardar.setEnabled(false);
            bnGuardar.setMaximumSize(new java.awt.Dimension(113, 41));
            bnGuardar.setMinimumSize(new java.awt.Dimension(113, 41));
            bnGuardar.setPreferredSize(new java.awt.Dimension(113, 41));
            bnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    bnGuardarActionPerformed(evt);
                }
            });
            panelBotonesformulario.add(bnGuardar, new java.awt.GridBagConstraints());

            bnCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            bnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
            bnCancelar.setText("Descartar");
            bnCancelar.setToolTipText("");
            bnCancelar.setEnabled(false);
            bnCancelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    bnCancelarActionPerformed(evt);
                }
            });
            panelBotonesformulario.add(bnCancelar, new java.awt.GridBagConstraints());

            panelFormulario.add(panelBotonesformulario);
            panelBotonesformulario.setBounds(0, 290, 890, 60);

            jSeparator1.setBackground(java.awt.SystemColor.inactiveCaption);
            jSeparator1.setForeground(java.awt.SystemColor.inactiveCaption);
            jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator1);
            jSeparator1.setBounds(159, 35, 2, 200);

            labelCorreo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo1.setText("Estado");
            labelCorreo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo1);
            labelCorreo1.setBounds(470, 160, 120, 25);

            txtLimitecredito.setEditable(false);
            txtLimitecredito.setBackground(new java.awt.Color(255, 255, 255));
            txtLimitecredito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtLimitecredito.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new FormatoDecimal("#####0.00",true))));
            txtLimitecredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            txtLimitecredito.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtLimitecredito.setEnabled(false);
            txtLimitecredito.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            txtLimitecredito.setPreferredSize(new java.awt.Dimension(80, 23));
            panelFormulario.add(txtLimitecredito);
            txtLimitecredito.setBounds(610, 80, 130, 25);

            rbEstado.setBackground(new java.awt.Color(255, 255, 255));
            rbEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            rbEstado.setSelected(true);
            rbEstado.setText("Activo");
            rbEstado.setEnabled(false);
            panelFormulario.add(rbEstado);
            rbEstado.setBounds(610, 160, 130, 21);

            labelCorreo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo2.setText("Límite de crédito");
            labelCorreo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo2);
            labelCorreo2.setBounds(470, 80, 120, 25);

            jSeparator3.setBackground(java.awt.SystemColor.inactiveCaption);
            jSeparator3.setForeground(java.awt.SystemColor.inactiveCaption);
            jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator3);
            jSeparator3.setBounds(600, 35, 2, 155);

            panelImage.add(panelFormulario);
            panelFormulario.setBounds(0, 370, 890, 350);

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
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            panelImage.add(panelEncabezado);
            panelEncabezado.setBounds(0, 0, 890, 22);

            getContentPane().add(panelImage, java.awt.BorderLayout.CENTER);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void bnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnBuscarActionPerformed
        // TODO add your handling code here:
        Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        Utilidades.setEditableTexto(this.panelBusqueda, true, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, true, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        model.setRowCount(0);
        txtBusqueda.requestFocus();
    }//GEN-LAST:event_bnBuscarActionPerformed

    private void bnSuprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnSuprimirActionPerformed
        // TODO add your handling code here:
        int resp;
        resp = JOptionPane.showInternalConfirmDialog(this, "¿Desea Eliminar el Registro?", "Pregunta", 0);
        if (resp == 0) {
            int s = 0;

            /* Guardamos el ID de dla fila selecciona en la variable s */
            s = tableResultados.getSelectedRow();

            /* Validamos que hallan seleccionado */
            if (s < 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
                return;
            }

            String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));

            if ((peticiones.eliminarRegistro(nombreTabla, "estado", nombreId, id)) > 0) {
                JOptionPane.showMessageDialog(rootPane, "El registro ha sido Eliminado correctamente ");
                nuevo();
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
        nuevo();

    }//GEN-LAST:event_bnCrearActionPerformed

    private void bnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnGuardarActionPerformed

        if (editar == false) {
            Guardar();
        } else if (editar == true) {
            Modificar();
        }

    }//GEN-LAST:event_bnGuardarActionPerformed

    private void bnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnCancelarActionPerformed
        // TODO add your handling code here:
        Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        Utilidades.setEditableTexto(this.panelBusqueda, true, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, true, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        //model.setRowCount(0);
    }//GEN-LAST:event_bnCancelarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla(txtBusqueda.getText());
        Utilidades.setEditableTexto(this.panelFormulario, false, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, false, null);

    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void tableResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResultadosMouseClicked
        // TODO add your handling code here:
        tableMouseClicked();

    }//GEN-LAST:event_tableResultadosMouseClicked

    private void bnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnEditarActionPerformed

        int s = 0;

        /* Guardamos el ID de dla fila selecciona en la variable s */
        s = tableResultados.getSelectedRow();

        /* Validamos que hallan seleccionado */
        if (s < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro");
            return;
        }
        tableMouseClicked();
        Utilidades.setEditableTexto(this.panelFormulario, true, null, false, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        editar = true;
    }//GEN-LAST:event_bnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnBuscar;
    private javax.swing.JButton bnCancelar;
    private javax.swing.JButton bnCrear;
    private javax.swing.JButton bnDeudores;
    private javax.swing.JButton bnEditar;
    private javax.swing.JButton bnEstadocuenta;
    private javax.swing.JButton bnGuardar;
    private javax.swing.JButton bnSuprimir;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelCorreo1;
    private javax.swing.JLabel labelCorreo2;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelEncabezado;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelNit;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBotonesformulario;
    private javax.swing.JPanel panelBusqueda;
    private jcMousePanel.jcMousePanel panelEncabezado;
    private javax.swing.JPanel panelFormulario;
    private elaprendiz.gui.panel.PanelImage panelImage;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JRadioButton rbEstado;
    private javax.swing.JScrollPane scrollpaneResultados;
    private javax.swing.JTable tableResultados;
    private elaprendiz.gui.textField.TextField txtBusqueda;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtCorreo;
    private elaprendiz.gui.textField.TextField txtDireccion;
    private javax.swing.JFormattedTextField txtLimitecredito;
    private elaprendiz.gui.textField.TextField txtNit;
    private elaprendiz.gui.textField.TextField txtNombre;
    private elaprendiz.gui.textField.TextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
