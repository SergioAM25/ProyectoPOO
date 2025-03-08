import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graficas extends JFrame {
    private Inventario inventario;

    public Graficas(Inventario inventario) {
        this.inventario = inventario;

        setTitle("Gráficas de Artículos Más Pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelGrafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGrafico(g);
            }
        };

        add(panelGrafico);
        setVisible(true);
    }

    private void dibujarGrafico(Graphics g) {
        ArrayList<Articulo> articulos = inventario.getListaArticulos();
        int x = 50;
        int maxHeight = 200;
        int maxCantidad = articulos.stream().mapToInt(Articulo::getCantidad).max().orElse(1);

        for (Articulo articulo : articulos) {
            int height = (int) (((double) articulo.getCantidad() / maxCantidad) * maxHeight);
            g.setColor(Color.BLUE);
            g.fillRect(x, 250 - height, 50, height);
            g.setColor(Color.BLACK);
            g.drawString(articulo.getNombre(), x, 270);
            x += 60;
        }
    }
}
