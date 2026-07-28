package Controlador;

import Modelo.*;
import Vista.pagarReserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPagarReserva implements ActionListener {
    pagarReserva vista;
    ManejoPrincipal manejoPrincipal;
    Factura facturaSeleccionada;
    DefaultTableModel modeloTablaDetalles;
    DefaultTableModel modeloTabla;
    public ControlPagarReserva(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new pagarReserva();
        vista.add(vista.principal);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.comboBox1.addActionListener(this);
        vista.pagarButton.addActionListener(this);
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Codigo", "Fecha de Factura", "Cedula del Usuario", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableCoincidencias.setModel(modeloTabla);

        modeloTablaDetalles = new DefaultTableModel(new String[]{"Actividad", "Precio", "Cantidad", "Precio Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableDetalles.setModel(modeloTablaDetalles);
        actualizarComboBoxFacturas();
    }

    public void actualizarTablaFacturas() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Nombre", "Codigo", "Fecha de Factura", "Cedula del Usuario", "Total"};
        modeloTabla.addRow(nombresColumnas);
        if (facturaSeleccionada != null) {
            Object[] rowData = {
                    facturaSeleccionada.getNombre(),
                    facturaSeleccionada.getCodigo(),
                    facturaSeleccionada.getFechaFacturacion(),
                    facturaSeleccionada.getCedulaUsuario(),
                    facturaSeleccionada.getPrecioFinal()
            };
            modeloTabla.addRow(rowData);
        }
    }

    public void actualizarTablaDetalles() {
        modeloTablaDetalles.setRowCount(0);
        Object[] nombresColumnas = {"Actividad", "Precio", "Cantidad", "Precio Total"};
        modeloTablaDetalles.addRow(nombresColumnas);
        if (facturaSeleccionada != null && !facturaSeleccionada.detalles.isEmpty()) {
            for (Detalle detalle : facturaSeleccionada.getDetalles()) {
                Object[] rowData = {
                        detalle.getCodigoActividades(),
                        detalle.getPrecio(),
                        detalle.getCantidad(),
                        detalle.getPrecioTotal()
                };
                modeloTablaDetalles.addRow(rowData);
            }
        }
    }
    public void actualizarComboBoxFacturas() {
        vista.comboBox1.removeAllItems();
        for (Factura factura: manejoPrincipal.getManejoFacturas().facturasReservadas) {
            vista.comboBox1.addItem(factura.getCodigo());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.comboBox1){
            facturaSeleccionada=manejoPrincipal.getManejoFacturas().buscarFacturaReservaPorCodigo((Integer)vista.comboBox1.getSelectedItem());
            actualizarComboBoxFacturas();
            actualizarTablaFacturas();
            actualizarTablaDetalles();
        }
        if(e.getSource()==vista.pagarButton){
            facturaSeleccionada=manejoPrincipal.getManejoFacturas().buscarFacturaReservaPorCodigo((Integer)vista.comboBox1.getSelectedItem());
            manejoPrincipal.getManejoFacturas().pagarReserva(facturaSeleccionada);
            JOptionPane.showMessageDialog(null,"Factura pagada correctamente");
        }
        if(e.getSource()==vista.cancelarButton){
            facturaSeleccionada=manejoPrincipal.getManejoFacturas().buscarFacturaReservaPorCodigo((Integer)vista.comboBox1.getSelectedItem());
            manejoPrincipal.getManejoFacturas().facturasReservadas.remove(facturaSeleccionada);
            actualizarComboBoxFacturas();
            actualizarTablaFacturas();
            actualizarTablaDetalles();
        }
    }
}
