package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class ManejoMiembros implements Serializable{
    private static ManejoMiembros instancia;
    public ArrayList<Miembro> miembros;

    private ManejoMiembros() {
        miembros = new ArrayList<>();
    }

    public static ManejoMiembros getInstancia() {
        if (instancia == null) {
            instancia = new ManejoMiembros();
        }
        return instancia;
    }

    public void registrarMiembro(String cedula, String nombres, String apellidos, String direccion, String telefono, LocalDate fechaInicio, LocalDate fechaFin,String rutaFoto) {
        miembros.add(new Miembro(cedula, nombres, apellidos, direccion, telefono, fechaInicio, fechaFin,rutaFoto));
    }
    public void agregarFactura(String cedula, String nombreFactura){
        for (Miembro miembro : miembros) {
            if (miembro.getCedula().equals(cedula)) {
                miembro.codigoFacturas.add(nombreFactura);
            }
        }
    }

    public Miembro buscarMiembro(String cedula) {
        for (Miembro miembro : miembros) {
            if (miembro.getCedula().equals(cedula)) {
                return miembro;
            }
        }
        return null;
    }

    public void eliminarMiembro(String cedula) {
        Miembro miembro = buscarMiembro(cedula);
        if (miembro != null) {
            miembros.remove(miembro);
        } else {
            System.out.println("Miembro no encontrado.");
        }
    }
    public Map.Entry<String, LocalDate> buscarActividadRegistrada(String cedula, String codigoActividad) {
        Miembro miembro = buscarMiembro(cedula);
        if (miembro != null) {
            for (Map.Entry<String, LocalDate> actividad : miembro.getListaActividadesRegistrado()) {
                if (actividad.getKey().equals(codigoActividad)) {
                    return actividad;
                }
            }
        }
        return null;
    }
    public Map.Entry<String, LocalDate> buscarActividadDeMiembro(String cedula, String codigoActividad) {
        Miembro miembro = buscarMiembro(cedula);
        if (miembro != null) {
            for (Map.Entry<String, LocalDate> actividad : miembro.listaActividadesRegistrado) {
                if (actividad.getKey().equals(codigoActividad)) {
                    return actividad;
                }
            }
        } else {
            System.out.println("Miembro no encontrado.");
        }
        return null;
    }

    public boolean eliminarActividadDeMiembro(String cedula, String codigoActividad) {
        Miembro miembro = buscarMiembro(cedula);
        if (miembro != null) {
            Map.Entry<String, LocalDate> actividad = buscarActividadDeMiembro(cedula, codigoActividad);
            if (actividad != null) {
                miembro.listaActividadesRegistrado.remove(actividad);
                return true;
            }
        } else {
            System.out.println("Miembro no encontrado.");
        }
        return false;
    }

    public void agregarOModificarActividadAMiembro(String cedula, String codigoActividad, int meses) {
        Miembro miembro = buscarMiembro(cedula);
        if (miembro != null) {
            Map.Entry<String, LocalDate> actividadExistente = buscarActividadDeMiembro(cedula, codigoActividad);
            LocalDate nuevaFecha;
            if (actividadExistente != null) {
                nuevaFecha = actividadExistente.getValue().plusMonths(meses);
                miembro.listaActividadesRegistrado.remove(actividadExistente);
            } else {
                nuevaFecha = LocalDate.now().plusMonths(meses);
            }
            miembro.listaActividadesRegistrado.add(new AbstractMap.SimpleEntry<>(codigoActividad, nuevaFecha));
            System.out.println("Actividad " + (actividadExistente != null ? "actualizada" : "agregada") + " para el miembro " + miembro.getNombres());
        } else {
            System.out.println("Miembro no encontrado.");
        }
    }
    public void imprimirMiembros() {
        System.out.println("Lista de miembros:");
        for (Miembro miembro : miembros) {
            System.out.println("Cedula: " + miembro.getCedula());
            System.out.println("Nombres: " + miembro.getNombres());
            System.out.println("Apellidos: " + miembro.getApellidos());
            System.out.println("Dirección: " + miembro.getDireccion());
            System.out.println("Teléfono: " + miembro.getTelefono());
            System.out.println("Fecha de Inicio: " + miembro.getFechaInicio());
            System.out.println("Fecha de Fin: " + miembro.getFechaFin());
            System.out.println("Códigos de Facturas:");
            for (String codigoFactura : miembro.codigoFacturas) {
                System.out.println("  Código de Factura: " + codigoFactura);
            }
            System.out.println("Actividades Registradas:");
            for (Map.Entry<String, LocalDate> actividad : miembro.listaActividadesRegistrado) {
                System.out.println("  Código de Actividad: " + actividad.getKey() + ", Fecha: " + actividad.getValue());
            }
            System.out.println();
        }
    }
}
