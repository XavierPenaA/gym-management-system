package Modelo;

import java.io.Serializable;

public class Propietario extends Persona implements Serializable {
    String contrasenia;
    String ruc;

    public Propietario(String cedula, String nombres, String apellidos, String direccion, String telefono, String contrasenia, String ruc) {
        super(cedula, nombres, apellidos, direccion, telefono);
        this.contrasenia = contrasenia;
        this.ruc = ruc;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
