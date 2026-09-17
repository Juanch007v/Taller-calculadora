package calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas que detectan los errores de CalculadoraConErrores")
class CalculadoraConErroresTest {

    private static final double DELTA = 0.0001;
    private CalculadoraConErrores calc;

    @BeforeEach
    void setUp() {
        calc = new CalculadoraConErrores();
    }

    @Test
    @DisplayName("Restar - debe devolver a - b")
    void restarCasos() {
        assertAll("restar",
                () -> assertEquals(5, calc.restar(10, 5), DELTA),
                () -> assertEquals(-5, calc.restar(5, 10), DELTA),
                () -> assertEquals(8.5, calc.restar(8.5, 0), DELTA)
        );
    }

    @Test
    @DisplayName("Multiplicar - el producto de negativos debe ser positivo")
    void multiplicarCasos() {
        assertAll("multiplicar",
                () -> assertEquals(6, calc.multiplicar(2, 3), DELTA),
                () -> assertEquals(-6, calc.multiplicar(2, -3), DELTA),
                () -> assertEquals(6, calc.multiplicar(-2, -3), DELTA),
                () -> assertEquals(0, calc.multiplicar(5, 0), DELTA)
        );
    }

    @Test
    @DisplayName("Dividir entre cero lanza ArithmeticException con el mensaje esperado")
    void dividirEntreCero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> calc.dividir(10, 0));
        assertEquals("No se puede dividir entre cero", ex.getMessage());
    }

    @ParameterizedTest(name = "esPar({0}) = {1}")
    @CsvSource({
            "4, true",
            "7, false",
            "0, true",
            "-2, true",
            "-3, false"
    })
    @DisplayName("esPar - los números impares negativos deben devolver false")
    void esParCasos(int numero, boolean esperado) {
        assertEquals(esperado, calc.esPar(numero));
    }

    @Test
    @DisplayName("Factorial - debe calcular 5!, 20! y rechazar negativos y sobrecarga")
    void factorialCasos() {
        assertAll("factorial",
                () -> assertEquals(1, calc.factorial(0)),
                () -> assertEquals(1, calc.factorial(1)),
                () -> assertEquals(120, calc.factorial(5)),
                () -> assertEquals(2432902008176640000L, calc.factorial(20))
        );

        IllegalArgumentException exNegativo = assertThrows(IllegalArgumentException.class,
                () -> calc.factorial(-1));
        assertEquals("El factorial no está definido para negativos", exNegativo.getMessage());

        ArithmeticException exExcede = assertThrows(ArithmeticException.class,
                () -> calc.factorial(21));
        assertEquals("El resultado supera la capacidad de un long", exExcede.getMessage());
    }

    @Test
    @DisplayName("Promedio - debe validar arreglos vacíos y nulos")
    void promedioCasos() {
        assertAll("promedio",
                () -> assertEquals(4.0, calc.promedio(new double[]{2, 4, 6}), DELTA),
                () -> assertEquals(5.0, calc.promedio(new double[]{5}), DELTA),
                () -> assertEquals(-2.0, calc.promedio(new double[]{-2, 4, -8}), DELTA)
        );

        IllegalArgumentException exVacio = assertThrows(IllegalArgumentException.class,
                () -> calc.promedio(new double[]{}));
        assertEquals("Se requiere al menos un número", exVacio.getMessage());

        IllegalArgumentException exNulo = assertThrows(IllegalArgumentException.class,
                () -> calc.promedio(null));
        assertEquals("Se requiere al menos un número", exNulo.getMessage());
    }
}
