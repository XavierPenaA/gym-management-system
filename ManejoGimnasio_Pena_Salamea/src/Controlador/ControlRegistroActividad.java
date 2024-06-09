package Controlador;

import Modelo.*;
import Vista.registrarActividad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ControlRegistroActividad implements ActionListener {
    registrarActividad vista;
    ManejoPrincipal manejoPrincipal;

    public ControlRegistroActividad() {
        vista = new registrarActividad();
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista.add(vista.principalRegistrarClase);
        vista.setSize(800,800);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarClase.addActionListener(this);
        vista.actualizarUbicacionesButton.addActionListener(this);
    }
    public void actualizarComboBox(){
        String[] dias = {
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
        };
        vista.comboBoxDiasSemana.setModel(new DefaultComboBoxModel<>(dias));
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
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        vista.cmbMesFechaFin.setModel(new DefaultComboBoxModel<>(months));
        vista.cmbMesFechaInicio.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 1; i <= 31; i++) {
            vista.cmbDiaFechaInicio.addItem(i);
            vista.cmbDiaFechaFin.addItem(i);
        }
        for (int i = 2024; i <= 2050; i++) {
            vista.cmbYearFechaInicio.addItem(i);
            vista.cmbYearFechaFinal.addItem(i);
        }
        vista.comboBoxGimnasios.removeAllItems();
        for (Gimnasio gimnasio : manejoPrincipal.getManejoGimnasio().gimnasios) {
            vista.comboBoxGimnasios.addItem(gimnasio.getNombre());
        }
        actualizarComboBoxUbicaciones();
        vista.cmbInstructor.removeAllItems();
        for (Personal personal : manejoPrincipal.getManejoPersonal().personal) {
            vista.cmbInstructor.addItem(personal.getNombres() + " " + personal.getApellidos());
        }
        String[] estados = {
                "Iniciada", "Aun no Inicia"
        };
        vista.cbmEstado.setModel(new DefaultComboBoxModel<>(estados));
    }
    public void actualizarComboBoxUbicaciones() {
        vista.comboBoxUbicaciones.removeAllItems();
        String gimnasioSeleccionado = (String) vista.comboBoxGimnasios.getSelectedItem();
        if (gimnasioSeleccionado != null) {
            Gimnasio gimnasio = manejoPrincipal.getManejoGimnasio().buscarGimnasio(gimnasioSeleccionado);
            if (gimnasio != null) {
                for (Ubicacion ubicacion : gimnasio.ubicaciones) {
                    vista.comboBoxUbicaciones.addItem(ubicacion.getNombre());
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnRegistrarClase){
            int day = (int) vista.cmbDiaFechaInicio.getSelectedItem();
            int month = vista.cmbMesFechaInicio.getSelectedIndex() + 1; // Los meses en LocalDate son 1-based
            int year = (int) vista.cmbYearFechaInicio.getSelectedItem();
            LocalDate fechaInicio = LocalDate.of(year, month, day);
            int day2 = (int) vista.cmbDiaFechaFin.getSelectedItem();
            int month2 = vista.cmbMesFechaFin.getSelectedIndex() + 1; // Los meses en LocalDate son 1-based
            int year2 = (int) vista.cmbYearFechaFinal.getSelectedItem();
            LocalDate fechaFin = LocalDate.of(year2, month2, day2);
            LocalTime horaInicio = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraInicio.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoInicio.getSelectedItem()))
            );
            LocalTime horaFin = LocalTime.of(
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxHoraFin.getSelectedItem())),
                    Integer.parseInt((String) Objects.requireNonNull(vista.comboBoxMinutoFin.getSelectedItem()))
            );
            Horario horario=new Horario(fechaInicio,fechaFin,horaInicio,horaFin);
            Ubicacion ubicacion=manejoPrincipal.getManejoGimnasio().buscarUbicacion((String) vista.comboBoxGimnasios.getSelectedItem(), (String) vista.comboBoxUbicaciones.getSelectedItem());
            Personal instructor=manejoPrincipal.getManejoPersonal().buscarPersonalPorNombre((String) vista.cmbInstructor.getSelectedItem());
            manejoPrincipal.getManejoActividad().agregarActividad(vista.txtCodigo.getText(),vista.txtNombreGim.getText(),vista.txtDescripcion.getText(),Integer.parseInt(vista.txtCupo.getText()),Integer.parseInt(vista.txtCupo.getText()), (String) vista.cbmEstado.getSelectedItem(),horario,ubicacion,instructor,Double.parseDouble(vista.txtCosto.getText()), (String) vista.comboBoxDiasSemana.getSelectedItem());
            manejoPrincipal.getManejoActividad().imprimirActividades();
        }
        if(e.getSource()==vista.actualizarUbicacionesButton){
            actualizarComboBoxUbicaciones();
        }
    }
}
