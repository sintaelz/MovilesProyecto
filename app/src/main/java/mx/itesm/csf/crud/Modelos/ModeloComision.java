package mx.itesm.csf.crud.Modelos;

/**
 * Created by seung on 9/05/17.
 */

public class ModeloComision {
    // definimos los componentes de cada objeto de ropa
    private String dia, semana, mes;

    public ModeloComision(){}

    public ModeloComision(String dia, String semana, String mes) {
        this.dia = dia;
        this.semana = semana;
        this.mes = mes;

    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getSemana() {return semana; }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

}
