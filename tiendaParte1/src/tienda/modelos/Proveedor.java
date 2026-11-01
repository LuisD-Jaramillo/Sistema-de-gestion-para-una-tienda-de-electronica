package tienda.modelos;
public class Proveedor extends Persona {
    private String empresa;
    private String ruc;

    public Proveedor(String cedula, String nombre, String telefono,String empresa, String ruc) {
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
        if(empresa!=null){
        this.empresa = empresa;
        }
        else{
            System.out.println("Empresa no puede esta vacio");
        }
    }    
    public void setRuc(String ruc) {
        if(ruc!= null && ruc.length()==13){
           this.ruc = ruc;
        }
        else if(ruc==null){
            System.out.println("Ruc no puede estar vacio");
        }
        else{
            System.out.println("Ruc debe tener 13 digitos");
        }
    }    
    @Override
    public void mostrarInfomacion(){
        System.out.println("------------------------------------");
        System.out.println("Tipo: Proveedor");
        System.out.println("Cédula: "+cedula);
        System.out.println("Nombre: "+nombre);
        System.out.println("Teléfono: "+telefono);
        System.out.println("Empresa: "+empresa);
        System.out.println("RUC: "+ruc);
        System.out.println("------------------------------------");
    }
}
