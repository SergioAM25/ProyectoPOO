import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String tipoUsuario;

    private static int contadorId = 1;
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        registrar("Admin", "admin@gmail.com", "123456", "Administrador");
    }

    public Usuario(String nombre, String correo, String contrasena, String tipoUsuario) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public static void registrar(String nombre, String correo, String contrasena, String tipoUsuario) {
        usuarios.add(new Usuario(nombre, correo, contrasena, tipoUsuario));
    }

    public static boolean iniciarSesion(String correo, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.correo.equals(correo) && usuario.contrasena.equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarLogin(JFrame mainFrame, JPanel panelMenu) {
        JFrame frame = new JFrame("Login - Merks and Spen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 30, 100, 30);
        frame.add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 30, 200, 30);
        frame.add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 80, 100, 30);
        frame.add(lblContrasena);

        JPasswordField txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 80, 200, 30);
        frame.add(txtContrasena);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(150, 130, 200, 30);
        frame.add(btnLogin);

        JButton btnCambiarContrasena = new JButton("Cambiar Contraseña");
        btnCambiarContrasena.setBounds(150, 170, 200, 30);
        frame.add(btnCambiarContrasena);

        btnLogin.addActionListener(e -> {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());

            if (iniciarSesion(correo, contrasena)) {
                JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso.");
                frame.dispose();
                mainFrame.setVisible(true);
                panelMenu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCambiarContrasena.addActionListener(e -> {
            cambiarContrasena();
        });

        frame.setVisible(true);
    }

    public static void cambiarContrasena() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtCorreo = new JTextField();
        JPasswordField txtActual = new JPasswordField();
        JPasswordField txtNueva = new JPasswordField();
        JPasswordField txtConfirmar = new JPasswordField();

        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Contraseña actual:"));
        panel.add(txtActual);
        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(txtNueva);
        panel.add(new JLabel("Confirmar nueva contraseña:"));
        panel.add(txtConfirmar);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cambiar Contraseña",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String correo = txtCorreo.getText().trim();
            String actual = new String(txtActual.getPassword());
            String nueva = new String(txtNueva.getPassword());
            String confirmar = new String(txtConfirmar.getPassword());

            Usuario usuarioEncontrado = null;
            for (Usuario usuario : usuarios) {
                if (usuario.correo.equals(correo) && usuario.contrasena.equals(actual)) {
                    usuarioEncontrado = usuario;
                    break;
                }
            }

            if (usuarioEncontrado == null) {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nueva.isEmpty() || confirmar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos de nueva contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nueva.equals(confirmar)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuarioEncontrado.contrasena = nueva;
            JOptionPane.showMessageDialog(null, "Contraseña actualizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
