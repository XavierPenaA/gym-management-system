package Controlador;

import Modelo.ManejoPrincipal;
import Vista.listarGimnasios;

import javax.swing.table.DefaultTableModel;

public class ControlListarGimnasios {
    listarGimnasios vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    public ControlListarGimnasios(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new listarGimnasios();
        vista.add(vista.principalListarGimnasios);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        modeloTabla = new DefaultTableModel(new String[]{"Nombres","Direccion","Telefono","Horario de Apertura","Hora de Salida"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.table1.setModel(modeloTabla);
    }
    public void actualizarTablaGimnasio() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Nombres","Direccion","Telefono","Horario de Apertura","Hora de Salida"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoGimnasio().gimnasios.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoGimnasio().gimnasios.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoGimnasio().gimnasios.get(i).getNombre(),
                        manejoPrincipal.getManejoGimnasio().gimnasios.get(i).getDireccion(),
                        manejoPrincipal.getManejoGimnasio().gimnasios.get(i).getTelefono(),
                        manejoPrincipal.getManejoGimnasio().gimnasios.get(i).getHorario().getHoraInicio(),
                        manejoPrincipal.getManejoGimnasio().gimnasios.get(i).getHorario().getHoraFin()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
}
