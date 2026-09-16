package calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Pruebas de la clase Calculadora")
class CalculadoraTest {

    private static final double DELTA = 0.0001;
    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Sumar dos números positivos")
    void sumarDosPositivos() {
        // Arrange (Preparar)
        double a = 5, b = 3;
        // Act (Actuar)
        double resultado = calc.sumar(a, b);
        // Assert (Verificar)
        assertEquals(8, resultado, DELTA);
    }

    @Test
    @DisplayName("Dividir entre cero lanza ArithmeticException")
    void dividirEntreCero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> calc.dividir(10, 0));
        assertEquals("No se puede dividir entre cero", ex.getMessage());
    }

    // TODO: escriba aquí sus pruebas para restar, multiplicar, dividir,
    //       potencia, raizCuadrada, esPar, factorial y promedio.

    // Nuevas pruebas
    @Test
    @DisplayName("Restar - Casos mínimos")
    void restarCasos() {
        // Act & Assert
        assertAll("Verificaciones del método restar",
                () -> assertEquals(5, calc.restar(10, 5), DELTA),
                () -> assertEquals(-5, calc.restar(5, 10), DELTA),
                () -> assertEquals(8.5, calc.restar(8.5, 0), DELTA)
        );
    }

    @Test
    @DisplayName("Multiplicar - Casos mínimos")
    void multiplicarCasos() {
        // Act & Assert
        assertAll("Verificaciones de multiplicación",
                () -> assertEquals(6, calc.multiplicar(2, 3), DELTA),
                () -> assertEquals(-6, calc.multiplicar(2, -3), DELTA),
                () -> assertEquals(6, calc.multiplicar(-2, -3), DELTA),
                () -> assertEquals(0, calc.multiplicar(5, 0), DELTA)
        );
    }

    @Test
    @DisplayName("Dividir - Casos mínimos restantes")
    void dividirOtrosCasos() {
        // Act & Assert
        assertAll("Verificaciones adicionales de división",
                () -> assertEquals(2.0, calc.dividir(10, 5), DELTA),
                () -> assertEquals(3.5, calc.dividir(7, 2), DELTA),
                () -> assertEquals(0.0, calc.dividir(0, 5), DELTA)
        );
    }

    @Test
    @DisplayName("Potencia - Casos mínimos")
    void potenciaCasos() {
        // Act & Assert
        assertAll("Verificaciones de potencia",
                () -> assertEquals(8, calc.potencia(2, 3), DELTA),
                () -> assertEquals(1, calc.potencia(5, 0), DELTA),
                () -> assertEquals(0.25, calc.potencia(2, -2), DELTA),
                () -> assertEquals(-8, calc.potencia(-2, 3), DELTA),
                () -> assertEquals(0, calc.potencia(0, 5), DELTA)
        );
    }

    @Test
    @DisplayName("Raíz Cuadrada - Casos válidos y error negativo")
    void raizCuadradaCasos() {
        // Act & Assert
        assertAll("Verificaciones de raíz cuadrada",
                () -> assertEquals(4, calc.raizCuadrada(16), DELTA),
                () -> assertEquals(0, calc.raizCuadrada(0), DELTA),
                () -> assertEquals(1.414213, calc.raizCuadrada(2), DELTA)
        );

        // Caso de error: Número negativo
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calc.raizCuadrada(-4));
        assertEquals("No existe raíz cuadrada real de un número negativo", ex.getMessage());
    }

    @ParameterizedTest(name = "esPar({0}) = {1}")
    @CsvSource({
            "4, true",
            "7, false",
            "0, true",
            "-2, true",
            "-3, false"
    })
    @DisplayName("Verificar si un número es par (Prueba Parametrizada)")
    void esParCasos(int numero, boolean esperado) {
        // Act & Assert
        assertEquals(esperado, calc.esPar(numero));
    }

    @Test
    @DisplayName("Factorial - Valores límites y excepciones")
    void factorialCasos() {
        // Act & Assert
        assertAll("Verificaciones válidas de factorial",
                () -> assertEquals(1, calc.factorial(0)),
                () -> assertEquals(1, calc.factorial(1)),
                () -> assertEquals(120, calc.factorial(5)),
                () -> assertEquals(2432902008176640000L, calc.factorial(20))
        );

        // Casos de excepciones
        IllegalArgumentException exNegativo = assertThrows(IllegalArgumentException.class,
                () -> calc.factorial(-1));
        assertEquals("El factorial no está definido para negativos", exNegativo.getMessage());

        ArithmeticException exExcede = assertThrows(ArithmeticException.class,
                () -> calc.factorial(21));
        assertEquals("El resultado supera la capacidad de un long", exExcede.getMessage());
    }

    @Test
    @DisplayName("Promedio - Casos mínimos y excepciones")
    void promedioCasos() {
        // Act & Assert
        assertAll("Verificaciones de promedios válidos",
                () -> assertEquals(4.0, calc.promedio(new double[]{2, 4, 6}), DELTA),
                () -> assertEquals(5.0, calc.promedio(new double[]{5}), DELTA),
                () -> assertEquals(-2.0, calc.promedio(new double[]{-2, 4, -8}), DELTA)
        );

        // Excepciones de arreglos vacíos o nulos
        IllegalArgumentException exVacio = assertThrows(IllegalArgumentException.class,
                () -> calc.promedio(new double[]{}));
        assertEquals("Se requiere al menos un número", exVacio.getMessage());

        IllegalArgumentException exNulo = assertThrows(IllegalArgumentException.class,
                () -> calc.promedio(null));
        assertEquals("Se requiere al menos un número", exNulo.getMessage());
    }
}
