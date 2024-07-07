package Vista;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class HorarioSemanaSwing extends JFrame {

    private JTable tablaHorario;
    private JButton btnBuscar;
    private JTextArea textAreaActividades;

    public HorarioSemanaSwing() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Horario Semanal");
        setLayout(new BorderLayout());

        // Panel superior para el botón de búsqueda
        JPanel panelSuperior = new JPanel(new FlowLayout());
        btnBuscar = new JButton("Buscar");
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para la tabla de horarios
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Tabla para mostrar el horario de la semana
        String[] diasSemana = {"Horas", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        Object[][] data = new Object[24][diasSemana.length]; // Datos para la tabla, filas x columnas

        // Inicializar datos con horas del día como filas y días de la semana como columnas
        for (int i = 0; i < 24; i++) {
            data[i][0] = String.format("%02d:00", i); // Hora del día
            for (int j = 1; j < diasSemana.length; j++) {
                data[i][j] = ""; // Inicializar sin datos
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, diasSemana);
        tablaHorario = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int preferredHeight = Math.max(component.getPreferredSize().height, getRowHeight(row));
                setRowHeight(row, preferredHeight);
                return component;
            }
        };

        tablaHorario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Para evitar que las columnas se redimensionen automáticamente

        // Ajustar ancho predeterminado de las columnas
        int columnWidth = 150; // Ancho predeterminado para las columnas
        for (int i = 0; i < tablaHorario.getColumnCount(); i++) {
            TableColumn column = tablaHorario.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidth);
        }

        // Configurar renderizador de celdas para ajuste automático del tamaño
        MultiLineTableCellRenderer cellRenderer = new MultiLineTableCellRenderer();
        for (int i = 0; i < tablaHorario.getColumnCount(); i++) {
            tablaHorario.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Panel con scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaHorario);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Mostrar siempre la barra vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Mostrar siempre la barra horizontal

        panelCentral.add(scrollPane, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // Área de texto para mostrar las actividades
        textAreaActividades = new JTextArea(20, 50);
        textAreaActividades.setEditable(false);
        JScrollPane scrollPaneActividades = new JScrollPane(textAreaActividades);
        add(scrollPaneActividades, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public JTable getTablaHorario() {
        return tablaHorario;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextArea getTextAreaActividades() {
        return textAreaActividades;
    }

    public void mostrarActividades(String[] actividades) {
        textAreaActividades.setText("");
        for (String actividad : actividades) {
            textAreaActividades.append(actividad + "\n");
        }
    }

    // Renderizador de celdas que ajusta el tamaño automáticamente para texto de varias líneas
    private static class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setPreferredSize(null);
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
}
