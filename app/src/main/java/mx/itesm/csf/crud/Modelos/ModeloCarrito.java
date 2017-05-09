package mx.itesm.csf.crud.Modelos;

import android.support.v7.app.AppCompatActivity;

public class ModeloCarrito extends AppCompatActivity {

    // definimos los componentes de cada objeto de ropa
    private String carrito_id, e_id, p_id, c_id, cantidad, fecha;

    public ModeloCarrito(){}

    public ModeloCarrito(String carrito_id, String e_id, String p_id, String c_id, String cantidad, String fecha) {
        this.carrito_id = carrito_id;
        this.e_id = e_id;
        this.p_id = p_id;
        this.c_id = c_id;
        this.cantidad = cantidad;
        this.fecha = fecha;

    }

    public String getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(String carrito_id) {
        this.carrito_id = carrito_id;
    }

    public String getE_id() {return e_id; }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }
}
