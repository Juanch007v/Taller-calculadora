package calculadora;

public class ValidadorContrasena {

    public boolean validarContrasena(String clave) {
        if (clave == null || clave.length() < 8) {
            return false;
        }

        boolean tieneNumero = false;
        boolean tieneMayuscula = false;

        for (char caracter : clave.toCharArray()) {
            if (Character.isDigit(caracter)) {
                tieneNumero = true;
            }
            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            }
        }

        return tieneNumero && tieneMayuscula;
    }
}