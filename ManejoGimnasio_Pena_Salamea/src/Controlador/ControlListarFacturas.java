package Controlador;

import Modelo.ManejoPrincipal;
import Vista.listarFacturas;

import javax.swing.table.DefaultTableModel;

public class ControlListarFacturas {
    listarFacturas vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    public ControlListarFacturas(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new listarFacturas();
        vista.add(vista.panel1);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        modeloTabla = new DefaultTableModel(new String[]{"Nombres","Fecha de Factura","Cedula del Usuario","Detalles","Codigo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.table1.setModel(modeloTabla);
    }
    public void actualizarTablaFacturas() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Nombre","Codigo","Fecha de Factura","Cedula del Usuario","Total"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoFacturas().facturas.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoFacturas().facturas.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoFacturas().facturas.get(i).getNombre(),
                        manejoPrincipal.getManejoFacturas().facturas.get(i).getCodigo(),
                        manejoPrincipal.getManejoFacturas().facturas.get(i).getFechaFacturacion(),
                        manejoPrincipal.getManejoFacturas().facturas.get(i).getCedulaUsuario(),
                        manejoPrincipal.getManejoFacturas().facturas.get(i).getPrecioFinal()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
}

