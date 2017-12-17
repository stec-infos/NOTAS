package clases;

/**
 *
 * @author GLARA
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Datos {

    private Connection con;
    public PreparedStatement ps;
    public String sql;

    public Datos() {
        con = Conexion.getConexion();

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost/caja";
//            con = DriverManager.getConnection(url, "root", "superprecios");
//        } catch (Exception ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    public Connection getCon() {
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost/caja";
//            con = DriverManager.getConnection(url, "root", "superprecios");
//            return con;
//        } catch (Exception ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//    public ResultSet validarUsuario(String usuario, String clave) {
//        try {
//            /* Definimos la consulta en la base datos
//             select 1 significa que si hay registro devuelve 1 si no 0 */
//            String sql = "SELECT cliente.id_personal,  cliente.nom_per, "
//                    + "cliente.ape_per, cliente.usuario, cliente.cargo, usuarios.tipo_usu "
//                    + "FROM cliente "
//                    + "INNER JOIN usuarios ON cliente.usuario = usuarios.usuario "
//                    + "WHERE usuarios.usuario = '" + usuario + "'"
//                    + "AND usuarios.clave = '" + clave + "'";
//
//            /* Creamos el statement para poder enviarle la sentencia sql */
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//    /* Funcion para insertar un proyecto a la base de datos la cual recibe
//     como parametro un objeto de la clase Proyecto */
//    public boolean agregarProyecto(Proyecto proyecto) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla proyecto */
//            String sql = "INSERT INTO proyecto VALUES("
//                    + proyecto.getIdProyecto() + ", '"
//                    + proyecto.getNombre() + "')";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    /* Funcion para insertar un usuario a la base de datos la cual recibe
//     como parametro un OBJETO de la clase Usuario */
//    public boolean agregarUsuario(Usuario usuario) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla usuarios */
//            String sql = "INSERT INTO usuarios VALUES("
//                    + usuario.getIdUsuario() + ", "
//                    + usuario.getIdTipo() + ", '"
//                    + usuario.getUsuario() + "', '"
//                    + usuario.getClave() + "', '"
//                    + usuario.getCorreo() + "')";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
    /**
     * Este metodo une los items de un array en una cadena, pero separado por
     * una coma<br/>
     * Y ademas verifica el tipo de dato de cada item, si el item no fuera
     * numeric, entonces<br/>
     * envuelve este item entre comiilas.
     *
     * @param valores
     * @return cadena
     */
    public String formatearValores(Object[] valores) {
        String cad = "* ";
        Number nb = null;
        if (valores != null) {
            if (valores.length > 0) {
                cad = "";
                if (valores.length == 1) {
                    nb = esNumerico(valores[0], null);
                    cad += (nb == null ? "'" + valores[0] + "'" : nb);
                    return cad;
                }
                for (int i = 0; i < valores.length; i++) {
                    nb = esNumerico(valores[i], valores[i]);
                    cad += (i == 0 ? " " : ", ") + (nb != null ? nb : "'" + valores[i] + "'");
                }
            }
        }
        return cad;
    }

    /**
     * Este metodo genera una cadena con simbolos "?" separada por comas <br/>
     *
     * @param num cantidad de repeticiones
     *
     * @return
     */
    public String formatearValores(int num) {
        String cad = "";
        if (num > 0) {
            cad = "";
            for (int i = 0; i < num; i++) {
                cad += (i == 0 ? " " : ", ") + "?";
            }
        }
        return cad;
    }

    /**
     * Verifica si un objetos es numerico
     *
     * @param valor
     * @param obj no se usa para nadaa
     * @return
     */
    public Number esNumerico(Object valor, Object obj) {
        Number nb = null;
        if (valor instanceof Number) {
            nb = (Number) valor;
            return nb;
        } else {
            return null;
        }
    }

    /**
     * verifica si un Objetc es de tipo numerico
     *
     * @param valor
     * @return
     */
    public boolean esNumerico(Object valor) {
        if (valor instanceof Number) {
            return true;
        } else {
            return false;
        }
    }

    private Integer getInt(Object valor) {
        Integer vl = null;
        if (valor instanceof Integer) {
            vl = (Integer) valor;
        }
        return vl;
    }

    private Double getDouble(Object valor) {
        Double vl = null;
        if (valor instanceof Double) {
            vl = (Double) valor;
        }
        return vl;
    }

    private Float getFloat(Object valor) {
        Float vl = null;
        if (valor instanceof Float) {
            vl = (Float) valor;
        }
        return vl;
    }

    /**
     *
     * @param cad cadena
     * @param separador simbolo para dividir la cadena
     * @return devuelve array de cadenas
     */
    public String[] stringToArray(String cad, String separador) {
        return cad.split(separador);
    }

    /**
     * Este metodo une los items de una array en una cadena, pero separado por
     * una coma
     *
     * @param campos array de cadenas
     * @return cadena
     */
    public String generarArrayAString(String[] campos) {
        String cad = "* ";
        if (campos != null) {
            if (campos.length > 0) {
                cad = "";
                if (campos.length == 1) {
                    return cad + campos[0];
                }
                for (int i = 0; i < campos.length; i++) {
                    cad += (i == 0 ? " " : ", ") + campos[i];
                }
            }
        }
        return cad;
    }

    /**
     *
     * @param cad cadena
     * @param separador simbolo separador, utilizado para partir la cadena
     * @param simbolo simbolo que asigna a cada item que se genere de la cadena
     * @return cadena: (nome = ?)
     */
    public String adjuntarSimbolo(String cad, String separador, String simbolo) {
        String[] campos = stringToArray(cad, separador);
        String ncad = "";
        for (int i = 0; i < campos.length; i++) {
            ncad += (i == 0 ? " " : ", ") + campos[i] + "= " + simbolo;
        }

        return ncad;
    }

    /* Funcion para insertar un empleado a la base de datos la cual recibe
     como parametro un objeto de la clase cliente */
    public boolean guardar(String nombreTabla, String[] campos, Object[] valores) {
        try {
            //con = Conexion.getConexion();
            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
             insert a la tabla cliente */
            sql = "insert into " + nombreTabla + "(" + generarArrayAString(campos) + ") values("
                    + formatearValores(valores.length) + ")";
            
            //System.out.print(sql+"\n");
            /* El createStatement cree un cuadro donde se puede insertar codigo
             sql, el statement se podria decir que es el cuadro en blanco que
             te da el phpmyadmin para insertar codigo sql. */
            ps = Conexion.getPreparedStatement(sql);
            setValores(ps, valores);

            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
             codigo sql que definimos en la variable sql. */
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Ya existe el código, porfavor asigne uno diferente");
            } else {
                JOptionPane.showMessageDialog(null, "Problema con: " + ex.getErrorCode());
            }
            return false;
        }
    }

    /* Funcion para insertar un empleado a la base de datos la cual recibe
     como parametro un objeto de la clase cliente */
    public int guardarReturnId(String nombreTabla, String[] campos, Object[] valores) {
        int id = 0;
        try {
            //con = Conexion.getConexion();
            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
             insert a la tabla cliente */
            sql = "insert into " + nombreTabla + "(" + generarArrayAString(campos) + ") values("
                    + formatearValores(valores.length) + ")";

            /* El createStatement cree un cuadro donde se puede insertar codigo
             sql, el statement se podria decir que es el cuadro en blanco que
             te da el phpmyadmin para insertar codigo sql. */
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //ps = Conexion.getPreparedStatement(sql);
            setValores(ps, valores);

            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
             codigo sql que definimos en la variable sql. */
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);//retorna el idrecibo guardado
            }
            ps.close();
            return id;

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Ya existe el código, porfavor asigne uno diferente");
            } else {
                //System.out.print(sql+"\n");
                JOptionPane.showMessageDialog(null, "Problema con: " + ex.getErrorCode() + "  "+ex.getMessage());
            }
            return id;
        }
    }

    /**
     * se debe especificar el valor da la calve primaria
     *
     * @param nomTabla nombre tabla
     * @param cnls lista de columnas y sus valor ejem: nombre= ?
     * @param columnaId campo id
     * @param id id
     * @return
     */
    public boolean modificar(String nomTabla, String cnls, Object[] valores) {

        sql = "update " + nomTabla + " set " + cnls;
        //System.out.print(sql + "\n");

        boolean op = false;
        try {
            ps = Conexion.getPreparedStatement(sql);
            setValores(ps, valores);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Ya existe el código, porfavor asigne uno diferente");
            } else {
                JOptionPane.showMessageDialog(null, "Problema con: " + ex);
            }

        }
        return op;
    }

    /**
     * Si el dato no esta en otras tablas se borrara totalmente de la BD
     *
     * @param nombreTabla nombre de la Tabla
     * @param nomColumnaId nombre de la columnas con las claves primarias
     * @param id clave primaria
     *
     * Si el dato esta en otras tablas no se borrara totalmente de la BD solo se
     * le cambiara el estado
     * @param nomColumnaCambiar nombre de la columan de la cual se cambiara el
     * valor si no es posible eliminarlo totalmetne de la bd
     * @return
     */
    public int eliminar(String nombreTabla, String nomColumnaCambiar, String nomColumnaId, Object id) {
        sql = "delete from " + nombreTabla + " where " + nomColumnaId + "= " + "'" + id + "'";
        int opcion = 0;
        try {
            opcion = Conexion.getStatement().executeUpdate(sql);
            Conexion.cerrarEnlacesConexion(Conexion.SOLO_STATEMENT);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                Object nuevoValor = "0";
                opcion = eliminarTemporal(nombreTabla, nomColumnaCambiar, nomColumnaId, id, nuevoValor);
            } else {
                JOptionPane.showMessageDialog(null, "Problema con: " + ex);
            }
        }
        return opcion;
    }

    /**
     *
     * @param nombreTabla nombre de la Tabla
     * @param nomColumnaCambiar nombre de la columan de la cual se cambiara el
     * valor
     * @param nomColumnaId nombre de la columnas con las claves primarias
     * @param id clave primaria
     * @param nuevoValor nuevo valor.
     * @return
     */
    public int eliminarTemporal(String nombreTabla, String nomColumnaCambiar, String nomColumnaId, Object id, Object nuevoValor) {
        int resultado = 0;

        Number vl = null;
        Number nv = null;
        if (esNumerico(id)) {
            vl = (Number) id;
        } else {
            id = "'" + id + "'";
        }

        if (esNumerico(nuevoValor)) {
            nv = (Number) nuevoValor;
        } else {
            nuevoValor = "'" + nuevoValor + "'";
        }

        String sql = "UPDATE " + nombreTabla + " SET " + nomColumnaCambiar + " = " + (nv == null ? nuevoValor : nv) + " where " + nomColumnaId + "=" + (vl == null ? id : vl) + "";
        try {
            resultado = Conexion.getStatement().executeUpdate(sql);
            //System.out.print(resultado);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema con: " + ex);
        }
        //System.out.println(sql);
        return resultado;
    }

//    /* Funcion para insertar un empleado a la base de datos la cual recibe
//     como parametro un objeto de la clase cliente */
//    public boolean modificarPersonal(CCliente cliente, String s) {
//        try {
//            //con = Conexion.getConexion();
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla cliente */
//            //String sql = "INSERT INTO clientes VALUES("
//
//            Object[] valores = {cliente.getNombre(), cliente.getDireccion(), cliente.getCorreo(),
//                cliente.getNit(), cliente.getTelefono(), cliente.getFec_reg(), cliente.getLimitecredito(), s
//            };
//
//            String sql = "update clientes set nombre= ?, direccion= ?,correo= ?,nit= ?,telefono= ?,fec_reg= ?,lim_cred= ? where idClientes =? ";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            //Statement st = con.createStatement();          
//            ps = Conexion.getPreparedStatement(sql);
//            setValores(ps, valores);
//            //op = ps.executeUpdate();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            ps.executeUpdate(sql);
//            ps.close();
//            return true;
//        } catch (SQLException ex) {
//            System.out.print(ex.getMessage());
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
    /**
     * establece valores a un objeto PreparedStatement para luego ser ejecutado
     *
     * @param ps
     * @param valores
     */
    private void setValores(PreparedStatement ps, Object[] valores) {
        try {
            for (int i = 0; i < valores.length; i++) {

                //System.out.print(valores[i] + "\n");
                if (getInt(valores[i]) != null) {
                    ps.setInt(i + 1, getInt(valores[i]));
                } else if (getDouble(valores[i]) != null) {
                    ps.setDouble(i + 1, getDouble(valores[i]));
                } else if (getFloat(valores[i]) != null) {
                    ps.setFloat(i + 1, getFloat(valores[i]));
                } else if ((valores[i]).toString().trim().equals("")) {
                    ps.setNull(i + 1, java.sql.Types.VARCHAR);
                } else {
                    if (valores[i] != null) {
                        ps.setString(i + 1, valores[i].toString());
                    } else {
                        ps.setString(i + 1, null);
                        ps.setCharacterStream(i, null);
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
//    /* Funcion para insertar un gerente a la base de datos y el proyecto al cual
//     pertenece, esta recibe como parametros el ID del gerente y del proyecto */
//    public boolean agregarProyeGere(int idProyecto, int idGerente) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla proye_geren */
//
//            String sql = "INSERT INTO proye_geren (id_proyecto, id_gerente) VALUES("
//                    + idProyecto + ", "
//                    + idGerente + ")";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    /* Funcion para insertar un empleado a la base de datos y el gerente al cual
//     pertenece, esta recibe como parametros el ID del empleado y del gerente */
//    public boolean agregarEmGeren(int idEmpleado, int idGerente) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla em_geren */
//
//            String sql = "INSERT INTO em_geren (id_empleado, id_gerente) VALUES("
//                    + idEmpleado + ", "
//                    + idGerente + ")";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    /* Funcion para insertar una Factura a la base de datos , esta recibe como 
//     parametros un Objeto de la clase Factura */
//    public boolean agregarFactura(Factura factura) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla Factura */
//
//            String sql = "INSERT INTO factura VALUES("
//                    + factura.getIdFactura() + ", "
//                    + factura.getnFactura() + ", '"
//                    + Utilidades.formateDate(factura.getFechaFactura()) + "', '"
//                    + Utilidades.formateDate(factura.getFechaCarga()) + "', "
//                    + factura.getIdProveedor() + ", "
//                    + factura.getIdPersonal() + ", "
//                    + factura.getIdServicio() + ", '"
//                    + factura.getDescripcion() + "', "
//                    + factura.getIdStatus() + ", "
//                    + factura.getMonto() + ")";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    /* Funcion para insertar una Proveedor a la base de datos , esta recibe como 
//     parametros un Objeto de la clase Proveedor */
//    public boolean agregarProveedor(Proveedor proveedor) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla Proveedor */
//
//            String sql = "INSERT INTO proveedor VALUES("
//                    + proveedor.getIdProveedor() + ", '"
//                    + proveedor.getRifCed() + "', '"
//                    + proveedor.getNombre() + "', '"
//                    + proveedor.getTelefono() + "', '"
//                    + proveedor.getDescripcion() + "')";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    /* Funcion para insertar una Monto a la base de datos , esta recibe como 
//     parametros una variable tipo Double */
//    public boolean agregarMonto(Double monto) {
//        try {
//            /* Definimos el codigo sql que queremos ejecutar. En este caso es un
//             insert a la tabla monto */
//
//            String sql = "INSERT INTO monto (monto) VALUES(" + monto + ")";
//
//            /* El createStatement cree un cuadro donde se puede insertar codigo
//             sql, el statement se podria decir que es el cuadro en blanco que
//             te da el phpmyadmin para insertar codigo sql. */
//            Statement st = con.createStatement();
//
//            /* Una vez creado el statement el cuadrito mandamos a ejecutar el 
//             codigo sql que definimos en la variable sql. */
//            st.executeUpdate(sql);
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean modificarProyecto(int id, String nombre) {
//
//        try {
//            String sql = "UPDATE proyecto SET  "
//                    + " nom_pro = '" + nombre + "'"
//                    + " WHERE id_proyecto = " + id + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }

    public boolean modificarPersonal(int id, String nombre, String apellido,
            String telefono, String cargo) {

        try {
            String sql = "UPDATE personal SET  "
                    + " nom_per = '" + nombre + "', "
                    + " ape_per = '" + apellido + "', "
                    + " tele_per = '" + telefono + "', "
                    + " cargo = '" + cargo + "'"
                    + " WHERE id_personal = " + id + " ";

            Statement st = con.createStatement();
            st.executeUpdate(sql);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

//    public boolean modificarProveedor(int id, String nombre, String telefono,
//            String descripcion) {
//
//        try {
//            String sql = "UPDATE proveedor SET  "
//                    + " nom_prove = '" + nombre + "', "
//                    + " tele_prove = '" + telefono + "', "
//                    + " direcc_prove = '" + descripcion + "' "
//                    + " WHERE id_proveedor = " + id + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean modificarMonto(int id, Double monto) {
//
//        try {
//            String sql = "UPDATE monto SET  "
//                    + " monto = " + monto + ""
//                    + " WHERE id_monto = " + id + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean modificarUsuario(String usuario, String clave) {
//
//        try {
//            String sql = "UPDATE usuarios SET  "
//                    + " clave = '" + clave + "'"
//                    + " WHERE usuario = '" + usuario + "' ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean modificarFactura(int factura, int status) {
//
//        try {
//            String sql = "UPDATE factura SET  "
//                    + " id_status = " + status + ""
//                    + " WHERE n_factura = " + factura + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    
//    public boolean modificarReserva(double reserva) {
//
//        try {
//            String sql = "UPDATE monto SET  "
//                    + " monto = " + reserva + ""
//                    + " WHERE id_monto = 1 ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean eliminarProyecto(int id) {
//
//        try {
//            String sql = "DELETE FROM proyecto WHERE id_proyecto = " + id + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
    public boolean eliminarPersonal(int id) {

        try {
            String sql = "DELETE FROM personal WHERE id_personal = " + id + " ";

            Statement st = con.createStatement();
            st.executeUpdate(sql);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

//    public boolean eliminarProveedor(int id) {
//
//        try {
//            String sql = "DELETE FROM proveedor WHERE id_proveedor = " + id + " ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public boolean eliminarUsuario(String usuario) {
//
//        try {
//            String sql = "DELETE FROM usuarios WHERE usuario = '" + usuario + "' ";
//
//            Statement st = con.createStatement();
//            st.executeUpdate(sql);
//
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public int getIdUsuario() {
//        try {
//            String sql = "SELECT MAX(id_usuario) AS num FROM usuarios";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt("num") + 1;
//            } else {
//                return 0;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
//
//    public int getIdProyecto() {
//        try {
//            String sql = "SELECT MAX(id_proyecto) AS num FROM proyecto";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt("num") + 1;
//            } else {
//                return 0;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
//
//    public int getIdProveedor() {
//        try {
//            String sql = "SELECT MAX(id_proveedor) AS num FROM proveedor";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt("num") + 1;
//            } else {
//                return 0;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
//
//    public ResultSet getProyectos() {
//
//        try {
//            String sql = "SELECT * FROM proyecto ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getCargos() {
//
//        try {
//            String sql = "SELECT * FROM cargo ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getPersonal() {
//
//        try {
//            String sql = "SELECT * FROM personal ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public int getIdPersonal() {
//        try {
//            String sql = "SELECT MAX(id_personal) AS num FROM personal";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt("num") + 1;
//            } else {
//                return 0;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
//
//    public ResultSet getGerentes(int usuario) {
//
//        try {
//            String sql = "SELECT personal.id_personal,  personal.nom_per, personal.ape_per, personal.cargo "
//                    + "FROM personal "
//                    + "INNER JOIN cargo ON personal.cargo = cargo.cargo "
//                    + "WHERE usuarios.usuario = '" + usuario + "'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getGerentes() {
//
//        try {
//            String sql = "SELECT personal.id_personal,  personal.nom_per, personal.ape_per "
//                    + "FROM personal "
//                    + "WHERE personal.cargo = 'Gerente' ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getUsuarios() {
//
//        try {
//            String sql = "SELECT * FROM usuarios";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getProyectoNom(String nombre) {
//
//        try {
//            String sql = "SELECT * FROM proyecto "
//                    + "WHERE nom_pro LIKE  '%" + nombre + "%'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
    public ResultSet getRegistros(String sql) {

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getregistrosId(String sql) {

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//
//    public ResultSet getProveedorNom(String nombre) {
//
//        try {
//            String sql = "SELECT * FROM proveedor "
//                    + "WHERE nom_prove LIKE  '%" + nombre + "%'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getUsuariosNom(String nombre) {
//
//        try {
//            String sql = "SELECT * FROM usuarios "
//                    + "WHERE usuario LIKE  '%" + nombre + "%'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public boolean getClaveUsuario(String clave) {
//
//        try {
//            String sql = "SELECT * FROM usuarios "
//                    + "WHERE clave =  '" + clave + "'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs.next();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    /* Funcion que sirve para validar si un proyecto ya esta registrado.
//     - La funcion recibe como parametro un String el cual corresponde
//     al nombre del proyecto.
//     - La funcion retorna true si el nombre del proyecto se encuentra y
//     si no false.
//     */
//    public boolean getProyecto(String nombre) {
//
//        try {
//            String sql = "SELECT * FROM proyecto "
//                    + "WHERE nom_pro = '" + nombre + "'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs.next();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public ResultSet getTiposUsuario() {
//
//        try {
//            String sql = "SELECT * FROM tipo_usuario";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public boolean getUsuario(String usuario) {
//
//        try {
//            String sql = "SELECT * FROM usuarios "
//                    + "WHERE usuario  = '" + usuario + "'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs.next();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public int getNumFac() {
//        try {
//            String sql = "SELECT MAX(id_factura) AS num FROM factura";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                return rs.getInt("num") + 1;
//            } else {
//                return 0;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
//        }
//    }
//
//    public boolean getNumeroFac(int fac) {
//        try {
//            String sql = "SELECT * FROM factura WHERE n_factura = " + fac + "";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs.next();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public ResultSet getProveedor() {
//        try {
//            String sql = "SELECT * FROM proveedor";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getStatus() {
//        try {
//            String sql = "SELECT * FROM status";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public boolean getStatus(String status) {
//        try {
//            String sql = "SELECT * FROM status WHERE nom_sta = '" + status + "'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs.next();
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//
//    public ResultSet getServicios() {
//        try {
//            String sql = "SELECT * FROM servicio";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public ResultSet getFacturas() {
//        try {
//            String sql = "SELECT * FROM factura";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getFacturas(String sql) {
//        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getMonto() {
//        try {
//            String sql = "SELECT * FROM monto";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getFacturasReporte() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "proveedor.rif_cedula, "
//                    + "proveedor.nom_prove, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//    
//    public ResultSet getFacturaStatus() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "proveedor.rif_cedula, "
//                    + "proveedor.nom_prove, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE status.id_status != 4";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getFacturasReporteId(int n) {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE factura.n_factura = " + n + "";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//    
//     public ResultSet getFacturasReporteEmpleado(String nombre) {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "proveedor.rif_cedula, "
//                    + "proveedor.nom_prove, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE personal.nom_per like '" + nombre + "%'";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//     
//      public ResultSet getFacturasStatusNombre(String nombre) {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "proveedor.rif_cedula, "
//                    + "proveedor.nom_prove, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE personal.nom_per like '" + nombre + "%' "
//                    + "AND status.id_status != 4";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getSumaMontoAprobadas() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto, "
//                    + "SUM(factura.monto) as total "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE status.id_status = 1 ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//    
//    public ResultSet getSumaMontoPagadas() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto, "
//                    + "SUM(factura.monto) as total "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE status.id_status = 4 ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//
//    public ResultSet getSumaMontoRechazadas() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto, "
//                    + "SUM(factura.monto) as total "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status "
//                    + "WHERE status.id_status = 2 ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
//    
//    public String getMontoTotal() {
//        try {
//            String sql = "SELECT factura.id_factura AS factura, factura.n_factura, "
//                    + "factura.fecha_fac AS fecha, "
//                    + "factura.fecha_carga AS carga, "
//                    + "CONCAT( proveedor.rif_cedula, '-', proveedor.nom_prove) AS proveedor, "
//                    + "CONCAT( personal.nom_per, ' ', personal.ape_per) AS empleado, "
//                    + "servicio.nom_servi AS servicio, "
//                    + "status.nom_sta AS status, "
//                    + "factura.descri_fac AS descripcion, "
//                    + "factura.monto AS monto, "
//                    + "SUM(factura.monto) as total "
//                    + "FROM factura "
//                    + "INNER JOIN proveedor ON factura.id_proveedor = proveedor.id_proveedor "
//                    + "INNER JOIN personal ON factura.id_personal = personal.id_personal "
//                    + "INNER JOIN servicio ON factura.id_servicio = servicio.id_servicio "
//                    + "INNER JOIN status ON factura.id_status = status.id_status ";
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()){
//                
//                return rs.getString("total");
//                
//            }
//            return null;
//        } catch (SQLException ex) {
//            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
}
