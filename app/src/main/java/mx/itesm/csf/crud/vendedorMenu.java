package mx.itesm.csf.crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import mx.itesm.csf.crud.Clientes.PrincipalClientes;
import mx.itesm.csf.crud.Ventas.PrincipalVentas;

public class vendedorMenu extends AppCompatActivity {

    ImageButton carrito, catalogo, pedidos, clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor_menu);

        carrito = (ImageButton) findViewById(R.id.carrito);
        catalogo = (ImageButton) findViewById(R.id.catalogo);
        clientes = (ImageButton) findViewById(R.id.clientes);
        pedidos = (ImageButton) findViewById(R.id.pedidos);



        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,PrincipalVentas.class);
                startActivity(intent);
            }
        });

        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(vendedorMenu.this,PrincipalVentas.class);
                //startActivity(intent);
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(vendedorMenu.this,PrincipalVentas.class);
                //startActivity(intent);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vendedorMenu.this,PrincipalClientes.class);
                startActivity(intent);
            }
        });
    }
}
