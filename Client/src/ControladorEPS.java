import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.swing.JOptionPane;

public class ControladorEPS {

    private static final byte[] SALT = "SysDistribudos".getBytes();
    private EPS modelo;
    private Persistencia persistencia;
    private static ControladorEPS coord;

    public static void main(String[] args) throws Exception {
        coord = new ControladorEPS();
        coord.iniciarlarPersistencia();
        coord.registrarse();
        coord.iniciarEPS();
    }

    private void registrarse() {

        String usuario = JOptionPane.showInputDialog("Ingrese su usuario");
        String contra = JOptionPane.showInputDialog("Ingrese su contraseña");
        String hash[] = new String[2];
        hash = coord.seguridad(usuario, contra);
        this.persistencia.registrarse(hash);
    }

    private String[] seguridad(String usuario, String contra) {
        String hash[] =  new String[2];
        String generatedPassword = null;
        String hashActual;
        try {
            for(int j = 0; j < 2; j++)
            {
                if(j == 0)
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
