package Controlador;

import Modelo.Jornada;
import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.registroPersonal;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControlRegistroPersonal implements ActionListener {
    registroPersonal vista;
    ManejoPrincipal manejoPrincipal;
    File selectedPhoto;
    public ControlRegistroPersonal() {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registroPersonal();
        vista.add(vista.principalRegistrarPersonal);
        vista.setSize(400,600);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarse.addActionListener(this);
        vista.escogerFotoButton.addActionListener(this);
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
            if(!manejoPrincipal.getVerificacion().validarCedula(vista.txtCedula.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombres.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtApellidos.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().validarTelefonoEcuador(vista.txtTelefono.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtRol.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else{
                String fotoRuta = copiarFoto();
                manejoPrincipal.getManejoPersonal().registrarPersonal(vista.txtCedula.getText(),vista.txtNombres.getText(),
                        vista.txtApellidos.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(),
                        vista.txtRol.getText(), password,
                        manejoPrincipal.getManejoJornada().buscarJornadaPorNombre((String) vista.cmbJornada.getSelectedItem()),fotoRuta);
                JOptionPane.showMessageDialog(null, "Personal Registrado Correctamente");
                vista.dispose();
                manejoPrincipal.getManejoPersonal().imprimirPersonal();
            }
        }
        if(e.getSource()==vista.escogerFotoButton){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(vista);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPhoto = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(selectedPhoto.getAbsolutePath());
                vista.foto.setIcon(imageIcon);
                vista.foto.setText("");
            }
        }
    }
    private String copiarFoto() {
        if (selectedPhoto != null) {
            Path destinoCarpeta = Paths.get("imagenes");
            if (!Files.exists(destinoCarpeta)) {
                try {
                    Files.createDirectories(destinoCarpeta);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String nombreArchivoDestino = vista.txtCedula.getText() + ".jpg";
            Path destinoRuta = destinoCarpeta.resolve(nombreArchivoDestino);
            try {
                Files.copy(selectedPhoto.toPath(), destinoRuta);
                return destinoRuta.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
