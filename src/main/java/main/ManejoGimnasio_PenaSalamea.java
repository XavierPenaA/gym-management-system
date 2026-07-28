package main;
import Controlador.ControlPrincipal;
public class ManejoGimnasio_PenaSalamea {

    public static void main(String[] args) {
        ControlPrincipal controlPrincipal =new ControlPrincipal();
        controlPrincipal.vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controlPrincipal.guardarDatosAlCerrar();
            }
        });
    }
}