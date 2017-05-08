package mx.itesm.csf.crud.Clientes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.crud.Controladores.Controlador;
import mx.itesm.csf.crud.R;

import static mx.itesm.csf.crud.Controladores.Servicios.CLIENTES_INSERT;
import static mx.itesm.csf.crud.Controladores.Servicios.CLIENTES_UPDATE;

public class InsertarClientes extends AppCompatActivity {

    // definimos los componentes de nuestra interfaz
    EditText clave,nombre,apellido, foto;
    Button boton_cancelar,boton_guardar;
    ProgressDialog barra_de_progreso;
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.layout_insertar_clientes);

        /* obtenemos los datos del intento*/
        Intent datos = getIntent();
        final int update = datos.getIntExtra("update",0);
        String intent_clave = datos.getStringExtra("clave");
        String intent_nombre = datos.getStringExtra("nombre");
        String intent_apellido = datos.getStringExtra("apellido");
        String intent_foto = datos.getStringExtra("foto");


        // hacemos referencia a nuestra interfaz gráfica XML
        clave = (EditText) findViewById(R.id.clave_cliente);
        nombre = (EditText) findViewById(R.id.nombre_cliente);
        apellido = (EditText) findViewById(R.id.apellido_cliente);
        foto = (EditText) findViewById(R.id.foto_cliente);

        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);
        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        barra_de_progreso = new ProgressDialog(InsertarClientes.this);

        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.sdvImage);


        // condición para inserción
        if(update == 1)
        {
            boton_guardar.setText("Actualizar datos");
            clave.setText(intent_clave);
            clave.setVisibility(View.GONE);
            nombre.setText(intent_nombre);
            apellido.setText(intent_apellido);
            foto.setText(intent_foto);
            Uri imageUri = Uri.parse(foto.getText().toString());
            draweeView.setImageURI(imageUri);

        }

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    actualizarDatos();
                }else {
                    guardarDatos();
                }
            }
        });


        boton_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent principal = new Intent(InsertarClientes.this,PrincipalClientes.class);
                startActivity(principal);
            }
        });
    }


    private void actualizarDatos()
    {
        barra_de_progreso.setMessage("Actualizar datos");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, CLIENTES_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        barra_de_progreso.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertarClientes.this, "Respuesta: "+   res.getString("Mensaje") , Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // comentado para que se quede en esta sección de mi app y ver los errores en caso de fallo al insertar
                        startActivity( new Intent(InsertarClientes.this,PrincipalClientes.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra_de_progreso.cancel();
                        Toast.makeText(InsertarClientes.this, "Respuesta: Error al insertar datos", Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                map.clear();
                map.put("c_id",clave.getText().toString());
                map.put("nombre",nombre.getText().toString());
                map.put("apellido",apellido.getText().toString());
                map.put("foto",foto.getText().toString());
                Log.d("Parámetros: ", CLIENTES_UPDATE + map.toString());

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

        Controlador.getInstance().agregaAlRequestQueue(updateReq);
    }



    private void guardarDatos()
    {
        barra_de_progreso.setMessage("Insertar datos");
        barra_de_progreso.setCancelable(false);
        barra_de_progreso.show();

        StringRequest enviaDatos = new StringRequest(Request.Method.POST, CLIENTES_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        barra_de_progreso.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertarClientes.this, "Respuesta : "+   res.getString("mensaje") , Toast.LENGTH_SHORT).show();
                            Log.d("Parámetros: ", response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertarClientes.this,PrincipalClientes.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra_de_progreso.cancel();
                        Toast.makeText(InsertarClientes.this, "Respuesta: Error al insertar datos", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                map.clear();
                map.put("nombre",nombre.getText().toString());
                map.put("apellido",apellido.getText().toString());
                map.put("foto",foto.getText().toString());

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

        Controlador.getInstance().agregaAlRequestQueue(enviaDatos);
    }
}
