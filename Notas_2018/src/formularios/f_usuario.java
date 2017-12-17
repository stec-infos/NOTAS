/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.Datos;
import clases.Peticiones;
import clases.Utilidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GLARA
 */
public class f_usuario extends javax.swing.JInternalFrame {

    /**
     * Variables para realizar las transacciones con la base de datos
     */
    String nombreTabla = "usuario";
    String[] titulos = {"Id", "Nombre Usuario", "Usuario", "Puesto", "Estado", "Fecha Reg",};
    String campos = "nombre, usuario, password, puesto, fec_reg, estado";
    String nombreId = "idusuario";

    DefaultTableModel model;
    Datos datos = new Datos();
    Peticiones peticiones = new Peticiones();
    boolean editar = false;

    /**
     * Creates new form Usuarios
     */
    public f_usuario() {
        initComponents();

        /**
         * Oculta la primer columna de la tala, la que contiene el Id
         */
        tableResultados.getColumnModel().getColumn(0).setMaxWidth(0);
        tableResultados.getColumnModel().getColumn(0).setMinWidth(0);
        tableResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    /**
     * Prepara el formulario y jtable para crear un nuevo f_usuario (Habilita y
 limpia los campos correspondientes
     */
    public void nuevo() {
        Utilidades.setEditableTexto(this.tbPane2, true, null, true, "");
        //Utilidades.setEditableTexto(this.panelBusqueda, false, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, false, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);

        model.setRowCount(0);
        txtNombre.requestFocus();
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

    /* Funcion para llenar la tabla cuando se busque un f_usuario en especifico
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
            ResultSet rs = peticiones.consultaUsuario(nombre);
            Object[] registro = new Object[6];

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {

                registro[0] = rs.getString("idusuario");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("usuario");
                //registro[3] = rs.getString("contrasenia");
                registro[3] = rs.getString("puesto");

                if (rs.getString("estado").equals("1")) {
                    registro[4] = ("Activo");
                } else if (rs.getString("estado").equals("0")) {
                    registro[4] = ("Inactivo");
                }
                registro[5] = rs.getString("fec_reg");
                //registro[6] = rs.getBoolean("estado"); //getString("lim_cred");
                model.addRow(registro);
            }
            tableResultados.setModel(model);
            Utilidades.ajustarAnchoColumnas(tableResultados);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }
    }

    /* Funcion para llenar la tabla cuando se busque un f_usuario en especifico
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
                txtNombre.setText(rs.getString("nombre"));
                txtUsuario.setText(rs.getString("usuario"));
                //txtDireccion.setText(rs.getString("nombreusuario"));
                txtPuesto.setText(rs.getString("puesto"));
                txtPassword.setText(rs.getString("password"));
                //txtPassword1.setText(rs.getString("contrasenia"));
                //txtTelefono.setText(rs.getString("telefono"));
                dateFecha.setDate(rs.getDate("fec_reg"));
                //txtLimitecredito.setText(rs.getString("lim_cred"));
                rbEstado.setSelected(rs.getBoolean("estado"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }

    }

    /* Funcion para llenar la tabla cuando se busque un f_usuario en especifico
     por el código, nombre, nit  */
    public void llenarPerfil(String nombre) {

        //  try {
        /* Limpiamos la tabla */
        //model.setRowCount(0);

        /* Llamamos a la funcion consultaClientes la cual nos devuelve todos 
             los clientes relaciones con el valor a buscar en la base de datos. 
            
             - Los datos recibidos lo guardamos en el objeto ResulSet para luego
             llenar la tabla con los registros.
            
         */
        // ResultSet rs = peticiones.consultaMenu(nombre);
        //Object[] registro = new Object[6];

        /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
        //while (rs.next()) {
//                registro[0] = rs.getString("idusuario");
//                registro[1] = rs.getString("nombre");
//                registro[2] = rs.getString("nombreusuario");
//                //registro[3] = rs.getString("contrasenia");
//                registro[3] = rs.getString("puesto");
//
//                if (rs.getString("estado").equals("1")) {
//                    registro[4] = ("Activo");
//                } else if (rs.getString("estado").equals("0")) {
//                    registro[4] = ("Inactivo");
//                }
//                registro[5] = rs.getString("fec_reg");
        //registro[6] = rs.getBoolean("estado"); //getString("lim_cred");
        //model.addRow(registro);
        //  }
        //tableResultados.setModel(model);
        // } catch (SQLException ex) {
        //   JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        //}
    }

    /**
     * Realiza la transacción para guardar los recistros de un nuevo f_usuario
     */
    private void Guardar() {

        if (Utilidades.esObligatorio(this.tbPane2, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] usuario = {
            txtNombre.getText(), txtUsuario.getText(), txtPassword.getText(),
            txtPuesto.getText(), getFecha(), peticiones.selected(rbEstado)
        };

        /* Llamamos a la funcion guardarRegistros la cual recibe como parametro
         el nombre de la tabla, los campos y los valores a insertar del f_usuario */
        int id = peticiones.guardarRegistrosId(nombreTabla, campos, usuario);
        //llenarPerfil(""+id);
        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);

        if (id != 0) {
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

        if (Utilidades.esObligatorio(this.tbPane2, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son"
                    + " Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));

        Object[] usuario = {
            txtNombre.getText(), txtUsuario.getText(), txtPassword.getText(),
            txtPuesto.getText(), getFecha(), peticiones.selected(rbEstado), id
        };

        if (peticiones.actualizarRegistroId(nombreTabla, campos, usuario, nombreId)) {
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
        Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
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
        bnEditar = new javax.swing.JButton();
        panelBusqueda = new javax.swing.JPanel();
        labelBusqueda = new javax.swing.JLabel();
        txtBusqueda = new elaprendiz.gui.textField.TextField();
        panelResultados = new javax.swing.JPanel();
        scrollpaneResultados = new javax.swing.JScrollPane();
        tableResultados = new javax.swing.JTable();
        panelEncabezado = new jcMousePanel.jcMousePanel();
        labelEncabezado = new javax.swing.JLabel();
        panelBotonesformulario = new javax.swing.JPanel();
        bnGuardar = new javax.swing.JButton();
        bnCancelar = new javax.swing.JButton();
        tbPane2 = new elaprendiz.gui.panel.TabbedPaneHeader();
        panelFormulario = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelNit = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        txtNombre = new elaprendiz.gui.textField.TextField();
        txtUsuario = new elaprendiz.gui.textField.TextField();
        txtPuesto = new elaprendiz.gui.textField.TextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        labelCorreo1 = new javax.swing.JLabel();
        rbEstado = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        panelFormulario1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelCompras = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        panelProveedores = new javax.swing.JPanel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        panelVentas = new javax.swing.JPanel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        panelClientes = new javax.swing.JPanel();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        panelProductos = new javax.swing.JPanel();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        panelInventario = new javax.swing.JPanel();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        panelReportes = new javax.swing.JPanel();
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jCheckBox40 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        penelOtros = new javax.swing.JPanel();
        jCheckBox43 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jCheckBox47 = new javax.swing.JCheckBox();
        jCheckBox48 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("usuario"); // NOI18N
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
                .addContainerGap(510, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnSuprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        labelBusqueda.setText("Buscar Usuario");
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

            panelEncabezado.setColor1(new java.awt.Color(102, 153, 255));
            panelEncabezado.setColor2(new java.awt.Color(255, 255, 255));
            panelEncabezado.setModo(3);

            labelEncabezado.setFont(new java.awt.Font("Decker", 1, 17)); // NOI18N
            labelEncabezado.setForeground(new java.awt.Color(255, 255, 255));
            labelEncabezado.setText("USUARIOS");

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

            panelImage.add(panelBotonesformulario);
            panelBotonesformulario.setBounds(0, 660, 890, 60);

            tbPane2.setFont(new java.awt.Font("Decker", 1, 16)); // NOI18N
            tbPane2.setOpaque(true);

            panelFormulario.setBackground(new java.awt.Color(255, 255, 255));
            panelFormulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            panelFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            labelCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCodigo.setText("Nombre*");
            labelCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 120, 25));

            labelNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelNombre.setText("Usuario*");
            labelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, 25));

            labelNit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelNit.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelNit.setText("Contraseña*");
            labelNit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 120, 25));

            labelCorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo.setText("Puesto");
            labelCorreo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 120, 25));

            labelFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelFecha.setText("Fecha*");
            labelFecha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 120, 25));

            txtNombre.setEditable(false);
            txtNombre.setBackground(new java.awt.Color(255, 255, 255));
            txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtNombre.setEnabled(false);
            txtNombre.setName("codigo"); // NOI18N
            txtNombre.setOpaque(true);
            txtNombre.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 250, 25));

            txtUsuario.setEditable(false);
            txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
            txtUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtUsuario.setEnabled(false);
            txtUsuario.setName("txtUsuario"); // NOI18N
            txtUsuario.setOpaque(true);
            txtUsuario.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 250, 25));

            txtPuesto.setEditable(false);
            txtPuesto.setBackground(new java.awt.Color(255, 255, 255));
            txtPuesto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtPuesto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtPuesto.setEnabled(false);
            txtPuesto.setOpaque(true);
            txtPuesto.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 250, 25));

            dateFecha.setBackground(new java.awt.Color(255, 255, 255));
            dateFecha.setDate(Calendar.getInstance().getTime());
            dateFecha.setDateFormatString("dd/MM/yyyy");
            dateFecha.setEnabled(false);
            dateFecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            dateFecha.setIcon(null);
            dateFecha.setMaxSelectableDate(new java.util.Date(3093496470100000L));
            dateFecha.setMinSelectableDate(new java.util.Date(-62135744300000L));
            dateFecha.setPreferredSize(new java.awt.Dimension(120, 22));
            panelFormulario.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 130, 25));

            jSeparator1.setBackground(java.awt.SystemColor.inactiveCaption);
            jSeparator1.setForeground(java.awt.SystemColor.inactiveCaption);
            jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 35, -1, 200));

            labelCorreo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo1.setText("Estado");
            labelCorreo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 120, 25));

            rbEstado.setBackground(new java.awt.Color(255, 255, 255));
            rbEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            rbEstado.setSelected(true);
            rbEstado.setText("Activo");
            rbEstado.setEnabled(false);
            panelFormulario.add(rbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 130, 21));

            jSeparator3.setBackground(java.awt.SystemColor.inactiveCaption);
            jSeparator3.setForeground(java.awt.SystemColor.inactiveCaption);
            jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 35, -1, 155));

            txtPassword.setEditable(false);
            txtPassword.setBackground(new java.awt.Color(255, 255, 255));
            txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            txtPassword.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtPassword.setEnabled(false);
            txtPassword.setName("password"); // NOI18N
            panelFormulario.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 250, 25));

            tbPane2.addTab("Usuariio", panelFormulario);

            panelFormulario1.setBackground(new java.awt.Color(255, 255, 255));
            panelFormulario1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            panelFormulario1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
            jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
            jTabbedPane1.setFont(new java.awt.Font("Decker", 1, 14)); // NOI18N
            jTabbedPane1.setOpaque(true);

            panelCompras.setBackground(new java.awt.Color(255, 255, 255));
            panelCompras.setOpaque(false);

            jCheckBox1.setText("Nueva Compra");
            jCheckBox1.setEnabled(false);

            jCheckBox2.setText("jCheckBox1");
            jCheckBox2.setEnabled(false);

            jCheckBox3.setText("Guardar Compra");
            jCheckBox3.setEnabled(false);

            jCheckBox4.setText("jCheckBox1");
            jCheckBox4.setEnabled(false);

            jCheckBox5.setText("jCheckBox1");
            jCheckBox5.setEnabled(false);

            jCheckBox6.setText("jCheckBox1");
            jCheckBox6.setEnabled(false);

            javax.swing.GroupLayout panelComprasLayout = new javax.swing.GroupLayout(panelCompras);
            panelCompras.setLayout(panelComprasLayout);
            panelComprasLayout.setHorizontalGroup(
                panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelComprasLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelComprasLayout.setVerticalGroup(
                panelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelComprasLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox6)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Compras", panelCompras);

            panelProveedores.setBackground(new java.awt.Color(255, 255, 255));
            panelProveedores.setOpaque(false);

            jCheckBox7.setText("Nuevo");
            jCheckBox7.setEnabled(false);
            jCheckBox7.setName("Nuevo proveedor"); // NOI18N

            jCheckBox8.setText("Modificar");
            jCheckBox8.setEnabled(false);
            jCheckBox8.setName("Modificar proveedor"); // NOI18N

            jCheckBox9.setText("Eleiminar");
            jCheckBox9.setEnabled(false);
            jCheckBox9.setName("Eliminar proveedor"); // NOI18N

            jCheckBox10.setText("Estado de Cuenta");
            jCheckBox10.setEnabled(false);

            jCheckBox11.setText("Abonos");
            jCheckBox11.setEnabled(false);

            jCheckBox12.setText("jCheckBox1");
            jCheckBox12.setEnabled(false);

            javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
            panelProveedores.setLayout(panelProveedoresLayout);
            panelProveedoresLayout.setHorizontalGroup(
                panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelProveedoresLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelProveedoresLayout.setVerticalGroup(
                panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelProveedoresLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox12)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Proveedores", panelProveedores);

            panelVentas.setBackground(new java.awt.Color(255, 255, 255));
            panelVentas.setOpaque(false);

            jCheckBox13.setText("Nuevo");
            jCheckBox13.setEnabled(false);

            jCheckBox14.setText("Modificar");
            jCheckBox14.setEnabled(false);

            jCheckBox15.setText("Eleiminar");
            jCheckBox15.setEnabled(false);

            jCheckBox16.setText("Estado de Cuenta");
            jCheckBox16.setEnabled(false);

            jCheckBox17.setText("Abonos");
            jCheckBox17.setEnabled(false);

            jCheckBox18.setText("jCheckBox1");
            jCheckBox18.setEnabled(false);

            javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
            panelVentas.setLayout(panelVentasLayout);
            panelVentasLayout.setHorizontalGroup(
                panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVentasLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelVentasLayout.setVerticalGroup(
                panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelVentasLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox14)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox15)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox17)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox18)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Ventas", panelVentas);

            panelClientes.setBackground(new java.awt.Color(255, 255, 255));
            panelClientes.setOpaque(false);

            jCheckBox19.setText("Nuevo");
            jCheckBox19.setEnabled(false);

            jCheckBox20.setText("Modificar");
            jCheckBox20.setEnabled(false);

            jCheckBox21.setText("Eleiminar");
            jCheckBox21.setEnabled(false);

            jCheckBox22.setText("Estado de Cuenta");
            jCheckBox22.setEnabled(false);

            jCheckBox23.setText("Abonos");
            jCheckBox23.setEnabled(false);

            jCheckBox24.setText("jCheckBox1");
            jCheckBox24.setEnabled(false);

            javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
            panelClientes.setLayout(panelClientesLayout);
            panelClientesLayout.setHorizontalGroup(
                panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelClientesLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox19, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelClientesLayout.setVerticalGroup(
                panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelClientesLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox19)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox20)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox21)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox23)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox24)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Clientes", panelClientes);

            panelProductos.setBackground(new java.awt.Color(255, 255, 255));
            panelProductos.setOpaque(false);

            jCheckBox25.setText("Nuevo");
            jCheckBox25.setEnabled(false);

            jCheckBox26.setText("Modificar");
            jCheckBox26.setEnabled(false);

            jCheckBox27.setText("Eleiminar");
            jCheckBox27.setEnabled(false);

            jCheckBox28.setText("Estado de Cuenta");
            jCheckBox28.setEnabled(false);

            jCheckBox29.setText("Abonos");
            jCheckBox29.setEnabled(false);

            jCheckBox30.setText("jCheckBox1");
            jCheckBox30.setEnabled(false);

            javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
            panelProductos.setLayout(panelProductosLayout);
            panelProductosLayout.setHorizontalGroup(
                panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelProductosLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox29, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelProductosLayout.setVerticalGroup(
                panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelProductosLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox25)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox27)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox28)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox29)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox30)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Productos", panelProductos);

            panelInventario.setBackground(new java.awt.Color(255, 255, 255));
            panelInventario.setOpaque(false);

            jCheckBox31.setText("Nuevo");
            jCheckBox31.setEnabled(false);

            jCheckBox32.setText("Modificar");
            jCheckBox32.setEnabled(false);

            jCheckBox33.setText("Eleiminar");
            jCheckBox33.setEnabled(false);

            jCheckBox34.setText("Estado de Cuenta");
            jCheckBox34.setEnabled(false);

            jCheckBox35.setText("Abonos");
            jCheckBox35.setEnabled(false);

            jCheckBox36.setText("jCheckBox1");
            jCheckBox36.setEnabled(false);

            javax.swing.GroupLayout panelInventarioLayout = new javax.swing.GroupLayout(panelInventario);
            panelInventario.setLayout(panelInventarioLayout);
            panelInventarioLayout.setHorizontalGroup(
                panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventarioLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelInventarioLayout.setVerticalGroup(
                panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventarioLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox31)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox33)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox34)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox35)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox36)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Inventario", panelInventario);

            panelReportes.setBackground(new java.awt.Color(255, 255, 255));
            panelReportes.setOpaque(false);

            jCheckBox37.setText("Nuevo");
            jCheckBox37.setEnabled(false);

            jCheckBox38.setText("Modificar");
            jCheckBox38.setEnabled(false);

            jCheckBox39.setText("Eleiminar");
            jCheckBox39.setEnabled(false);

            jCheckBox40.setText("Estado de Cuenta");
            jCheckBox40.setEnabled(false);

            jCheckBox41.setText("Abonos");
            jCheckBox41.setEnabled(false);

            jCheckBox42.setText("jCheckBox1");
            jCheckBox42.setEnabled(false);

            javax.swing.GroupLayout panelReportesLayout = new javax.swing.GroupLayout(panelReportes);
            panelReportes.setLayout(panelReportesLayout);
            panelReportesLayout.setHorizontalGroup(
                panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelReportesLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox41, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox42, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox39, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox37, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            panelReportesLayout.setVerticalGroup(
                panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelReportesLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox37)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox39)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox40)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox41)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox42)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Reportes", panelReportes);

            penelOtros.setBackground(new java.awt.Color(255, 255, 255));
            penelOtros.setOpaque(false);

            jCheckBox43.setText("Nuevo");
            jCheckBox43.setEnabled(false);

            jCheckBox44.setText("Modificar");
            jCheckBox44.setEnabled(false);

            jCheckBox45.setText("Eleiminar");
            jCheckBox45.setEnabled(false);

            jCheckBox46.setText("Estado de Cuenta");
            jCheckBox46.setEnabled(false);

            jCheckBox47.setText("Abonos");
            jCheckBox47.setEnabled(false);

            jCheckBox48.setText("jCheckBox1");
            jCheckBox48.setEnabled(false);

            javax.swing.GroupLayout penelOtrosLayout = new javax.swing.GroupLayout(penelOtros);
            penelOtros.setLayout(penelOtrosLayout);
            penelOtrosLayout.setHorizontalGroup(
                penelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(penelOtrosLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(penelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox47, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox48, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox46, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox44, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox45, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox43, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            penelOtrosLayout.setVerticalGroup(
                penelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(penelOtrosLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jCheckBox43)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox44)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox45)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox47)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jCheckBox48)
                    .addContainerGap(56, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Otros", penelOtros);

            panelFormulario1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 490, 235));

            tbPane2.addTab("Permisos", panelFormulario1);

            panelImage.add(tbPane2);
            tbPane2.setBounds(0, 370, 890, 292);

            getContentPane().add(panelImage, java.awt.BorderLayout.CENTER);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void bnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnBuscarActionPerformed
        // TODO add your handling code here:
        Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
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
        Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        Utilidades.setEditableTexto(this.panelBusqueda, true, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, true, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, false, editar);
        //model.setRowCount(0);
    }//GEN-LAST:event_bnCancelarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla(txtBusqueda.getText());
        Utilidades.setEditableTexto(this.tbPane2, false, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, false, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, false, editar);

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
        Utilidades.setEditableTexto(this.tbPane2, true, null, false, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);
        editar = true;
    }//GEN-LAST:event_bnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnBuscar;
    private javax.swing.JButton bnCancelar;
    private javax.swing.JButton bnCrear;
    private javax.swing.JButton bnEditar;
    private javax.swing.JButton bnGuardar;
    private javax.swing.JButton bnSuprimir;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox47;
    private javax.swing.JCheckBox jCheckBox48;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelCorreo1;
    private javax.swing.JLabel labelEncabezado;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelNit;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBotonesformulario;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelCompras;
    private jcMousePanel.jcMousePanel panelEncabezado;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelFormulario1;
    private elaprendiz.gui.panel.PanelImage panelImage;
    private javax.swing.JPanel panelInventario;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JPanel penelOtros;
    private javax.swing.JRadioButton rbEstado;
    private javax.swing.JScrollPane scrollpaneResultados;
    private javax.swing.JTable tableResultados;
    private elaprendiz.gui.panel.TabbedPaneHeader tbPane2;
    private elaprendiz.gui.textField.TextField txtBusqueda;
    private elaprendiz.gui.textField.TextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private elaprendiz.gui.textField.TextField txtPuesto;
    private elaprendiz.gui.textField.TextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
