package Controlador;

import Modelo.*;
import Vista.editarMateriales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControlEditarMaterial implements ActionListener {
    editarMateriales vista;
    ManejoPrincipal manejoPrincipal;
    int indexEditar;
    int indexEquipo;
    int indexGim;
    int indexUbi;
    public ControlEditarMaterial(Gimnasio gim, Ubicacion ubi, Equipos equipo, Material materialAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexGim = manejoPrincipal.getManejoGimnasio().gimnasios.indexOf(gim);
        indexUbi=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.indexOf(ubi);
        indexEquipo=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.indexOf(equipo);
        indexEditar=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEquipo).materiales.indexOf(materialAEditar);
        vista = new editarMateriales();
        vista.add(vista.principalEditarMateriales);
        //vista.setSize(800, 400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.cambiarDescripcionButton.addActionListener(this);
        vista.cambiarEstadoButton.addActionListener(this);
        vista.cambiarFechaButton.addActionListener(this);
        actualizarComboBox();
    }
    public void actualizarComboBox(){
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        vista.monthComboBox.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 1; i <= 31; i++) {
            vista.dayComboBox.addItem(i);
        }
        for (int i = 2024; i <= 2050; i++) {
            vista.yearComboBox.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cambiarEstadoButton) {
            if (vista.txtEstadoMaterial.getText()==null) {
                JOptionPane.showMessageDialog(null, "Escriba un estado");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEquipo).materiales.get(indexEditar).setEstado(vista.txtEstadoMaterial.getText());
                JOptionPane.showMessageDialog(null, "Estado Cambiado Correctamente");
            }
        }
        if (e.getSource() == vista.cambiarDescripcionButton) {
            if (vista.txtDescripcionMaterial.getText() == null) {
                JOptionPane.showMessageDialog(null, "Ingrese un Nombre");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEquipo).materiales.get(indexEditar).setEstado(vista.txtDescripcionMaterial.getText());
                JOptionPane.showMessageDialog(null, "Descripcion Cambiada Correctamente");
            }
        }
        if(e.getSource()==vista.cambiarFechaButton){
            int day2 = (int) vista.dayComboBox.getSelectedItem();
            int month2 = vista.monthComboBox.getSelectedIndex() + 1;
            int year2 = (int) vista.yearComboBox.getSelectedItem();
            LocalDate dateProx = LocalDate.of(year2, month2, day2);
            manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEquipo).materiales.get(indexEditar).setFechaMantenimiento(dateProx);
            JOptionPane.showMessageDialog(null, "Fecha Mantenimiento Cambiada Correctamente");

        }
    }
}