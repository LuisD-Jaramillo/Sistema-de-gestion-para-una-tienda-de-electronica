# Sistema de gestión para una tienda de electrónica

Aplicación en Java (proyecto NetBeans) para gestionar una tienda de electrónica: clientes, proveedores, inventario, ventas, alquileres y reabastecimiento.

## Reparto del trabajo

| Persona | Parte |
|---|---|
| Persona 1 | Usuarios y alquileres: `ICalculable`, `IPrestable`, `Persona`, `Cliente`, `Proveedor`, `RegistroAlquiler` |
| Persona 2 | Componentes, inventario y `Main`: `Componente`, `ComponenteActivo`, `ComponentePasivo`, módulo 1 del sistema y el menú |
| Persona 3 | Transacciones y reportes: `DetalleTransaccion`, `Venta`, `OrdenReabastecimiento`, `registrarVenta`, reportes |

## Requisitos

- JDK 25 (configurado en `javac.source` y `javac.target` de `nbproject/project.properties`)
- Apache NetBeans (o Ant, ya que el proyecto incluye `build.xml`)

## Instrucciones de Ejecución y Uso

### 1. Compilación y Ejecución
1. Clona el repositorio a tu equipo local.
2. Abre Apache NetBeans y selecciona **File → Open Project**[cite: 1].
3. Elige la carpeta raíz del proyecto[cite: 1].
4. Ejecuta el proyecto presionando `F6` o haciendo clic derecho sobre la clase `tienda.Main` → **Run File**[cite: 1].

### 2. Flujo de Uso en Consola
1. **Configuración inicial:** Al arrancar por primera vez, el sistema solicitará ingresar el nombre de la tienda y las dimensiones de la bodega (filas y columnas) para inicializar la matriz[cite: 4].
2. **Navegación:** Utiliza el teclado para ingresar el número de la opción deseada en el menú principal (1 al 6) y presiona `Enter`[cite: 4].
3. **Módulos:**
   - **Opción 1 (Inventario):** Permite registrar componentes (activos/pasivos), actualizar stock, buscar e imprimir el mapa visual de bodega[cite: 4].
   - **Opción 2 (Usuarios):** Registro y consulta de clientes y proveedores[cite: 4].
   - **Opción 3 (Préstamos, Devoluciones y Ventas):** Gestión transaccional con clientes[cite: 4].
   - **Opción 4 (Reabastecimiento):** Creación y procesamiento de órdenes de compra a proveedores[cite: 4].
   - **Opción 5 (Reportes):** Consultas generales de inventario y alertas de stock crítico[cite: 4].
     
## Estructura del proyecto

```
Sistema-de-gestion-para-una-tienda-de-electronica/
├── README.md
├── .gitignore
├── build.xml
├── manifest.mf
├── nbproject/                      Configuración del proyecto NetBeans
└── src/tienda/
    ├── Main.java                   Punto de entrada
    ├── interfaces/
    │   ├── ICalculable.java        calcularPrecioFinal()
    │   └── IPrestable.java         prestar, devolver, estaDisponible, esTarjetaDeDatos
    ├── modelos/
    │   ├── Persona.java            Clase abstracta (cédula, nombre, teléfono)
    │   ├── Cliente.java            Correo y saldo de crédito
    │   ├── Proveedor.java          Empresa y RUC
    │   ├── Componente.java        Clase abstracta (código, nombre, precio base, stock)
    │   ├── ComponenteActivo.java  Voltaje de operación; se puede prestar (IPrestable)
    │   └── ComponentePasivo.java  Tolerancia y potencia
    ├── servicios/
    │   └── SistemaTiendaElectronica.java   Lógica central de la tienda
    └── transacciones/
        ├── Venta.java
        ├── DetalleTransaccion.java
        ├── OrdenReabastecimiento.java
        └── RegistroAlquiler.java
```
## UML

```mermaid
classDiagram

%% 1. INTERFACES
class IPrestable {
    <<interface>>
    +prestar(dias: int) boolean
    +devolver() boolean
    +estaDisponible() boolean
    +esTarjetaDeDatos() boolean
}

class ICalculable {
    <<interface>>
    +calcularPrecioFinal() double
}

%% 2. PERSONAS
class Persona {
    <<abstract>>
    #cedula: String
    #nombre: String
    #telefono: String
    +mostrarInformacion()* void
}

class Cliente {
    -correo: String
    -saldoCredito: double
    +mostrarInformacion() void
}

class Proveedor {
    -empresa: String
    -RUC: String
    +mostrarInformacion() void
}

%% 3. COMPONENTES
class Componente {
    <<abstract>>
    #codigo: String
    #nombre: String
    #precioBase: double
    #stock: int
    #categoriasCompatibles: String[]
    +obtenerDetalleTecnico()* String
    +calcularPrecioFinal()* double
}

class ComponentePasivo {
    -tolerancia: double
    -potenciaWatts: double
    +obtenerDetalleTecnico() String
    +calcularPrecioFinal() double
}

class ComponenteActivo {
    -voltajeOperacion: double
    -prestado: boolean
    -TARJETAS_PERMITIDAS: String[]$
    +esTarjetaDeDatos() boolean
    +prestar(dias: int) boolean
    +devolver() boolean
    +estaDisponible() boolean
    +obtenerDetalleTecnico() String
    +calcularPrecioFinal() double
}

%% 4. TRANSACCIONES
class DetalleTransaccion {
    -cantidad: int
    -precioUnitario: double
    +calcularSubtotal() double
}

class Venta {
    -idVenta: String
    +agregarDetalle(componente: Componente, cantidad: int) void
    +calcularPrecioFinal() double
}

class RegistroAlquiler {
    -idAlquiler: String
    -diasAlquilados: int
    -devuelto: boolean
    -recargoMora: double
    -tarifaDiaria: double
    +registrarDevolucion(diasRetraso: int, costoDiaMora: double) double
    +calcularPrecioFinal() double
}

class OrdenReabastecimiento {
    -idOrden: String
    -procesada: boolean
    +agregarItem(componente: Componente, cantidad: int, costoCompra: double) void
    +procesarOrden() void
}

%% 5. SISTEMA
class SistemaTiendaElectronica {
    -nombreTienda: String
    -inventarioBodega: Componente[][]
    +registrarComponente(c: Componente, fila: int, columna: int) void
    +actualizarStock(codigo: String, nuevoStock: int) boolean
    +buscarComponente(codigo: String) Componente
    +mostrarMapaBodega() void
    +generarReporteGeneral() void
}

%% RELACIONES DE HERENCIA
Persona <|-- Cliente
Persona <|-- Proveedor
Componente <|-- ComponentePasivo
Componente <|-- ComponenteActivo

%% REALIZACIÓN DE INTERFACES
ICalculable <|.. Componente
ICalculable <|.. Venta
ICalculable <|.. RegistroAlquiler
IPrestable <|.. ComponenteActivo

%% RELACIONES DE ASOCIACIÓN Y COMPOSICIÓN
DetalleTransaccion --> "1" Componente : referencia
Venta --> "1" Cliente : realizada a
Venta *-- "1..*" DetalleTransaccion : contiene

RegistroAlquiler --> "1" Cliente : solicitado por
RegistroAlquiler --> "1" ComponenteActivo : alquila

OrdenReabastecimiento --> "1" Proveedor : solicitada a
OrdenReabastecimiento *-- "1..*" DetalleTransaccion : contiene

SistemaTiendaElectronica *-- "1..*" Componente : inventario
SistemaTiendaElectronica o-- "*" Cliente : gestiona
SistemaTiendaElectronica o-- "*" Proveedor : gestiona
SistemaTiendaElectronica o-- "*" Venta : procesa
SistemaTiendaElectronica o-- "*" RegistroAlquiler : procesa
SistemaTiendaElectronica o-- "*" OrdenReabastecimiento : procesa
```


