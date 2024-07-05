package Controlador;

import Modelo.*;
import Vista.buscarGimnasios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBusquedaGimnasio implements ActionListener {
    buscarGimnasios vista;
    ManejoPrincipal manejoPrincipal;
    ArrayList<Gimnasio> gimnasiosEncontrados;
    Gimnasio gimnasioSeleccionado;
    Ubicacion ubicacionSeleccionada;
    Equipos equipoSeleccionado;
    Material materialSeleccionado;
    DefaultTableModel modeloTablaGimnasios;
    DefaultTableModel modeloTablaUbicaciones;
    DefaultTableModel modeloTablaEquipos;
    DefaultTableModel modeloTablaMateriales;
    public ControlBusquedaGimnasio(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        gimnasiosEncontrados = new ArrayList<>();
        vista = new buscarGimnasios();
        vista.add(vista.principal);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.buscarButton.addActionListener(this);
        vista.agregarEquipoButton.addActionListener(this);
        vista.agregarUbicacionButton.addActionListener(this);
        vista.editarGimnasioSeleccionadoButton.addActionListener(this);
        vista.editarUbicacionSeleccionadaButton.addActionListener(this);
        vista.comboBoxEquipos.addActionListener(this);
        vista.comboBoxUbicacion.addActionListener(this);
        vista.comboBoxMateriales.addActionListener(this);
        vista.comboBoxGimnasio.addActionListener(this);
        modeloTablaGimnasios = new DefaultTableModel(new String[]{"Nombres", "Direccion","Telefono","Horario"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableGimnasios.setModel(modeloTablaGimnasios);
        modeloTablaUbicaciones = new DefaultTableModel(new String[]{"Codigo", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableUbicaciones.setModel(modeloTablaUbicaciones);
        modeloTablaEquipos = new DefaultTableModel(new String[]{"Codigo", "Descripcion","Fecha de Adquisición","Fecha de Mantenimiento"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableEquipos.setModel(modeloTablaEquipos);
        modeloTablaMateriales = new DefaultTableModel(new String[]{"Codigo", "Descripcion","Fecha de Mantenimiento","Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableMateriales.setModel(modeloTablaMateriales);
    }
    public void actualizarTablaUbicaciones() {
        modeloTablaUbicaciones.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Nombre"};
        modeloTablaUbicaciones.addRow(nombresColumnas);
        if (!gimnasioSeleccionado.ubicaciones.isEmpty()) {
            for (int i = 0; i < gimnasioSeleccionado.ubicaciones.size(); i++) {
                Object[] rowData = {
                        gimnasioSeleccionado.ubicaciones.get(i).getCodigo(),
                        gimnasioSeleccionado.ubicaciones.get(i).getNombre()
                };
                modeloTablaUbicaciones.addRow(rowData);
            }
        }
    }
    public void actualizarTablaEquipos() {
        modeloTablaEquipos.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Descripcion","Fecha de Adquisición","Fecha de Mantenimiento"};
        modeloTablaEquipos.addRow(nombresColumnas);
        if (!ubicacionSeleccionada.equipos.isEmpty()) {
            for (int i = 0; i < ubicacionSeleccionada.equipos.size(); i++) {
                Object[] rowData = {
                        ubicacionSeleccionada.equipos.get(i).getCodigo(),
                        ubicacionSeleccionada.equipos.get(i).getDescripcion(),
                        ubicacionSeleccionada.equipos.get(i).getFechaAdquisicion(),
                        ubicacionSeleccionada.equipos.get(i).getFechaProxima()
                };
                modeloTablaEquipos.addRow(rowData);
            }
        }
    }
    public void actualizarTablaMateriales() {
        modeloTablaMateriales.setRowCount(0);
        Object[] nombresColumnas = {"Codigo", "Descripcion","Fecha de Mantenimiento","Estado"};
        modeloTablaMateriales.addRow(nombresColumnas);
        if (!equipoSeleccionado.materiales.isEmpty()) {
            for (int i = 0; i < equipoSeleccionado.materiales.size(); i++) {
                Object[] rowData = {
                        equipoSeleccionado.materiales.get(i).getCodigo(),
                        equipoSeleccionado.materiales.get(i).getDescripcion(),
                        equipoSeleccionado.materiales.get(i).getFechaMantenimiento(),
                        equipoSeleccionado.materiales.get(i).getEstado()
                };
                modeloTablaMateriales.addRow(rowData);
            }
        }
    }
    public void actualizarTablaGimnasios() {
        modeloTablaGimnasios.setRowCount(0);
        Object[] nombresColumnas = {"Nombres","Direccion","Telefono","Horario de Apertura","Hora de Salida"};
        modeloTablaGimnasios.addRow(nombresColumnas);
        if (!gimnasiosEncontrados.isEmpty()) {
            for (int i = 0; i < gimnasiosEncontrados.size(); i++) {
                Object[] rowData = {
                        gimnasiosEncontrados.get(i).getNombre(),
                        gimnasiosEncontrados.get(i).getDireccion(),
                        gimnasiosEncontrados.get(i).getTelefono(),
                        gimnasiosEncontrados.get(i).getHorario().getHoraInicio(),
                        gimnasiosEncontrados.get(i).getHorario().getHoraFin()
                };
                modeloTablaGimnasios.addRow(rowData);
            }
        }
    }
    public void actualizarComboBoxGimnasios() {
        vista.comboBoxGimnasio.removeAllItems();
        for (Gimnasio gimnasio : gimnasiosEncontrados) {
            vista.comboBoxGimnasio.addItem(gimnasio.getNombre());
        }
    }
    public void actualizarComboBoxUbicaciones() {
        vista.comboBoxUbicacion.removeAllItems();
        for (Ubicacion ubicacion : gimnasioSeleccionado.ubicaciones) {
            vista.comboBoxUbicacion.addItem(ubicacion.getNombre());
        }
    }
    public void actualizarComboBoxEquipos() {
        vista.comboBoxEquipos.removeAllItems();
        for (Equipos equipo : ubicacionSeleccionada.equipos) {
            vista.comboBoxEquipos.addItem(equipo.getCodigo());
        }
    }
    public void actualizarComboBoxMateriales() {
        vista.comboBoxMateriales.removeAllItems();
        for (Material material : equipoSeleccionado.materiales) {
            vista.comboBoxMateriales.addItem(material.getCodigo());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.buscarButton){
            if(vista.ingresoBuscar.getText()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
            }
            else{
                gimnasiosEncontrados =manejoPrincipal.getManejoGimnasio().buscar(vista.ingresoBuscar.getText());
                actualizarTablaGimnasios();
                actualizarComboBoxGimnasios();
                gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
                actualizarTablaUbicaciones();
                actualizarComboBoxUbicaciones();
                ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
                actualizarTablaEquipos();
                actualizarComboBoxEquipos();
                equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
                actualizarTablaMateriales();
                actualizarComboBoxMateriales();
                materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
            }
        }
        if(e.getSource()==vista.comboBoxGimnasio){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            actualizarTablaUbicaciones();
            actualizarComboBoxUbicaciones();
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            actualizarTablaEquipos();
            actualizarComboBoxEquipos();
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            actualizarTablaMateriales();
            actualizarComboBoxMateriales();
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
        if(e.getSource()==vista.comboBoxUbicacion){
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            actualizarTablaEquipos();
            actualizarComboBoxEquipos();
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            actualizarTablaMateriales();
            actualizarComboBoxMateriales();
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
        if(e.getSource()==vista.comboBoxEquipos){
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            actualizarTablaMateriales();
            actualizarComboBoxMateriales();
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
        if (e.getSource() == vista.comboBoxMateriales) {
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
    }
}
