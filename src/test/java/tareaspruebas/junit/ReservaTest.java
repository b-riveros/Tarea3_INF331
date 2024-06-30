package tareaspruebas.junit;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReservaTest {

    private GestorReservas gestorReservas;
    private GestorSalas gestorSalas;
    private GestorUsuarios gestorUsuarios;
    private final InputStream sysInBackup = System.in;
    private ByteArrayInputStream in;

    @Before
    public void setUp() {
        gestorSalas = new GestorSalas();
        gestorUsuarios = new GestorUsuarios();

        // Preparar datos de prueba (sala y usuario)
        Sala sala = new Sala("001", "Sala de Reuniones", "Piso 1");
        Sala sala2 = new Sala("002", "Sala de descanso", "Edificio M");

        Usuario usuario = new Usuario("20981387", "Bárbara", "IT", "Desarrollador");
        Usuario usuario2 = new Usuario("19695556", "Lucia", "Redes Sociales", "Diseño");


        Map<String, Usuario> usuarios = new HashMap<>();
        Map<String, Sala> salas = new HashMap<>();
        List<Reserva> reservas = new ArrayList<Reserva>();

        usuarios.put(usuario.getId(), usuario);
        usuarios.put(usuario2.getId(), usuario2);

        salas.put(sala.getCodigo(), sala);
        salas.put(sala2.getCodigo(), sala2);
        
        reservas.add(new Reserva (sala, usuario, LocalDate.of(2024, 7, 1)));

        gestorSalas.setSalas(salas);
        gestorUsuarios.setUsuarios(usuarios);
        
        gestorReservas = new GestorReservas(gestorSalas, gestorUsuarios);
        gestorReservas.setReservas(reservas);
    }

    @After
    public void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    public void testCrearReserva() {
        // Preparar entrada simulada
        String input = "1\n001\n001\n01-07-2024\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorReservas.menu(scanner);

        // Verificar que la reserva se creó correctamente
        assertEquals(1, gestorReservas.getReservas().size());
        Reserva reservaAgregada = gestorReservas.buscarReserva("001", "20981387");
        assertNotNull(reservaAgregada);
    }

    @Test
    public void testActualizarReserva() {
        // Preparar entrada simulada de actualización
        String input = "3\n001\n20981387\n05-07-2024\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorReservas.menu(scanner);

        // Verificar que la reserva se actualizó correctamente
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedFecha = LocalDate.of(2024, 7, 5);

        try {
            assertEquals(expectedFecha, gestorReservas.getReservas().get(0).getFecha());
        } catch (Exception e) {
            fail("Error al verificar la fecha de la reserva actualizada.");
        }
    }

    @Test
    public void testEliminarReserva() {
        // Crear una reserva para eliminar
        // Preparar entrada simulada para eliminar la reserva
        String input = "4\n001\n20981387\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorReservas.menu(scanner);

        // Verificar que la reserva se eliminó correctamente
        assertEquals(0, gestorReservas.getReservas().size());
    }
}



