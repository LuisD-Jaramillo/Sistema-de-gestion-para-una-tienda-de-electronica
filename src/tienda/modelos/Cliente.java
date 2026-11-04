package tienda.modelos;

public class Cliente extends Persona {
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
        if (correo != null && !correo.trim().isEmpty()) {
            this.correo = correo;
        } else {
            System.out.println("El correo no puede estar vacío.");
        }
    }

    public void setSaldoCredito(double saldoCredito) {
        if (saldoCredito >= 0) {
            this.saldoCredito = saldoCredito;
        } else {
            System.out.println("El saldo no puede ser negativo.");
        }
    }

    public void cargarCredito(double monto) {
        if (monto > 0) {
            this.saldoCredito += monto;
        } else {
            System.out.println("El monto a cargar debe ser positivo.");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("------------------------------------");
        System.out.println("Tipo: Cliente");
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
        System.out.println("Saldo de Crédito: $" + saldoCredito);
        System.out.println("------------------------------------");
    }
}