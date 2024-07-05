package Controlador;

import Modelo.ManejoPrincipal;
import Vista.inicioSesion;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInicioSesion implements ActionListener {
    inicioSesion vistaInicioSesion;
    ManejoPrincipal manejoPrincipal;
    private ControlPrincipal controlPrincipal;
    public ControlInicioSesion(ControlPrincipal controlPrincipal){
        this.controlPrincipal = controlPrincipal;
        vistaInicioSesion = new inicioSesion();
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vistaInicioSesion.add(vistaInicioSesion.principalInicioSesion);
        vistaInicioSesion.setSize(400,400);
        vistaInicioSesion.setLocationRelativeTo(null);
        vistaInicioSesion.setVisible(true);
        vistaInicioSesion.btnverificar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vistaInicioSesion.btnverificar){
            controlPrincipal.logeado= manejoPrincipal.iniciarSesionPropietario(vistaInicioSesion.txtCedula.getText(), vistaInicioSesion.txtClave.getText());
            if(controlPrincipal.logeado!=null){
                JOptionPane.showMessageDialog(null, "Dueño Logeado Correctamente");
                vistaInicioSesion.dispose();
                controlPrincipal.definirUsuarioLogeado();
            }
            else{
                controlPrincipal.logeado= manejoPrincipal.getManejoPersonal().iniciarSesion(vistaInicioSesion.txtCedula.getText(), vistaInicioSesion.txtClave.getText());
                if (controlPrincipal.logeado!=null) {
                    JOptionPane.showMessageDialog(null, "Usuario Logeado Correctamente");
                    vistaInicioSesion.dispose();
                    controlPrincipal.definirUsuarioLogeado();

                } else {
                    JOptionPane.showMessageDialog(null, "Error: Credenciales incorrectas");
                }
            }
        }
    }
}
