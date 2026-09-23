package calculadora;

/**
 * Calculadora de referencia para el taller de pruebas unitarias.
 * Esta clase funciona correctamente: sus pruebas deben quedar en VERDE.
 */
public class Calculadora {

    // ----- Métodos básicos (vistos en clase) -----

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }

    // ----- Métodos nuevos (Parte 2 del taller) -----

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
        return numero % 2 == 0;
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para negativos");
        }
        if (n > 20) {
            throw new ArithmeticException("El resultado supera la capacidad de un long");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public double promedio(double[] numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("Se requiere al menos un número");
        }
        double suma = 0;
        for (double n : numeros) {
            suma += n;
        }
        return suma / numeros.length;
    }

    public double porcentaje(double valor, double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException("El porcentaje no puede ser negativo");
        }
        return valor * porcentaje / 100;
    }
}
