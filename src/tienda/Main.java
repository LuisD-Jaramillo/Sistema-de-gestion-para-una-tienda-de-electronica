package tienda;

import java.util.ArrayList;
import java.util.Scanner;
import tienda.modelos.*;
import tienda.servicios.SistemaTiendaElectronica;
import tienda.transacciones.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final String LINEA = "==================================================";

    public static void main(String[] args) {
        System.out.println(LINEA);
        System.out.println(" CONFIGURACIÓN INICIAL DEL SISTEMA");
        System.out.println(LINEA);
        System.out.print("Nombre de la tienda: ");
        String nombreTienda = sc.nextLine().trim();
        int filas = leerEnteroPositivo("Filas de la bodega: ");
        int columnas = leerEnteroPositivo("Columnas de la bodega: ");

        SistemaTiendaElectronica sistema = new SistemaTiendaElectronica(nombreTienda, filas, columnas);

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    menuInventario(sistema);
                    break;
                case 2:
                    menuUsuarios(sistema);
                    break;
                case 3:
                    menuPrestamos(sistema);
                    break;
                case 4:
                    menuReabastecimiento(sistema);
                    break;
                case 5:
                    menuConsultas(sistema);
                    break;
                case 6:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Ingrese un número entre 1 y 6.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println(" SISTEMA DE GESTIÓN DE COMPONENTES Y PRÉSTAMOS");
        System.out.println(LINEA);
        System.out.println("1. Gestión de Inventario");
        System.out.println("2. Gestión de Usuarios (Clientes / Proveedores)");
        System.out.println("3. Préstamos y Devoluciones");
        System.out.println("4. Órdenes de Reabastecimiento");
        System.out.println("5. Consultas y Reportes");
        System.out.println("6. Salir");
        System.out.println(LINEA);
    }

    // --- Submenú 1: Inventario ---
    private static void menuInventario(SistemaTiendaElectronica sistema) {
        int opcion;
        do {
            System.out.println();
            System.out.println("--- GESTIÓN DE INVENTARIO ---");
            System.out.println("1. Registrar nuevo componente");
            System.out.println("2. Actualizar stock");
            System.out.println("3. Buscar componente");
            System.out.println("4. Mostrar mapa de bodega");
            System.out.println("5. Listar todos los componentes");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarComponente(sistema);
                    break;
                case 2:
                    actualizarStock(sistema);
                    break;
                case 3:
                    menuBuscar(sistema);
                    break;
                case 4:
                    sistema.mostrarMapaBodega();
                    break;
                case 5:
                    listarComponentes(sistema.getListaComponentes(), "No hay componentes registrados");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void registrarComponente(SistemaTiendaElectronica sistema) {
        System.out.println("Tipo: 1. Activo (alquilable)  2. Pasivo (solo venta)");
        int tipo = leerEntero("Tipo: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo no válido.");
            return;
        }

        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        double precioBase = leerDoublePositivo("Precio base ($): ");
        int stock = leerEnteroPositivo("Stock inicial: ");
        System.out.print("Categorías (separadas por coma): ");
        String[] categorias = sc.nextLine().split(",");
        for (int i = 0; i < categorias.length; i++) {
            categorias[i] = categorias[i].trim();
        }

        Componente nuevo;
        if (tipo == 1) {
            double voltaje = leerDoublePositivo("Voltaje de operación (V): ");
            nuevo = new ComponenteActivo(codigo, nombre, precioBase, stock, categorias, voltaje);
        } else {
            double tolerancia = leerDoublePositivo("Tolerancia (%): ");
            double potencia = leerDoublePositivo("Potencia (W): ");
            nuevo = new ComponentePasivo(codigo, nombre, precioBase, stock, categorias, tolerancia, potencia);
        }

        sistema.mostrarMapaBodega();
        int maxFila = sistema.getInventarioBodega().length - 1;
        int maxColumna = sistema.getInventarioBodega()[0].length - 1;
        int fila = leerEntero("Fila (0 a " + maxFila + "): ");
        int columna = leerEntero("Columna (0 a " + maxColumna + "): ");
        sistema.registrarComponente(nuevo, fila, columna);
    }

    private static void actualizarStock(SistemaTiendaElectronica sistema) {
        System.out.print("Código del componente: ");
        String codigo = sc.nextLine().trim();
        int nuevoStock = leerEnteroPositivo("Nuevo stock: ");
        if (sistema.actualizarStock(codigo, nuevoStock)) {
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar: el código no existe.");
        }
    }

    private static void menuBuscar(SistemaTiendaElectronica sistema) {
        System.out.println("--- BUSCAR COMPONENTE ---");
        System.out.println("1. Por código");
        System.out.println("2. Por nombre y categoría");
        System.out.println("3. Por posición en la bodega");
        System.out.println("4. Filtrar por categoría");
        System.out.println("0. Volver");
        int opcion = leerEntero("Seleccione una opción: ");

        switch (opcion) {
            case 1:
                System.out.print("Código: ");
                mostrarComponente(sistema.buscarComponente(sc.nextLine().trim()));
                break;
            case 2:
                System.out.print("Nombre exacto: ");
                String nombre = sc.nextLine().trim();
                System.out.print("Categoría: ");
                String categoria = sc.nextLine().trim();
                mostrarComponente(sistema.buscarComponente(nombre, categoria));
                break;
            case 3:
                int fila = leerEntero("Fila: ");
                int columna = leerEntero("Columna: ");
                mostrarComponente(sistema.buscarComponente(fila, columna));
                break;
            case 4:
                System.out.print("Categoría: ");
                listarComponentes(sistema.filtrarPorCategoria(sc.nextLine().trim()),
                        "No hay componentes en esa categoría");
                break;
            case 0:
                break;
        }
    }

    // --- Submenú 2: Usuarios ---
    private static void menuUsuarios(SistemaTiendaElectronica sistema) {
        int opcion;
        do {
            System.out.println();
            System.out.println("--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Proveedor");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Buscar Proveedor");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    System.out.print("Cédula: "); String ced = sc.nextLine().trim();
                    System.out.print("Nombre: "); String nom = sc.nextLine().trim();
                    System.out.print("Teléfono: "); String tel = sc.nextLine().trim();
                    System.out.print("Correo: "); String cor = sc.nextLine().trim();
                    double saldo = leerDoublePositivo("Saldo inicial de crédito: ");
                    sistema.registrarUsuario(new Cliente(ced, nom, tel, cor, saldo));
                    break;
                case 2:
                    System.out.print("Cédula: "); String cedP = sc.nextLine().trim();
                    System.out.print("Nombre: "); String nomP = sc.nextLine().trim();
                    System.out.print("Teléfono: "); String telP = sc.nextLine().trim();
                    System.out.print("Empresa: "); String emp = sc.nextLine().trim();
                    System.out.print("RUC: "); String ruc = sc.nextLine().trim();
                    sistema.registrarUsuario(new Proveedor(cedP, nomP, telP, emp, ruc));
                    break;
                case 3:
                    System.out.print("Cédula a buscar: ");
                    Cliente c = sistema.buscarCliente(sc.nextLine().trim());
                    if (c != null) c.mostrarInformacion();
                    else System.out.println("Cliente no encontrado.");
                    break;
                case 4:
                    System.out.print("Cédula a buscar: ");
                    Proveedor p = sistema.buscarProveedor(sc.nextLine().trim());
                    if (p != null) p.mostrarInformacion();
                    else System.out.println("Proveedor no encontrado.");
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    // --- Submenú 3: Préstamos ---
    private static void menuPrestamos(SistemaTiendaElectronica sistema) {
        int opcion;
        do {
            System.out.println();
            System.out.println("--- PRÉSTAMOS Y DEVOLUCIONES ---");
            System.out.println("1. Registrar nuevo préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    System.out.print("Cédula del cliente: ");
                    Cliente cli = sistema.buscarCliente(sc.nextLine().trim());
                    if (cli == null) { System.out.println("Cliente no registrado."); break; }

                    System.out.print("Código del componente: ");
                    Componente comp = sistema.buscarComponente(sc.nextLine().trim());
                    if (comp == null) { System.out.println("Componente no encontrado."); break; }

                    int dias = leerEnteroPositivo("Días de alquiler: ");
                    System.out.print("ID Alquiler (ej. ALQ-01): ");
                    String id = sc.nextLine().trim();

                    sistema.registrarAlquiler(new RegistroAlquiler(id, cli, comp, dias));
                    break;
                case 2:
                    System.out.print("ID del alquiler: ");
                    String idDev = sc.nextLine().trim();
                    int retraso = leerEntero("Días de retraso (0 si no hubo): ");
                    if (sistema.procesarDevolucion(idDev, retraso)) {
                        System.out.println("Devolución registrada correctamente.");
                    } else {
                        System.out.println("No se encontró el alquiler o ya fue devuelto.");
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    // --- Submenú 4: Reabastecimiento ---
    private static void menuReabastecimiento(SistemaTiendaElectronica sistema) {
        int opcion;
        do {
            System.out.println();
            System.out.println("--- ÓRDENES DE REABASTECIMIENTO ---");
            System.out.println("1. Crear orden de reabastecimiento");
            System.out.println("2. Procesar orden recibida");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    System.out.print("Cédula del proveedor: ");
                    Proveedor prov = sistema.buscarProveedor(sc.nextLine().trim());
                    if (prov == null) { System.out.println("Proveedor no registrado."); break; }

                    System.out.print("ID Orden (ej. ORD-01): ");
                    String idOrd = sc.nextLine().trim();
                    OrdenReabastecimiento orden = new OrdenReabastecimiento(idOrd, prov);

                    boolean agregarMas = true;
                    while (agregarMas) {
                        System.out.print("Código del componente: ");
                        Componente c = sistema.buscarComponente(sc.nextLine().trim());
                        if (c != null) {
                            int cant = leerEnteroPositivo("Cantidad a pedir: ");
                            double costo = leerDoublePositivo("Costo de compra unitario ($): ");
                            orden.agregarItem(c, cant, costo);
                        } else {
                            System.out.println("Componente no existe.");
                        }
                        System.out.print("¿Desea agregar otro componente a la orden? (s/n): ");
                        agregarMas = sc.nextLine().trim().equalsIgnoreCase("s");
                    }
                    sistema.registrarOrdenReabastecimiento(orden);
                    System.out.println("Orden creada correctamente.");
                    break;
                case 2:
                    System.out.print("ID de la orden a procesar: ");
                    String idProc = sc.nextLine().trim();
                    if (sistema.procesarOrdenRecibida(idProc)) {
                        System.out.println("Orden procesada. El stock del inventario ha sido actualizado.");
                    } else {
                        System.out.println("No se encontró la orden o ya fue procesada.");
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    // --- Submenú 5: Consultas ---
    private static void menuConsultas(SistemaTiendaElectronica sistema) {
        System.out.println();
        System.out.println("--- CONSULTAS Y REPORTES ---");
        System.out.println("Total de componentes registrados: " + sistema.getListaComponentes().size());
        System.out.println("Total de clientes registrados: " + sistema.getListaClientes().size());
        System.out.println("Total de proveedores registrados: " + sistema.getListaProveedores().size());
        System.out.println("Total de alquileres registrados: " + sistema.getListaAlquileres().size());
        System.out.println("Total de órdenes registradas: " + sistema.getListaOrdenes().size());
    }

    private static void listarComponentes(ArrayList<Componente> lista, String mensajeVacio) {
        if (lista.isEmpty()) {
            System.out.println(mensajeVacio);
            return;
        }
        for (Componente c : lista) {
            mostrarComponente(c);
        }
    }

    private static void mostrarComponente(Componente c) {
        if (c == null) {
            System.out.println("Componente no encontrado");
            return;
        }
        System.out.println("------------------------------------");
        System.out.println(c);
        System.out.println("   Precio base: $" + c.getPrecioBase());
        System.out.println("   Categorías: " + String.join(", ", c.getCategorias()));
        System.out.println("   " + c.obtenerDetalleTecnico());
        System.out.println("------------------------------------");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            valor = leerEntero(mensaje);
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor a 0.");
            }
        } while (valor <= 0);
        return valor;
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static double leerDoublePositivo(String mensaje) {
        double valor;
        do {
            valor = leerDouble(mensaje);
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor a 0.");
            }
        } while (valor <= 0);
        return valor;
    }
}