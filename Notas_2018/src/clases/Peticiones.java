/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JRadioButton;

/**
 *
 * @author GLARA
 */
public class Peticiones extends Datos {

    /**
     * Paa varias condiciones WHERE campo1=condicionid1 and campo2=condicionid2
     * ...
     *
     * @param nombreTabla , nombre de la tabla en la BD
     * @param campos , los campos de la tabla a consultar ejem: nombre, codigo ,
     * rirección etc
     * @param valores , los valores que guardaremos en la BD.
     * @return
     */
    public boolean guardarRegistros(String nombreTabla, String campos, Object[] valores) {

        boolean gravado = false;
        gravado = this.guardar(nombreTabla, this.stringToArray(campos, ","), valores);
        if (gravado == true) {
            return true;
        } else {
            return false;
        }

    }

    public int guardarRegistrosId(String nombreTabla, String campos, Object[] valores) {

        int gravado = 0;
        gravado = this.guardarReturnId(nombreTabla, this.stringToArray(campos, ","), valores);
        return gravado;
    }

    public boolean actualizarRegistroId(String nomTabla, String campos, Object[] valores, String columnaId) {
        boolean gravado = false;
        gravado = this.modificar(nomTabla, this.adjuntarSimbolo(campos, ",", "?") + " where " + columnaId + " = ? ", valores);
        return gravado;
    }

    public int eliminarRegistro(String nombreTabla, String nomColumnaCambiar, String nomColumnaId, Object id) {

        int gravado = 0;
        gravado = this.eliminar(nombreTabla, nomColumnaCambiar, nomColumnaId, id);
        return gravado;
    }

    public ResultSet consultaClientes(String buscar) {

        String sql = "SELECT * FROM clientes "
                + "WHERE nombre LIKE  '%" + buscar + "%' or codigo='"
                + buscar + "' or nit='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaApoderado(String buscar) {

        String sql = "SELECT * FROM apoderado "
                + "WHERE nombres LIKE  '%" + buscar + "%' or apellidos LIKE '%"
                + buscar + "%' or dpi='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
     public ResultSet consultaDocente(String buscar) {

        String sql = "SELECT * FROM docente "
                + "WHERE nombre LIKE  '%" + buscar + "%' or apellido LIKE '%"
                + buscar + "%' or codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    public ResultSet consultaEstablecimiento(String buscar) {

        String sql = "SELECT * FROM datos_establecimiento "
                + "WHERE nombre LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaNit_Clientes(String buscar) {

        String sql = "SELECT * FROM clientes "
                + "WHERE codigo='" + buscar + "' or nit='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    /* nombre de la tabla, campo id de la tabla, valor del id*/
    public ResultSet consultaRegistrosId(String tabla, String id, String nombreId) {

        String sql = "SELECT * FROM " + tabla + " WHERE " + nombreId
                + " = '" + id + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaProveedores(String buscar) {

        String sql = "SELECT * FROM proveedor "
                + "WHERE nombre LIKE  '%" + buscar + "%' or codigo='"
                + buscar + "' or nit='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaCicloescolar(String buscar) {

        String sql = "SELECT * FROM cicloescolar "
                + "WHERE descripcion LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaSeccion(String buscar) {

        String sql = "SELECT * FROM seccion "
                + "WHERE nombre LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaNivel(String buscar) {

        String sql = "SELECT * FROM nivel "
                + "WHERE nombre LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    
    public ResultSet consultaUnidad(String buscar) {

        String sql = "SELECT * FROM unidad "
                + "WHERE nombre LIKE  '%" + buscar + "%' or codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaaño(String buscar) {

        String sql = "SELECT * FROM cicloescolar "
                + "WHERE descripcion LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    public ResultSet consultaCarrera(String buscar) {

        String sql = "SELECT * FROM carrera "
                + "WHERE descripcion LIKE  '%" + buscar + "%'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaMarca(String buscar) {

        String sql = "SELECT * FROM marca "
                + "WHERE nombre LIKE  '%" + buscar + "%' or codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaUsuario(String buscar) {

        String sql = "SELECT * FROM usuario "
                + "WHERE nombre LIKE  '%" + buscar + "%' or usuario='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaProducto(String buscar) {

        String sql = "SELECT * FROM producto "
                + "WHERE nombre LIKE  '%" + buscar + "%' or codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaBimestre(String buscar) {

        String sql = "SELECT * FROM bimestre "
                + "WHERE nombre LIKE  '%" + buscar + "%' or numero='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
        public ResultSet consultaGrupo(String buscar) {

        String sql = "SELECT * FROM grupo "
                + "WHERE descripcion LIKE  '%" + buscar + "%' or codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public ResultSet consultaAlumno(String buscar) {

        String sql = "SELECT * FROM alumno "
                + "WHERE nombres LIKE  '%" + buscar + "%' or apellidos LIKE  '%" + buscar + "%' or codigo='" + buscar + "' or codigomineduc='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet consultaCodigo_Producto(String buscar) {

        String sql = "SELECT * FROM unidad INNER JOIN producto "
                + "ON unidad.idunidad = producto.idunidad WHERE producto.codigo='" + buscar + "'";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public ResultSet Buacar_Producto(String buscar) {
        
        String sql = "SELECT\n"
                + "     producto.idproducto, producto.codigo, producto.nombre, producto.observacion,\n"
                + "     producto.ubicacion, producto.preciocoste, producto.precioventa, producto.preciomayoreo,\n"
                + "     producto.invminimo, producto.existencia, producto.fec_reg, producto.idCategoria,\n"
                + "     producto.idunidad, producto.estado, categoria.nombre, categoria.estado,\n"
                + "     unidad.nombre, unidad.estado\n"
                + "FROM\n"
                + "     categoria INNER JOIN producto ON categoria.idCategoria = producto.idCategoria\n"
                + "     INNER JOIN unidad ON producto.idunidad = unidad.idUnidad"
                + " WHERE producto.nombre LIKE  '%" + buscar + "%' or categoria.nombre LIKE  '%" + buscar + "%' or unidad.nombre LIKE  '%" + buscar + "%'"
                + " or producto.codigo='" + buscar + "'";

//        String sql = "SELECT * FROM unidad INNER JOIN producto "
//                + "ON unidad.idunidad = producto.idunidad WHERE producto.codigo='" + buscar + "'";
        ResultSet rs = this.getRegistros(sql);
        return rs;
    }

    public int consultaMenu(String buscar) {

        String sql = "SELECT * FROM menu WHERE nombre = " + buscar;
        int id = 0;

        ResultSet rs = this.getRegistros(sql);
        try {
            while (rs.next()) {
                id = rs.getInt("idmenu");
            }
        } catch (SQLException e) {
        }

        return id;
    }
    
    public ResultSet buacar_lote(String idprodicto) {

        String sql = "SELECT * FROM lote "
                + "WHERE idproducto='" + idprodicto + "' and estado=true and stock > 0 limit 1";

        ResultSet rs = this.getRegistros(sql);
        return rs;
    }
    
    public Object selected(JRadioButton rbEstado) {

        if (rbEstado.isSelected()) {
            return "1";
        } else {
            return "0";
        }

    }
}
