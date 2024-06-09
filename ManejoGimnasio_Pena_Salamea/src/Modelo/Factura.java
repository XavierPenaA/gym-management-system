package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Factura implements Serializable {
    String nombre;
    LocalDate fechaFacturacion;
    String cedulaUsuario;
    ArrayList<Detalle> detalles;
    private static int codigoCounter = 0;
    private int codigo;
    double precioFinal;

    public Factura(String nombre, LocalDate fechaFacturacion, ArrayList<Detalle> detalles,String cedulaUsuario, double precioFinal) {
        this.codigo = ++codigoCounter;
        this.nombre = nombre;
        this.fechaFacturacion = fechaFacturacion;
        this.detalles=new ArrayList<>();
        this.detalles.addAll(detalles);
        this.cedulaUsuario=cedulaUsuario;
        this.precioFinal=precioFinal;
    }
    public int getCodigo() {
        return codigo;
    }
    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(LocalDate fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public ArrayList<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<Detalle> detalles) {
        this.detalles = detalles;
    }
}
