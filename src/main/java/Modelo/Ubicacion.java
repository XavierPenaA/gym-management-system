package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Ubicacion implements Serializable {
    String codigo;
    String nombre;
    public ArrayList<Equipos> equipos;

    public Ubicacion(String codigo, String nombre, ArrayList<Equipos> equipos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.equipos=new ArrayList<>();
        this.equipos.addAll(equipos);
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

    public ArrayList<Equipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipos> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'';
    }
}
