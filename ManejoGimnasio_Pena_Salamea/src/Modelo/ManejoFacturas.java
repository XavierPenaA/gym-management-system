package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManejoFacturas implements Serializable {
    private static ManejoFacturas instancia;
    public ArrayList<Factura> facturas;
    private ManejoMiembros manejoMiembros;
    public ArrayList<Detalle> detallesSinAsignar;
    public double precioFinal;
    private ManejoFacturas() {
        facturas = new ArrayList<>();
        detallesSinAsignar=new ArrayList<>();
        manejoMiembros=ManejoMiembros.getInstancia();
        precioFinal=0;
    }

    public static ManejoFacturas getInstancia() {
        if (instancia == null) {
            instancia = new ManejoFacturas();
        }
        return instancia;
    }

    public void agregarFactura(String nombre, LocalDate fechaFacturacion,String cedula, double precioFinal) {
        Factura factura=new Factura(nombre, fechaFacturacion, detallesSinAsignar,cedula,precioFinal);
        facturas.add(factura);
        manejoMiembros.agregarFactura(cedula,nombre);
        for(Detalle detalle: factura.detalles){
            manejoMiembros.agregarOModificarActividadAMiembro(cedula, detalle.codigoActividades, detalle.cantidad);
        }
        detallesSinAsignar.clear();
        this.precioFinal=0;
    }
    public void agregarDetalle(double precio,int cantidad,String codigoActividad){
        Detalle detalle=new Detalle(precio,cantidad,codigoActividad);
        detallesSinAsignar.add(detalle);
        this.precioFinal=this.precioFinal+detalle.precioTotal;
    }
    public Factura buscarFacturaPorNombre(String nombre) {
        for (Factura factura : facturas) {
            if (factura.getNombre().equalsIgnoreCase(nombre)) {
                return factura;
            }
        }
        return null;
    }

    public void imprimirFacturas() {
        System.out.println("Lista de Facturas:");
        for (Factura factura : facturas) {
            System.out.println("Código de la Factura: " + factura.getCodigo());
            System.out.println("Nombre: " + factura.getNombre());
            System.out.println("Fecha de Facturación: " + factura.getFechaFacturacion());
            System.out.println("Cédula del Usuario: " + factura.getCedulaUsuario());
            System.out.println("Precio Final: " + factura.getPrecioFinal());
            System.out.println("Detalles:");
            for (Detalle detalle : factura.getDetalles()) {
                System.out.println("    Código de la Clase: " + detalle.getCodigoActividades());
                System.out.println("    Cantidad: " + detalle.getCantidad());
                System.out.println("    Precio Unitario: " + detalle.getPrecio());
            }
            System.out.println();
        }
    }

}