package Controlador;

import Modelo.ManejoPrincipal;
import Modelo.Miembro;
import Modelo.Verificacion;
import Vista.editarMiembros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControlEditarMiembros implements ActionListener {
    editarMiembros vista;
    ManejoPrincipal manejoPrincipal;
    File selectedPhoto;
    int indexEditar;
    public ControlEditarMiembros(Miembro miembroAEditar) {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        indexEditar=manejoPrincipal.getManejoMiembros().miembros.indexOf(miembroAEditar);
        vista = new editarMiembros();
        vista.add(vista.principaleditarMiembros);
        //vista.setSize(400,400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.escogerFotoButton.addActionListener(this);
        vista.cambiarNombreButton.addActionListener(this);
        vista.cambiarFotoButton.addActionListener(this);
        vista.cambiarApellidosButton.addActionListener(this);
        vista.cambiarDireccionButton.addActionListener(this);
        vista.cambiarTelefonoButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==vista.cambiarNombreButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtNombres.getText())){
                JOptionPane.showMessageDialog(null, "El nombre contiene caracteres no permitidos");
            }
            else{
                manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).setNombres(vista.txtNombres.getText());
                JOptionPane.showMessageDialog(null,"Nombres Cambiados Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarApellidosButton) {
            if(!manejoPrincipal.getVerificacion().validarLetras(vista.txtApellidos.getText())){
                JOptionPane.showMessageDialog(null,"Los apellidos contienen caracteres no permitidos");
            }
            else {
                manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).setApellidos(vista.txtApellidos.getText());
                JOptionPane.showMessageDialog(null,"Apellidos Cambiados Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarDireccionButton) {
            if(vista.txtDireccion.getText()==null){
                JOptionPane.showMessageDialog(null,"Ingrese una dirección");
            }
            else{
                manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).setDireccion(vista.txtDireccion.getText());
                JOptionPane.showMessageDialog(null,"Dirección Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarTelefonoButton) {
            if(!manejoPrincipal.getVerificacion().validarTelefonoEcuador(vista.txtTelefono.getText())){
                JOptionPane.showMessageDialog(null, Verificacion.mensajeERROR);
            }
            else{
                manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).setTelefono(vista.txtTelefono.getText());
                JOptionPane.showMessageDialog(null,"Telefono Cambiado Correctamente");
            }
        }
        if (e.getSource()==vista.cambiarFotoButton) {
            String fotoRuta = copiarFoto();
            manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).setRutaFoto(fotoRuta);
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
            String nombreArchivoDestino = manejoPrincipal.getManejoMiembros().miembros.get(indexEditar).getCedula() + ".jpg";
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
