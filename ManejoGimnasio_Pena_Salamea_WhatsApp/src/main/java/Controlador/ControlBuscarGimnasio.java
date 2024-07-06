package Controlador;

import Modelo.*;
import Vista.buscarGimnasios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBuscarGimnasio implements ActionListener {
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
    DefaultTableModel modeloTablaHistorialEquipos;
    DefaultTableModel modeloTablaHistorialMateriales;
    public ControlBuscarGimnasio(){
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
        vista.agregarMaterialButton.addActionListener(this);
        vista.editarMaterialButton.addActionListener(this);
        vista.editarEquipoButton.addActionListener(this);
        vista.editarGimnasioSeleccionadoButton.addActionListener(this);
        vista.editarUbicacionSeleccionadaButton.addActionListener(this);
        vista.mostrarHistorialDeMantenimientoButton.addActionListener(this);
        vista.mostrarHistorialDeMantenimientoEquipoButton.addActionListener(this);
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
        modeloTablaHistorialEquipos= new DefaultTableModel(new String[]{"Fecha de Mantenimiento"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableHistorialEquipo.setModel(modeloTablaHistorialEquipos);
        modeloTablaHistorialMateriales= new DefaultTableModel(new String[]{"Fecha de Mantenimiento"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.tableMantenimientoMaterial.setModel(modeloTablaHistorialMateriales);
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
        for (Equipos equips : ubicacionSeleccionada.equipos) {
            vista.comboBoxEquipos.addItem(equips.getCodigo());
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
                gimnasiosEncontrados=manejoPrincipal.getManejoGimnasio().buscar(vista.ingresoBuscar.getText());
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
        if(e.getSource()==vista.editarGimnasioSeleccionadoButton){
            ControlEditarGimnasio controlEditarGimnasio=new ControlEditarGimnasio(manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem()));
        }
        if(e.getSource()==vista.editarUbicacionSeleccionadaButton){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            ControlEditarUbicacion controlEditarUbicacion=new ControlEditarUbicacion(gimnasioSeleccionado,ubicacionSeleccionada);
        }
        if(e.getSource()==vista.editarEquipoButton){

        }
        if(e.getSource()==vista.editarMaterialButton){

        }
        if(e.getSource()==vista.eliminarGimnasioSeleccionadoButton){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            manejoPrincipal.getManejoGimnasio().gimnasios.remove(gimnasioSeleccionado);
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
        if(e.getSource()==vista.eliminarUbicacionSeleccionadaButton){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.remove(ubicacionSeleccionada);
            actualizarTablaEquipos();
            actualizarComboBoxEquipos();
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            actualizarTablaMateriales();
            actualizarComboBoxMateriales();
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
        if(e.getSource()==vista.eliminarEquipoSeleccionadoButton){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                    get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                    equipos.remove(equipoSeleccionado);
            actualizarTablaMateriales();
            actualizarComboBoxMateriales();
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
        }
        if(e.getSource()==vista.eliminarMaterialSeleccionadoButton){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
            materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());
            manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                    get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                    equipos.get( manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                            get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                            equipos.indexOf(equipoSeleccionado)).materiales.remove(materialSeleccionado);
        }
    }
}
