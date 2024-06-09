package Modelo;

import java.io.Serializable;

public class Actividad implements Serializable {
    String codigo;
    String nombre;
    String descripcion;
    int cupos;
    int disponible;
    String estado;
    Horario horario;
    Ubicacion ubicacion;
    Personal instructor;
    double precio;
    String dia;

    public Actividad(String codigo, String nombre, String descripcion, int cupos, int disponible, String estado, Horario horario, Ubicacion ubicacion,Personal instructor, double precio, String dia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cupos = cupos;
        this.disponible = disponible;
        this.estado = estado;
        this.horario = horario;
        this.ubicacion = ubicacion;
        this.instructor=instructor;
        this.precio = precio;
        this.dia = dia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Personal getInstructor() {
        return instructor;
    }

    public void setInstructor(Personal instructor) {
        this.instructor = instructor;
    }
}
