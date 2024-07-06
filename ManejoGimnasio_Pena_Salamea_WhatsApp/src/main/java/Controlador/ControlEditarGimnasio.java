package Controlador;

import Modelo.Gimnasio;
import Modelo.Horario;
import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.editarGimnasios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Objects;

public class ControlEditarGimnasio implements ActionListener {
    editarGimnasios vista;
    ManejoPrincipal manejoPrincipal;
    int indexEditar;

    public ControlEditarGimnasio(Gimnasio gimnasioAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexEditar = manejoPrincipal.getManejoGimnasio().gimnasios.indexOf(gimnasioAEditar);
        vista = new editarGimnasios();
        vista.add(vista.principalEditarGimnasio);
        vista.setSize(800, 400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.cambiarNombreButton.addActionListener(this);
        vista.cambiarHorarioButton.addActionListener(this);
        vista.cambiarTelefonoButton.addActionListener(this);
        vista.cambiarDireccionButton.addActionListener(this);
        vista.cambiarTelefonoButton.addActionListener(this);
        actualizarComboBox();
    }
    public void actualizarComboBox(){
        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }
        vista.comboBoxHoraInicio.setModel(new DefaultComboBoxModel<>(hours));
        vista.comboBoxHoraFin.setModel(new DefaultComboBoxModel<>(hours));
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        vista.comboBoxMinutoInicio.setModel(new DefaultComboBoxModel<>(minutes));
        vista.comboBoxMinutoFin.setModel(new DefaultComboBoxModel<>(minutes));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cambiarNombreButton) {
            if (!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombreGim.getText())) {
                JOptionPane.showMessageDialog(null, "El nombre contiene caracteres no permitidos");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexEditar).setNombre(vista.txtNombreGim.getText());
                JOptionPane.showMessageDialog(null, "Nombres Cambiados Correctamente");
            }
        }
        if (e.getSource() == vista.cambiarDireccionButton) {
            if (vista.txtDireccionGim.getText() == null) {
                JOptionPane.showMessageDialog(null, "Ingrese una dirección");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexEditar).setDireccion(vista.txtDireccionGim.getText());
                JOptionPane.showMessageDialog(null, "Dirección Correctamente");
            }
        }
        if (e.getSource() == vista.cambiarTelefonoButton) {
            if (!manejoPrincipal.getVerificacion().validarTelefonoEcuador(vista.txtTelefonoGim.getText())) {
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexEditar).setTelefono(vista.txtTelefonoGim.getText());
                JOptionPane.showMessageDialog(null, "Telefono Cambiado Correctamente");
            }
        }
        if(e.getSource()==vista.cambiarHorarioButton){
            Horario horario;
            LocalTime horaInicio = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraInicio.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoInicio.getSelectedItem()))
            );
            LocalTime horaFin = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraFin.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoFin.getSelectedItem()))
            );
            horario=new Horario(horaInicio,horaFin);
            manejoPrincipal.getManejoGimnasio().gimnasios.get(indexEditar).setHorario(horario);
            JOptionPane.showMessageDialog(null,"Horario Cambiado Correctamente");

        }
    }
}