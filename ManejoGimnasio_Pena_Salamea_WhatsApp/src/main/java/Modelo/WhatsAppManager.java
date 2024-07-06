package Modelo;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.*;

public class WhatsAppManager implements Serializable {

    public static final String ACCOUNT_SID = "ACb03297207d3e3fcf3c9e4a85679f6737";
    public static final String AUTH_TOKEN = "14b062e4ae27b5d3aa01d2499ea897ca";
    public static final String FROM_PHONE_NUMBER = "+14155238886";

    public void enviarMensajeConContenido(String numeroTelefono, String mensaje, String rutaArchivo) {
        // Inicialización de Twilio con tus credenciales
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Leer el contenido del archivo y enviarlo como parte del mensaje
        String contenidoArchivo = leerContenidoArchivo(rutaArchivo);
            String mensajeCompleto = mensaje + "\n\n" + contenidoArchivo;
            enviarMensajePorWhatsApp(numeroTelefono, mensajeCompleto);
    }

    private String leerContenidoArchivo(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(rutaArchivo)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            contenido= new StringBuilder(" ");
        }
        return contenido.toString();
    }

    private void enviarMensajePorWhatsApp(String numeroTelefono, String mensaje) {
        // Envío del mensaje por WhatsApp usando Twilio API
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:" + numeroTelefono),
                        new com.twilio.type.PhoneNumber("whatsapp:" + FROM_PHONE_NUMBER),
                        mensaje)
                .create();

        // Verificación de estado de envío
        System.out.println("Mensaje enviado correctamente por WhatsApp a " + numeroTelefono +
                ". SID del mensaje: " + message.getSid());
    }
}
