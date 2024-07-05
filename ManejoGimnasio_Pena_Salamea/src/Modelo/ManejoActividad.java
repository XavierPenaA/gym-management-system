package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class ManejoActividad implements Serializable {
    private static ManejoActividad instancia;
    public ArrayList<Actividad> actividades;

    private ManejoActividad() {
        actividades = new ArrayList<>();
        actividades.add(new Actividad("RegistroDeMeses","Agregar Meses","Meses de membresía",0,0,"",null,null,null,30,""));
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
        if (actividad != null && actividad.getDisponible() > 0) {
            actividad.setDisponible(actividad.getDisponible() - 1);
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
}
