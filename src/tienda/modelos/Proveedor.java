package tienda.modelos;

public class Proveedor extends Persona {
    private String empresa;
    private String ruc;

    public Proveedor(String cedula, String nombre, String telefono, String empresa, String ruc) {
        super(cedula, nombre, telefono);
        this.empresa = empresa;
        this.ruc = ruc;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setEmpresa(String empresa) {
        if (empresa != null && !empresa.trim().isEmpty()) {
            this.empresa = empresa;
        } else {
            System.out.println("Empresa no puede estar vacía.");
        }
    }

    public void setRuc(String ruc) {
        if (ruc != null && ruc.trim().length() == 13) {
            this.ruc = ruc;
        } else {
            System.out.println("El RUC debe contener 13 dígitos.");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("------------------------------------");
        System.out.println("Tipo: Proveedor");
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Empresa: " + empresa);
        System.out.println("RUC: " + ruc);
        System.out.println("------------------------------------");
    }
}