package tareaspruebas.junit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\nGestión de Usuarios");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de nextInt()

                switch (opcion) {
                    case 1:
                        agregarUsuario(scanner);
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        actualizarUsuario(scanner);
                        break;
                    case 4:
                        eliminarUsuario(scanner);
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
  

    void agregarUsuario(Scanner scanner) {
    	
        System.out.print("Ingrese el identificador del usuario: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el departamento del usuario: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese una descripción para el usuario: ");
        String descripcion = scanner.nextLine();

        // Validar que ninguna entrada sea nula o vacía
        if (id == null || id.isEmpty() || 
            nombre == null || nombre.isEmpty() || 
            departamento == null || departamento.isEmpty() || 
        	descripcion == null || descripcion.isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return;
        }   
        
        Usuario usuario = new Usuario(id, nombre, departamento, descripcion);
        usuarios.put(id, usuario);
        System.out.println("Usuario agregado exitosamente.");
    }

    private void listarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Departamento: " + usuario.getDepartamento() + ", Descripción: " + usuario.getDescripcion());
        }
    }

    private void actualizarUsuario(Scanner scanner) {
        System.out.print("Ingrese el identificador del usuario a actualizar: ");
        String id = scanner.nextLine();

        Usuario usuario = usuarios.get(id);
        if (usuario != null) {
            System.out.print("Ingrese el nuevo nombre del usuario: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el nuevo departamento del usuario: ");
            String departamento = scanner.nextLine();
            System.out.print("Ingrese la nueva descripción del usuario: ");
            String descripcion = scanner.nextLine();

            // Validar que ninguna entrada sea nula o vacía
            if (nombre == null || nombre.isEmpty() || 
                departamento == null || departamento.isEmpty() || 
            	descripcion == null || descripcion.isEmpty()) {
                System.out.println("Error: Los campos no pueden estar vacíos.");
                return;
            }               
            
            usuario.setNombre(nombre);
            usuario.setDepartamento(departamento);
            usuario.setDescripcion(descripcion);
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void eliminarUsuario(Scanner scanner) {
        System.out.print("Ingrese el identificador del usuario a eliminar: ");
        String id = scanner.next();

        if (usuarios.remove(id) != null) {
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.get(id);
    }

	public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
		
	}

	public Map<String, Usuario> getUsuarios() {
        return this.usuarios;
		
	}
}

