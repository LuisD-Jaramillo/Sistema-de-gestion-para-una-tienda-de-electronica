package tienda.modelos;
public class Cliente extends Persona{
    private String correo;
    private double saldoCredito;

    public Cliente(String cedula, String nombre, String telefono, String correo, double saldoCredito) {
        super(cedula, nombre, telefono);
        this.correo = correo;
        this.saldoCredito = saldoCredito;
    }

    public String getCorreo() {
        return correo;
    }

    public double getSaldoCredito() {
        return saldoCredito;
    }

    public void setCorreo(String correo) {
        if(correo!=null){
        this.correo = correo;
        }
        else{
            System.out.println("Correo no puede estar vacio");
        }
    }
    public void setSaldoCredito(double saldoCredito) {
        if(saldoCredito>=0){
        this.saldoCredito = saldoCredito;
        }
        else{
            System.out.println("El saldo no puede ser negativo");
        }
    }    
    @Override
    public void mostrarInformación(){
        System.out.println("------------------------------------");
        System.out.println("Tipo: Cliente");
        System.out.println("Cédula: "+cedula);
        System.out.println("Nombre: "+nombre);
        System.out.println("Teléfono: "+telefono);
        System.out.println("Correo: "+correo);
        System.out.println("Saldo de Credito: $"+saldoCredito);
        System.out.println("------------------------------------");
    }
    
        
}
