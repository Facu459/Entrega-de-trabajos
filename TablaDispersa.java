
import java.util.Scanner;

public class TablaDispersa {

    private Tarea[] tabla;
    private int numElementos;
    private double factorCarga;
    private static final int TAMANIO = 101;
    

    public TablaDispersa() {
        this.tabla = new Tarea[TAMANIO];
        this.numElementos = 0;
        this.factorCarga = 0.0;
    }

    public boolean insertar(Tarea t) {
        if (t == null || numElementos >= TAMANIO)
            return false;

        int posicion = calcularPosicion(t.getId());
        int i = 0;

        while (tabla[posicion] != null && !tabla[posicion].getId().equals(t.getId())) {//equals compara el contenido de los objetos
            i++;
            posicion = resolverColision(posicion, i);
        }

        tabla[posicion] = t;
        numElementos++;
        calcularFactorCarga();
        return true;
    }

    public Tarea buscar(String id) {
        int posicion = calcularPosicion(id);
        int i = 0;

        while (tabla[posicion] != null && i < TAMANIO) {
            if (tabla[posicion].getId().equals(id))
                return tabla[posicion];
            i++;
            posicion = resolverColision(posicion, i);
        }

        return null;
    }

    public boolean eliminar(String id) {
        int posicion = calcularPosicion(id);
        int i = 0;

        while (tabla[posicion] != null && i < TAMANIO) {
            if (tabla[posicion].getId().equals(id)) {
                tabla[posicion].setEsAlta(false);
                numElementos--;
                calcularFactorCarga();
                return true;
            }
            i++;
            posicion = resolverColision(posicion, i);
        }

        return false;
    }

    public int calcularPosicion(String id) {
        double A = 0.6180339887; 
        double valor = obtenerValorNumerico(id);
        double producto = valor * A;
        double decimal = producto - Math.floor(producto);
        return (int) (decimal * TAMANIO);
    }

    private double obtenerValorNumerico(String id) {
        long valor = 0;
        for (int i = 0; i < id.length(); i++) {
            valor += id.charAt(i) * Math.pow(31, i); //31 es un número primo comunmente usado en tablas hash
            //charAt(i) devuelve el carácter en una posición específica de una cadena.
        }
        return valor;
    }

    public int resolverColision(int posicionInicial, int i) {
        return (posicionInicial + i * i) % TAMANIO;
    }

    public double calcularFactorCarga() {
        this.factorCarga = (double) numElementos / TAMANIO;
        return this.factorCarga;
    }

    public void mostrarTareas() {
        System.out.println("TAREAS ACTIVAS:");
        for (Tarea t : tabla) {
            if (t != null && t.getEsAlta()) {
                System.out.println(t);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TablaDispersa tabla = new TablaDispersa();

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar tarea");
            System.out.println("2. Buscar tarea por ID");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    Tarea nueva = new Tarea();
                    System.out.print("Nombre: ");
                    nueva.setNombre(scanner.nextLine());
                    System.out.print("Descripción: ");
                    nueva.setDescripcion(scanner.nextLine());
                    System.out.print("Estado: ");
                    nueva.setEstado(scanner.nextLine());
                    if (tabla.insertar(nueva)) {
                        System.out.println("Tarea insertada con ID: " + nueva.getId());
                    } else {
                        System.out.println("No se pudo insertar la tarea.");
                    }
                    break;
                case 2:
                    System.out.print("ID de la tarea a buscar: ");
                    String idBuscar = scanner.nextLine();
                    Tarea encontrada = tabla.buscar(idBuscar);
                    if (encontrada != null && encontrada.getEsAlta()) {
                        System.out.println("Tarea encontrada: " + encontrada);
                    } else {
                        System.out.println("Tarea no encontrada o dada de baja.");
                    }
                    break;
                case 3:
                    System.out.print("ID de la tarea a eliminar: ");
                    String idEliminar = scanner.nextLine();
                    if (tabla.eliminar(idEliminar)) {
                        System.out.println("Tarea eliminada lógicamente.");
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;
                case 4:
                    tabla.mostrarTareas();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}
