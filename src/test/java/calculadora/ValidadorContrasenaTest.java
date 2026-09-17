package calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TDD: validar contrasena")
class ValidadorContrasenaTest {

    private final ValidadorContrasena validador = new ValidadorContrasena();

    @ParameterizedTest(name = "{0} => {1}")
    @CsvSource({
            "Abc12345, true",
            "Clave2026, true",
            "abc12345, false",
            "ABCDEFgh, false",
            "Abc1234, false",
            "'', false"
    })
    @DisplayName("Acepta solo contrasenas con longitud, numero y mayuscula")
    void validaLasReglasDeLaContrasena(String clave, boolean esperado) {
        assertEquals(esperado, validador.validarContrasena(clave));
    }

    @ParameterizedTest(name = "clave nula => {0}")
    @CsvSource("false")
    @DisplayName("Rechaza una contrasena nula")
    void rechazaClaveNula(boolean esperado) {
        assertEquals(esperado, validador.validarContrasena(null));
    }
}