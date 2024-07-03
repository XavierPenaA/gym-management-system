package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Miembro extends Persona implements Serializable {
    LocalDate fechaInicio;
    LocalDate fechaFin;
    ArrayList<String> codigoFacturas;
    LinkedList<Map.Entry<String, LocalDate>> listaActividadesRegistrado;

    public Miembro(String cedula, String nombres, String apellidos, String direccion, String telefono, LocalDate fechaInicio, LocalDate fechaFin,String rutaFoto) {
        super(cedula, nombres, apellidos, direccion, telefono,rutaFoto);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        codigoFacturas=new ArrayList<>();
        listaActividadesRegistrado=new LinkedList<>();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public LinkedList<Map.Entry<String, LocalDate>> getListaActividadesRegistrado() {
        return listaActividadesRegistrado;
    }

}
