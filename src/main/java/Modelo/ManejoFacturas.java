package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManejoFacturas implements Serializable {
    private static ManejoFacturas instancia;
    public ArrayList<Factura> facturas;
    public ArrayList<Factura> facturasReservadas;
    private ManejoMiembros manejoMiembros;
    public ArrayList<Detalle> detallesSinAsignar;
    public double precioFinal;
    private WhatsAppManager whatsappManager;
    private ManejoFacturas() {
        facturas = new ArrayList<>();
        facturasReservadas=new ArrayList<>();
        detallesSinAsignar=new ArrayList<>();
        manejoMiembros=ManejoMiembros.getInstancia();
        precioFinal=0;
        whatsappManager = new WhatsAppManager();
    }

    public static ManejoFacturas getInstancia() {
        if (instancia == null) {
            instancia = new ManejoFacturas();
        }
        return instancia;
    }
    public void pagarReserva(Factura facturaAPagar){
        facturas.add(facturaAPagar);
        manejoMiembros.agregarFactura(facturaAPagar.cedulaUsuario, facturaAPagar.nombre);
        for (Detalle detalle : facturaAPagar.detalles) {
            manejoMiembros.agregarOModificarActividadAMiembro(facturaAPagar.cedulaUsuario, detalle.getCodigoActividades(), detalle.getCantidad());
        }
        guardarFacturaEnArchivo(facturaAPagar);
        Miembro miembroActual=manejoMiembros.buscarMiembro(facturaAPagar.cedulaUsuario);
        enviarMensajeWhatsApp(facturaAPagar.cedulaUsuario, facturaAPagar,"Hola " + miembroActual.getNombres() + ", su factura electrónica está adjunta.");
    }
    public void reservarFactura(String nombre, LocalDate fechaFacturacion, String cedula, double precioFinal){
        Factura factura = new Factura(nombre, fechaFacturacion, detallesSinAsignar, cedula, precioFinal);
        facturasReservadas.add(factura);
        this.precioFinal = 0;
        Miembro miembroActual=manejoMiembros.buscarMiembro(cedula);
        enviarMensajeWhatsApp(cedula, factura,"Hola " + miembroActual.getNombres() + ", su reserva está adjunta. No olvide pagarla al llegar a recepción");

    }
    public void agregarFactura(String nombre, LocalDate fechaFacturacion, String cedula, double precioFinal) {
        Factura factura = new Factura(nombre, fechaFacturacion, detallesSinAsignar, cedula, precioFinal);
        facturas.add(factura);
        manejoMiembros.agregarFactura(cedula, nombre);
        for (Detalle detalle : factura.detalles) {
                manejoMiembros.agregarOModificarActividadAMiembro(cedula, detalle.getCodigoActividades(), detalle.getCantidad());
        }

        detallesSinAsignar.clear();
        this.precioFinal = 0;
        guardarFacturaEnArchivo(factura);
        Miembro miembroActual=manejoMiembros.buscarMiembro(cedula);
        enviarMensajeWhatsApp(cedula, factura,"Hola " + miembroActual.getNombres() + ", su factura electrónica está adjunta.");
    }
    public void agregarDetalle(double precio,int cantidad,String codigoActividad){
        Detalle detalle=new Detalle(precio,cantidad,codigoActividad);
        detallesSinAsignar.add(detalle);
        this.precioFinal=this.precioFinal+detalle.precioTotal;
    }
    public Factura buscarFacturaPorCodigo(int codigo) {
        for (Factura factura : facturas) {
            if (factura.getCodigo()==codigo) {
                return factura;
            }
        }
        return null;
    }
    public Factura buscarFacturaReservaPorCodigo(int codigo) {
        for (Factura factura : facturasReservadas) {
            if (factura.getCodigo()==codigo) {
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
    public String verificarDisponibilidadActividades() {
        ManejoActividad manejoActividad = ManejoActividad.getInstancia();
        StringBuilder actividadesSinCupo = new StringBuilder();

        for (Detalle detalle : detallesSinAsignar) {
            System.out.println("Verificando disponibilidad para la actividad con código: " + detalle.getCodigoActividades());

            if (detalle.getCodigoActividades().equals("Agregar Meses")) {
                System.out.println("En meses no hay cupos");
            } else {
                Actividad actividad = manejoActividad.buscarActividad(detalle.getCodigoActividades());
                if (actividad == null) {
                    System.out.println("Actividad no encontrada para el código: " + detalle.getCodigoActividades());
                } else {
                    System.out.println("Disponibilidad actual para " + detalle.getCodigoActividades() + ": " + actividad.getDisponible());
                    if (actividad.getDisponible() <= 0) {
                        if (!actividadesSinCupo.isEmpty()) {
                            actividadesSinCupo.append("\n ");
                        }
                        actividadesSinCupo.append(detalle.getCodigoActividades());
                    }
                    else{
                        actividad.setDisponible(actividad.getDisponible()-1);
                    }
                }
            }
        }

        if (actividadesSinCupo.isEmpty()) {
            return "correcto";
        } else {
            return "Las siguientes actividades no tienen cupo: " + actividadesSinCupo.toString();
        }
    }
    private void guardarFacturaEnArchivo(Factura factura) {
        File directorio = new File("facturas");
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        String nombreArchivo = "facturas/" + factura.getCodigo() + ".txt";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("Código de la Factura: " + factura.getCodigo() + "\n");
            writer.write("Nombre: " + factura.getNombre() + "\n");
            writer.write("Fecha de Facturación: " + factura.getFechaFacturacion() + "\n");
            writer.write("Cédula del Usuario: " + factura.getCedulaUsuario() + "\n");
            writer.write("Precio Final: " + factura.getPrecioFinal() + "\n");
            writer.write("Detalles:\n");
            for (Detalle detalle : factura.getDetalles()) {
                writer.write("    Código de la Clase: " + detalle.getCodigoActividades() + "\n");
                writer.write("    Cantidad: " + detalle.getCantidad() + "\n");
                writer.write("    Precio Unitario: " + detalle.getPrecio() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void enviarMensajeWhatsApp(String cedula, Factura factura,String mensaje) {
        Miembro miembroActual=manejoMiembros.buscarMiembro(cedula);
        String nombreArchivo = "facturas/" + factura.getCodigo() + ".txt";
        whatsappManager.enviarMensajeConContenido("+593" + miembroActual.getTelefono().substring(1), mensaje, nombreArchivo);
    }
    public ArrayList<Factura> buscar(String campo, String valor) {
        ArrayList<Factura> resultados = new ArrayList<>();
        for (Factura factura : facturas) {
            switch (campo.toLowerCase()) {
                case "nombre":
                    if (factura.getNombre().equalsIgnoreCase(valor)) {
                        resultados.add(factura);
                    }
                    break;
                case "codigo":
                    if (factura.getCodigo()==Integer.parseInt(valor)) {
                        resultados.add(factura);
                    }
                    break;
                case "cedula del usuario":
                    if (factura.getCedulaUsuario().equalsIgnoreCase(valor)) {
                        resultados.add(factura);
                    }
                    break;
                case "total":
                    try {
                        double total = Double.parseDouble(valor);
                        if (factura.getPrecioFinal() == total) {
                            resultados.add(factura);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("El valor para el total no es válido.");
                    }
                    break;
                default:
                    System.out.println("Campo de búsqueda no válido.");
                    break;
            }
        }
        return resultados;
    }
    public ArrayList<Factura> buscarPorFecha(String criterio, String mes, int anio) {
        ArrayList<Factura> resultados = new ArrayList<>();
        int mesInt = obtenerNumeroMes(mes);

        for (Factura factura : facturas) {
            LocalDate fecha = null;
            fecha=factura.getFechaFacturacion();
            if (fecha != null && fecha.getYear() == anio && fecha.getMonthValue() == mesInt) {
                resultados.add(factura);
            }
        }
        return resultados;
    }
    private int obtenerNumeroMes(String mes) {
        switch (mes.toLowerCase()) {
            case "enero":
                return 1;
            case "febrero":
                return 2;
            case "marzo":
                return 3;
            case "abril":
                return 4;
            case "mayo":
                return 5;
            case "junio":
                return 6;
            case "julio":
                return 7;
            case "agosto":
                return 8;
            case "septiembre":
                return 9;
            case "octubre":
                return 10;
            case "noviembre":
                return 11;
            case "diciembre":
                return 12;
            default:
                throw new IllegalArgumentException("Mes inválido: " + mes);
        }
    }
}