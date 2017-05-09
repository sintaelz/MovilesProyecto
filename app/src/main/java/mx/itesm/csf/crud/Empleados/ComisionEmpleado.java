package mx.itesm.csf.crud.Empleados;

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

import mx.itesm.csf.crud.Adaptadores.AdaptadorComision;
import mx.itesm.csf.crud.Controladores.Controlador;
import mx.itesm.csf.crud.Controladores.Servicios;
import mx.itesm.csf.crud.Modelos.ModeloComision;
import mx.itesm.csf.crud.R;

public class ComisionEmpleado extends AppCompatActivity {

    private int e_id;

    RecyclerView miRecyclerview;
    AdaptadorComision miAdaptador;
    RecyclerView.LayoutManager miAdministrador;
    //List<DataModel> misElementos;
    List<ModeloComision> misElementos;
    Button botonInsertar, botonBorrar;
    ProgressDialog barra_de_progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comision_empleado);
        Bundle b = getIntent().getExtras();
        if(b != null){
            e_id = (Integer) b.get("e_id");
        }
        setContentView(R.layout.activity_historial_ventas);

        // Mapeamos los elementos de nuestra vista y la del CardView
        miRecyclerview = (RecyclerView) findViewById(R.id.reciclador);
        barra_de_progreso = new ProgressDialog(mx.itesm.csf.crud.Empleados.ComisionEmpleado.this);
        misElementos = new ArrayList<>();

        // Vamos a llamar la funcion cargarJSON con Volley para procesar los datos
        cargarJSON();

        // utilizamos los componentes de CardView
        miAdministrador = new LinearLayoutManager(mx.itesm.csf.crud.Empleados.ComisionEmpleado.this,LinearLayoutManager.VERTICAL,false);
        miRecyclerview.setLayoutManager(miAdministrador);
        miAdaptador = new AdaptadorComision(mx.itesm.csf.crud.Empleados.ComisionEmpleado.this,misElementos);
        miRecyclerview.setAdapter(miAdaptador);


        // LayoutManager  se encarga del layout de todas las vistas dentro del RecyclerView, concretando con el LinearLayoutManager,
        // permite entre otros acceder a elementos mostrados en la pantalla.
        // https://developer.android.com/reference/android/support/v7/widget/LinearLayoutManager.html
        miAdministrador = new LinearLayoutManager(mx.itesm.csf.crud.Empleados.ComisionEmpleado.this,LinearLayoutManager.VERTICAL,false);
        miRecyclerview.setLayoutManager(miAdministrador);
        miAdaptador = new AdaptadorComision(mx.itesm.csf.crud.Empleados.ComisionEmpleado.this,misElementos);
        miRecyclerview.setAdapter(miAdaptador);
    }

    // creamos nuestro método cargarJSON() con la librería Volley
    private void cargarJSON()
    {
        barra_de_progreso.setMessage("Cargando datos...");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET, Servicios.COMISION_READ+"?e_id=" + e_id,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        barra_de_progreso.cancel();
                        Log.d("Objeto","respuesta : " + response.toString());
                        List<ModeloComision> comisions = new ArrayList<>();

                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                comisions.add(new ModeloComision(object.getString("dia"), object.getString("semana"), object.getString("mes")));

                                if (i == 0) { //solo para el confirmation
                                    JSONObject first = response.getJSONObject(i);
                                    if (first.getString("codigo") == "01"){
                                        Toast.makeText(getApplicationContext(), "Recibiendo datos...", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    JSONObject data = response.getJSONObject(i);
                                    ModeloComision comision = new ModeloComision();
                                    comision.setDia(data.getString("dia"));
                                    comision.setSemana(data.getString("semana"));
                                    comision.setMes(data.getString("mes"));
                                    misElementos.add(comision);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        miAdaptador.update(comisions);
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

