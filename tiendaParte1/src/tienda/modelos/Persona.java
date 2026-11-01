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
        if(cedula!=null && cedula.length()==9){
            this.cedula = cedula;
        }
        else if(cedula==null){
            System.out.println("La cedula no puede estar vacia");
        }
        else{
            System.out.println("La cedula debe contener 9 digitos");
        }
    }

    public void setNombre(String nombre) {
        if(nombre!=null){
         this.nombre = nombre;
        }
        else{
            System.out.println("Nombre no puede estar vacio");
        }
    }

    public void setTelefono(String telefono) {
        if(telefono!=null&& telefono.length()==9){
            this.telefono = telefono;
        }
        else if(telefono==null){
            System.out.println("Telefono no puede estar vacio");
        }
        else{
            System.out.println("Telefono debe tener 9 digitos");
        }
    }
    public abstract void mostrarInfomacion();
}
