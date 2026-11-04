package tienda.transacciones;

import java.util.ArrayList;
import tienda.modelos.Componente;
import tienda.modelos.Proveedor;

public class OrdenReabastecimiento {
    private String idOrden;
    private Proveedor proveedor;
    private ArrayList<DetalleTransaccion> detalles;
    private boolean procesada;

    public OrdenReabastecimiento(String idOrden, Proveedor proveedor) {
        this.idOrden = idOrden;
        this.proveedor = proveedor;
        this.detalles = new ArrayList<>();
        this.procesada = false;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public ArrayList<DetalleTransaccion> getDetalles() {
        return detalles;
    }

    public boolean isProcesada() {
        return procesada;
    }

    public void agregarItem(Componente componente, int cantidad, double costoCompra) {
        if (componente != null && cantidad > 0) {
            DetalleTransaccion detalle = new DetalleTransaccion(componente, cantidad, costoCompra);
            detalles.add(detalle);
        }
    }

    public void procesarOrden() {
        if (!procesada) {
            for (DetalleTransaccion d : detalles) {
                if (d.getComponente() != null) {
                    int stockActual = d.getComponente().getStock();
                    d.getComponente().setStock(stockActual + d.getCantidad());
                }
            }
            procesada = true;
        }
    }
}