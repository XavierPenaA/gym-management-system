package Controlador;

import Modelo.Jornada;
import Modelo.ManejoPrincipal;
import Modelo.Personal;
import Modelo.Verificacion;
import Vista.editarPersonal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControlEditarPersonal implements ActionListener {
    editarPersonal vista;
    ManejoPrincipal manejoPrincipal;
    File selectedPhoto;
    int indexEditar;
    public ControlEditarPersonal(Personal personalAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexEditar=manejoPrincipal.getManejoPersonal().personal.indexOf(personalAEditar);
        vista = new editarPersonal();
        vista.add(vista.principalEditarPersonal);
        vista.setSize(400,400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.escogerFotoButton.addActionListener(this);
        vista.cambiarNombresButton.addActionListener(this);
        vista.cambiarFotoButton.addActionListener(this);
        vista.cambiarApellidosButton.addActionListener(this);
        vista.cambiarDireccionButton.addActionListener(this);
        vista.cambiarTelefonoButton.addActionListener(this);
        vista.cambiarJornadaButton.addActionListener(this);
        vista.cambiarClaveButton.addActionListener(this);
        vista.cambiarRolButton.addActionListener(this);
        actualizarComboBox();
    }
    public void actualizarComboBox(){
        vista.cmbJornada.removeAllItems();
        for (Jornada jornada : manejoPrincipal.getManejoJornada().jornadas) {
            vista.cmbJornada.addItem(jornada.getNombre());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==vista.cambiarNombresButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombres.getText())){
                JOptionPane.showMessageDialog(null, "El nombre contiene caracteres no permitidos");
            }
            else{
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setNombres(vista.txtNombres.getText());
                JOptionPane.showMessageDialog(null,"Nombres Cambiados Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarApellidosButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtApellidos.getText())){
                JOptionPane.showMessageDialog(null,"Los apellidos contienen caracteres no permitidos");
            }
            else {
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setApellidos(vista.txtApellidos.getText());
                JOptionPane.showMessageDialog(null,"Apellidos Cambiados Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarClaveButton) {
            char[] passwordChars=vista.txtClave.getPassword();

            if(passwordChars.length==0){
                JOptionPane.showMessageDialog(null,"Ingrese una clave");
            }
            else {
                String password = new String(passwordChars);
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setContrasenia(password);
                JOptionPane.showMessageDialog(null,"Clave cambiada correctamente");
            }
        }
        if (e.getSource()==vista.cambiarRolButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtRol.getText())){
                JOptionPane.showMessageDialog(null,"El rol contiene caracteres no permitidos");
            }
            else {
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setRol(vista.txtRol.getText());
                JOptionPane.showMessageDialog(null,"Rol Cambiado Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarDireccionButton) {
            if(vista.txtDireccion.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese una dirección");
            }
            else{
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setDireccion(vista.txtDireccion.getText());
                JOptionPane.showMessageDialog(null,"Dirección Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarTelefonoButton) {
            if(!manejoPrincipal.getVerificacion().validarTelefonoEcuador(vista.txtTelefono.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else{
                manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setTelefono(vista.txtTelefono.getText());
                JOptionPane.showMessageDialog(null,"Telefono Cambiado Correctamente");
            }
        }
        if(e.getSource()==vista.cambiarJornadaButton){
            if(vista.cmbJornada.getSelectedItem()==null){
                JOptionPane.showMessageDialog(null,"Debe seleccionar una jornada");
            }
            else {
               manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setJornada(
                       manejoPrincipal.getManejoJornada().buscarJornadaPorNombre(
                        (String) vista.cmbJornada.getSelectedItem()));
                JOptionPane.showMessageDialog(null,"Jornada Cambiada Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarFotoButton) {
            String fotoRuta = copiarFoto();
            manejoPrincipal.getManejoPersonal().personal.get(indexEditar).setRutaFoto(fotoRuta);
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
            String nombreArchivoDestino = manejoPrincipal.getManejoPersonal().personal.get(indexEditar).getCedula() + ".jpg";
            Path destinoRuta = destinoCarpeta.resolve(nombreArchivoDestino);
            try {
                Files.deleteIfExists(destinoRuta);
                Files.copy(selectedPhoto.toPath(), destinoRuta);
                JOptionPane.showMessageDialog(null,"Foto Cambiada Correctamente");
                return destinoRuta.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
