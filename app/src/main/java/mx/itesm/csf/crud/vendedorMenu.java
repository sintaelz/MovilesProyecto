package mx.itesm.csf.crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import mx.itesm.csf.crud.Carrito.PrincipalCarrito;
import mx.itesm.csf.crud.Clientes.PrincipalClientes;
import mx.itesm.csf.crud.Empleados.ComisionEmpleado;
import mx.itesm.csf.crud.Login.MainActivity;
import mx.itesm.csf.crud.Ropa.Principal;
import mx.itesm.csf.crud.Ventas.HistorialVentas;
import mx.itesm.csf.crud.Ventas.PrincipalVentas;

public class vendedorMenu extends AppCompatActivity {

    ImageButton carrito, catalogo, pedidos, clientes;
    Button logout, comision, historial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor_menu);

        comision = (Button) findViewById(R.id.comision);
        historial = (Button) findViewById(R.id.historial);
        carrito = (ImageButton) findViewById(R.id.carrito);
        catalogo = (ImageButton) findViewById(R.id.catalogo);
        clientes = (ImageButton) findViewById(R.id.clientes);
        pedidos = (ImageButton) findViewById(R.id.pedidos);
        logout = (Button) findViewById(R.id.logout);

        Intent intent = getIntent();
        final int e_id = intent.getIntExtra("e_id", -1);

        comision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this, ComisionEmpleado.class);
                startActivity(intent);
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this, HistorialVentas.class);
                intent.putExtra("e_id", e_id);
                startActivity(intent);
            }
        });

        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,Principal.class);
                startActivity(intent);
            }
        });

        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,PrincipalCarrito.class);
                startActivity(intent);
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,PrincipalVentas.class);
                startActivity(intent);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,PrincipalClientes.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
