package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Material implements Serializable {
    String codigo;
    String descripcion;
    LocalDate fechaMantenimiento;
    String estado;

    public Material(String codigo, String descripcion, LocalDate fechaMantenimiento, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaMantenimiento = fechaMantenimiento;
        this.estado = estado;
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
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
