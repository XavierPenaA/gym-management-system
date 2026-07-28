package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Horario implements Serializable {
    LocalDate fechaInicio;
    LocalDate fechaFin;
    LocalTime horaInicio;
    LocalTime horaFinal;

    public Horario(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFinal) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public Horario(LocalTime horaInicio, LocalTime horaFinal) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                '}';
    }
}
