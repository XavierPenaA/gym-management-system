package Modelo;

import java.io.Serializable;
import java.time.LocalTime;

public class Jornada implements Serializable {
    String nombre;
    LocalTime horaInicio;
    LocalTime horaFin;

    public Jornada(String nombre, LocalTime horaInicio, LocalTime horaFin) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", \nhoraInicio=" + horaInicio +
                ", \nhoraFin=" + horaFin;
    }
}
