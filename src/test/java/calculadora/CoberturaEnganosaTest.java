package calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Demostracion de cobertura enganosa")
class CoberturaEnganosaTest {

    @Test
    @DisplayName("Una llamada sin asercion aumenta cobertura, pero no verifica el resultado")
    void llamadaSinAsercion() {
        new Calculadora().sumar(2, 2);
    }
}