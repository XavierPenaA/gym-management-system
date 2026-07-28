package Controlador;

import Modelo.*;
import Vista.buscarActividad;
import Vista.buscarPersonas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlBuscarActividad implements ActionListener {
    buscarActividad vista;
    ManejoPrincipal manejoPrincipal;
    ArrayList<Actividad> actividadesEncontradas;
    Actividad actividadSeleccionda;
    DefaultTableModel modeloTabla;
    public ControlBuscarActividad(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        actividadesEncontradas = new ArrayList<>();
        vista = new buscarActividad();
        vista.add(vista.principalBuscarActividad);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.buscarButton.addActionListener(this);
        vista.editarButton.addActionListener(this);
        vista.comboBoxCampoBusqueda.addActionListener(this);
        vista.elimanrSeleccionadoButton.addActionListener(this);
        actualizarComboBox();
        modeloTabla = new DefaultTableModel(new String[]{"Codigo","Nombre", "Descripcion", "Cupos","Disponible","Estado","Ubicacion","Instructor","Precio","Hora Inicio","Hora Fin","Dia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.table1.setModel(modeloTabla);
    }
    public void actualizarTablaActividad() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Codigo","Nombre", "Descripcion", "Cupos","Disponible","Estado","Ubicacion","Instructor","Precio","Hora Inicio","Hora Fin","Dia"};
        modeloTabla.addRow(nombresColumnas);
        if (! actividadesEncontradas.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoActividad().actividades.size(); i++) {
                Object[] rowData = {
                         actividadesEncontradas.get(i).getCodigo(),
                         actividadesEncontradas.get(i).getNombre(),
                         actividadesEncontradas.get(i).getDescripcion(),
                         actividadesEncontradas.get(i).getCupos(),
                         actividadesEncontradas.get(i).getDisponible(),
                         actividadesEncontradas.get(i).getEstado(),
                         actividadesEncontradas.get(i).getUbicacion(),
                         actividadesEncontradas.get(i).getInstructor(),
                         actividadesEncontradas.get(i).getPrecio(),
                         actividadesEncontradas.get(i).getHorario().getHoraInicio(),
                         actividadesEncontradas.get(i).getHorario().getHoraFin(),
                         actividadesEncontradas.get(i).getDia()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
    public void actualizarComboBox(){
       String[] campos = {
                "Codigo", "Nombre"
        };
        vista.comboBoxCampoBusqueda.setModel(new DefaultComboBoxModel<>(campos));
    }
    public void actualizarComboBoxActividades() {
        vista.comboBox1.removeAllItems();
        for (Actividad actividad : actividadesEncontradas) {
            vista.comboBox1.addItem(actividad.getNombre());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.buscarButton){
            if(vista.textField1.getText()==null){
                JOptionPane.showMessageDialog(null,"Debe ingresar el valor a buscar");
            } else if (vista.comboBoxCampoBusqueda.getSelectedItem()==null) {
                JOptionPane.showMessageDialog(null,"Debe seleccionar un campo de busqueda");
            }
            else{
                actividadesEncontradas=manejoPrincipal.getManejoActividad().buscar((String) vista.comboBoxCampoBusqueda.getSelectedItem(),vista.textField1.getText());
                actualizarTablaActividad();
                actualizarComboBoxActividades();
            }
        }
        if(e.getSource()==vista.elimanrSeleccionadoButton){
            if(vista.comboBox1.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar una actividad");
            }
            else {
                actividadSeleccionda=manejoPrincipal.getManejoActividad().buscarActividad((String) vista.comboBox1.getSelectedItem());
                manejoPrincipal.getManejoActividad().actividades.remove(actividadSeleccionda);
                JOptionPane.showMessageDialog(null, "Actividad  Eliminada Correctamente");
            }
        }
        if(e.getSource()==vista.editarButton){
            if(vista.comboBox1.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar una actividad");
            }
            else {
                actividadSeleccionda = manejoPrincipal.getManejoActividad().buscarActividad((String) vista.comboBox1.getSelectedItem());
                ControlEditarActividad controlEditarActividad = new ControlEditarActividad(actividadSeleccionda);
            }
        }
    }
}
