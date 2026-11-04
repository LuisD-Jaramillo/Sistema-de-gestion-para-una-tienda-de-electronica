package tienda.modelos;

import tienda.interfaces.ICalculable;

public abstract class Componente implements ICalculable {
    protected String codigo;
    protected String nombre;
    protected double precioBase; // Visibilidad protected según UML (#)
    protected int stock;
    protected String[] categorias; // Atributo categorias según UML

    public Componente(String codigo, String nombre, double precioBase, int stock, String[] categorias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("El stock no puede ser negativo, se asigna 0");
            this.stock = 0;
        }
        this.categorias = (categorias != null) ? categorias : new String[0];
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getStock() {
        return stock;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("El stock no puede ser negativo");
        }
    }

    public boolean perteneceACategoria(String categoria) {
        if (categoria == null) {
            return false;
        }
        for (String c : categorias) {
            if (c.equalsIgnoreCase(categoria.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Componente)) return false;
        Componente otro = (Componente) o;
        return codigo != null && codigo.equalsIgnoreCase(otro.codigo);
    }

    @Override
    public int hashCode() {
        return (codigo == null) ? 0 : codigo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " | Stock: " + stock
                + " | Precio final: $" + String.format("%.2f", calcularPrecioFinal());
    }

    public abstract String obtenerDetalleTecnico();

    @Override
    public abstract double calcularPrecioFinal();
}