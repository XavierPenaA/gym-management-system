package Controlador;

import Modelo.*;
import Vista.buscarFacturas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBuscarFactura implements ActionListener {
    buscarFacturas vista;
    ManejoPrincipal manejoPrincipal;
    ArrayList<Factura> facturasEncontradas;
    Factura facturaSeleccionada;
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTablaDetalles;
    public ControlBuscarFactura(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        facturasEncontradas = new ArrayList<>();
        vista = new buscarFacturas();
        vista.add(vista.principal);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.comboBoxYear.setVisible(false);
        vista.comboBoxMes.setVisible(false);
        vista.buscarButton.addActionListener(this);
        vista.comboBoxCampoBusqueda.addActionListener(this);
        vista.comboBox1.addActionListener(this);
        actualizarComboBox();
        modeloTabla = new DefaultTableModel(new String[]{"Nombres","Fecha de Factura","Cedula del Usuario","Detalles","Codigo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableEcontrados.setModel(modeloTabla);
        modeloTablaDetalles = new DefaultTableModel(new String[]{"Actividad","Precio","Cantidad","Precio Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableDetalles.setModel(modeloTablaDetalles);
    }
    public void actualizarTablaFacturas() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Nombre","Codigo","Fecha de Factura","Cedula del Usuario","Total"};
        modeloTabla.addRow(nombresColumnas);
        if (!facturasEncontradas.isEmpty()) {
            for (int i = 0; i < facturasEncontradas.size(); i++) {
                Object[] rowData = {
                        facturasEncontradas.get(i).getNombre(),
                        facturasEncontradas.get(i).getCodigo(),
                        facturasEncontradas.get(i).getFechaFacturacion(),
                        facturasEncontradas.get(i).getCedulaUsuario(),
                        facturasEncontradas.get(i).getPrecioFinal()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
    public void actualizarTablaDetalles() {
        if(vista.comboBox1.getSelectedItem()!=null){
            facturaSeleccionada=manejoPrincipal.getManejoFacturas().buscarFacturaPorCodigo((Integer) vista.comboBox1.getSelectedItem());
            modeloTablaDetalles.setRowCount(0);
            Object[] nombresColumnas = {"Actividad","Precio","Cantidad","Precio Total"};
            modeloTablaDetalles.addRow(nombresColumnas);
            if (!facturaSeleccionada.detalles.isEmpty()) {
                for (int i = 0; i < facturaSeleccionada.detalles.size(); i++) {
                    Object[] rowData = {
                            facturaSeleccionada.detalles.get(i).getCodigoActividades(),
                            facturaSeleccionada.detalles.get(i).getPrecio(),
                            facturaSeleccionada.detalles.get(i).getCantidad(),
                            facturaSeleccionada.detalles.get(i).getPrecioTotal()
                    };
                    modeloTablaDetalles.addRow(rowData);
                }
            }
        }
    }
    public void actualizarComboBox(){
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        vista.comboBoxMes.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 2024; i <= 2050; i++) {
            vista.comboBoxYear.addItem(i);
        }String[] campos = {
                "Nombre","Codigo","Fecha de Factura","Cedula del Usuario","Total"
        };
        vista.comboBoxCampoBusqueda.setModel(new DefaultComboBoxModel<>(campos));
    }
    public void actualizarComboBoxFacturas() {
        vista.comboBox1.removeAllItems();
        for (Factura factura: facturasEncontradas) {
            vista.comboBox1.addItem(factura.getCodigo());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.comboBox1){
            actualizarTablaDetalles();
        }
        if(e.getSource()==vista.comboBoxCampoBusqueda) {
            if(vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Factura" ){
                vista.comboBoxYear.setVisible(true);
                vista.comboBoxMes.setVisible(true);
                vista.ingresoBusqueda.setVisible(false);
            }
            else{
                vista.comboBoxYear.setVisible(false);
                vista.comboBoxMes.setVisible(false);
                vista.ingresoBusqueda.setVisible(true);
            }
        }
        if(e.getSource()==vista.buscarButton){
            if(vista.ingresoBusqueda.getText()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
            }
            else{
                if(vista.ingresoBusqueda.getText()==null){
                    JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
                }else if(vista.comboBoxCampoBusqueda.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar un campo de busqueda");
                }
                else{
                    if(vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Factura"){
                        if(vista.comboBoxMes.getSelectedItem() == null){
                            JOptionPane.showMessageDialog(null,"Debe seleccionar un mes");
                        } else if (vista.comboBoxYear.getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(null,"Debe seleccionar un año");
                        }
                        else {
                            facturasEncontradas=manejoPrincipal.getManejoFacturas().buscarPorFecha((String) vista.comboBoxCampoBusqueda.getSelectedItem(), (String) vista.comboBoxMes.getSelectedItem(), (Integer) vista.comboBoxYear.getSelectedItem());
                            actualizarTablaFacturas();
                            actualizarComboBoxFacturas();
                            actualizarTablaDetalles();
                        }
                    }
                    else{
                        if(vista.comboBoxCampoBusqueda.getSelectedItem()==null){
                            JOptionPane.showMessageDialog(null,"Debe seleccionar un campo de busqueda");
                        }
                        else {
                            facturasEncontradas =manejoPrincipal.getManejoFacturas().buscar(
                                    (String) vista.comboBoxCampoBusqueda.getSelectedItem(),vista.ingresoBusqueda.getText());
                            actualizarTablaFacturas();
                            actualizarComboBoxFacturas();
                            actualizarTablaDetalles();
                        }
                    }

                }

            }
        }
    }
}
