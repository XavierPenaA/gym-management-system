package Controlador;

import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.registroEquipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControlRegistroEquipo implements ActionListener {
    registroEquipo vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    private ControlRegistroGimnasio controlRegistroGimnasio;
    public ControlRegistroEquipo(ControlRegistroGimnasio controlRegistroGimnasio){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        this.controlRegistroGimnasio=controlRegistroGimnasio;
        vista = new registroEquipo();
        vista.add(vista.principalRegistroEquipo);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnAgregarEquipo.addActionListener(this);
        vista.btnAgregarMaterial.addActionListener(this);
        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Descripcion", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tabla.setModel(modeloTabla);
    }
    public void actualizarTablaMaterial() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Descripcion", "Estado"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoGimnasio().materialesSinAsignar.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoGimnasio().materialesSinAsignar.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoGimnasio().materialesSinAsignar.get(i).getCodigo(),
                        manejoPrincipal.getManejoGimnasio().materialesSinAsignar.get(i).getDescripcion(),
                        manejoPrincipal.getManejoGimnasio().materialesSinAsignar.get(i).getEstado()
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
        vista.monthComboBox.setModel(new DefaultComboBoxModel<>(months));
        vista.monthAdqComboBox.setModel(new DefaultComboBoxModel<>(months));
        vista.monthProxComboBox.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 1; i <= 31; i++) {
            vista.dayComboBox.addItem(i);
            vista.dayAdqComboBox.addItem(i);
            vista.dayProxComboBox.addItem(i);
        }
        for (int i = 2024; i <= 2050; i++) {
            vista.yearComboBox.addItem(i);
            vista.yearAdqComboBox.addItem(i);
            vista.yearProxComboBox.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnAgregarMaterial){
            int day = (int) vista.dayComboBox.getSelectedItem();
            int month = vista.monthComboBox.getSelectedIndex() + 1;
            int year = (int) vista.yearComboBox.getSelectedItem();
            LocalDate date = LocalDate.of(year, month, day);
            if(vista.txtCondigoMaterial.getText()==null){
                JOptionPane.showMessageDialog(null, "Ingrese un código");
            }
            else if(vista.txtDescripcionMaterial.getText()==null){
                JOptionPane.showMessageDialog(null, "Ingrese una descripción");
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtEstadoMaterial.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else{
                manejoPrincipal.getManejoGimnasio().agregarMateriales(vista.txtCondigoMaterial.getText(),
                        vista.txtDescripcionMaterial.getText(),date,vista.txtEstadoMaterial.getText());
                actualizarTablaMaterial();
            }
        }
        if(e.getSource()==vista.btnAgregarEquipo){
            int day = (int) vista.dayAdqComboBox.getSelectedItem();
            int month = vista.monthAdqComboBox.getSelectedIndex() + 1;
            int year = (int) vista.yearAdqComboBox.getSelectedItem();
            LocalDate dateAdq = LocalDate.of(year, month, day);
            int day2 = (int) vista.dayProxComboBox.getSelectedItem();
            int month2 = vista.monthProxComboBox.getSelectedIndex() + 1;
            int year2 = (int) vista.yearProxComboBox.getSelectedItem();
            LocalDate dateProx = LocalDate.of(year2, month2, day2);
            if(manejoPrincipal.getManejoGimnasio().materialesSinAsignar.isEmpty()){
                JOptionPane.showMessageDialog(null,"El equipo no tiene  nmateriales asignados");
            }
            else if (vista.txtCodigoEquipo.getText()==null) {
                JOptionPane.showMessageDialog(null,"Ingrese un código");
            }
            else if(vista.txtDescripcionEquipo.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese una descripción");
            }
            else if(vista.txtEstadoEquipo.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese un estado");
            }
            else if(!manejoPrincipal.getVerificacion().esFechaMayor(dateAdq,dateProx)){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else {
                manejoPrincipal.getManejoGimnasio().agregarEquipos(vista.txtCodigoEquipo.getText(),
                        vista.txtDescripcionEquipo.getText(),vista.txtEstadoEquipo.getText(),dateAdq,dateProx);
                actualizarTablaMaterial();
                vista.dispose();
                controlRegistroGimnasio.actualizarTablaEquipos();
            }
        }
    }
}
