package Controlador;
import Modelo.*;
import Vista.HorarioSemanaSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HorarioSemanaController {

    private HorarioSemanaSwing vista;
    private ManejoPrincipal manejoPrincipal;

    public HorarioSemanaController() {
        vista =new HorarioSemanaSwing();
        vista.setVisible(true);
        manejoPrincipal=ManejoPrincipal.getInstancia();

        // Configurar acciones de los componentes en la vista
        this.vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActividades();
            }
        });
    }

    private void buscarActividades() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaHorario().getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            String horaInicioStr = (String) model.getValueAt(row, 0);
            if (horaInicioStr != null && !horaInicioStr.isEmpty()) {
                LocalTime horaInicio = LocalTime.parse(horaInicioStr);
                for (int col = 1; col < colCount; col++) { // Comenzar desde 1 para omitir la columna de horas
                    String dia = model.getColumnName(col);
                    ArrayList<Actividad> actividades = manejoPrincipal.getManejoActividad().obtenerActividadesPorDiaYHora(dia, horaInicio);

                    // Construir una cadena con las actividades encontradas
                    StringBuilder actividadesStr = new StringBuilder();
                    for (Actividad actividad : actividades) {
                        if (actividadesStr.length() > 0) {
                            actividadesStr.append("\n");
                        }
                        Ubicacion ubicacion = actividad.getUbicacion();
                        Gimnasio gimnasio = ManejoGimnasio.getInstancia().buscarGimnasioPorUbicacion(ubicacion.getCodigo());
                        actividadesStr.append("Actividad: ").append(actividad.getNombre()).append("\n");
                        if (gimnasio != null) {
                            actividadesStr.append(", Gimnasio: ").append(gimnasio.getNombre()).append("\n");
                        } else {
                            actividadesStr.append(", Gimnasio: No encontrado");
                        }
                               actividadesStr.append(", Ubicación: ").append(ubicacion.getNombre()).append("\n");
                    }

                    // Actualizar la celda de la tabla con las actividades encontradas
                    model.setValueAt(actividadesStr.toString(), row, col);
                }
            }
        }
    }
}
