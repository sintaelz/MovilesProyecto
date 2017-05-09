package mx.itesm.csf.crud.Controladores;

// definición de los microservicios php en ubiquitous

public class Servicios {

    //SERVICIOS DE ROPA
    public static final String ROPA_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.r.ropa.php";
    public static final String ROPA_CREATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.c.ropa.php";
    public static final String ROPA_DELETE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.d.ropa.php";
    public static final String ROPA_UPDATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.u.ropa.php";

    //SERVICIOS DE EMPLEADO
    public static final String EMPLEADOS_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.r.empleados.php";
    public static final String EMPLEADOS_CREATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.c.empleados.php";
    public static final String EMPLEADOS_DELETE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.d.empleados.php";
    public static final String EMPLEADOS_UPDATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.u.empleados.php";

    //SERVICIOS DE CLIENTE
    public static final String CLIENTES_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.r.clientes.php";
    public static final String CLIENTES_INSERT = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.c.clientes.php";
    public static final String CLIENTES_DELETE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.d.clientes.php";
    public static final String CLIENTES_UPDATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.u.clientes.php";

    //SERVICIOS DE VENTAS
    public static final String VENTAS_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.r.ventas.php";
    public static final String VENTAS_CREATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.c.ventas.php";
    public static final String VENTAS_DELETE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.d.ventas.php";
    public static final String VENTAS_UPDATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.u.ventas.php";

    //Servicio de carrito
    public static final String CARRITO_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.r.carrito.php";
    //public static final String CARRITO_CREATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.c.carrito.php";
    public static final String CARRITO_CREATE = "http://ubiquitous.csf.itesm.mx/~pddm-1020365/ParcialFinal/PHPs/servicio.e.agregarCarrito.php";
    public static final String CARRITO_DELETE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.d.carrito.php";
    public static final String CARRITO_UPDATE = "http://ubiquitous.csf.itesm.mx/~pddm-1021720/content/proyectoFinalProtected/servicio.u.carrito.php";
    public static final String CARRITO_CHECKOUT = "http://ubiquitous.csf.itesm.mx/~pddm-1020365/ParcialFinal/PHPs/checkoutCart.php";
    public static final String HISTORIAL_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1020365/ParcialFinal/PHPs/servicio.r.ventasID.php";
    public static final String COMISION_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1020365/ParcialFinal/PHPs/servicio.e.comisiones.php";
    public static final String HISTVENTAS_READ = "http://ubiquitous.csf.itesm.mx/~pddm-1020365/ParcialFinal/PHPs/servicio.e.historial.php";
}
