package Controlador;

import Modelo.Actividad;
import Modelo.ManejoPrincipal;
import Modelo.Miembro;
import Vista.factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControlFactura implements ActionListener {
    factura vista;
    ManejoPrincipal manejoPrincipal;
    Miembro facturado;
    DefaultTableModel modeloTabla;
    public ControlFactura(){
        vista=new factura();
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista.add(vista.principalFactura);
        vista.setSize(800,800);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.buscarUsuarioButton.addActionListener(this);
        vista.agregarDetalleButton.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.comboBoxActividades.addActionListener(this);
        if(manejoPrincipal.getManejoFacturas().facturas.isEmpty()){
            vista.txtCodigo.setText("1");
        }
        else{
            vista.txtCodigo.setText(String.valueOf(manejoPrincipal.getManejoFacturas().facturas.getLast().getCodigo()+1));
        }
        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Cantidad de Meses", "Precio Unitario", "Precio Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tabla.setModel(modeloTabla);
    }
    public void actualizarDatos(){
        if(manejoPrincipal.getManejoFacturas().facturas.isEmpty()){
            vista.txtCodigo.setText("1");
        }
        else{
            vista.txtCodigo.setText(String.valueOf(manejoPrincipal.getManejoFacturas().facturas.getLast().getCodigo()+1));
        }
        if(facturado!=null){
            vista.pnlInformacionUsuario.setVisible(true);
            vista.txtNombresUsuario.setText(facturado.getNombres());
            vista.txtApellidosUsuario.setText(facturado.getApellidos());
            vista.txtCedulaUsuario.setText(facturado.getCedula());
            vista.txtDireccionUsuario.setText(facturado.getDireccion());
            vista.txtTelefonoUsuario.setText(facturado.getTelefono());
        }
        else{
            vista.pnlInformacionUsuario.setVisible(false);
        }
        vista.ponerPrecioFinal.setText(String.valueOf(manejoPrincipal.getManejoFacturas().precioFinal));
    }
    public void actualizarComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Actividad actividad : manejoPrincipal.getManejoActividad().actividades) {
            model.addElement(actividad.getNombre());
        }
        vista.comboBoxActividades.setModel(model);
    }
    public void actualizarTablaDetalles() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Cantidad de Meses", "Precio Unitario", "Precio Total"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoFacturas().detallesSinAsignar.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoFacturas().detallesSinAsignar.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoFacturas().detallesSinAsignar.get(i).getCodigoActividades(),
                        manejoPrincipal.getManejoFacturas().detallesSinAsignar.get(i).getCantidad(),
                        manejoPrincipal.getManejoFacturas().detallesSinAsignar.get(i).getPrecio(),
                        manejoPrincipal.getManejoFacturas().detallesSinAsignar.get(i).getPrecioTotal()
                };
                modeloTabla.addRow(rowData);
            }
        }
        vista.ponerPrecioFinal.setText(String.valueOf(manejoPrincipal.getManejoFacturas().precioFinal));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.comboBoxActividades){
            Actividad actividadFacturando=manejoPrincipal.getManejoActividad().buscarActividad((String) vista.comboBoxActividades.getSelectedItem());
            vista.txtPrecio.setText(String.valueOf(actividadFacturando.getPrecio()));
        }
        if(e.getSource()==vista.buscarUsuarioButton){
            facturado=manejoPrincipal.getManejoMiembros().buscarMiembro(vista.txtCedula.getText());
            if(facturado!=null){
                JOptionPane.showMessageDialog(null, "Usuario Encontrado Correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario No Encontrado");
            }
            actualizarDatos();
        }
        if(e.getSource()==vista.agregarDetalleButton){
            manejoPrincipal.getManejoFacturas().agregarDetalle(Double.parseDouble(vista.txtPrecio.getText()),Integer.parseInt(vista.txtCantidad.getText()), (String) vista.comboBoxActividades.getSelectedItem());

            actualizarTablaDetalles();
        }
        if(e.getSource()==vista.btnGuardar){
            if(facturado!=null){
                JOptionPane.showMessageDialog(null, "Usuario No Seleccionado");
            }
            else if (Double.parseDouble(vista.ponerPrecioFinal.getText())==0) {
                JOptionPane.showMessageDialog(null, "Detalle No Agregado");
            }
            if(!manejoPrincipal.getManejoFacturas().verificarDisponibilidadActividades().equals("correcto")){
                JOptionPane.showMessageDialog(null,manejoPrincipal.getManejoFacturas().verificarDisponibilidadActividades());
            }
            else{
                manejoPrincipal.getManejoFacturas().reducirDisponibilidadActividades();
                manejoPrincipal.getManejoFacturas().agregarFactura(vista.txtNombre.getText(), LocalDate.now(),vista.txtCedulaUsuario.getText(), Double.parseDouble(vista.ponerPrecioFinal.getText()));
                JOptionPane.showMessageDialog(null, "Factura creada correctamente");
                manejoPrincipal.getManejoFacturas().imprimirFacturas();
                manejoPrincipal.getManejoMiembros().imprimirMiembros();
                actualizarDatos();
                actualizarTablaDetalles();
            }

            }
        }
    }
}
