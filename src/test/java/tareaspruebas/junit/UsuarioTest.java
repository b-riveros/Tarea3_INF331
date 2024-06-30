package tareaspruebas.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.Assert.*;

public class UsuarioTest {

    private GestorUsuarios gestorUsuarios;
    private final InputStream sysInBackup = System.in;
    private ByteArrayInputStream in;

    @Before
    public void setUp() {
    	
        gestorUsuarios = new GestorUsuarios();
        Usuario usuario1 = new Usuario("001", "Bárbara", "IT", "Desarrollador");
        Usuario usuario2 = new Usuario("002", "Pablo", "Finanzas", "Contador");

        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put(usuario1.getId(), usuario1);
        usuarios.put(usuario2.getId(), usuario2);

        gestorUsuarios.setUsuarios(usuarios);

    }

    @After
    public void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    public void testAgregarUsuario() {
        // Preparar entrada simulada
        String input = "1\n004\nCarlos\nRRHH\nAnalista\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorUsuarios.menu(scanner);

        // Verificar que el usuario se agregó correctamente
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId("004");
        assertNotNull(usuario);
        assertEquals("Carlos", usuario.getNombre());
        assertEquals("RRHH", usuario.getDepartamento());
        assertEquals("Analista", usuario.getDescripcion());
    }
    
    @Test
    public void testAgregarUsuarioIncorrecto() {
        // Preparar entrada simulada
        String input = "1\n004\n\nRRHH\nAnalista\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorUsuarios.menu(scanner);

        // Verificar que el usuario se agregó correctamente
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId("004");
        assertNull(usuario);
    }

    @Test
    public void testListarUsuarios() {
        // Preparar entrada simulada
        String simulatedInput = "2\n5\n"; // Suponiendo que '1' es la opción para listar usuarios
        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStreamCaptor);
    
        // Capturar la salida estándar
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scanner = new Scanner(System.in);

        // Llamar al método que genera la salida
        try {
            gestorUsuarios.menu(scanner);
        } finally {
            System.setOut(originalOut);
        }

        // Obtener la salida capturada
        String actualOutput = outputStreamCaptor.toString().trim();

        // Comparar salida esperada con la salida actual
        // Verificar que la salida contiene los fragmentos esperados
        assertTrue(actualOutput.contains("ID: 001, Nombre: Bárbara, Departamento: IT, Descripción: Desarrollador"));
        assertTrue(actualOutput.contains("ID: 002, Nombre: Pablo, Departamento: Finanzas, Descripción: Contador"));
    }

    @Test
    public void testActualizarUsuario() {
        // Preparar entrada simulada
        String input = "3\n002\nAna\nMarketing\nEspecialista\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorUsuarios.menu(scanner);

        // Verificar que el usuario se actualizó correctamente
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId("002");
        assertNotNull(usuario);
        assertEquals("Ana", usuario.getNombre());
        assertEquals("Marketing", usuario.getDepartamento());
        assertEquals("Especialista", usuario.getDescripcion());
    }

    @Test
    public void testActualizarUsuarioIncorrecto() {
        // Preparar entrada simulada
        String input = "3\n002\n\nMarketing\n\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorUsuarios.menu(scanner);

        // Verificar que el usuario no se actualizó
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId("002");
        assertNotNull(usuario);
        assertEquals("Pablo", usuario.getNombre());
        assertEquals("Finanzas", usuario.getDepartamento());
        assertEquals("Contador", usuario.getDescripcion());
    }
    
    
    
    @Test
    public void testEliminarUsuarioExistente() {
        // Preparar entrada simulada
        String input = "4\n002\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Ejecutar método de prueba
        gestorUsuarios.menu(scanner);

        // Verificar que el usuario se eliminó correctamente
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId("002");
        assertNull(usuario);
    }
   
}

