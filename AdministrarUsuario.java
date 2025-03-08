import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdministrarUsuario {

    private static ArrayList<String[]> usuarios = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            usuarios.add(new String[]{"Usuario" + i, "usuario" + i + "@ejemplo.com"});
        }
    }

    public static void mostrarAdministrarUsuario() {
        JFrame frame = new JFrame("Administrar Usuarios");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nombre", "Correo"}, 0);
        JTable table = new JTable(model);
        actualizarTabla(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        JTextField txtNombre = new JTextField(10);
        JTextField txtCorreo = new JTextField(10);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        panel.add(btnRegistrar);
        panel.add(btnAgregar);
        panel.add(btnEliminar);

        ActionListener agregarUsuario = e -> {
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            if (!nombre.isEmpty() && !correo.isEmpty()) {
                usuarios.add(new String[]{nombre, correo});
                actualizarTabla(model);
                txtNombre.setText("");
                txtCorreo.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Nombre y correo no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnRegistrar.addActionListener(agregarUsuario);
        btnAgregar.addActionListener(agregarUsuario);

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                usuarios.remove(selectedRow);
                actualizarTabla(model);
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (String[] usuario : usuarios) {
            model.addRow(usuario);
        }
    }
}
