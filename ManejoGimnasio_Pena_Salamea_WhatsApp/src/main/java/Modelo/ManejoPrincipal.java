package Modelo;

import java.io.*;

public class ManejoPrincipal implements Serializable{
    private ManejoMiembros manejoMiembros;
    private ManejoPersonal manejoPersonal;
    private ManejoJornada manejoJornada;
    private ManejoGimnasio manejoGimnasio;
    private ManejoActividad manejoActividad;
    private ManejoFacturas manejoFacturas;
    private Verificacion verificacion;
    private static ManejoPrincipal instancia;
    Propietario duenio= new Propietario("0150097954","Xavier","Peña","Av. Don Bosco","0962833533","prueba1234","20191712561","imagenes/propietario.jpg");
    private ManejoPrincipal() {
        manejoMiembros = ManejoMiembros.getInstancia();
        manejoPersonal = ManejoPersonal.getInstancia();
        manejoJornada = ManejoJornada.getInstancia();
        manejoGimnasio = ManejoGimnasio.getInstancia();
        manejoActividad = ManejoActividad.getInstancia();
        manejoFacturas= ManejoFacturas.getInstancia();
        verificacion= Verificacion.getInstancia();
    }

    public static ManejoPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ManejoPrincipal();
        }
        return instancia;
    }

    public ManejoMiembros getManejoMiembros() {
        return manejoMiembros;
    }

    public ManejoPersonal getManejoPersonal() {
        return manejoPersonal;
    }
    public ManejoJornada getManejoJornada(){
        return manejoJornada;
    }
    public ManejoGimnasio getManejoGimnasio(){
        return manejoGimnasio;
    }
    public ManejoActividad getManejoActividad(){
        return manejoActividad;
    }
    public ManejoFacturas getManejoFacturas(){
        return manejoFacturas;
    }
    public Verificacion getVerificacion(){
        return verificacion;
    }
    public Propietario iniciarSesionPropietario(String cedula, String contrasenia) {
        if (duenio.getCedula().equals(cedula) && duenio.getContrasenia().equals(contrasenia)) {
            return duenio;
        } else {
            return null;
        }
    }
    public void guardarDatos(String nombreArchivo) {
        try (FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo);
             ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida)) {
            flujoSalida.writeObject(manejoMiembros);
            flujoSalida.writeObject(manejoPersonal);
            flujoSalida.writeObject(manejoJornada);
            flujoSalida.writeObject(manejoGimnasio);
            flujoSalida.writeObject(manejoActividad);
            flujoSalida.writeObject(manejoFacturas);
            flujoSalida.close();
            archivoSalida.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    public void cargarDatos(String nombreArchivo) {
        try (FileInputStream archivoEntrada = new FileInputStream(nombreArchivo);
             ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada)) {
            manejoMiembros = (ManejoMiembros) flujoEntrada.readObject();
            manejoPersonal = (ManejoPersonal) flujoEntrada.readObject();
            manejoJornada = (ManejoJornada) flujoEntrada.readObject();
            manejoGimnasio = (ManejoGimnasio) flujoEntrada.readObject();
            manejoActividad = (ManejoActividad) flujoEntrada.readObject();
            manejoFacturas = (ManejoFacturas) flujoEntrada.readObject();
            archivoEntrada.close();
            flujoEntrada.close();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
