package tareaspruebas.junit;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorSalas gestorSalas = new GestorSalas();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorReservas gestorReservas = new GestorReservas(gestorSalas, gestorUsuarios);

        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Salas");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestorUsuarios.menu(scanner);
                    break;
                case 2:
                    gestorSalas.menu(scanner);
                    break;
                case 3:
                    gestorReservas.menu(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
