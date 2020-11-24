import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import GUI.GUI;

public class ControladorEPS {

    private static final byte[] SALT = "SysDistribudos".getBytes();
    private EPS modelo;
    private Persistencia persistencia;
    private GUI gui;
    private static ControladorEPS coord;

    public static void main(String[] args) throws Exception {
        coord = new ControladorEPS();
        coord.iniciarlarPersistencia();
        coord.iniciarlarGUI();
        coord.ingresarSistemas();
        coord.iniciarEPS();
    }

    private void iniciarlarGUI() {
        this.gui = new GUI();
        String usuario = this.gui.getRegistrarPanel().getUsuario().toString();
        String password = this.gui.getRegistrarPanel().getPass().toString();
        coord.registrarse(usuario, password);
        System.out.println("usuario " + usuario);
        System.out.println("password " + password);
    }

    private void ingresarSistemas() {
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario");
        String contra = JOptionPane.showInputDialog("Ingrese su contraseña");
        String hash[] = new String[2];
        hash = coord.seguridad(usuario, contra);
        boolean estadoIngresoSistema = this.persistencia.IngresoSistema(hash);

        if (estadoIngresoSistema) {

            System.out.println("Ingreso Satisfactorio");
            JOptionPane.showMessageDialog(null, "Ingreso satisfactoriamente", "Iniciar Sesion",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {

            System.out.println("Datos mal");
            JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneas, intente de nuevo ", "Iniciar Sesion",
                    JOptionPane.WARNING_MESSAGE);

        }
    }

    private void registrarse(String usuario, String password) {

        /*
         * String usuario = JOptionPane.showInputDialog("Ingrese su usuario"); String
         * contra = JOptionPane.showInputDialog("Ingrese su contraseña");
         */
        String hash[] = new String[2];
        hash = coord.seguridad(usuario, password);
        int estadoRegistro = this.persistencia.registrarse(hash);

        switch (estadoRegistro) {
            case -1:
                System.out.println("error registrando");
                JOptionPane.showMessageDialog(null,
                        "Hubo un error y no se pudo registrar su usuario, intente más tarde ", "Registrarse",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case 0:
                System.out.println("registrado");
                JOptionPane.showMessageDialog(null, "Usuario Registrado satisfactoriamente", "Registrarses",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                System.out.println("ya registrado");
                JOptionPane.showMessageDialog(null, "Usuario ya registrado ", "Registrarse",
                        JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    private String[] seguridad(String usuario, String contra) {
        String hash[] = new String[2];
        String generatedPassword = null;
        String hashActual;
        try {
            for (int j = 0; j < 2; j++) {
                if (j == 0)
                    hashActual = usuario;
                else
                    hashActual = contra;
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(SALT);
                byte[] bytes = md.digest(hashActual.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
                hash[j] = generatedPassword;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private void iniciarlarPersistencia() {
        persistencia = new Persistencia();
    }

    public void analizarSolicitudes() {
        System.out.println("");

    }

    public EPS getModelo() {
        return modelo;
    }

    public void setModelo(EPS model) {
        modelo = model;
    }

    private void iniciarEPS() {
        modelo = new EPS();

        modelo.connectServer();
    }

}
