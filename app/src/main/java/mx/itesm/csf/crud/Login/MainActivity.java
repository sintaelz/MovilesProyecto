package mx.itesm.csf.crud.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import mx.itesm.csf.crud.R;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etCorreo = (EditText) findViewById(R.id.etCorreo);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                final String correo = etCorreo.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse (String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                //aqui es donde se le pasan todos los datos que se quieren pasar del usuario para desplegar la informacion

                                int e_id = jsonResponse.getInt("e_id");
                                String nombre = jsonResponse.getString("nombre");
                                String apellido = jsonResponse.getString("apellido");
                                int admin = jsonResponse.getInt("admin");
                                String correo = jsonResponse.getString("correo");

                                if (admin == 1) {
                                    Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                    intent.putExtra("e_id",e_id);
                                    intent.putExtra("nombre",nombre);
                                    intent.putExtra("apellido",apellido);
                                    intent.putExtra("admin",admin);
                                    intent.putExtra("correo",correo);
                                    startActivity(intent);
                                } else {
                                    //etMail.setText(email);
                                    Intent otherIntent = new Intent(MainActivity.this, UserAreaActivity.class);
                                    otherIntent.putExtra("e_id",e_id);
                                    otherIntent.putExtra("nombre",nombre);
                                    otherIntent.putExtra("apellido",apellido);
                                    otherIntent.putExtra("admin",admin);
                                    otherIntent.putExtra("correo",correo);
                                    String message = nombre + " " + apellido + " bienvenido a tu cuenta, tu email es " + correo + ", tu id es: " + e_id + "" + " y tu num de admin es: " + admin + "";
                                    startActivity(otherIntent);
                                }

                                /*Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                intent.putExtra("e_id",e_id);
                                intent.putExtra("nombre",nombre);
                                intent.putExtra("apellido",apellido);
                                intent.putExtra("admin",admin);
                                intent.putExtra("correo",correo);

                                MainActivity.this.startActivity(intent);*/

                            } else {
                                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(correo, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });


    }
}
