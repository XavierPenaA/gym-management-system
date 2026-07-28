package Controlador;

import Modelo.Horario;
import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.registrarGimnasio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Objects;

public class ControlRegistroGimnasio implements ActionListener {
    registrarGimnasio vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTablaEquipos;
    DefaultTableModel modeloTablaUbicaciones;
    public ControlRegistroGimnasio(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registrarGimnasio();
        vista.add(vista.principalRegistrarGimnasio);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarGimnasio.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.agregarEquiposButton.addActionListener(this);
        modeloTablaEquipos = new DefaultTableModel(new String[]{"Codigo", "Descripcion", "Estado"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tablaEquipos.setModel(modeloTablaEquipos);

        // Crear modelo de tabla para ubicaciones
        modeloTablaUbicaciones = new DefaultTableModel(new String[]{"Codigo", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tablaUbicaciones.setModel(modeloTablaUbicaciones);
    }
    public void actualizarTablaEquipos() {
        modeloTablaEquipos.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Descripcion", "Estado"};
        modeloTablaEquipos.addRow(nombresColumnas);
        if(!manejoPrincipal.getManejoGimnasio().equiposSinAsignar.isEmpty()){
            for (int i = 0; i < manejoPrincipal.getManejoGimnasio().equiposSinAsignar.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoGimnasio().equiposSinAsignar.get(i).getCodigo(),
                        manejoPrincipal.getManejoGimnasio().equiposSinAsignar.get(i).getDescripcion(),
                        manejoPrincipal.getManejoGimnasio().equiposSinAsignar.get(i).getEstado()
                };
                modeloTablaEquipos.addRow(rowData);
            }
        }

    }
    public void actualizarTablaUbicaciones() {
        modeloTablaUbicaciones.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Nombre"};
        modeloTablaUbicaciones.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoGimnasio().ubicacionesSinAsignar.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoGimnasio().ubicacionesSinAsignar.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoGimnasio().ubicacionesSinAsignar.get(i).getCodigo(),
                        manejoPrincipal.getManejoGimnasio().ubicacionesSinAsignar.get(i).getNombre(),
                };
                modeloTablaUbicaciones.addRow(rowData);
            }
        }
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
        if(e.getSource()==vista.agregarEquiposButton){
            ControlRegistroEquipo controlRegistroEquipo= new ControlRegistroEquipo(this);
            controlRegistroEquipo.actualizarTablaMaterial();
            controlRegistroEquipo.actualizarComboBox();
        }
        if(e.getSource()==vista.btnAgregar){
            if(manejoPrincipal.getManejoGimnasio().equiposSinAsignar.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ingrese equipos para la ubicación");
            }
            else if (vista.txtCodigo.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese un código");
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombre.getText())){
                JOptionPane.showMessageDialog(null,Verificacion.mensajeERROR);
            }
            else{
                manejoPrincipal.getManejoGimnasio().agregarUbicaciones(vista.txtCodigo.getText(),
                        vista.txtNombre.getText());
                actualizarTablaUbicaciones();
                actualizarTablaEquipos();
            }
        }
        if(e.getSource()==vista.btnRegistrarGimnasio){
            Horario horario;
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
            else if(manejoPrincipal.getManejoGimnasio().ubicacionesSinAsignar.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese una ubicación");
            }
            else{
                horario=new Horario(horaInicio,horaFin);
                manejoPrincipal.getManejoGimnasio().agregarGimnasio(vista.txtNombreGim.getText(),
                        vista.txtDireccionGim.getText(),
                        vista.txtTelefonoGim.getText(),horario);
                actualizarTablaUbicaciones();
                actualizarTablaEquipos();
                JOptionPane.showMessageDialog(null,"Gimnasio Registrado Correctamente");
                vista.dispose();
                manejoPrincipal.getManejoGimnasio().imprimirGimnasios();
            }
        }
    }
}
