package tienda.interfaces;
public interface IPrestable {
    boolean prestar(int dias);
    boolean devolver();
    boolean estaDisponible();
    boolean esTargetaDatos();
}
