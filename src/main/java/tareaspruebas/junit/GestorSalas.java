package tareaspruebas.junit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorSalas {
    private Map<String, Sala> salas;

    public GestorSalas() {
        this.salas = new HashMap<>();
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\nGestión de Salas");
            System.out.println("1. Agregar Sala");
            System.out.println("2. Listar Salas");
            System.out.println("3. Actualizar Sala");
            System.out.println("4. Eliminar Sala");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
	                case 1:
	                    agregarSala(scanner);
	                    break;
	                case 2:
	                    listarSalas();
	                    break;
	                case 3:
	                    actualizarSala(scanner);
	                    break;
	                case 4:
	                    eliminarSala(scanner);
	                    break;
	                case 5:
	                    return;
	                default:
	                    System.out.println("Opción no válida.");
            }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: La opcion ingresada debe ser un numero.");
                scanner.nextLine(); // Limpiar el búfer del Scanner
            }
            
        }
    }

    private void agregarSala(Scanner scanner) {
        System.out.print("Ingrese el código de la sala: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre de la sala: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la ubicación de la sala: ");
        String ubicacion = scanner.nextLine();

        // Validar que ninguna entrada sea nula o vacía
        if (codigo == null || codigo.isEmpty() || 
            nombre == null || nombre.isEmpty() || 
            ubicacion == null || ubicacion.isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return;
        }
        
        Sala sala = new Sala(codigo, nombre, ubicacion);
        salas.put(codigo, sala);
        System.out.println("Sala agregada exitosamente.");
    }

    private void listarSalas() {
        for (Sala sala : salas.values()) {
            String reservada = sala.isReservada() ? "Sí" : "No";
            System.out.println("Código: " + sala.getCodigo() + ", Nombre: " + sala.getNombre() + ", Ubicación: " + sala.getUbicacion() + ", Reservada: " + reservada);
        }
    }

    private void actualizarSala(Scanner scanner) {
        System.out.print("Ingrese el código de la sala a actualizar: ");
        String codigo = scanner.nextLine();

        Sala sala = salas.get(codigo);
        if (sala != null) {
            System.out.print("Ingrese el nuevo nombre de la sala: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la nueva ubicación de la sala: ");
            String ubicacion = scanner.nextLine();

            sala.setNombre(nombre);
            sala.setUbicacion(ubicacion);
            System.out.println("Sala actualizada exitosamente.");
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    private void eliminarSala(Scanner scanner) {
        System.out.print("Ingrese el código de la sala a eliminar: ");
        String codigo = scanner.next();

        if (salas.remove(codigo) != null) {
            System.out.println("Sala eliminada exitosamente.");
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

   
    public Sala buscarSalaPorCodigo(String codigo) {
        return salas.get(codigo); 
    }
    
    public int cantidadSalas() {
        return salas.size();
    }

    // Getter para obtener el mapa de salas
    public Map<String, Sala> getSalas() {
        return salas;
    }

    // Setter para establecer el mapa de salas
    public void setSalas(Map<String, Sala> salas) {
        this.salas = salas;
    }
}

