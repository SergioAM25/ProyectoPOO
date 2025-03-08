import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Solicitud {
    private String articulo;
    private int cantidad;
    private String estadoEnvio;

    private static List<Solicitud> solicitudes = new ArrayList<>();

    public Solicitud(String articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.estadoEnvio = "En Proceso";
    }

    public static void registrarSolicitud(String articulo, int cantidad) {
        Solicitud solicitud = new Solicitud(articulo, cantidad);
        solicitudes.add(solicitud);
    }

    public static void mostrarFormularioSolicitud(JFrame mainFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtArticulo = new JTextField();
        JTextField txtCantidad = new JTextField();

        panel.add(new JLabel("Artículo:"));
        panel.add(txtArticulo);
        panel.add(new JLabel("Cantidad:"));
        panel.add(txtCantidad);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Solicitud de Pedido",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String articulo = txtArticulo.getText().trim();
            int cantidad = 0;
            try {
                cantidad = Integer.parseInt(txtCantidad.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (articulo.isEmpty() || cantidad <= 0) {
                JOptionPane.showMessageDialog(mainFrame, "Todos los campos son obligatorios y la cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            registrarSolicitud(articulo, cantidad);
            JOptionPane.showMessageDialog(mainFrame, "Solicitud registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            mostrarSolicitudes(mainFrame);
        }
    }

    public static void mostrarSolicitudes(JFrame mainFrame) {
        String[] columnNames = {"Artículo", "Cantidad", "Estado del Envío"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        solicitudes.add(new Solicitud("Artículo A", 10));
        solicitudes.add(new Solicitud("Artículo B", 5));

        for (Solicitud solicitud : solicitudes) {
            Object[] row = {solicitud.articulo, solicitud.cantidad, solicitud.estadoEnvio};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
        panelTabla.add(scrollPane);

        JOptionPane.showMessageDialog(mainFrame, panelTabla, "Solicitudes Registradas", JOptionPane.INFORMATION_MESSAGE);
    }
}
