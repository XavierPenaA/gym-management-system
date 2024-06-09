package Controlador;

import Modelo.ManejoPrincipal;
import Vista.listarActividad;
import Vista.listarPersonal;

import javax.swing.table.DefaultTableModel;

public class ControlListarActividad {
    listarActividad vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    public ControlListarActividad(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new listarActividad();
        vista.add(vista.principalListarActividad);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
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
}

