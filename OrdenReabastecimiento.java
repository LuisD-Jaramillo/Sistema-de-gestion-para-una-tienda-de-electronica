import java.util.ArrayList;

public class OrdenReabastecimiento{

    //Atributos
    private String idOrden;
    private Proveedor proveedor;
    private ArrayList<DetalleTransaccion> detalles;
    private boolean procesada;

    //Constructor
    public OrdenReabastecimiento(String idOrden, Proveedor proveedor){
        this.idOrden = idOrden;
        this.proveedor = proveedor;
        this.detalles = new ArrayList<>();
        this.procesada = false;
    }

    //Getters
    public String getIdOrden(){
        return idOrden;
    }

    public Proveedor getProveedor(){
        return proveedor;
    }

    public ArrayList<DetalleTransaccion> getDetalles(){
        return detalles;
    }

    public boolean isProcesada(){
        return procesada;
    }
    
    //Metodos
    public void agregarItem(Componente componente, int cantidad, double costoCompra){
        DetalleTransaccion detalle = new DetalleTransaccion(componente, cantidad, costoCompra);
        detalles.add(detalle);
    }

    public void procesarOrden(){
        procesada = true;
    }
}