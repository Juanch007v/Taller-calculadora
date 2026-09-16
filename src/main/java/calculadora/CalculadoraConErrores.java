package calculadora;

/**
 * ¡CUIDADO! Esta clase tiene 6 errores escondidos.
 * Debe cumplir exactamente la misma especificación que Calculadora.
 * Su misión: escribir pruebas que los detecten (pruebas en ROJO).
 * No lea el código buscando el error: deje que las pruebas lo encuentren.
 */
public class CalculadoraConErrores {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return b - a;
    }

    public double multiplicar(double a, double b) {
        if (a < 0 && b < 0) {
            return -(a * b);
        }
        return a * b;
    }

    public double dividir(double a, double b) {
        return a / b;
    }

    public double potencia(double base, int exponente) {
        return Math.pow(base, exponente);
    }

    public double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("No existe raíz cuadrada real de un número negativo");
        }
        return Math.sqrt(numero);
    }

    public boolean esPar(int numero) {
        return numero % 2 != 1;
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para negativos");
        }
        if (n > 20) {
            throw new ArithmeticException("El resultado supera la capacidad de un long");
        }
        long resultado = 1;
        for (int i = 1; i < n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public double promedio(double[] numeros) {
        double suma = 0;
        for (double n : numeros) {
            suma += n;
        }
        return suma / numeros.length;
    }
}
