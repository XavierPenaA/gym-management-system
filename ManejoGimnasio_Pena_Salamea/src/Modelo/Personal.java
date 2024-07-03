package Modelo;

import java.io.Serializable;

public class Personal extends Persona implements Serializable {
    String rol;
    String contrasenia;
    Jornada jornada;

    public Personal(String cedula, String nombres, String apellidos, String direccion, String telefono, String rol, String contrasenia, Jornada jornada,String rutaFoto) {
        super(cedula, nombres, apellidos, direccion, telefono,rutaFoto);
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.jornada = jornada;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "nombres:'" + nombres + '\'' +
                ", cedula:'" + cedula + '\'';
    }
}
