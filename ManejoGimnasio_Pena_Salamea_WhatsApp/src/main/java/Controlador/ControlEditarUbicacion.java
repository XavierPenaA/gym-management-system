package Controlador;

import Modelo.*;
import Vista.editarUbicaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlEditarUbicacion implements ActionListener {
    editarUbicaciones vista;
    ManejoPrincipal manejoPrincipal;
    int indexEditar;
    int indexGim;

    public ControlEditarUbicacion(Gimnasio gim, Ubicacion ubicacionAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexGim = manejoPrincipal.getManejoGimnasio().gimnasios.indexOf(gim);
        indexEditar=manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.indexOf(ubicacionAEditar);
        vista = new editarUbicaciones();
        vista.add(vista.principal);
        vista.setSize(800, 400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.cambiarNombreButton.addActionListener(this);
        vista.cambiarCodigoButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.cambiarCodigoButton) {
            if (vista.txtCodigo.getText()==null) {
                JOptionPane.showMessageDialog(null, "Escriba un Codigo");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexEditar).setCodigo(vista.txtCodigo.getText());
                JOptionPane.showMessageDialog(null, "Codigo Cambiado Correctamente");
            }
        }
        if (e.getSource() == vista.cambiarNombreButton) {
            if (vista.txtNombre.getText() == null) {
                JOptionPane.showMessageDialog(null, "Ingrese un Nombre");
            } else {
                manejoPrincipal.getManejoGimnasio().gimnasios.get(indexGim).ubicaciones.get(indexEditar).setNombre(vista.txtNombre.getText());
                JOptionPane.showMessageDialog(null, "Nombre Cambiado Correctamente");
            }
        }
    }
}