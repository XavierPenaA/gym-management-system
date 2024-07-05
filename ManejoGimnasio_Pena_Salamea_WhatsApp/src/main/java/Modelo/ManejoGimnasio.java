package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManejoGimnasio implements Serializable {
    private static ManejoGimnasio instancia;
    public ArrayList<Gimnasio> gimnasios;
    public ArrayList<Ubicacion> ubicacionesSinAsignar;
    public ArrayList<Equipos> equiposSinAsignar;
    public ArrayList<Material> materialesSinAsignar;
    private ManejoGimnasio() {
        gimnasios = new ArrayList<>();
        ubicacionesSinAsignar=new ArrayList<>();
        equiposSinAsignar= new ArrayList<>();
        materialesSinAsignar= new ArrayList<>();
    }

    public static ManejoGimnasio getInstancia() {
        if (instancia == null) {
            instancia = new ManejoGimnasio();
        }
        return instancia;
    }
    public void agregarUbicaciones(String codigo, String nombre){
        ubicacionesSinAsignar.add(new Ubicacion(codigo,nombre,equiposSinAsignar));
        equiposSinAsignar.clear();
    }
    public void agregarEquipos(String codigo, String descripcion, String estado,LocalDate fechaAdquisicion, LocalDate fechaProxima){
        equiposSinAsignar.add(new Equipos(codigo,descripcion,estado,materialesSinAsignar,fechaAdquisicion, fechaProxima));
        materialesSinAsignar.clear();
    }
    public void agregarMateriales(String codigo, String descripcion, LocalDate fechaMantenimiento, String estado){
        materialesSinAsignar.add(new Material(codigo,descripcion,fechaMantenimiento,estado));
    }
    public void agregarGimnasio(String nombre, String direccion, String telefono,Horario horario) {
        gimnasios.add(new Gimnasio(nombre, direccion, telefono,horario,ubicacionesSinAsignar));
        ubicacionesSinAsignar.clear();
    }

    public void eliminarGimnasio(String nombre) {
        Gimnasio gimnasio = buscarGimnasio(nombre);
        if (gimnasio != null) {
            gimnasios.remove(gimnasio);
        } else {
            System.out.println("Gimnasio no encontrado.");
        }
    }
    public Gimnasio buscarGimnasio(String nombre) {
        for (Gimnasio gimnasio : gimnasios) {
            if (gimnasio.getNombre().equals(nombre)) {
                return gimnasio;
            }
        }
        return null;
    }

    public Ubicacion buscarUbicacion(String nombreGimnasio, String nombreUbicacion) {
        Gimnasio gimnasio = buscarGimnasio(nombreGimnasio);
        if (gimnasio != null) {
            for (Ubicacion ubicacion : gimnasio.getUbicaciones()) {
                if (ubicacion.getNombre().equals(nombreUbicacion)) {
                    return ubicacion;
                }
            }
        }
        return null;
    }

    public void imprimirGimnasios() {
        System.out.println("Lista de gimnasios:");
        for (Gimnasio gimnasio : gimnasios) {
            System.out.println("Nombre: " + gimnasio.getNombre());
            System.out.println("Dirección: " + gimnasio.getDireccion());
            System.out.println("Teléfono: " + gimnasio.getTelefono());
            System.out.println("Horario:");
            System.out.println("    Hora de inicio: " + gimnasio.getHorario().getHoraInicio());
            System.out.println("    Hora de fin: " + gimnasio.getHorario().getHoraFin());
            System.out.println("Ubicaciones:");
            for (Ubicacion ubicacion : gimnasio.ubicaciones) {
                System.out.println("  Código: " + ubicacion.getCodigo());
                System.out.println("  Nombre: " + ubicacion.getNombre());
                System.out.println("  Equipos:");
                for (Equipos equipo : ubicacion.equipos) {
                    System.out.println("    Código: " + equipo.getCodigo());
                    System.out.println("    Descripción: " + equipo.getDescripcion());
                    System.out.println("    Estado: " + equipo.getEstado());
                    System.out.println("    Fecha de adquisición: " + equipo.getFechaAdquisicion());
                    System.out.println("    Fecha próxima: " + equipo.getFechaProxima());
                    System.out.println("    Materiales:");
                    for (Material material : equipo.materiales) {
                        System.out.println("      Código: " + material.getCodigo());
                        System.out.println("      Descripción: " + material.getDescripcion());
                        System.out.println("      Fecha de mantenimiento: " + material.getFechaMantenimiento());
                        System.out.println("      Estado: " + material.getEstado());
                    }
                }
            }
            System.out.println();
        }
    }
    public ArrayList<Gimnasio> buscar(String valor) {
        ArrayList<Gimnasio> resultados = new ArrayList<>();
        for (Gimnasio gimnasio : gimnasios) {
                    if (gimnasio.getNombre().equalsIgnoreCase(valor)) {
                        resultados.add(gimnasio);
                    }
        }
        return resultados;
    }
    public Equipos buscarEquipo(String nombreGimnasio, String nombreUbicacion, String codigoEquipo) {
        Gimnasio gimnasio = buscarGimnasio(nombreGimnasio);
        if (gimnasio != null) {
            Ubicacion ubicacion = buscarUbicacion(nombreGimnasio, nombreUbicacion);
            if (ubicacion != null) {
                for (Equipos equipo : ubicacion.getEquipos()) {
                    if (equipo.getCodigo().equals(codigoEquipo)) {
                        return equipo;
                    }
                }
            }
        }
        return null;
    }
    public Material buscarMaterial(String nombreGimnasio, String nombreUbicacion, String codigoEquipo, String codigoMaterial) {
        Gimnasio gimnasio = buscarGimnasio(nombreGimnasio);
        if (gimnasio != null) {
            Ubicacion ubicacion = buscarUbicacion(nombreGimnasio, nombreUbicacion);
            if (ubicacion != null) {
                for (Equipos equipo : ubicacion.getEquipos()) {
                    if (equipo.getCodigo().equals(codigoEquipo)) {
                        for (Material material : equipo.getMateriales()) {
                            if (material.getCodigo().equals(codigoMaterial)) {
                                return material;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
