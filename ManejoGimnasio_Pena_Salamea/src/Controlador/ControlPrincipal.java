package Controlador;

import Modelo.*;
import Vista.ventanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPrincipal implements ActionListener {
    public ventanaPrincipal vista;
    ManejoPrincipal manejoPrincipal;
    public Persona logeado=null;
    public ControlPrincipal(){
        vista = new ventanaPrincipal();
        manejoPrincipal = ManejoPrincipal.getInstancia();
        vista.add(vista.principal);
        vista.setSize(800,600);
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        vista.setVisible(true);
        vista.btnFacturar.addActionListener(this);
        vista.btniniciarSesion.addActionListener(this);
        vista.btnCerrarSesion.addActionListener(this);
        vista.btnRegistrarse.addActionListener(this);
        vista.btnSegunUsuario.addActionListener(this);
        vista.btnSegunUsuario2.addActionListener(this);
        vista.btnSegunUsuario3.addActionListener(this);
        vista.btnSegunUsuario4.addActionListener(this);
        vista.btnSegunUsuario5.addActionListener(this);
        vista.btnSegunUsuario6.addActionListener(this);
        vista.btnSegunUsuario7.addActionListener(this);
        vista.btnSegunUsuario8.addActionListener(this);

        manejoPrincipal.cargarDatos("datos.dat");
        definirUsuarioLogeado();
    }
    public void guardarDatosAlCerrar() {
        manejoPrincipal.guardarDatos("datos.dat");
    }
    public void definirUsuarioLogeado(){
        if(logeado==null){
            vista.labelInfoSesion.setVisible(true);
            vista.btnSegunUsuario5.setVisible(false);
            vista.btnFacturar.setVisible(false);
            vista.btnRegistrarse.setVisible(false);
            vista.btnCerrarSesion.setVisible(false);
            vista.btniniciarSesion.setVisible(true);
            vista.btnSegunUsuario.setVisible(false);
            vista.pnlInformacionUsuario.setVisible(false);
            vista.pnlSegunUsuario.setVisible(false);
            vista.btnSegunUsuario6.setVisible(false);
            vista.btnSegunUsuario7.setVisible(false);
            vista.btnSegunUsuario8.setVisible(false);
            vista.foto.setVisible(false);
        }
        else {
            vista.pnlInformacionUsuario.setVisible(true);
            vista.txtNombresUsuario.setText(logeado.getNombres());
            vista.txtApellidosUsuario.setText(logeado.getApellidos());
            vista.txtCedulaUsuario.setText(logeado.getCedula());
            vista.txtDireccionUsuario.setText(logeado.getDireccion());
            vista.txtTelefonoUsuario.setText(logeado.getTelefono());
            vista.pnlSegunUsuario.setVisible(true);
            vista.labelInfoSesion.setVisible(false);
            vista.btniniciarSesion.setVisible(false);
            vista.btnCerrarSesion.setVisible(true);
            vista.foto.setVisible(true);
            ImageIcon foto = new ImageIcon(logeado.getRutaFoto());
            Image img = foto.getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            vista.foto.setIcon(new ImageIcon(scaledImg));

            if (logeado instanceof Personal) {
                vista.txtRolUsuario.setText(((Personal) logeado).getRol());
                vista.btnRegistrarse.setText("Registrar Miembro");
                vista.btnFacturar.setVisible(true);
                vista.btnSegunUsuario.setText("Registrar Actividad");
                vista.btnSegunUsuario2.setText("Listar Miembros");
                vista.btnSegunUsuario3.setText("Buscar/Editar Miembros");
                vista.btnSegunUsuario4.setText("Listar Actividad");
                vista.btnSegunUsuario5.setText("Buscar/Editar Actividad");
                vista.btnRegistrarse.setVisible(true);
                vista.btnSegunUsuario6.setVisible(false);
                vista.btnSegunUsuario7.setVisible(false);
                vista.btnSegunUsuario8.setVisible(false);
            }
            else if (logeado instanceof Propietario) {
                vista.txtRolUsuario.setText("Dueño");
                vista.btnRegistrarse.setText("Registrar Personal");
                vista.btnFacturar.setVisible(true);
                vista.btnSegunUsuario.setText("Registrar Jornada");
                vista.btnSegunUsuario2.setText("Registrar Gimnasio");
                vista.btnSegunUsuario3.setText("Listar Personal");
                vista.btnSegunUsuario4.setText("Listar Gimnasio");
                vista.btnSegunUsuario5.setText("Listar Facturas");
                vista.btnSegunUsuario6.setText("Buscar Factura");
                vista.btnSegunUsuario7.setText("Buscar/Editar Personal");
                vista.btnSegunUsuario8.setText("Buscar/Editar Gimnasio");
                vista.btnRegistrarse.setVisible(true);
                vista.btnSegunUsuario6.setVisible(true);
                vista.btnSegunUsuario7.setVisible(true);
                vista.btnSegunUsuario8.setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btniniciarSesion){
            ControlInicioSesion controlInicioSesion=new ControlInicioSesion(this);
        }
        if(e.getSource()==vista.btnCerrarSesion){
            logeado = null;
            definirUsuarioLogeado();
        }
        if(e.getSource()==vista.btnRegistrarse){
            if(logeado instanceof Personal){
                ControlRegistroUsuario controlRegistroUsuario = new ControlRegistroUsuario();
                controlRegistroUsuario.actualizarComboBox();
            }
            if(logeado instanceof Propietario){
                ControlRegistroPersonal controlRegistroPersonal=new ControlRegistroPersonal();
                controlRegistroPersonal.actualizarComboBox();

            }
        }
        if(e.getSource()==vista.btnSegunUsuario){
            if(logeado instanceof Propietario){
                ControlRegistroJornada controlRegistroJornada=new ControlRegistroJornada();
                controlRegistroJornada.actualizarComboBox();
            }
            if(logeado instanceof Personal){
                ControlRegistroActividad controlRegistroClase=new ControlRegistroActividad();
                controlRegistroClase.actualizarComboBox();

            }
        }
        if(e.getSource()==vista.btnSegunUsuario2){
            if(logeado instanceof Propietario){
                ControlRegistroGimnasio controlRegistroGimnasio=new ControlRegistroGimnasio();
                controlRegistroGimnasio.actualizarComboBox();
                controlRegistroGimnasio.actualizarTablaEquipos();
                controlRegistroGimnasio.actualizarTablaUbicaciones();
            }
            if(logeado instanceof Personal){
                ControlListarMiembros controlListarMiembros = new ControlListarMiembros();
                controlListarMiembros.actualizarTablaMiembros();
            }
        }
        if(e.getSource()==vista.btnSegunUsuario3){
            if(logeado instanceof Propietario){
                ControlListarPersonal controlListarPersonal=new ControlListarPersonal();
                controlListarPersonal.actualizarTablaPersonal();
            }
        }
        if(e.getSource()==vista.btnSegunUsuario4){
            if(logeado instanceof Propietario){
                ControlListarGimnasios controlListarGimnasios=new ControlListarGimnasios();
                controlListarGimnasios.actualizarTablaGimnasio();
            }
            if(logeado instanceof Personal){
                ControlListarActividad controlListarActividad= new ControlListarActividad();
                controlListarActividad.actualizarTablaActividad();
            }
        }
        if(e.getSource()==vista.btnSegunUsuario5){
            if(logeado instanceof Propietario){
                ControlListarFacturas controlListarFacturas=new ControlListarFacturas();
                controlListarFacturas.actualizarTablaFacturas();
            }
        }
        if(e.getSource()==vista.btnFacturar){
            ControlFactura controlFactura=new ControlFactura();
            controlFactura.actualizarComboBox();
            controlFactura.actualizarDatos();
            controlFactura.actualizarTablaDetalles();
        }
    }
}
