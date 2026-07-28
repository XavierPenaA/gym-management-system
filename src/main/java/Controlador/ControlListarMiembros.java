package Controlador;

import Modelo.ManejoPrincipal;
import Vista.listarMiembros;

import javax.swing.table.DefaultTableModel;

public class ControlListarMiembros {
    listarMiembros vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    public ControlListarMiembros(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new listarMiembros();
        vista.add(vista.principalListarMiembros);
        //vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        modeloTabla = new DefaultTableModel(new String[]{"Cedula","Nombres", "Apellidos","Direccion","Telefono","Fecha de Inicio","Fecha de Fin de Membresia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.table1.setModel(modeloTabla);
    }
    public void actualizarTablaMiembros() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Cedula","Nombres", "Apellidos","Direccion","Telefono","Fecha de Inicio","Fecha de Fin de Membresia"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoMiembros().miembros.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoMiembros().miembros.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getCedula(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getNombres(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getApellidos(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getDireccion(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getTelefono(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getFechaInicio(),
                        manejoPrincipal.getManejoMiembros().miembros.get(i).getFechaFin()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
}
