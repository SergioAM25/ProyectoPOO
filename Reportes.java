import javax.swing.*;
import java.awt.*;

public class Reportes extends JFrame {
    private JComboBox<String> comboTipoReporte;
    private JTextField txtParametro;
    private JButton btnGenerar;

    public Reportes() {
        setTitle("Generar Reportes");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTipo = new JLabel("Tipo de Reporte:");
        comboTipoReporte = new JComboBox<>(new String[]{"Inventario", "Usuarios", "Ventas"});

        JLabel lblParametro = new JLabel("Parámetro de búsqueda:");
        txtParametro = new JTextField();

        btnGenerar = new JButton("Generar Reporte");

        add(lblTipo);
        add(comboTipoReporte);
        add(lblParametro);
        add(txtParametro);
        add(new JLabel());
        add(btnGenerar);

        btnGenerar.addActionListener(e -> generarReporte());

        setVisible(true);
    }

    private void generarReporte() {
        String tipo = (String) comboTipoReporte.getSelectedItem();
        String parametro = txtParametro.getText();

        JOptionPane.showMessageDialog(
                this,
                "Reporte generado:\n" +
                        "Tipo: " + tipo + "\n" +
                        "Filtro: " + parametro,
                "Reporte",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
