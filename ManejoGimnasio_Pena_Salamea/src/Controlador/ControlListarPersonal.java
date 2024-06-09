package Controlador;

import Modelo.ManejoPrincipal;
import Vista.listarPersonal;

import javax.swing.table.DefaultTableModel;

public class ControlListarPersonal {
    listarPersonal vista;
    ManejoPrincipal manejoPrincipal;
    DefaultTableModel modeloTabla;
    public ControlListarPersonal(){
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new listarPersonal();
        vista.add(vista.principalListarPersonal);
        vista.setSize(1000,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        modeloTabla = new DefaultTableModel(new String[]{"Cedula","Nombres", "Apellidos", "Rol","Direccion","Telefono","Jornada"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista.table1.setModel(modeloTabla);
    }
    public void actualizarTablaPersonal() {
        modeloTabla.setRowCount(0);
        Object[] nombresColumnas = {"Cedula","Nombres", "Apellidos", "Rol","Direccion","Telefono","Jornada"};
        modeloTabla.addRow(nombresColumnas);
        if (!manejoPrincipal.getManejoPersonal().personal.isEmpty()) {
            for (int i = 0; i < manejoPrincipal.getManejoPersonal().personal.size(); i++) {
                Object[] rowData = {
                        manejoPrincipal.getManejoPersonal().personal.get(i).getCedula(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getNombres(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getApellidos(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getRol(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getDireccion(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getTelefono(),
                        manejoPrincipal.getManejoPersonal().personal.get(i).getJornada()
                };
                modeloTabla.addRow(rowData);
            }
        }
    }
}
