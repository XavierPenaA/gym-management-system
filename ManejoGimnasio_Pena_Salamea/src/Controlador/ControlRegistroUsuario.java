package Controlador;

import Modelo.ManejoPrincipal;
import Vista.registroUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControlRegistroUsuario implements ActionListener {
    registroUsuario vista;
    ManejoPrincipal manejoPrincipal;
    public ControlRegistroUsuario() {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registroUsuario();
        vista.add(vista.principalRegistroUsuario);
        vista.setSize(400,400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarse.addActionListener(this);
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
        if(e.getSource()==vista.btnRegistrarse){
            int day = (int) vista.dayComboBox.getSelectedItem();
            int month = vista.monthComboBox.getSelectedIndex() + 1; // Los meses en LocalDate son 1-based
            int year = (int) vista.yearComboBox.getSelectedItem();
            LocalDate date = LocalDate.of(year, month, day);
            manejoPrincipal.getManejoMiembros().registrarMiembro(vista.txtCedula.getText(),vista.txtNombres.getText(),vista.txtApellidos.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(), LocalDate.now(),date);
            JOptionPane.showMessageDialog(null, "Miembro Registrado Correctamente");
            vista.dispose();
            manejoPrincipal.getManejoMiembros().imprimirMiembros();
        }
    }
}
