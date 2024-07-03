package Controlador;

import Modelo.ManejoPrincipal;
import Modelo.Verificacion;
import Vista.registroUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ControlRegistroUsuario implements ActionListener {
    registroUsuario vista;
    ManejoPrincipal manejoPrincipal;
    File selectedPhoto;
    public ControlRegistroUsuario() {
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista = new registroUsuario();
        vista.add(vista.principalRegistroUsuario);
        vista.setSize(400,400);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnRegistrarse.addActionListener(this);
        vista.escogerFotoButton.addActionListener(this);
    }
    public void actualizarComboBox(){
        String[] months = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        vista.monthComboBox.setModel(new DefaultComboBoxModel<>(months));
        for (int i = 1; i <= 31; i++) {
            vista.dayComboBox.addItem(i);
        }
        for (int i = 2024; i <= 2050; i++) {
            vista.yearComboBox.addItem(i);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnRegistrarse){
            int day = (int) vista.dayComboBox.getSelectedItem();
            int month = vista.monthComboBox.getSelectedIndex() + 1; // Los meses en LocalDate son 1-based
            int year = (int) vista.yearComboBox.getSelectedItem();
            LocalDate date = LocalDate.of(year, month, day);
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
            else if(!manejoPrincipal.getVerificacion().esFechaMayor(LocalDate.now(),date)){
                JOptionPane.showMessageDialog(null, "La fecha de membresía debe ser mayor a la fecha actual");
            }
            else{
                String fotoRuta = copiarFoto();
                manejoPrincipal.getManejoMiembros().registrarMiembro(vista.txtCedula.getText(),vista.txtNombres.getText(),
                        vista.txtApellidos.getText(), vista.txtDireccion.getText(),vista.txtTelefono.getText(),
                        LocalDate.now(),date,fotoRuta);
                JOptionPane.showMessageDialog(null, "Miembro Registrado Correctamente");
                vista.dispose();
                manejoPrincipal.getManejoMiembros().imprimirMiembros();
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
