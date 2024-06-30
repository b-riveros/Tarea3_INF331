package tareaspruebas.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SalaTest {

    private final InputStream originalSystemIn = System.in;
    private ByteArrayInputStream inputStream;
    private GestorSalas gestorSalas;

    @Before
    public void setUp() {

        // Crear un GestorSalas y agregar una sala manualmente al mapa salas
        gestorSalas = new GestorSalas();
        Sala salaInicial1 = new Sala("C001", "Sala de prueba", "Ubicación de prueba");
        Sala salaInicial2 = new Sala("C002", "Sala de prueba 2", "Piso 2");

        Map<String, Sala> salas = new HashMap<>();
        salas.put(salaInicial1.getCodigo(), salaInicial1);
        salas.put(salaInicial2.getCodigo(), salaInicial2);

        gestorSalas.setSalas(salas);
       
    }

    @After
    public void tearDown() {
        // Restaurar System.in al original después de cada prueba
        System.setIn(originalSystemIn);
    }

    @Test
    public void testAgregarSalaCorrecto() {
        // Simular entrada de usuario para agregarSala
        String inputAgregar = "1\nC001\nSala de prueba\nUbicación de prueba\n5\n";
        inputStream = new ByteArrayInputStream(inputAgregar.getBytes());
        System.setIn(inputStream);
        gestorSalas.menu(new Scanner(System.in));

        // Verificar el resultado esperado
        assertEquals(2, gestorSalas.cantidadSalas());
        assertNotNull(gestorSalas.buscarSalaPorCodigo("C001"));
        assertEquals("Sala de prueba", gestorSalas.buscarSalaPorCodigo("C001").getNombre());
        assertEquals("Ubicación de prueba", gestorSalas.buscarSalaPorCodigo("C001").getUbicacion());
    }

    @Test
    public void testEliminarSala() {
        // Simular entrada de usuario para eliminar una sala
        String inputEliminar = "4\nC001\n5\n";
        inputStream = new ByteArrayInputStream(inputEliminar.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método bajo prueba
        gestorSalas.menu(new Scanner(System.in));

        // Verificar el resultado esperado
        assertEquals(1, gestorSalas.cantidadSalas());
        assertNull(gestorSalas.buscarSalaPorCodigo("C001"));
    }

    @Test
    public void testActualizarSala() {
        // Simular entrada de usuario para actualizarSala
        String inputActualizar = "3\nC001\nNuevo Nombre\nNueva Ubicación\n5\n";
        inputStream = new ByteArrayInputStream(inputActualizar.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método bajo prueba
        gestorSalas.menu(new Scanner(System.in));

        // Verificar el resultado esperado
        assertEquals("Nuevo Nombre", gestorSalas.buscarSalaPorCodigo("C001").getNombre());
        assertEquals("Nueva Ubicación", gestorSalas.buscarSalaPorCodigo("C001").getUbicacion());
    }
    
    @Test
    public void testListarSalas() {
        // Simular entrada de usuario para listarSalas
        String inputActualizar = "2\n5\n";
        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(inputActualizar.getBytes());
        System.setIn(inputStreamCaptor);

        // Capturar la salida estándar
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));

        // Ejecutar el método bajo prueba
        try {
            gestorSalas.menu(new Scanner(System.in));
        } finally {
            System.setOut(originalOut);
            System.setIn(originalSystemIn); // Restaurar System.in
        }

        // Obtener la salida capturada
        String actualOutput = outputStreamCaptor.toString().trim();

        // Verificar que la salida contiene los fragmentos esperados
        assertTrue(actualOutput.contains("Código: C001, Nombre: Sala de prueba, Ubicación: Ubicación de prueba, Reservada: No"));
        assertTrue(actualOutput.contains("Código: C002, Nombre: Sala de prueba 2, Ubicación: Piso 2, Reservada: No"));
    }

    
    
    
    
}



