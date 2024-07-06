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
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.buscarButton.addActionListener(this);
        vista.editarButton.addActionListener(this);
        vista.comboBoxCampoBusqueda.addActionListener(this);
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
        if (!manejoPrincipal.getManejoActividad().actividades.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoActividad().actividades.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoActividad().actividades.get(i).getCodigo(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getNombre(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getDescripcion(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getCupos(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getDisponible(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getEstado(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getUbicacion(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getInstructor(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getPrecio(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getHorario().getHoraInicio(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getHorario().getHoraFin(),
                        manejoPrincipal.getManejoActividad().actividades.get(i).getDia()
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
            }
            else{
                actividadesEncontradas=manejoPrincipal.getManejoActividad().buscar((String) vista.comboBoxCampoBusqueda.getSelectedItem(),vista.textField1.getText());
                actualizarTablaActividad();
                actualizarComboBoxActividades();
                }
        }
        if(e.getSource()==vista.elimanrSeleccionadoButton){
            actividadSeleccionda=manejoPrincipal.getManejoActividad().buscarActividad((String) vista.comboBox1.getSelectedItem());
            manejoPrincipal.getManejoActividad().actividades.remove(actividadSeleccionda);
        }
        if(e.getSource()==vista.editarButton){
            actividadSeleccionda=manejoPrincipal.getManejoActividad().buscarActividad((String) vista.comboBox1.getSelectedItem());
            ControlEditarActividad controlEditarActividad=new ControlEditarActividad(actividadSeleccionda);
        }
    }
}
