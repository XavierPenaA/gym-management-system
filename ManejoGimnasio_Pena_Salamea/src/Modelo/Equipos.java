package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Equipos implements Serializable {
    String codigo;
    String descripcion;
    String estado;
    LocalDate fechaAdquisicion;
    LocalDate fechaProxima;
    public ArrayList<Material> materiales;

    public Equipos(String codigo, String descripcion, String estado, ArrayList<Material> materiales, LocalDate fechaAdquisicion, LocalDate fechaProxima) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.materiales=new ArrayList<>();
        this.materiales.addAll(materiales);
        this.fechaAdquisicion=fechaAdquisicion;
        this.fechaProxima=fechaProxima;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public LocalDate getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(LocalDate fechaProxima) {
        this.fechaProxima = fechaProxima;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }
}
