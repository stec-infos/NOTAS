package modelos;

public class Opcion {

    private String valor;
    private String descripcion;

    public Opcion(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Opcion() {

    }

    public String getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
