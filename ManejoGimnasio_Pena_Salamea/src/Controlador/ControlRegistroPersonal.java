package Controlador;

import Modelo.Jornada;
import Modelo.ManejoPrincipal;
import Vista.registroPersonal;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlRegistroPersonal implements ActionListener {
    registroPersonal vista;
    ManejoPrincipal manejoPrincipal;

    public ControlRegistroPersonal() {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registroPersonal();
        vista.add(vista.principalRegistrarPersonal);
        vista.setSize(400,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarse.addActionListener(this);
    }
    public void actualizarComboBox(){
        vista.cmbJornada.removeAllItems();
        for (Jornada jornada : manejoPrincipal.getManejoJornada().jornadas) {
            vista.cmbJornada.addItem(jornada.getNombre());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnRegistrarse){
            char[] passwordChars=vista.txtClave.getPassword();
            String password = new String(passwordChars);
            manejoPrincipal.getManejoPersonal().registrarPersonal(vista.txtCedula.getText(),vista.txtNombres.getText(),vista.txtApellidos.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(),vista.txtRol.getText(), password,manejoPrincipal.getManejoJornada().buscarJornadaPorNombre((String) vista.cmbJornada.getSelectedItem()));
            JOptionPane.showMessageDialog(null, "Personal Registrado Correctamente");
            vista.dispose();
            manejoPrincipal.getManejoPersonal().imprimirPersonal();
        }
    }
}
