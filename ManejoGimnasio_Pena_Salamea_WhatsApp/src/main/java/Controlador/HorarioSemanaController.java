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
    private ManejoActividad manejoActividad;

    public HorarioSemanaController() {
        vista =new HorarioSemanaSwing();
        vista.setVisible(true);
        manejoActividad = ManejoActividad.getInstancia();

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
                    ArrayList<Actividad> actividades = manejoActividad.obtenerActividadesPorDiaYHora(dia, horaInicio);

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
    public static void main(String[] args) {
         ManejoActividad manejoActividad;
        manejoActividad = ManejoActividad.getInstancia();
        SwingUtilities.invokeLater(() -> {
                    Ubicacion ubicacion1 = new Ubicacion("UB001", "Gimnasio Principal", new ArrayList<>());
                    Ubicacion ubicacion2 = new Ubicacion("UB002", "Piscina", new ArrayList<>());

                    // Creación de equipos para las ubicaciones
                    Equipos equiposUb1 = new Equipos("E001", "Máquinas de pesas", "Disponible", new ArrayList<>(), LocalDate.of(2023, 1, 1), LocalDate.of(2023, 6, 1));
                    Equipos equiposUb2 = new Equipos("E002", "Chalecos salvavidas", "Disponible", new ArrayList<>(), LocalDate.of(2023, 1, 1), LocalDate.of(2023, 6, 1));

                    // Agregar equipos a las ubicaciones
                    ubicacion1.getEquipos().add(equiposUb1);
                    ubicacion2.getEquipos().add(equiposUb2);
            Horario horarioGim1 = new Horario(LocalTime.of(8, 0), LocalTime.of(20, 0));
            Gimnasio gimnasio1 = new Gimnasio("Gimnasio 1", "Dirección 1", "123456789", horarioGim1, new ArrayList<>());
            gimnasio1.getUbicaciones().add(ubicacion1);

            Horario horarioGim2 = new Horario(LocalTime.of(9, 0), LocalTime.of(21, 0));
            Gimnasio gimnasio2 = new Gimnasio("Gimnasio 2", "Dirección 2", "987654321", horarioGim2, new ArrayList<>());
            gimnasio2.getUbicaciones().add(ubicacion2);

            // Agregar gimnasios al manejo de gimnasios
            ManejoGimnasio.getInstancia().gimnasios.add(gimnasio1);
            ManejoGimnasio.getInstancia().gimnasios.add(gimnasio2);
                    // Creación de jornada para el personal
                    Jornada jornadaInstructor = new Jornada("Mañana", LocalTime.of(9, 0), LocalTime.of(12, 0));

                    // Creación de actividades
                    Actividad actividad1 = new Actividad("ACT001", "Aeróbicos", "Clase de aeróbicos", 20, 15, "Activo",
                            new Horario(LocalTime.of(9, 0), LocalTime.of(10, 0)), ubicacion1, new Personal("1718137151", "Sebastian", "Pena", "la marin", "0960367911", "Instructor", "password", jornadaInstructor, ""),45,"Lunes");
            Actividad actividad3 = new Actividad("ACT003", "Boxeo", "Clase de aeróbicos", 20, 15, "Activo",
                    new Horario(LocalTime.of(9, 0), LocalTime.of(10, 0)), ubicacion1, new Personal("1718137151", "Sebastian", "Pena", "la marin", "0960367911", "Instructor", "password", jornadaInstructor, ""),45,"Lunes");

                    Actividad actividad2 = new Actividad("ACT002", "Natación", "Clase de natación", 15, 10, "Activo",
                            new Horario(LocalTime.of(11, 0), LocalTime.of(12, 0)), ubicacion2, new Personal("1718137152", "María", "García", "Calle Principal", "0987654321", "Instructor", "password", jornadaInstructor, ""),25,"Viernes");

                    // Mostrar las actividades creadas
            manejoActividad.actividades.add(actividad1);
            manejoActividad.actividades.add(actividad2);
            manejoActividad.actividades.add(actividad3);
                    System.out.println("Actividad 1:");
                    System.out.println(actividad1);
                    System.out.println("\nActividad 2:");
                    System.out.println(actividad2);

            HorarioSemanaController controller = new HorarioSemanaController();
        });
    }
}
