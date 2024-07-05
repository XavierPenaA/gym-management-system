package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Gimnasio implements Serializable {
    String nombre;
    String direccion;
    String telefono;
    Horario horario;
    public ArrayList<Ubicacion> ubicaciones;

    public Gimnasio(String nombre, String direccion, String telefono, Horario horario, ArrayList<Ubicacion> ubicaciones) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horario=horario;
        this.ubicaciones=new ArrayList<>();
        this.ubicaciones.addAll(ubicaciones);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public ArrayList<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
}
