package mx.itesm.csf.crud.Ropa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.crud.R;

public class LeerDatos extends AppCompatActivity {

    // definimos los componentes de nuestra interfaz
    EditText clave,nombre,precio,imagen,stock;
    Button boton_cancelar,boton_guardar;
    ProgressDialog barra_de_progreso;
    Map<String,String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.layout_insertar_datos);

        /* obtenemos los datos del intento*/
        Intent datos = getIntent();
        final int update = datos.getIntExtra("update",0);
        String intent_clave = datos.getStringExtra("clave");
        String intent_nombre = datos.getStringExtra("nombre");
        String intent_precio = datos.getStringExtra("precio");
        String intent_imagen = datos.getStringExtra("imagen");
        String intent_stock = datos.getStringExtra("stock");


        // hacemos referencia a nuestra interfaz gráfica XML
        clave = (EditText) findViewById(R.id.clave_vehiculo);
        nombre = (EditText) findViewById(R.id.nombre_del_vehiculo);
        precio = (EditText) findViewById(R.id.precio_del_vehículo);
        imagen= (EditText) findViewById(R.id.url_imagen);
        stock= (EditText) findViewById(R.id.stock);

        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);
        boton_guardar = (Button) findViewById(R.id.boton_guardar);

        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.sdvImage);

        // condición para inserción
        if(update == 1)
        {
            boton_guardar.setText("Actualizar datos");
            clave.setText(intent_clave);
            clave.setVisibility(View.GONE);
            nombre.setText(intent_nombre);
            precio.setText(intent_precio);
            imagen.setText(intent_imagen);
            stock.setText(intent_stock);
            Uri imageUri = Uri.parse(imagen.getText().toString());
            draweeView.setImageURI(imageUri);

        }
    }

}
