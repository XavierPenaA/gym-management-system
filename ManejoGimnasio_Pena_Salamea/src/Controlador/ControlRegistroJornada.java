package Controlador;

import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.registroJornada;
import Vista.registroUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Objects;

public class ControlRegistroJornada implements ActionListener {
    registroJornada vista;
    ManejoPrincipal manejoPrincipal;

    public ControlRegistroJornada() {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registroJornada();
        vista.add(vista.principalRegistroJornada);
        vista.setSize(800,200);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnAgregarJornada.addActionListener(this);
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
        if(e.getSource()==vista.btnAgregarJornada){
            LocalTime horaInicio = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraInicio.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoInicio.getSelectedItem()))
            );
            LocalTime horaFin = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraFin.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoFin.getSelectedItem()))
            );
            if(!manejoPrincipal.getVerificacion().esHoraMayor(horaInicio,horaFin)){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombre.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else{
                manejoPrincipal.getManejoJornada().agregarJornada(vista.txtNombre.getText(),horaInicio,horaFin);
                JOptionPane.showMessageDialog(vista, "Jornada agregada");
            }
        }
    }
}
