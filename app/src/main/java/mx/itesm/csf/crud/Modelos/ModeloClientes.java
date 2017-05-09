package mx.itesm.csf.crud.Modelos;

/**
 * Created by biller on 4/2/17.
 */

public class ModeloClientes {
    private String c_id, nombre, apellido, foto;


    public ModeloClientes() {}

    public ModeloClientes(String c_id, String nombre, String apellido, String foto) {
        this.c_id = c_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}