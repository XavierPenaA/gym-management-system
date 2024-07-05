package Controlador;

import Modelo.*;
import Vista.buscarPersonas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBusquedaMiembros implements ActionListener {
    buscarPersonas vista;
    ManejoPrincipal manejoPrincipal;
    ArrayList<Miembro> miembrosEncontrados;
    DefaultTableModel modeloTabla;
    public ControlBusquedaMiembros(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        miembrosEncontrados= new ArrayList<>();
        vista = new buscarPersonas();
        vista.add(vista.principal);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.comboBoxYear.setVisible(false);
        vista.comboBoxMes.setVisible(false);
        vista.setVisible(true);
        vista.buscarButton.addActionListener(this);
        vista.editarButton.addActionListener(this);
        vista.verFacturasButton.addActionListener(this);
        vista.comboBoxCampoBusqueda.addActionListener(this);
        actualizarComboBox();
        modeloTabla = new DefaultTableModel(new String[]{"Cedula","Nombres", "Apellidos","Direccion","Telefono","Fecha de Inicio","Fecha de Fin de Membresia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableEcontrados.setModel(modeloTabla);
    }
    public void actualizarTablaMiembros() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Cedula","Nombres", "Apellidos","Direccion","Telefono","Fecha de Inicio","Fecha de Fin de Membresia"};
        modeloTabla.addRow(nombresColumnas);
        if (!miembrosEncontrados.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoMiembros().miembros.size(); i++) {
                Object[] rowData = {
                        miembrosEncontrados.get(i).getCedula(),
                        miembrosEncontrados.get(i).getNombres(),
                        miembrosEncontrados.get(i).getApellidos(),
                        miembrosEncontrados.get(i).getDireccion(),
                        miembrosEncontrados.get(i).getTelefono(),
                        miembrosEncontrados.get(i).getFechaInicio(),
                        miembrosEncontrados.get(i).getFechaFin()
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
                "Cedula", "Nombre", "Apellido", "Fecha de Registro", "Fecha de Fin de Membresía"
        };
        vista.comboBoxCampoBusqueda.setModel(new DefaultComboBoxModel<>(campos));
    }
    public void actualizarComboBoxMimbros() {
        vista.comboBoxUsuario.removeAllItems();
        for (Miembro miembros : miembrosEncontrados) {
            vista.comboBoxUsuario.addItem(miembros.getCedula());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.comboBoxCampoBusqueda) {
            if(vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Registro" || vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Fin de Membresía"){
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
                if(vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Registro" || vista.comboBoxCampoBusqueda.getSelectedItem()=="Fecha de Fin de Membresía"){
                    miembrosEncontrados=manejoPrincipal.getManejoMiembros().buscarPorFecha((String) vista.comboBoxCampoBusqueda.getSelectedItem(), (String) vista.comboBoxMes.getSelectedItem(), (Integer) vista.comboBoxYear.getSelectedItem());
                    actualizarTablaMiembros();
                    actualizarComboBoxMimbros();
                }
                else{
                    miembrosEncontrados=manejoPrincipal.getManejoMiembros().buscar((String) vista.comboBoxCampoBusqueda.getSelectedItem(),vista.ingresoBusqueda.getText());
                    actualizarTablaMiembros();
                    actualizarComboBoxMimbros();
                }

            }
        }
        if(e.getSource()==vista.editarButton){

        }
    }
}
