package tienda.modelos;

import tienda.interfaces.IPrestable;

public class ComponenteActivo extends Componente implements IPrestable {
    private static final double RECARGO_ACTIVO = 0.20;
    private static final String[] TARJETAS_PERMITIDAS = {"MicroSD", "SD", "SIM", "CompactFlash"};

    private double voltajeOperacion;
    private boolean prestable;

    public ComponenteActivo(String codigo, String nombre, double precioBase, int stock,
                            String[] categorias, double voltajeOperacion) {
        super(codigo, nombre, precioBase, stock, categorias);
        this.voltajeOperacion = voltajeOperacion;
        this.prestable = true;
    }

    public double getVoltajeOperacion() {
        return voltajeOperacion;
    }

    public boolean esTarjetaDeDatos() {
        for (String tarjeta : TARJETAS_PERMITIDAS) {
            if (nombre != null && nombre.toLowerCase().contains(tarjeta.toLowerCase())) {
                return true;
            }
            if (categorias != null) {
                for (String categoria : categorias) {
                    if (categoria.equalsIgnoreCase(tarjeta)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean prestar() {
        if (prestable && stock > 0) {
            prestable = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver() {
        if (!prestable) {
            prestable = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean estaDisponible() {
        return prestable && stock > 0;
    }

    @Override
    public String obtenerDetalleTecnico() {
        return "Componente activo | Voltaje de operación: " + voltajeOperacion + " V"
                + " | Tarjeta de datos: " + (esTarjetaDeDatos() ? "Sí" : "No")
                + " | Estado: " + (prestable ? "Disponible" : "Prestado");
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase * (1 + RECARGO_ACTIVO);
    }
}