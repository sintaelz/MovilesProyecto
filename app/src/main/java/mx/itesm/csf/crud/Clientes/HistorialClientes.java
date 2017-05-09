package mx.itesm.csf.crud.Clientes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.itesm.csf.crud.Adaptadores.AdaptadorVentas;
import mx.itesm.csf.crud.Controladores.Controlador;
import mx.itesm.csf.crud.Controladores.Servicios;
import mx.itesm.csf.crud.Modelos.ModeloVentas;
import mx.itesm.csf.crud.R;

public class HistorialClientes extends AppCompatActivity {
    private String c_id;

    RecyclerView miRecyclerview;
    RecyclerView.Adapter miAdaptador;
    RecyclerView.LayoutManager miAdministrador;
    //List<DataModel> misElementos;
    List<ModeloVentas> misElementos;
    Button botonInsertar, botonBorrar;
    ProgressDialog barra_de_progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_clientes);

        Bundle b = getIntent().getExtras();
        if(b != null){
            c_id = (String) b.get("c_id");
        }
        // Mapeamos los elementos de nuestra vista y la del CardView
        miRecyclerview = (RecyclerView) findViewById(R.id.reciclador);
        barra_de_progreso = new ProgressDialog(mx.itesm.csf.crud.Clientes.HistorialClientes.this);
        misElementos = new ArrayList<>();

        // Vamos a llamar la funcion cargarJSON con Volley para procesar los datos
        cargarJSON();

        // utilizamos los componentes de CardView
        miAdministrador = new LinearLayoutManager(mx.itesm.csf.crud.Clientes.HistorialClientes.this,LinearLayoutManager.VERTICAL,false);
        miRecyclerview.setLayoutManager(miAdministrador);
        miAdaptador = new AdaptadorVentas(mx.itesm.csf.crud.Clientes.HistorialClientes.this,misElementos);
        miRecyclerview.setAdapter(miAdaptador);


        // LayoutManager  se encarga del layout de todas las vistas dentro del RecyclerView, concretando con el LinearLayoutManager,
        // permite entre otros acceder a elementos mostrados en la pantalla.
        // https://developer.android.com/reference/android/support/v7/widget/LinearLayoutManager.html
        miAdministrador = new LinearLayoutManager(mx.itesm.csf.crud.Clientes.HistorialClientes.this,LinearLayoutManager.VERTICAL,false);
        miRecyclerview.setLayoutManager(miAdministrador);
        miAdaptador = new AdaptadorVentas(mx.itesm.csf.crud.Clientes.HistorialClientes.this,misElementos);
        miRecyclerview.setAdapter(miAdaptador);
    }

    // creamos nuestro método cargarJSON() con la librería Volley
    private void cargarJSON()
    {
        barra_de_progreso.setMessage("Cargando datos...");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET, Servicios.HISTORIAL_READ+"?c_id=" + c_id,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        barra_de_progreso.cancel();
                        Log.d("Objeto","respuesta : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                if (i == 0) { //solo para el confirmation
                                    JSONObject first = response.getJSONObject(i);
                                    if (first.getString("codigo") == "01"){
                                        Toast.makeText(getApplicationContext(), "Recibiendo datos...", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    JSONObject data = response.getJSONObject(i);
                                    ModeloVentas ventas = new ModeloVentas();
                                    ventas.setV_id(data.getString("v_id"));
                                    ventas.setE_id(data.getString("e_id"));
                                    ventas.setP_id(data.getString("p_id"));
                                    ventas.setC_id(data.getString("c_id"));
                                    ventas.setCantidad(data.getString("cantidad"));
                                    ventas.setFecha(data.getString("fecha"));
                                    misElementos.add(ventas);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        miAdaptador.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra_de_progreso.cancel();
                        Log.d("Volley", "Error : " + error.getMessage());
                    }
                }){
            @Override
            public Map< String, String > getHeaders() throws AuthFailureError {
                HashMap< String, String > headers = new HashMap < String, String > ();
                String encodedCredentials = Base64.encodeToString("pddm-1021720:1021720".getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", "Basic " + encodedCredentials);
                return headers;
            }
        };

        Controlador.getInstance().agregaAlRequestQueue(reqData);
    }

}