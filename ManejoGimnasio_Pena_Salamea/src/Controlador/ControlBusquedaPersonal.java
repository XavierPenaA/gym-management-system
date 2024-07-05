package Controlador;

import Modelo.*;
import Vista.buscarPersonas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBusquedaPersonal implements ActionListener {
    buscarPersonas vista;
    ManejoPrincipal manejoPrincipal;
    ArrayList<Personal> personalEncontrados;
    DefaultTableModel modeloTabla;
    public ControlBusquedaPersonal(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        personalEncontrados = new ArrayList<>();
        vista = new buscarPersonas();
        vista.add(vista.principal);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.comboBoxYear.setVisible(false);
        vista.comboBoxMes.setVisible(false);
        vista.buscarButton.addActionListener(this);
        vista.editarButton.addActionListener(this);
        vista.verFacturasButton.addActionListener(this);
        actualizarComboBox();
        modeloTabla = new DefaultTableModel(new String[]{"Cedula","Nombres", "Apellidos","Direccion",
                "Telefono","Rol","Jornada"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableEcontrados.setModel(modeloTabla);
    }
    public void actualizarTablaMiembros() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Cedula","Nombres", "Apellidos","Direccion","Telefono","Rol","Jornada"};
        modeloTabla.addRow(nombresColumnas);
        if (!personalEncontrados.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoMiembros().miembros.size(); i++) {
                Object[] rowData = {
                        personalEncontrados.get(i).getCedula(),
                        personalEncontrados.get(i).getNombres(),
                        personalEncontrados.get(i).getApellidos(),
                        personalEncontrados.get(i).getDireccion(),
                        personalEncontrados.get(i).getTelefono(),
                        personalEncontrados.get(i).getRol(),
                        personalEncontrados.get(i).getJornada()
                };
                modeloTabla.addRow(rowData);
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
                "Cedula", "Nombre", "Apellido", "Rol","Jornada"
        };
        vista.comboBoxCampoBusqueda.setModel(new DefaultComboBoxModel<>(campos));
    }
    public void actualizarComboBoxMimbros() {
        vista.comboBoxUsuario.removeAllItems();
        for (Personal personal : personalEncontrados) {
            vista.comboBoxUsuario.addItem(personal.getCedula());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.buscarButton){
            if(vista.ingresoBusqueda.getText()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
            }
            else{
                personalEncontrados =manejoPrincipal.getManejoPersonal().buscar(
                        (String) vista.comboBoxCampoBusqueda.getSelectedItem(),vista.ingresoBusqueda.getText());
                actualizarTablaMiembros();
                actualizarComboBoxMimbros();
            }
        }
        if(e.getSource()==vista.editarButton){

        }
    }
}
