import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Merks and Spen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Inventario inventario = new Inventario();

        String[] opciones = {
                "Usuario", "Inventario", "Administrar artículos",
                "Administrar usuarios", "Solicitar artículos", "Ver Gráficas", "Cerrar sesión"
        };

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, 400, 300);
        int y = 30;
        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.setBounds(100, y, 200, 40);
            panelMenu.add(boton);
            y += 45;

            boton.addActionListener(e -> {
                switch (opcion) {
                    case "Usuario":
                        break;
                    case "Inventario":
                        new GestionInventario(inventario);
                        break;
                    case "Administrar artículos":
                        Articulos.mostrarArticulos();
                        break;
                    case "Administrar usuarios":
                        AdministrarUsuario.mostrarAdministrarUsuario();
                        break;
                    case "Solicitar artículos":
                        Solicitud.mostrarFormularioSolicitud(frame);
                        break;
                    case "Ver Gráficas":
                        new Graficas(inventario);
                        break;
                    case "Cerrar sesión":
                        frame.dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Seleccionaste: " + opcion);
                        break;
                }
            });
        }

        frame.add(panelMenu);
        panelMenu.setVisible(true);

        Usuario.mostrarLogin(frame, panelMenu);
    }
}