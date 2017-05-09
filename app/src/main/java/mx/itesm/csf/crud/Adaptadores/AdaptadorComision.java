package mx.itesm.csf.crud.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.itesm.csf.crud.Modelos.ModeloComision;
import mx.itesm.csf.crud.R;
import mx.itesm.csf.crud.Ventas.InsertarVentas;

/**
 * Created by seung on 9/05/17.
 */

public class AdaptadorComision extends RecyclerView.Adapter<AdaptadorComision.ContenedorDeDatos> {
    // definimos una lista en donde vamos a incorporar todos los autos de nuestro JSON
    //private List<DataModel> misElementos ;
    private List<ModeloComision> misElementos ;

    // Context representa el estado actual de la aplicación y permite obtener información acerca de su entorno de ejecución
    // permite el acceso a recursos y clases específicos de la aplicación y también efectuar operaciones como lanzar actividades,
    // generación de intents, etc.
    private Context context;

    // creamos el método para enviar los parámetros de contexto y la lista de elementos
    public AdaptadorComision (Context context, List<ModeloComision> elementos)
    {
        this.misElementos = elementos;
        this.context = context;
    }

    public void update(List<ModeloComision> comisiones){
        this.misElementos.clear();
        this.misElementos.addAll(comisiones);
        notifyDataSetChanged();
    }

    // Creamos el ViewHolder con las vista de un elemento sin personalizar
    @Override
    public AdaptadorComision.ContenedorDeDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_filas,parent,false);
        AdaptadorComision.ContenedorDeDatos contenedorDeDatos = new AdaptadorComision.ContenedorDeDatos(layout);
        return contenedorDeDatos;
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(AdaptadorComision.ContenedorDeDatos titulo, int position) {
        ModeloComision datamodel  = misElementos.get(position);
        titulo.Cantidad.setText("Dia: " + datamodel.getDia() + " | Semana: " + datamodel.getSemana() + " | Mes: " + datamodel.getMes());

        titulo.datamodel = datamodel;
    }

    // obtenemos el tamaño de elementos que tenemos en la lista
    @Override
    public int getItemCount() {
        return misElementos.size();
    }

    // Para poder seguir con la implementación del adaptador debemos definir primero el ViewHolder necesario
    // para nuestra app. Lo definiremos como clase ContenedorDeDatos interna a nuestro adaptador, extendiendo de la clase
    // RecyclerView.ViewHolder, sólo tendremos que incluir como atributos las referencias
    // a los controles del layout de un elemento de la lista (en nuestro caso los dos TextView) e inicializarlas en el
    // constructor utilizando como siempre el método findViewById() sobre la vista recibida como parámetro.
    class ContenedorDeDatos extends RecyclerView.ViewHolder
    {
        TextView Cantidad,Clave_v;
        ModeloComision datamodel;

        // definimos nuestra vista
        public  ContenedorDeDatos (View view)
        {
            super(view);

            // mapeamos los componentes de la interfaz
            Cantidad = (TextView) view.findViewById(R.id.Nombre);
            Clave_v = (TextView) view.findViewById(R.id.Clave_auto);

            // definimos el listener
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertarVentas.class);
                    update.putExtra("update",1);
                    update.putExtra("dia",datamodel.getDia());
                    update.putExtra("semana",datamodel.getSemana());
                    update.putExtra("mes",datamodel.getMes());

                    context.startActivity(update);
                }
            });
        }
    }
}
