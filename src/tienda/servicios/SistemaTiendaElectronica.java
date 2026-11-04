package tienda.servicios;

import tienda.interfaces.IPrestable;
import tienda.modelos.*;
import tienda.transacciones.*;
import java.util.ArrayList;

public class SistemaTiendaElectronica {
    private String nombreTienda;
    private Componente[][] inventarioBodega;
    private ArrayList<Componente> listaComponentes = new ArrayList<>();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    private ArrayList<Venta> listaVentas = new ArrayList<>();
    private ArrayList<RegistroAlquiler> listaAlquileres = new ArrayList<>();
    private ArrayList<OrdenReabastecimiento> listaOrdenes = new ArrayList<>();

    public SistemaTiendaElectronica(String nombreTienda, int filasBodega, int columnasBodega) {
        if (filasBodega <= 0 || columnasBodega <= 0) {
            throw new IllegalArgumentException("La bodega debe tener al menos 1 fila y 1 columna");
        }
        this.nombreTienda = nombreTienda;
        this.inventarioBodega = new Componente[filasBodega][columnasBodega];
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public Componente[][] getInventarioBodega() {
        return inventarioBodega;
    }

    public ArrayList<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }

    public ArrayList<RegistroAlquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public ArrayList<OrdenReabastecimiento> getListaOrdenes() {
        return listaOrdenes;
    }

    // Gestión de inventario y bodega
    public void registrarComponente(Componente c, int fila, int columna) {
        if (c == null) {
            System.out.println("El componente no puede ser nulo.");
            return;
        }
        if (fila < 0 || fila >= inventarioBodega.length || columna < 0 || columna >= inventarioBodega[0].length) {
            System.out.println("Posición fuera de la bodega.");
            return;
        }
        if (c.getPrecioBase() <= 0) {
            System.out.println("El precio debe ser mayor a 0.");
            return;
        }
        if (buscarComponente(c.getCodigo()) != null) {
            System.out.println("Ya existe un componente con el código: " + c.getCodigo());
            return;
        }
        if (inventarioBodega[fila][columna] != null) {
            System.out.println("La posición [" + fila + "][" + columna + "] ya está ocupada.");
            return;
        }
        inventarioBodega[fila][columna] = c;
        listaComponentes.add(c);
        System.out.println("Componente registrado: " + c.getNombre() + " en [" + fila + "][" + columna + "]");
    }

    public boolean actualizarStock(String codigo, int nuevoStock) {
        Componente c = buscarComponente(codigo);
        if (c == null || nuevoStock < 0) {
            return false;
        }
        c.setStock(nuevoStock);
        return true;
    }

    public Componente buscarComponente(String codigo) {
        for (Componente c : listaComponentes) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) {
                return c;
            }
        }
        return null;
    }

    public Componente buscarComponente(int fila, int columna) {
        if (fila < 0 || fila >= inventarioBodega.length || columna < 0 || columna >= inventarioBodega[0].length) {
            return null;
        }
        return inventarioBodega[fila][columna];
    }

    public Componente buscarComponente(String nombre, String categoria) {
        for (Componente c : listaComponentes) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.perteneceACategoria(categoria)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Componente> filtrarPorCategoria(String categoria) {
        ArrayList<Componente> resultado = new ArrayList<>();
        for (Componente c : listaComponentes) {
            if (c.perteneceACategoria(categoria)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void mostrarMapaBodega() {
        System.out.println("=== Mapa de bodega: " + nombreTienda + " ===");
        System.out.print(String.format("%-8s", ""));
        for (int j = 0; j < inventarioBodega[0].length; j++) {
            System.out.print(String.format("%-10s", "Col " + j));
        }
        System.out.println();
        for (int i = 0; i < inventarioBodega.length; i++) {
            System.out.print(String.format("%-8s", "Fila " + i));
            for (int j = 0; j < inventarioBodega[i].length; j++) {
                Componente c = inventarioBodega[i][j];
                String celda = (c == null) ? "[ ---- ]" : "[" + c.getCodigo() + "]";
                System.out.print(String.format("%-10s", celda));
            }
            System.out.println();
        }
    }

    // Gestión de personas
    public void registrarUsuario(Persona p) {
        if (p == null) return;
        if (p instanceof Cliente) {
            Cliente cli = (Cliente) p;
            if (buscarCliente(cli.getCedula()) == null) {
                listaClientes.add(cli);
                System.out.println("Cliente registrado: " + cli.getNombre());
            } else {
                System.out.println("Ya existe un cliente con esa cédula.");
            }
        } else if (p instanceof Proveedor) {
            Proveedor prov = (Proveedor) p;
            if (buscarProveedor(prov.getCedula()) == null) {
                listaProveedores.add(prov);
                System.out.println("Proveedor registrado: " + prov.getNombre());
            } else {
                System.out.println("Ya existe un proveedor con esa cédula.");
            }
        }
    }

    public Cliente buscarCliente(String cedula) {
        for (Cliente c : listaClientes) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public Proveedor buscarProveedor(String cedula) {
        for (Proveedor c : listaProveedores) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    // Ventas
    public void registrarVenta(Venta v) {
        if (v != null) {
            listaVentas.add(v);
            System.out.println("Venta registrada exitosamente. Total: $" + v.calcularPrecioFinal());
        }
    }

    // Préstamos y devoluciones
    public void registrarAlquiler(RegistroAlquiler a) {
        if (a == null) return;
        Componente comp = a.getComponentePrestable();
        if (comp instanceof IPrestable) {
            IPrestable prestable = (IPrestable) comp;
            if (prestable.estaDisponible()) {
                if (prestable.prestar()) {
                    listaAlquileres.add(a);
                    System.out.println("Préstamo registrado correctamente.");
                } else {
                    System.out.println("No se pudo realizar el préstamo.");
                }
            } else {
                System.out.println("El componente no se encuentra disponible.");
            }
        } else {
            System.out.println("Este componente no admite alquileres.");
        }
    }

    public boolean procesarDevolucion(String idAlquiler, int diasRetraso) {
        for (RegistroAlquiler a : listaAlquileres) {
            if (a.getIdAlquiler().equalsIgnoreCase(idAlquiler) && !a.isDevuelto()) {
                a.registrarDevolucion(diasRetraso, 1.50);
                Componente comp = a.getComponentePrestable();
                if (comp instanceof IPrestable) {
                    ((IPrestable) comp).devolver();
                    return true;
                }
            }
        }
        return false;
    }

    // Órdenes de reabastecimiento
    public void registrarOrdenReabastecimiento(OrdenReabastecimiento orden) {
        if (orden != null) {
            listaOrdenes.add(orden);
        }
    }

    public boolean procesarOrdenRecibida(String idOrden) {
        for (OrdenReabastecimiento orden : listaOrdenes) {
            if (orden.getIdOrden().equalsIgnoreCase(idOrden) && !orden.isProcesada()) {
                orden.procesarOrden();
                return true;
            }
        }
        return false;
    }
}