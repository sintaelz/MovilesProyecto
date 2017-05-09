package mx.itesm.csf.crud.Carrito;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.crud.Controladores.Controlador;
import mx.itesm.csf.crud.R;
import mx.itesm.csf.crud.Ventas.BorrarVentas;
import mx.itesm.csf.crud.Ventas.PrincipalVentas;

import static mx.itesm.csf.crud.Controladores.Servicios.CARRITO_DELETE;

public class BorrarCarrito extends AppCompatActivity {

    // como siempre, definimos los componente de nuestra interfaz
    EditText clave_carrito;
    Button boton_borrar;
    ProgressDialog barra_de_progreso;
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_carrito);

        // mapeamos componentes de la interfaz
        clave_carrito = (EditText) findViewById(R.id.clave_carrito);
        boton_borrar = (Button) findViewById(R.id.boton_borrar);
        barra_de_progreso = new ProgressDialog(BorrarCarrito.this);

        // creamos un listener para el boton que eliminará al registro
        boton_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borraRegistro();
            }
        });
    }

    // metodo para borrar el registro
    private void borraRegistro()
    {
        barra_de_progreso.setMessage("Eliminando registro...");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        // usamos el método POST --> ver filminas del porqué hacemos esto
        StringRequest requestBorrar = new StringRequest(Request.Method.POST, CARRITO_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        barra_de_progreso.cancel();
                        Log.d("volley","Respuesta : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(BorrarCarrito.this,"Respuesta: " +res.getString("Mensaje"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(BorrarCarrito.this,PrincipalCarrito.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra_de_progreso.cancel();
                        Log.d("volley", "Error : " + error.getMessage());
                        Toast.makeText(BorrarCarrito.this, "Respuesta: Error al eliminar registro", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                map.clear();
                map.put("v_id", clave_carrito.getText().toString());
                Log.d("Parámetros enviados: ", CARRITO_DELETE + map.toString());
                return map;
            }
            @Override
            public Map < String, String > getHeaders() throws AuthFailureError {
                HashMap < String, String > headers = new HashMap < String, String > ();
                String encodedCredentials = Base64.encodeToString("pddm-1021720:1021720".getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", "Basic " + encodedCredentials);
                return headers;
            }
        };

        Controlador.getInstance().agregaAlRequestQueue(requestBorrar);
    }
}
