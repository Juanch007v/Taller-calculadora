# Guía de capturas para la entrega

El taller solicita tres evidencias. Todas pueden tomarse desde VS Code con la extensión **Extension Pack for Java** instalada.

## 1. Pruebas verdes contra `Calculadora`

1. Abra `src/test/java/calculadora/CalculadoraTest.java`.
2. Pulse el botón de reproducción que aparece sobre la clase o use el panel **Testing**.
3. Espere a que todas las pruebas terminen en verde.
4. Tome una captura donde se vean el explorador de pruebas y el resultado exitoso.
5. Guarde la imagen como `captura-01-pruebas-verdes.png`.

## 2. Pruebas rojas contra `CalculadoraConErrores`

1. Abra `src/test/java/calculadora/CalculadoraConErroresTest.java`.
2. Ejecute la clase completa desde el botón de reproducción.
3. Deben aparecer fallos en `restar`, `multiplicar`, `dividir`, `esPar`, `factorial` y `promedio`.
4. Tome una captura donde se vean las pruebas fallidas y el panel de resultados.
5. Guarde la imagen como `captura-02-pruebas-rojas.png`.

## 3. Evidencia del ciclo TDD de `porcentaje`

El método ya está implementado para que el proyecto final compile y pase las pruebas. Para evidenciar el ciclo solicitado:

1. Muestre primero `porcentajeCasosValidos` y `porcentajeNegativo` antes de ejecutar; esa es la especificación en forma de prueba.
2. Ejecute `CalculadoraTest` con el método `porcentaje` temporalmente ausente o incompleto y capture el resultado rojo como `captura-03a-tdd-rojo.png`.
3. Restaure la implementación actual de `porcentaje` en `Calculadora.java`.
4. Ejecute nuevamente `CalculadoraTest` y capture el resultado verde como `captura-03b-tdd-verde.png`.
5. Después de la captura, conserve la implementación final y ejecute todas las pruebas del proyecto.

## Archivos que deben adjuntarse

- `captura-01-pruebas-verdes.png`
- `captura-02-pruebas-rojas.png`
- `captura-03a-tdd-rojo.png`
- `captura-03b-tdd-verde.png`
- El proyecto sin `target/`.
- El documento de respuestas del taller.