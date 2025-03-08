import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Articulos {

    private static ArrayList<String[]> articulos = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            articulos.add(new String[]{"Artículo" + i, "Descripción " + i});
        }
    }

    public static void mostrarArticulos() {
        JFrame frame = new JFrame("Administrar Artículos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nombre", "Descripción"}, 0);
        JTable table = new JTable(model);
        actualizarTabla(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        JTextField txtNombre = new JTextField(10);
        JTextField txtDescripcion = new JTextField(10);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        panel.add(btnAgregar);
        panel.add(btnEliminar);

        ActionListener agregarArticulo = e -> {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            if (!nombre.isEmpty() && !descripcion.isEmpty()) {
                articulos.add(new String[]{nombre, descripcion});
                actualizarTabla(model);
                txtNombre.setText("");
                txtDescripcion.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Nombre y descripción no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnAgregar.addActionListener(agregarArticulo);

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                articulos.remove(selectedRow);
                actualizarTabla(model);
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un artículo para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (String[] articulo : articulos) {
            model.addRow(articulo);
        }
    }
}
