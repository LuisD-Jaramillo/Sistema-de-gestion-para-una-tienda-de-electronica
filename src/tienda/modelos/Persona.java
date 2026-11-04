package tienda.modelos;

public abstract class Persona {
    protected String cedula;
    protected String nombre;
    protected String telefono;

    public Persona(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setCedula(String cedula) {
        if (cedula != null && !cedula.trim().isEmpty()) {
            this.cedula = cedula;
        } else {
            System.out.println("La cédula no puede estar vacía.");
        }
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre no puede estar vacío.");
        }
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            System.out.println("El teléfono no puede estar vacío.");
        }
    }

    public abstract void mostrarInformacion();
}