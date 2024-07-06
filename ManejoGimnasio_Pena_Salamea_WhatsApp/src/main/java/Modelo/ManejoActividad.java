package Modelo;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ManejoActividad implements Serializable {
    private static ManejoActividad instancia;
    public ArrayList<Actividad> actividades;

    private ManejoActividad() {
        actividades = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(0, 0); // Medianoche
        LocalTime horaFinal = LocalTime.of(23, 59);
        Horario registroHorario=new Horario(horaInicio,horaFinal);
    }

    public static ManejoActividad getInstancia() {
        if (instancia == null) {
            instancia = new ManejoActividad();
        }
        return instancia;
    }

    public void agregarActividad(String codigo, String nombre, String descripcion, int cupos, int disponible, String estado, Horario horario, Ubicacion ubicacion, Personal instructor,double precio, String dia) {
        actividades.add(new Actividad(codigo, nombre, descripcion, cupos, disponible, estado, horario, ubicacion,instructor, precio, dia));
    }
    public Actividad buscarActividadCodigo(String codigo) {
        for (Actividad actividad : actividades) {
            if (actividad.getCodigo().equalsIgnoreCase(codigo)) {
                return actividad;
            }
        }
        return null;
    }
    public Actividad buscarActividad(String nombre) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                return actividad;
            }
        }
        return null;
    }

    public void eliminarActividad(String codigo) {
        Actividad actividad = buscarActividad(codigo);
        if (actividad != null) {
            actividades.remove(actividad);
        } else {
            System.out.println("Actividad no encontrada.");
        }
    }
    public void reducirDisponibilidad(String codigo) {
        Actividad actividad = buscarActividad(codigo);

        if (actividad == null) {
            System.out.println("Error: Actividad no encontrada para el código: " + codigo);
        } else {
            if (actividad.getDisponible() > 0) {
                actividad.setDisponible(actividad.getDisponible() - 1);
                System.out.println("Disponibilidad reducida para la actividad con código: " + codigo);
            } else {
                System.out.println("No hay disponibilidad para reducir en la actividad con código: " + codigo);
            }
        }
    }

    public void imprimirActividades() {
        System.out.println("Lista de actividades:");
        for (Actividad actividad : actividades) {
            System.out.println("Código: " + actividad.getCodigo());
            System.out.println("Nombre: " + actividad.getNombre());
            System.out.println("Descripción: " + actividad.getDescripcion());
            System.out.println("Cupos: " + actividad.getCupos());
            System.out.println("Disponible: " + actividad.getDisponible());
            System.out.println("Estado: " + actividad.getEstado());
            System.out.println("Horario: " + actividad.getHorario().toString());
            System.out.println("Ubicación: " + actividad.getUbicacion());
            System.out.println("Precio: " + actividad.getPrecio());
            System.out.println("Día: " + actividad.getDia());
            System.out.println();
        }
    }
    public ArrayList<Actividad> buscar(String criterio, String valor) {
        ArrayList<Actividad> resultados = new ArrayList<>();
        for (Actividad actividad : actividades) {
            switch (criterio) {
                case "Codigo":
                    if (actividad.getCodigo().equalsIgnoreCase(valor)) {
                        resultados.add(actividad);
                    }
                    break;
                case "Nombre":
                    if (actividad.getNombre().equalsIgnoreCase(valor)) {
                        resultados.add(actividad);
                    }
                    break;
                default:
                    break;
            }
        }
        return resultados;
    }
}
