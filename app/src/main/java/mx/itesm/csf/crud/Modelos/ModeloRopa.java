package mx.itesm.csf.crud.Modelos;

/**
 * Created by Alonso on 4/2/17.
 */

public class ModeloRopa {

    // definimos los componentes de cada objeto de ropa
    private String p_id, nombre, imagen, stock, stock_T2, stock_T3, precio;

    public ModeloRopa(){}

    public ModeloRopa(String p_id, String nombre, String imagen, String stock, String stock_T2, String stock_T3, String precio) {
        this.p_id = p_id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.stock = stock;
        this.stock_T2 = stock_T2;
        this.stock_T3 = stock_T3;
        this.precio = precio;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock_T2() {
        return stock_T2;
    }

    public void setStock_T2(String stock_T2) {
        this.stock_T2 = stock_T2;
    }

    public String getStock_T3() {
        return stock_T3;
    }

    public void setStock_T3(String stock_T3) {
        this.stock_T3 = stock_T3;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
