/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author GLARA
 */
public class CCliente {

    private String codigo;
    private String nombre;
    private String direccion;
    private String correo;
    private String nit;
    private String telefono;
    private String fec_reg;
    private String limitecredito;
    private boolean estado;

    public CCliente(String codigo, String nombre, String direccion, String correo, String nit, String telefono, String fec_reg, String limitecredito, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.nit = nit;
        this.telefono = telefono;
        this.fec_reg = fec_reg;
        this.limitecredito = limitecredito;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getLimitecredito() {
        return limitecredito;
    }

    public void setLimitecredito(String limitecredito) {
        this.limitecredito = limitecredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFec_reg() {
        return fec_reg;
    }

    public void setFec_reg(String fec_reg) {
        this.fec_reg = fec_reg;
    }

    public Object[] getClientes() {

        Object[] valores = {getCodigo(), getNombre(), getDireccion(), getCorreo(),
            getNit(), getTelefono(), getFec_reg(), getLimitecredito(), isEstado()
        };
        return valores;

    }
}
