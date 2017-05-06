package mx.itesm.csf.crud.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import mx.itesm.csf.crud.CRUDmenu;
import mx.itesm.csf.crud.R;
import mx.itesm.csf.crud.Ventas.PrincipalVentas;

public class UserAreaActivity extends AppCompatActivity {

    int waitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etMail = (EditText) findViewById(R.id.etCorreo);
        //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        //String usuario = intent.getStringExtra("usuario");
        //String email = intent.getStringExtra("email");
        int e_id = intent.getIntExtra("e_id", -1);
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        final int admin = intent.getIntExtra("admin", -1);
        String correo = intent.getStringExtra("correo");

        String message = nombre + " " + apellido + " bienvenido a tu cuenta\nTu email es " + correo + "\nTu id es: " + e_id + "\nTu num de admin es: " + admin + "";
        welcomeMessage.setText(message);
        //etMail.setText(email);

        waitTime = 5000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent mainIntent;
                if(admin == 1) {
                    mainIntent = new Intent().setClass(UserAreaActivity.this, CRUDmenu.class);
                } else {
                    mainIntent = new Intent().setClass(UserAreaActivity.this, PrincipalVentas.class);
                }
                startActivity(mainIntent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, waitTime);
        /*
        Intent otherIntent = new Intent(UserAreaActivity.this,PrincipalVentas.class);
        startActivity(otherIntent);*/
    }
}
