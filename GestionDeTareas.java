import java.util.Scanner;

public class GestionDeTareas {
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
