package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Verificacion {
    public static String mensajeERROR = "";
    private static Verificacion instancia;
    private Verificacion() {}
    public static Verificacion getInstancia() {
        if (instancia == null) {
            instancia = new Verificacion();
        }
        return instancia;
    }
    public boolean validarCedula(String cedula) {
        Verificacion.mensajeERROR = "Cédula no válida";
        if (cedula == null || cedula.length() != 10) {
            return false;
        }
        try {
            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;
            int digitoVerificador = Integer.parseInt(cedula.substring(9, 10));
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 1 || provincia > 24) {
                return false;
            }
            for (int i = 0; i < 9; i++) {
                int digito = Integer.parseInt(cedula.substring(i, i + 1)) * coeficientes[i];
                suma += digito > 9 ? digito - 9 : digito;
            }
            int mod = suma % 10;
            int digitoCalculado = mod == 0 ? 0 : 10 - mod;
            return digitoCalculado==digitoVerificador;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean validarNumero(String str) {
        Verificacion.mensajeERROR = "Se ha ingresado un número no válido";
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return str.matches("\\d+");
    }
    public boolean validarTelefonoEcuador(String telefono) {
        Verificacion.mensajeERROR = "Número de Teléfono Inválido";
        if (telefono == null) {
            return false;
        }
        return telefono.matches("^(09\\d{8})|(0[2-7]\\d{7})$");
    }
    public boolean validarLetras(String texto) {
        Verificacion.mensajeERROR = "Cadena Inválida";
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }
    public boolean esFechaMayor(LocalDate fecha1, LocalDate fecha2) {
        Verificacion.mensajeERROR = "La fecha de inicio debe ser anterior a la fecha de fin";
        if (fecha1 == null || fecha2 == null) {
            return false;
        }
        return fecha2.isAfter(fecha1);
    }
    public boolean esHoraMayor(LocalTime hora1, LocalTime hora2) {
        Verificacion.mensajeERROR = "La hora de inicio debe ser anterior a la hora de fin";
        if (hora1 == null || hora2 == null) {
            return false;
        }
        return hora2.isAfter(hora1);
    }
}
