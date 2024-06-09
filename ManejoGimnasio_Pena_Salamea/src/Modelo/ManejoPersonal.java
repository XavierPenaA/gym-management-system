package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class ManejoPersonal implements Serializable {
    private static ManejoPersonal instancia;
    public ArrayList<Personal> personal;

    private ManejoPersonal() {
        personal = new ArrayList<>();
    }

    public static ManejoPersonal getInstancia() {
        if (instancia == null) {
            instancia = new ManejoPersonal();
        }
        return instancia;
    }
    public void registrarPersonal(String cedula, String nombres, String apellidos, String direccion, String telefono, String rol, String contrasenia, Jornada jornada) {
        personal.add(new Personal(cedula, nombres, apellidos, direccion, telefono, rol, contrasenia, jornada));
    }
    public Personal iniciarSesion(String cedula, String contrasenia) {
        for (Personal empleado : personal) {
            if (empleado.getCedula().equals(cedula) && empleado.getContrasenia().equals(contrasenia)) {
                return empleado;
            }
        }
        return null;
    }
    public Personal buscarPersonalPorNombre(String nombreCompleto) {
        for (Personal personal : personal) {
            String nombrePersonal = personal.getNombres() + " " + personal.getApellidos();
            if (nombrePersonal.equalsIgnoreCase(nombreCompleto)) {
                return personal;
            }
        }
        return null;
    }

    public void eliminarPersonal(String nombreCompleto) {
        Personal empleado = buscarPersonalPorNombre(nombreCompleto);
        if (empleado != null) {
            personal.remove(empleado);
        } else {
            System.out.println("Personal no encontrado.");
        }
    }

    public void imprimirPersonal() {
        System.out.println("Lista de personal:");
        for (Personal empleado : personal) {
            System.out.println("Cedula: " + empleado.getCedula());
            System.out.println("Nombres: " + empleado.getNombres());
            System.out.println("Apellidos: " + empleado.getApellidos());
            System.out.println("Direccion: " + empleado.getDireccion());
            System.out.println("Telefono: " + empleado.getTelefono());
            System.out.println("Rol: " + empleado.getRol());
            System.out.println("Contraseña: " + empleado.getContrasenia());
            System.out.println("Jornada: " + empleado.getJornada());
            System.out.println();
        }
    }
}
