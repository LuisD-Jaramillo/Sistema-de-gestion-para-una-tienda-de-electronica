public class DetalleTransaccion {
    //Atributos
    private Componente componente;
    private int cantidad;
    private double precioUnitario;
    
    //Constructor
    public DetalleTransaccion(Componente componente, int cantidad, double precioUnitario){  
        this.componente = componente;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    //Getters
    public Componente getComponente(){
        return componente;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public double getPrecioUnitario(){
        return precioUnitario;
    }
    
    //Método de calculo
    public double calcularSubtotal(){
        return cantidad*precioUnitario;
    }
    
    
}
