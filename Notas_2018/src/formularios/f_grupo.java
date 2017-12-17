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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Opcion;

/**
 *
 * @author GLARA
 */
public class f_grupo extends javax.swing.JInternalFrame {

    /**
     * Variables para realizar las transacciones con la base de datos
     */
    String nombreTabla = "grupo";
    String[] titulos = {"Id", "Codigo", "Descripción", "Fecha Inicio", "No. Alumno", "estado"};
    String campos = "codigo, descripcion, fechainicio, fechafin, cantalumnos, idcarrera, idseccion, estado ";
    String nombreId = "idgrupo";

    public Hashtable<String, String> hashUnidad = new Hashtable<>();
    public Hashtable<String, String> hashUnidad2 = new Hashtable<>();

    public Hashtable<String, String> hashCategoria = new Hashtable<>();
    public Hashtable<String, String> hashCategoria2 = new Hashtable<>();
    DefaultTableModel model;
    Datos datos = new Datos();
    Peticiones peticiones = new Peticiones();
    boolean editar = false;

    /**
     * Creates new form Productos
     */
    public f_grupo() {
        initComponents();
        llenarcombobox();

        /**
         * Oculta la primer columna de la tala, la que contiene el Id
         */
        tableResultados.getColumnModel().getColumn(0).setMaxWidth(0);
        tableResultados.getColumnModel().getColumn(0).setMinWidth(0);
        tableResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void llenarcombobox() {

        try {

            /* Instaciamos un objeto de la clase Opcion para cargar el combo box
             de los proveedores  */
            Opcion op = new Opcion("0", " ");

            /* Añadimos el primer elemento al combo box */
            comboCarrera.addItem(op);

            /* Llamos a la funcion consultaUnidad la cual nos devuelve todas las
             Unidades que hay, esos datos los guardamos en un ResultSet para luego
             llenar el combo box con todas las Unidades */
            ResultSet rs = peticiones.consultaCarrera("");

            /* Hacemos un while que mientras hallan registros en rs, sobreescrira
             al objeto de la clase opcion con los datos del objeto rs, y los añada
             al combo box */
            int count = 0;
            while (rs.next()) {
                count++;
                op = new Opcion(
                        rs.getString("idcarrera"),
                        rs.getString("descripcion"));
                comboCarrera.addItem(op);
                hashUnidad.put(rs.getString("descripcion"), "" + count);
                hashUnidad2.put(rs.getString("idcarrera"), rs.getString("descripcion"));
            }

            /* Instaciamos un objeto de la clase Opcion para cargar el combo box
             de los servicios  */
            Opcion op2 = new Opcion("0", " ");

            /* Añadimos el primer elemento al combo box */
            comboSeccion.addItem(op2);

            /* Llamos a la funcion getServicios la cual nos devuelve todos los
             Servicios que hay, esos datos los guardamos en un ResultSet para luego
             llenar el combo box con todos los Servicios */
            ResultSet rsSer = peticiones.consultaSeccion("");

            /* Hacemos un while que mientras hallan registros en rs, sobreescrira
             al objeto de la clase opcion con los datos del objeto rs, y los añada
             al combo box */
            int count2 = 0;
            while (rsSer.next()) {
                count2++;
                op2 = new Opcion(
                        rsSer.getString("idseccion"),
                        rsSer.getString("nombre"));
                comboSeccion.addItem(op2);
                hashCategoria.put(rsSer.getString("nombre"), "" + count2);
                hashCategoria2.put(rsSer.getString("idseccion"), rsSer.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problema con: " + e.getMessage());
        }
    }

    /**
     * Prepara el formulario y jtable para crear un nuevo grupo (Habilita y
 limpia los campos correspondientes
     */
    private void nuevo() {
        Utilidades.setEditableTexto(this.tbPane2, true, null, true, "");
        //Utilidades.setEditableTexto(this.panelBusqueda, false, null, true, "");
        Utilidades.setEditableTexto(this.panelResultados, false, null, true, "");
        Utilidades.buscarBotones(this.panelBotonesformulario, true, null);
        //Utilidades.buscarMenu(this.jTabbedPane1, true, editar);

        model.setRowCount(0);
        txtNumero.requestFocus();
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
            int años = dateFechaInicio.getCalendar().get(Calendar.YEAR);
            int dias = dateFechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mess = dateFechaInicio.getCalendar().get(Calendar.MONTH) + 1;
            int hours = dateFechaInicio.getCalendar().get(Calendar.HOUR_OF_DAY);
            int minutes = dateFechaInicio.getCalendar().get(Calendar.MINUTE);
            int seconds = dateFechaInicio.getCalendar().get(Calendar.SECOND);

            fecha = "" + años + "-" + mess + "-" + dias + " " + hours + ":" + minutes + ":" + seconds;
            return fecha;
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(this, "Verifique la fecha");

        }
        return null;
    }
    /**
     * Obtiene la fecha de un JDateChooser, y devuelve la fecha como un string
     *
     * @return fecha
     */
    private String getFecha1() {

        try {
            String fecha;
            int años = dateFechaFin.getCalendar().get(Calendar.YEAR);
            int dias = dateFechaFin.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mess = dateFechaFin.getCalendar().get(Calendar.MONTH) + 1;
            int hours = dateFechaFin.getCalendar().get(Calendar.HOUR_OF_DAY);
            int minutes = dateFechaFin.getCalendar().get(Calendar.MINUTE);
            int seconds = dateFechaFin.getCalendar().get(Calendar.SECOND);

            fecha = "" + años + "-" + mess + "-" + dias + " " + hours + ":" + minutes + ":" + seconds;
            return fecha;
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(this, "Verifique la fecha");

        }
        return null;
    }

    /* Funcion para llenar la tabla cuando se busque un grupo en especifico
     por el código, nombre, nit  */
    private void llenarTabla(String nombre) {

        try {
            /* Limpiamos la tabla */
            model.setRowCount(0);

            /* Llamamos a la funcion consultaProducto la cual nos devuelve todos 
             los productos relaciones con el valor a buscar en la base de datos. 
            
             - Los datos recibidos lo guardamos en el objeto ResulSet para luego
             llenar la tabla con los registros.
            
             */
            ResultSet rs = peticiones.consultaGrupo(nombre);
            Object[] registro = new Object[7];

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {

                registro[0] = rs.getString("idgrupo");
                registro[1] = rs.getString("codigo");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getDate("fechainicio");
                registro[4] = rs.getDate("fechafin");
                registro[5] = rs.getString("cantalumnos");
               

                if (rs.getString("estado").equals("1")) {
                    registro[6] = ("Activo");
                } else if (rs.getString("estado").equals("0")) {
                    registro[6] = ("Inactivo");
                }
                //registro[7] = rs.getString("fec_reg");
                model.addRow(registro);
            }
            tableResultados.setModel(model);
            Utilidades.ajustarAnchoColumnas(tableResultados);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }
    }

    /* Funcion para llenar la tabla cuando se busque un grupo en especifico
     por el Id */
    private void llenarFormulario(int s) {

        try {

            /* Llamamos a la funcion consultaRegistrosId la cual nos devuelve todos 
             los productos relaciones con el id a buscar en la base de datos. 
            
             - Los datos recibidos lo guardamos en el objeto ResulSet para luego
             llenar la tabla con los registros.
            
             */
            ResultSet rs = peticiones.consultaRegistrosId(nombreTabla,
                    (String) tableResultados.getValueAt(s, 0), nombreId);

            /* Hacemos un while que mientras en rs hallan datos el ira agregando
             filas a la tabla. */
            while (rs.next()) {
                txtNumero.setText(rs.getString("codigo"));
                txtNombre.setText(rs.getString("descripcion"));
                txtcantidadALum.setText(rs.getString("cantalumnos"));
                comboCarrera.setEditable(true);
                            
                int u = Integer.parseInt((String) hashUnidad.get(hashUnidad2.get(rs.getString("idcarrera"))));
                comboCarrera.setSelectedIndex(u);
                comboSeccion.setEditable(true);
                int c = Integer.parseInt((String) hashCategoria.get(hashCategoria2.get(rs.getString("idseccion"))));
                comboSeccion.setSelectedIndex(c);
                //txtObservacion.setText(rs.getString("observacion"));
                dateFechaInicio.setDate(rs.getDate("fechainicio"));
                dateFechaFin.setDate(rs.getDate("fechafin"));
                rbEstado.setSelected(rs.getBoolean("estado"));
                //txtUbicacion.setText(rs.getString("ubicacion"));
                //txtMinimo.setText(rs.getString("invminimo"));
                //txtExistencia.setText(rs.getString("existencia"));
                //txtCosto.setText(rs.getString("preciocoste"));
                //txtVenta.setText(rs.getString("precioventa"));
                //txtMayoreo.setText(rs.getString("preciomayoreo"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        }
    }

    /**
     * Realiza la transacción para guardar los recistros de un nuevo grupo
     */
    private void Guardar() {

        if (Utilidades.esObligatorio(this.panelFormulario, true)) {

            //Utilidades.esObligatorio(this.panelFormulario1, true);
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if (Utilidades.esObligatorio(this.panelFormulario1, true)) {
//
//            Utilidades.esObligatorio(this.panelFormulario, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        Object[] producto = {
            txtNumero.getText(), txtNombre.getText(), getFecha(), getFecha1(), txtcantidadALum.getText(),
            Integer.parseInt(((Opcion) comboCarrera.getSelectedItem()).getValor()),
               Integer.parseInt(((Opcion) comboSeccion.getSelectedItem()).getValor()),
            //Integer.parseInt(((Opcion) comboCategoria.getSelectedItem()).getValor()),
            //txtObservacion.getText(), getFecha(),
            peticiones.selected(rbEstado)
        };

        /* Llamamos a la funcion guardarRegistrosId la cual recibe como parametro
         el nombre de la tabla, los campos y los valores a insertar del grupo */
        int id = peticiones.guardarRegistrosId(nombreTabla, campos, producto);

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

        if (Utilidades.esObligatorio(this.panelFormulario, true)) {

           // Utilidades.esObligatorio(this.panelFormulario1, true);
            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if (Utilidades.esObligatorio(this.panelFormulario1, true)) {
//
//            Utilidades.esObligatorio(this.panelFormulario, true);
//            JOptionPane.showInternalMessageDialog(this, "Los campos marcados son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        String id = Utilidades.objectToString(tableResultados.getValueAt(s, 0));

        Object[] grupo = {
            txtNumero.getText(), txtNombre.getText(), getFecha(), getFecha1(), txtcantidadALum.getText(),
            Integer.parseInt(((Opcion) comboCarrera.getSelectedItem()).getValor()),
               Integer.parseInt(((Opcion) comboSeccion.getSelectedItem()).getValor()),
            //Integer.parseInt(((Opcion) comboCategoria.getSelectedItem()).getValor()),
            //txtObservacion.getText(), getFecha(),
            peticiones.selected(rbEstado), id
        };

        if (peticiones.actualizarRegistroId(nombreTabla, campos, grupo, nombreId)) {
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
        panelBotonesformulario = new javax.swing.JPanel();
        bnGuardar = new javax.swing.JButton();
        bnCancelar = new javax.swing.JButton();
        tbPane2 = new elaprendiz.gui.panel.TabbedPaneHeader();
        panelFormulario = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        txtNumero = new elaprendiz.gui.textField.TextField();
        txtNombre = new elaprendiz.gui.textField.TextField();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        labelCorreo1 = new javax.swing.JLabel();
        rbEstado = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        labelCorreo2 = new javax.swing.JLabel();
        comboCarrera = new javax.swing.JComboBox();
        dateFechaFin = new com.toedter.calendar.JDateChooser();
        labelFecha1 = new javax.swing.JLabel();
        labelCorreo3 = new javax.swing.JLabel();
        txtcantidadALum = new elaprendiz.gui.textField.TextField();
        labelCorreo4 = new javax.swing.JLabel();
        comboSeccion = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("producto"); // NOI18N
        setOpaque(true);

        panelImage.setBackground(new java.awt.Color(255, 255, 255));
        panelImage.setLayout(null);

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

        panelBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBusqueda.setPreferredSize(new java.awt.Dimension(890, 82));
        panelBusqueda.setLayout(null);

        labelBusqueda.setFont(new java.awt.Font("Decker", 1, 22)); // NOI18N
        labelBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBusqueda.setText("Buscar Grupo");
        panelBusqueda.add(labelBusqueda);
        labelBusqueda.setBounds(240, 10, 380, 29);

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

        tableResultados.setAutoCreateRowSorter(true);
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
            labelEncabezado.setText("GRUPO");

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
            labelCodigo.setText("Código*");
            labelCodigo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 120, 25));

            labelCorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo.setText("Carrera*");
            labelCorreo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 120, 25));

            labelFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelFecha.setText("Fecha Inicio*");
            labelFecha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 120, 25));

            txtNumero.setEditable(false);
            txtNumero.setBackground(new java.awt.Color(255, 255, 255));
            txtNumero.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNumero.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtNumero.setEnabled(false);
            txtNumero.setName("codigo"); // NOI18N
            txtNumero.setOpaque(true);
            txtNumero.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 320, 25));

            txtNombre.setEditable(false);
            txtNombre.setBackground(new java.awt.Color(255, 255, 255));
            txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtNombre.setEnabled(false);
            txtNombre.setName("descripcion"); // NOI18N
            txtNombre.setOpaque(true);
            txtNombre.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 320, 25));

            dateFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
            dateFechaInicio.setDate(Calendar.getInstance().getTime());
            dateFechaInicio.setDateFormatString("dd/MM/yyyy");
            dateFechaInicio.setEnabled(false);
            dateFechaInicio.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            dateFechaInicio.setIcon(null);
            dateFechaInicio.setMaxSelectableDate(new java.util.Date(3093496470100000L));
            dateFechaInicio.setMinSelectableDate(new java.util.Date(-62135744300000L));
            dateFechaInicio.setPreferredSize(new java.awt.Dimension(120, 22));
            panelFormulario.add(dateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 130, 25));

            jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 35, -1, 200));

            labelCorreo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo1.setText("Estado");
            labelCorreo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 120, 25));

            rbEstado.setBackground(new java.awt.Color(255, 255, 255));
            rbEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            rbEstado.setSelected(true);
            rbEstado.setText("Activo");
            rbEstado.setEnabled(false);
            panelFormulario.add(rbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 130, 25));

            jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
            panelFormulario.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 35, -1, 200));

            labelCorreo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo2.setText("Descripción*");
            labelCorreo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 120, 25));

            comboCarrera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            comboCarrera.setEnabled(false);
            comboCarrera.setName("unidad"); // NOI18N
            panelFormulario.add(comboCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 320, 27));

            dateFechaFin.setBackground(new java.awt.Color(255, 255, 255));
            dateFechaFin.setDate(Calendar.getInstance().getTime());
            dateFechaFin.setDateFormatString("dd/MM/yyyy");
            dateFechaFin.setEnabled(false);
            dateFechaFin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            dateFechaFin.setIcon(null);
            dateFechaFin.setMaxSelectableDate(new java.util.Date(3093496470100000L));
            dateFechaFin.setMinSelectableDate(new java.util.Date(-62135744300000L));
            dateFechaFin.setPreferredSize(new java.awt.Dimension(120, 22));
            panelFormulario.add(dateFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 130, 25));

            labelFecha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelFecha1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelFecha1.setText("Fecha Fin*");
            labelFecha1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 120, 25));

            labelCorreo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo3.setText("No. Alumnos*");
            labelCorreo3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, 25));

            txtcantidadALum.setEditable(false);
            txtcantidadALum.setBackground(new java.awt.Color(255, 255, 255));
            txtcantidadALum.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtcantidadALum.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            txtcantidadALum.setEnabled(false);
            txtcantidadALum.setName("descripcion"); // NOI18N
            txtcantidadALum.setOpaque(true);
            txtcantidadALum.setPreferredSize(new java.awt.Dimension(120, 21));
            panelFormulario.add(txtcantidadALum, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 320, 25));

            labelCorreo4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            labelCorreo4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            labelCorreo4.setText("Sección*");
            labelCorreo4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            panelFormulario.add(labelCorreo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 120, 25));

            comboSeccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            comboSeccion.setEnabled(false);
            comboSeccion.setName("unidad"); // NOI18N
            panelFormulario.add(comboSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 320, 27));

            tbPane2.addTab("Información", panelFormulario);

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
    private javax.swing.JButton bnDeudores;
    private javax.swing.JButton bnEditar;
    private javax.swing.JButton bnEstadocuenta;
    private javax.swing.JButton bnGuardar;
    private javax.swing.JButton bnSuprimir;
    private javax.swing.JComboBox comboCarrera;
    private javax.swing.JComboBox comboSeccion;
    private com.toedter.calendar.JDateChooser dateFechaFin;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelBusqueda;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelCorreo1;
    private javax.swing.JLabel labelCorreo2;
    private javax.swing.JLabel labelCorreo3;
    private javax.swing.JLabel labelCorreo4;
    private javax.swing.JLabel labelEncabezado;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFecha1;
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
    private elaprendiz.gui.panel.TabbedPaneHeader tbPane2;
    private elaprendiz.gui.textField.TextField txtBusqueda;
    private elaprendiz.gui.textField.TextField txtNombre;
    private elaprendiz.gui.textField.TextField txtNumero;
    private elaprendiz.gui.textField.TextField txtcantidadALum;
    // End of variables declaration//GEN-END:variables
}
