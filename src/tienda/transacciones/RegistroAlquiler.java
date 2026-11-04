package tienda.transacciones;

import tienda.interfaces.ICalculable;
import tienda.modelos.Cliente;
import tienda.modelos.Componente;

public class RegistroAlquiler implements ICalculable {
    private String idAlquiler;
    private Cliente cliente;
    private Componente componentePrestable;
    private int diasAlquilados;
    private boolean devuelto;
    private double recargoMora;
    private double tarifaDiaria;

    public RegistroAlquiler(String idAlquiler, Cliente cliente, Componente componentePrestable, int diasAlquilados) {
        this.idAlquiler = idAlquiler;
        this.cliente = cliente;
        this.componentePrestable = componentePrestable;
        this.diasAlquilados = diasAlquilados;
        this.devuelto = false;
        this.recargoMora = 0.0;
        this.tarifaDiaria = componentePrestable.getPrecioBase() * 0.05;
    }

    public String getIdAlquiler() {
        return idAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Componente getComponentePrestable() {
        return componentePrestable;
    }

    public int getDiasAlquilados() {
        return diasAlquilados;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public double getRecargoMora() {
        return recargoMora;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setDiasAlquilados(int diasAlquilados) {
        if (diasAlquilados > 0) {
            this.diasAlquilados = diasAlquilados;
        } else {
            System.out.println("Días alquilados debe ser mayor a 0");
        }
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public double registrarDevolucion(int diasRetraso, double costoDiasMora) {
        if (!this.devuelto) {
            this.devuelto = true;
            if (diasRetraso > 0) {
                this.recargoMora = diasRetraso * costoDiasMora;
            }
        }
        return this.recargoMora;
    }

    @Override
    public double calcularPrecioFinal() {
        double subtotal = this.diasAlquilados * tarifaDiaria;
        return subtotal + recargoMora;
    }
}