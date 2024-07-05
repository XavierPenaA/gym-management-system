package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Material implements Serializable {
    String codigo;
    String descripcion;
    LocalDate fechaMantenimiento;
    String estado;
    public ArrayList<LocalDate> historialMantenimiento;

    public Material(String codigo, String descripcion, LocalDate fechaMantenimiento, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaMantenimiento = fechaMantenimiento;
        this.estado = estado;
        historialMantenimiento =new ArrayList<>();
        historialMantenimiento.add(fechaMantenimiento);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
        historialMantenimiento.add(fechaMantenimiento);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
