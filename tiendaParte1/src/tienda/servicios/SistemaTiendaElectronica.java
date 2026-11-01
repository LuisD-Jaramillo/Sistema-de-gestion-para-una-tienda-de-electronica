package tienda.servicios;
import tienda.interfaces.IPrestable;
import tienda.modelos.*;
import tienda.transacciones.*;
import java.util.ArrayList;
public class SistemaTiendaElectronica {
    private String nombreTienda;
    //private Componente[][] inventarioBodega;
    //private ArrayList<Componente> listaComponentes;
    private ArrayList<Cliente> listaClientes = new ArrayList();
    private ArrayList<Proveedor> listaProveedores = new ArrayList();
    //private ArrayList<Venta> listaVentas;
    private ArrayList<RegistroAlquiler> listaAlquileres= new ArrayList();
    //private ArrayList<OrdenReabastecimiento> listaOrdenes;
    
    //faltan agregar los otros atributos q estan como comentarios
    public SistemaTiendaElectronica(String nombreTienda) {
        this.nombreTienda = nombreTienda;
        // this.inventarioBodega= new Componente[][]
    }
    //agregar los getters  faltantes
    public String getNombreTienda() {
        return nombreTienda;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public ArrayList<RegistroAlquiler> getListaAlquileres() {
        return listaAlquileres;
    }
    //Gestion inventario y bodega
    
    
    
    
    
    
    //Gestion Usuarios
    public void registrarUsuario(Persona p){
        if(p==null)return;
        if(p instanceof Cliente){
            Cliente cli=(Cliente)p;
            if(buscarCliente(cli.getCedula())==null){
                listaClientes.add(cli);
                System.out.println("Cliente registrado: "+cli.getNombre());
            }
        }
        else if(p instanceof Proveedor){
            Proveedor prov=(Proveedor)p;
            if(buscarProveedor(prov.getCedula())==null){
                listaProveedores.add(prov);
                System.out.println("Proveedor registrado: "+prov.getNombre());
            }
        }  
    }
    public Cliente buscarCliente(String cedula){
        for(Cliente c: listaClientes){
            if(c.getCedula().equals(cedula)){
                return c;
            }
        }return null;
    }
    public Proveedor buscarProveedor(String cedula){
        for(Proveedor c: listaProveedores){
            if(c.getCedula().equals(cedula)){
                return c;
            }
        }return null;

    }
    //Ventas
    
    
    
    
    //Devolucion y alquiler
    public void registrarAlquiler(RegistroAlquiler a){
        if(a==null)return;
        //Componente comp=a.getComponentePrestable();
        if(comp instanceof IPrestable){
           Iprestable prestable= (prestable)comp;
           if(prestable.estaDisponible()){
               if(prestable.prestar(a.getDiasAlquilados())){
                   listaAlquileres.add(a);
               }
           }
        }
    }
    
    public boolean procesarDevolucion(String idAlquiler,int diasRetraso){
        for(RegistroAlquiler a: listaAlquileres){
            if(a.getIdAlquiler().equalsIgnoreCase(idAlquiler)&& a.isDevuelto()==false){
                a.registrarDevolucion(diasRetraso,1.50);
                //Componente comp= a.getComponentePrestable();
                if(comp instanceof IPrestable){
                    ((IPrestable)comp).devolver();
                    return true;
                }    
            }
        }return false;
    }
}