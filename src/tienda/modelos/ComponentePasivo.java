package tienda.modelos;

public class ComponentePasivo extends Componente {
    private static final double RECARGO_PASIVO = 0.10;

    private double tolerancia;
    private double potenciaWatts;

    public ComponentePasivo(String codigo, String nombre, double precioBase, int stock,
                            String[] categorias, double tolerancia, double potenciaWatts) {
        super(codigo, nombre, precioBase, stock, categorias);
        this.tolerancia = tolerancia;
        this.potenciaWatts = potenciaWatts;
    }

    public double getTolerancia() {
        return tolerancia;
    }

    public double getPotenciaWatts() {
        return potenciaWatts;
    }

    @Override
    public String obtenerDetalleTecnico() {
        return "Componente pasivo | Tolerancia: " + tolerancia + " %"
                + " | Potencia: " + potenciaWatts + " W";
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase * (1 + RECARGO_PASIVO);
    }
}