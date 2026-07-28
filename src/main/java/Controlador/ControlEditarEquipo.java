package Controlador;

import Modelo.*;
import Vista.editarEquipos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControlEditarEquipo implements ActionListener {
    editarEquipos vista;
    ManejoPrincipal manejoPrincipal;
    int indexEditar;
    int indexGim;
    int indexUbi;
    public ControlEditarEquipo(Gimnasio gim, Ubicacion ubi, Equipos equipoAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexGim = manejoPrincipal.getManejoGimnasio().gimnasios.indexOf(gim);
        indexUbi=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.indexOf(ubi);
        indexEditar=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.indexOf(equipoAEditar);
        vista = new editarEquipos();
        vista.add(vista.principalEditarEquipo);
        //vista.setSize(800, 400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.cambiarDescripcionButton.addActionListener(this);
        vista.cambiarEstadoButton.addActionListener(this);
        vista.cambiarFechaProximaButton.addActionListener(this);
        actualizarComboBox();
    }
    public void actualizarComboBox(){
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        vista.monthProxComboBox.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 1; i <= 31; i++) {
            vista.dayProxComboBox.addItem(i);
        }
        for (int i = 2024; i <= 2050; i++) {
            vista.yearProxComboBox.addItem(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cambiarEstadoButton) {
            if (vista.txtEstadoEquipo.getText()==null) {
                JOptionPane.showMessageDialog(null, "Escriba un estado");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEditar).setEstado(vista.txtEstadoEquipo.getText());
                JOptionPane.showMessageDialog(null, "Estado Cambiado Correctamente");
            }
        }
        if (e.getSource() == vista.cambiarDescripcionButton) {
            if (vista.txtDescripcionEquipo.getText() == null) {
                JOptionPane.showMessageDialog(null, "Ingrese un Nombre");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEditar).setDescripcion(vista.txtDescripcionEquipo.getText());
                JOptionPane.showMessageDialog(null, "Descripcion Cambiada Correctamente");
            }
        }
        if(e.getSource()==vista.cambiarFechaProximaButton){
            int day2 = (int) vista.dayProxComboBox.getSelectedItem();
            int month2 = vista.monthProxComboBox.getSelectedIndex() + 1;
            int year2 = (int) vista.yearProxComboBox.getSelectedItem();
            LocalDate dateProx = LocalDate.of(year2, month2, day2);
            manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexUbi).equipos.get(indexEditar).setFechaProxima(dateProx);
            JOptionPane.showMessageDialog(null, "Fecha Proxima Cambiada Correctamente");

        }
    }
}