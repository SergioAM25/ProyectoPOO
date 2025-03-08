import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class Inventario {
    private ArrayList<Articulo> listaArticulos;

    public Inventario() {
        listaArticulos = new ArrayList<>();
    }

    public boolean agregarArticulo(int id, String nombre, int cantidad) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getId() == id) {
                return false;
            }
        }
        listaArticulos.add(new Articulo(id, nombre, cantidad));
        return true;
    }

    public void eliminarArticulo(int id) {
        listaArticulos.removeIf(articulo -> articulo.getId() == id);
    }

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }
}

class Articulo {
    private int id;
    private String nombre;
    private int cantidad;

    public Articulo(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}

class GestionInventario extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtId, txtNombre, txtCantidad;
    private Inventario inventario;

    public GestionInventario(Inventario inventario) {
        this.inventario = inventario;

        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Nombre", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelEntrada = new JPanel(new GridLayout(2, 3, 5, 5));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Agregar Artículo"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtCantidad = new JTextField();

        panelEntrada.add(new JLabel("ID:"));
        panelEntrada.add(new JLabel("Nombre:"));
        panelEntrada.add(new JLabel("Cantidad:"));
        panelEntrada.add(txtId);
        panelEntrada.add(txtNombre);
        panelEntrada.add(txtCantidad);

        add(panelEntrada, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnReportes = new JButton("Ver Reportes");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnReportes);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarArticulo());
        btnEliminar.addActionListener(e -> eliminarArticulo());
        btnCancelar.addActionListener(e -> dispose());
        btnReportes.addActionListener(e -> abrirReportes());

        setVisible(true);
    }

    private void agregarArticulo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText().trim();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (inventario.agregarArticulo(id, nombre, cantidad)) {
                modeloTabla.addRow(new Object[]{id, nombre, cantidad});
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "ID duplicado, usa otro ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID y Cantidad deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarArticulo() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            inventario.eliminarArticulo(id);
            modeloTabla.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un artículo para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
    }

    private void abrirReportes() {
        new Reportes();
    }

}