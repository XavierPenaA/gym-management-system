package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Detalle implements Serializable {
    double precio;
    int cantidad;
    double precioTotal;
    String codigoActividades;
    public Detalle(double precio, int cantidad,String codigoActividades) {
        this.precio = precio;
        this.cantidad = cantidad;
        precioTotal=precio*cantidad;
        this.codigoActividades = codigoActividades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoActividades() {
        return codigoActividades;
    }

    public void setCodigoActividades(String codigoActividades) {
        this.codigoActividades = codigoActividades;
    }
}
