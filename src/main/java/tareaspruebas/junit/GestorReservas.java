package tareaspruebas.junit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorReservas {
    private List<Reserva> reservas;
    private GestorSalas gestorSalas;
    private GestorUsuarios gestorUsuarios;

    public GestorReservas(GestorSalas gestorSalas, GestorUsuarios gestorUsuarios) {
        this.reservas = new ArrayList<>();
        this.gestorSalas = gestorSalas;
        this.gestorUsuarios = gestorUsuarios;
    }

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("Gestión de Reservas");
            System.out.println("1. Crear Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Actualizar Reserva");
            System.out.println("4. Eliminar Reserva");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    crearReserva(scanner);
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    actualizarReserva(scanner);
                    break;
                case 4:
                    eliminarReserva(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Método para buscar una reserva por código de sala y fecha
    public Reserva buscarReserva(String codigoSala, String idUsuario) {
        for (Reserva reserva : reservas) {
            if (reserva.getSala().getCodigo().equals(codigoSala) && reserva.getUsuario().getId().equals(idUsuario)) {
                return reserva;
            }
        }
        return null; // Si no se encuentra ninguna reserva con los criterios especificados
    }
    
    private void crearReserva(Scanner scanner) {
        System.out.print("Ingrese el código de la sala a reservar: ");
        String codigoSala = scanner.nextLine();
        System.out.print("Ingrese el identificador del usuario: ");
        String idUsuario = scanner.nextLine();
        System.out.print("Ingrese la fecha de la reserva (DD-MM-YYYY): ");
        String fechaStr = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error al parsear la fecha. Asegúrese de ingresarla en el formato DD-MM-YYYY.");
            return;
        }

        Sala sala = gestorSalas.buscarSalaPorCodigo(codigoSala);
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId(idUsuario);

        if (sala != null && usuario != null && !sala.isReservada()) {
            Reserva reserva = new Reserva(sala, usuario, fecha);
            reservas.add(reserva);
            sala.setReservada(true);
            System.out.println("Reserva creada exitosamente.");
        } else {
            System.out.println("No se pudo crear la reserva. Verifique los datos ingresados.");
        }
    }

    private void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("Listado de Reservas:");
            for (Reserva reserva : reservas) {
                System.out.println("Sala: " + reserva.getSala().getCodigo() + ", Usuario: " + reserva.getUsuario().getId() + ", Fecha: " + reserva.getFecha());
            }
        }
    }

    private void actualizarReserva(Scanner scanner) {
        System.out.print("Ingrese el código de la sala de la reserva a actualizar: ");
        String codigoSala = scanner.nextLine();
        System.out.print("Ingrese el identificador del usuario de la reserva a actualizar: ");
        String idUsuario = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha de la reserva (DD-MM-YYYY): ");
        String fechaStr = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, formatter);
        } catch (Exception e) {
            System.out.println("Error al parsear la fecha. Asegúrese de ingresarla en el formato DD-MM-YYYY.");
            return;
        }

        boolean reservaEncontrada = false;
        for (Reserva reserva : reservas) {
            if (reserva.getSala().getCodigo().equals(codigoSala) && reserva.getUsuario().getId().equals(idUsuario)) {
                reserva.setFecha(fecha);
                System.out.println("Reserva actualizada exitosamente.");
                reservaEncontrada = true;
                break;
            }
        }

        if (!reservaEncontrada) {
            System.out.println("Reserva no encontrada.");
        }
    }

    private void eliminarReserva(Scanner scanner) {
        System.out.print("Ingrese el código de la sala de la reserva a eliminar: ");
        String codigoSala = scanner.nextLine();
        System.out.print("Ingrese el identificador del usuario de la reserva a eliminar: ");
        String idUsuario = scanner.nextLine();

        boolean reservaEncontrada = false;
        for (Reserva reserva : reservas) {
            if (reserva.getSala().getCodigo().equals(codigoSala) && reserva.getUsuario().getId().equals(idUsuario)) {
                reservas.remove(reserva);
                reserva.getSala().setReservada(false);
                System.out.println("Reserva eliminada exitosamente.");
                reservaEncontrada = true;
                break; // Importante salir del bucle al eliminar
            }
        }

        if (!reservaEncontrada) {
            System.out.println("Reserva no encontrada.");
        }
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public GestorSalas getGestorSalas() {
        return gestorSalas;
    }

    public void setGestorSalas(GestorSalas gestorSalas) {
        this.gestorSalas = gestorSalas;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    public void setGestorUsuarios(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
    }
}



