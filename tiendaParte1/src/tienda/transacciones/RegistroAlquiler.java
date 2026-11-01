package tienda.transacciones;
import tienda.interfaces.ICalculable;
import tienda.modelos.Cliente;
//Importar el paquete de donde esten la clase componente
public class RegistroAlquiler implements ICalculable{
    private String idAlquiler;
    private Cliente cliente;
    //private Componente componentePrestable;
    private int diasAlquilados;
    private boolean devuelto;
    private double recargoMora;
    //private double tarifaDiaria;
    //falta  agregrar Componente componente en el constructor y tarifaDiaria
    public RegistroAlquiler(String idAlquiler, Cliente cliente, int diasAlquilados, boolean devuelto, double recargoMora) {
        this.idAlquiler = idAlquiler;
        this.cliente = cliente;
        this.diasAlquilados = diasAlquilados;
        this.devuelto = false;
        this.recargoMora = 0.0;
        //this.componentePrestable=componentePrestable;
        //this.tarifaDiaria=componentePrestable.getPrecioBase()*0.05;
    }
    //agregar setter y getter de componentePrestable y de tarifaDiaria
    public String getIdAlquiler() {
        return idAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
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

    public void setIdAlquiler(String idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDiasAlquilados(int diasAlquilados) {
        if(diasAlquilados>0){
        this.diasAlquilados = diasAlquilados;
        }
        else{
            System.out.println("Dias alquilados debe ser mayor a 0");
        }
    }
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public void setRecargoMora(double recargoMora) {
        this.recargoMora = recargoMora;
    }
    public double registrarDevolucion(int diasRetraso,double costoDiasMora){
        if(this.devuelto==false){
            this.devuelto=true;
            if(diasRetraso>0){
                this.recargoMora=diasRetraso*costoDiasMora;
            }
        }
        return this.recargoMora;
    }
   /* 
    @Override
    public double calcularPrecioFinal(){
        double subtotal=  this.diasAlquilados*tarifaDiaria;
        return subtotal+=recargoMora;
    }
    */
    
}

