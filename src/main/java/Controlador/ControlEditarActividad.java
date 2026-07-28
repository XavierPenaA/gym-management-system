package Controlador;

import Modelo.*;
import Vista.editarActividad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ControlEditarActividad implements ActionListener {
    editarActividad vista;
    ManejoPrincipal manejoPrincipal;
    int indexEditar;
    public ControlEditarActividad(Actividad actividadAEditar) {
        vista = new editarActividad();
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexEditar =manejoPrincipal.getManejoActividad().actividades.indexOf(actividadAEditar);;
        vista.add(vista.panel);
        //vista.setSize(800,800);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.cambiarCodigoButton.addActionListener(this);
        vista.cambiarCostoButton.addActionListener(this);
        vista.cambiarDescripcionButton.addActionListener(this);
        vista.cambiarCupoButton.addActionListener(this);
        vista.cambiarEstadoButton.addActionListener(this);
        vista.cambiarHorarioButton.addActionListener(this);
        vista.cambiarInstructorButton.addActionListener(this);
        vista.cambiarUbicacionButton.addActionListener(this);
        vista.cambiarNombreButton.addActionListener(this);
        vista.comboBoxGimnasios.addActionListener(this);
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
        if (e.getSource()==vista.cambiarNombreButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombreAct.getText())){
                JOptionPane.showMessageDialog(null, "El nombre contiene caracteres no permitidos");
            }
            else{
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setNombre(vista.txtNombreAct.getText());
                JOptionPane.showMessageDialog(null,"Nombres Cambiados Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarDescripcionButton) {
            if(vista.txtDescripcion.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese una Descripcion");
            }
            else {
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setDescripcion(vista.txtDescripcion.getText());
                JOptionPane.showMessageDialog(null,"Descripcion Cambiada Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarCupoButton) {
            if(!manejoPrincipal.getVerificacion().validarNumero(vista.txtCupo.getText())){
                JOptionPane.showMessageDialog(null,"Ingrese un cupo valido");
            }
            else{
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setCupos(Integer.parseInt(vista.txtCupo.getText()));
                JOptionPane.showMessageDialog(null,"Cupo Cambiado Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarCostoButton) {
            if(!manejoPrincipal.getVerificacion().validarNumero(vista.txtCosto.getText())){
                JOptionPane.showMessageDialog(null,"Ingrese un costo valido");
            }
            else{
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setCupos(Integer.parseInt(vista.txtCosto.getText()));
                JOptionPane.showMessageDialog(null,"Costo Cambiado Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarEstadoButton) {
            manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setEstado((String) vista.cbmEstado.getSelectedItem());
        }
        if (e.getSource()==vista.cambiarCodigoButton) {
            if(vista.textField1.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese un Codigo");
            }
            else {
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setCodigo(vista.textField1.getText());
                JOptionPane.showMessageDialog(null,"Codigo Cambiado Correctamente");
            }
        }
        if(e.getSource()==vista.cambiarInstructorButton){
            Personal instructor=
                    manejoPrincipal.getManejoPersonal().buscarPersonalPorNombre((String) vista.cmbInstructor.getSelectedItem());
            manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setInstructor(instructor);

        }
        if(e.getSource()==vista.cambiarUbicacionButton){
            Ubicacion ubicacion=
                    manejoPrincipal.getManejoGimnasio().buscarUbicacion((String) vista.comboBoxGimnasios.getSelectedItem(),
                            (String) vista.comboBoxUbicaciones.getSelectedItem());
            manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setUbicacion(ubicacion);
        }
        if(e.getSource()==vista.cambiarHorarioButton){
            int day = (int) vista.cmbDiaFechaInicio.getSelectedItem();
            int month = vista.cmbMesFechaInicio.getSelectedIndex() + 1;
            int year = (int) vista.cmbYearFechaInicio.getSelectedItem();
            LocalDate fechaInicio = LocalDate.of(year, month, day);
            int day2 = (int) vista.cmbDiaFechaFin.getSelectedItem();
            int month2 = vista.cmbMesFechaFin.getSelectedIndex() + 1;
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
            if(!manejoPrincipal.getVerificacion().esFechaMayor(fechaInicio,fechaFin)){
                JOptionPane.showMessageDialog(null,Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().esHoraMayor(horaInicio,horaFin)){
                JOptionPane.showMessageDialog(null,Verificacion.mensajeERROR);
            }
            else{
                Horario horario=new Horario(fechaInicio,fechaFin,horaInicio,horaFin);
                manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setHorario(horario);
            }
        }
        if(e.getSource()==vista.cambiarDiaButton){
            manejoPrincipal.getManejoActividad().actividades.get(indexEditar).setDia((String) vista.comboBoxDiasSemana.getSelectedItem());

        }
        if(e.getSource()==vista.comboBoxGimnasios){
            actualizarComboBoxUbicaciones();
        }
    }
}
