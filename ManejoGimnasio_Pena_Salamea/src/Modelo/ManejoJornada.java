package Modelo;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class ManejoJornada implements Serializable {
    public ArrayList<Jornada> jornadas;
    private static ManejoJornada instancia;

    private ManejoJornada() {
        jornadas = new ArrayList<>();
    }

    public static ManejoJornada getInstancia() {
        if (instancia == null) {
            instancia = new ManejoJornada();
        }
        return instancia;
    }

    public void agregarJornada(String nombre, LocalTime horaInicio, LocalTime horaFin) {
        jornadas.add(new Jornada(nombre, horaInicio, horaFin));
    }

    public void eliminarJornada(String nombre) {
        jornadas.removeIf(jornada -> jornada.getNombre().equals(nombre));
    }
    public Jornada buscarJornadaPorNombre(String nombreJornada) {
        for (Jornada jornada : jornadas) {
            if (jornada.getNombre().equals(nombreJornada)) {
                return jornada;
            }
        }
        return null;
    }
}
