package tienda.transacciones;

import java.util.ArrayList;
import tienda.interfaces.ICalculable;
import tienda.modelos.Cliente;
import tienda.modelos.Componente;

public class Venta implements ICalculable {
    //Atributos
    private String idVenta;
    private Cliente cliente;
    private ArrayList<DetalleTransaccion> detalles;

    //Constructor
    public Venta(String idVenta, Cliente cliente) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }
    
    //Getters
    public String getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetalleTransaccion> getDetalles() {
        return detalles;
    }
    
    //Metodos
    public void agregarDetalle(Componente componente, int cantidad) {
        if (componente != null && cantidad > 0) {
            double precioUnitario = componente.calcularPrecioFinal();
            DetalleTransaccion detalle = new DetalleTransaccion(componente, cantidad, precioUnitario);
            detalles.add(detalle);
        }
    }

    @Override
    public double calcularPrecioFinal() {
        double total = 0;
        for (DetalleTransaccion detalle : detalles) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }
}