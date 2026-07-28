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
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.buscarButton.addActionListener(this);
        vista.editarMaterialButton.addActionListener(this);
        vista.editarEquipoButton.addActionListener(this);
        vista.editarGimnasioSeleccionadoButton.addActionListener(this);
        vista.editarUbicacionSeleccionadaButton.addActionListener(this);
        vista.eliminarMaterialSeleccionadoButton.addActionListener(this);
        vista.eliminarGimnasioSeleccionadoButton.addActionListener(this);
        vista.eliminarUbicacionSeleccionadaButton.addActionListener(this);
        vista.eliminarEquipoSeleccionadoButton.addActionListener(this);
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
    public void actualizarTablaHistorialEqipo() {
        modeloTablaHistorialEquipos.setRowCount(0);
        Object[] nombresColumnas = {"Fecha de Mantenimiento"};
        modeloTablaHistorialEquipos.addRow(nombresColumnas);
        if (!equipoSeleccionado.historialMantenimiento.isEmpty()) {
            for (int i = 0; i < equipoSeleccionado.historialMantenimiento.size(); i++) {
                Object[] rowData = {
                        equipoSeleccionado.historialMantenimiento.get(i)
                };
                modeloTablaHistorialEquipos.addRow(rowData);
            }
        }
    }
    public void actualizarTablaHistorialMaterial() {
        modeloTablaHistorialMateriales.setRowCount(0);
        Object[] nombresColumnas = {"Fecha de Mantenimiento"};
        modeloTablaHistorialMateriales.addRow(nombresColumnas);
        if (!materialSeleccionado.historialMantenimiento.isEmpty()) {
            for (int i = 0; i < materialSeleccionado.historialMantenimiento.size(); i++) {
                Object[] rowData = {
                        materialSeleccionado.historialMantenimiento.get(i)
                };
                modeloTablaHistorialMateriales.addRow(rowData);
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
    public void seleccionarUbicacion(){
        gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
        ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
    }
    public void seleccionarEquipo(){
        gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
        ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
        equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
    }
    public void seleccionarMaterial(){
        gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
        ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
        equipoSeleccionado=manejoPrincipal.getManejoGimnasio().buscarEquipo(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(), (String) vista.comboBoxEquipos.getSelectedItem());
        materialSeleccionado=manejoPrincipal.getManejoGimnasio().buscarMaterial(gimnasioSeleccionado.getNombre(),ubicacionSeleccionada.getNombre(),equipoSeleccionado.getCodigo(), (String) vista.comboBoxMateriales.getSelectedItem());

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.buscarButton){
            if(vista.ingresoBuscar.getText()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
            }
            else{
                gimnasiosEncontrados=manejoPrincipal.getManejoGimnasio().buscar(vista.ingresoBuscar.getText());
                actualizarComboBoxGimnasios();
                actualizarTablaGimnasios();
                gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());

            }
        }
        if(e.getSource()==vista.comboBoxGimnasio){
            gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
            actualizarComboBoxUbicaciones();
            actualizarTablaUbicaciones();
        }
        if(e.getSource()==vista.comboBoxUbicacion){
            ubicacionSeleccionada=manejoPrincipal.getManejoGimnasio().buscarUbicacion(gimnasioSeleccionado.getNombre(), (String) vista.comboBoxUbicacion.getSelectedItem());
            actualizarTablaEquipos();
            actualizarComboBoxEquipos();
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
            if(vista.comboBoxGimnasio.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar un gimnasio");
            }else {
                ControlEditarGimnasio controlEditarGimnasio=new ControlEditarGimnasio(manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem()));
            }
        }
        if(e.getSource()==vista.editarUbicacionSeleccionadaButton){
            seleccionarUbicacion();
            ControlEditarUbicacion controlEditarUbicacion=new ControlEditarUbicacion(gimnasioSeleccionado,ubicacionSeleccionada);
        }
        if(e.getSource()==vista.editarEquipoButton){
            if(vista.comboBoxEquipos.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar un equipo");
            }else {
                seleccionarEquipo();
                ControlEditarEquipo controlEditarEquipo=new ControlEditarEquipo(gimnasioSeleccionado,ubicacionSeleccionada,equipoSeleccionado);
            }
        }
        if(e.getSource()==vista.editarMaterialButton){
            if(vista.comboBoxMateriales.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar un material");
            }else {
                seleccionarMaterial();
                ControlEditarMaterial controlEditarMaterial=new ControlEditarMaterial(gimnasioSeleccionado,ubicacionSeleccionada,equipoSeleccionado,materialSeleccionado);
            }
        }
        if(e.getSource()==vista.eliminarGimnasioSeleccionadoButton){
            if(vista.comboBoxGimnasio.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar un gimnasio");
            }else {
                gimnasioSeleccionado=manejoPrincipal.getManejoGimnasio().buscarGimnasio((String) vista.comboBoxGimnasio.getSelectedItem());
                manejoPrincipal.getManejoGimnasio().gimnasios.remove(gimnasioSeleccionado);
                JOptionPane.showMessageDialog(null,"Se ha eliminado el gimnasio correctamente" );
            }
        }
        if(e.getSource()==vista.eliminarUbicacionSeleccionadaButton){
            if(vista.comboBoxUbicacion.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar una ubicacion");
            }else {
                seleccionarUbicacion();
                manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                        gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.remove(ubicacionSeleccionada);
                JOptionPane.showMessageDialog(null, "Se ha eliminado la Ubicacion correctamente");
            }
        }
        if(e.getSource()==vista.eliminarEquipoSeleccionadoButton){
            seleccionarEquipo();
            manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                    get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                    equipos.remove(equipoSeleccionado);
            JOptionPane.showMessageDialog(null,"Se ha eliminado el equipo correctamente");
        }
        if(e.getSource()==vista.eliminarMaterialSeleccionadoButton){
            seleccionarMaterial();
             manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                    get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                            gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                    equipos.get( manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.
                            get(manejoPrincipal.getManejoGimnasio().gimnasios.get(manejoPrincipal.getManejoGimnasio().
                                    gimnasios.indexOf(gimnasioSeleccionado)).ubicaciones.indexOf(ubicacionSeleccionada)).
                            equipos.indexOf(equipoSeleccionado)).materiales.remove(materialSeleccionado);
            JOptionPane.showMessageDialog(null,"Se ha eliminado el material correctamente");
        }
        if(e.getSource()==vista.mostrarHistorialDeMantenimientoButton){
            actualizarTablaHistorialMaterial();
        }
        if(e.getSource()==vista.mostrarHistorialDeMantenimientoEquipoButton){
            actualizarTablaHistorialEqipo();
        }

    }
}
