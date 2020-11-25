package EPS;

import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.GUI;

public class ControladorEPS implements Runnable {

    private static final byte[] SALT = "SysDistribudos".getBytes();
    private EPS modelo;
    private Persistencia persistencia;
    private GUI gui;
    private static ControladorEPS coord;
    private String SERVER_HOST_NAME = "RemotoRMI";
    private String SERVER_HOST_NAME2 = "RemotoRMI2";
    private String SERVER_HOST_NAME3 = "RemotoRMI3";
    private ArrayList<Integer> vacunasFinales;

    public static void main(String[] args) throws Exception {
        coord = new ControladorEPS();
        coord.iniciarlarPersistencia();
        coord.iniciarlarGUI();
        // coord.iniciarEPS();
    }

    private ArrayList<Integer> generarTransaccion2(ArrayList<Integer> vacunas) {
        try {
            List<Integer> transaccionDevuelta = new ArrayList<Integer>();
            transaccionDevuelta = this.modelo.crearTransaccion(vacunas);
            for (Integer integer : transaccionDevuelta) {
                System.out.println("transaccionDevuelta = " + integer);
            }
            return (ArrayList<Integer>) transaccionDevuelta;
        } catch (RemoteException e) {
            System.err.println("error generarTransaccion: " + e.getMessage());
            return null;
        }
    }

    public void generarTransaccion() {
        ArrayList<Integer> vacunas = persistencia.leerVacunas();
        ArrayList<Integer> vacunasSumadas = new ArrayList<Integer>();
        int typeA = 0;
        int typeB = 0;
        int typeC = 0;
        for (Integer integer : vacunas) {
            System.out.println("Vacunas: " + integer);
            switch (integer) {
                case 1:
                    typeA++;
                    break;
                case 2:
                    typeB++;
                    break;
                case 3:
                    typeC++;
                    break;
                default:
                    break;
            }
        }
        vacunasSumadas.add(0,typeA);
        vacunasSumadas.add(1,typeB);
        vacunasSumadas.add(2,typeC);
        System.out.println("hello");
        gui.Transaccion(vacunasSumadas);

    }

    public void iniciarlarGUI() {
        this.gui = new GUI(this);
        gui.setVisible(true);
    }

    public void ingresarSistemas(String user, String pass) {
        String usuario = user;
        String contra = pass;
        String hash[] = new String[2];
        hash = coord.seguridad(usuario, contra);
        boolean estadoIngresoSistema = this.persistencia.IngresoSistema(hash);

        if (estadoIngresoSistema) {

            System.out.println("Ingreso Satisfactorio");
            JOptionPane.showMessageDialog(null, "Ingreso satisfactoriamente", "Iniciar Sesion",
                    JOptionPane.INFORMATION_MESSAGE);
            generarTransaccion();
            coord.iniciarEPS(usuario, contra);

        } else {

            System.out.println("Datos mal");
            JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneas, intente de nuevo ", "Iniciar Sesion",
                    JOptionPane.WARNING_MESSAGE);
            gui.setVisible(false);
            gui.dispose();
            gui = new GUI(this);

        }
    }

    public void registrarse(String usuario, String password) {

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
                JOptionPane.showMessageDialog(null, "Usuario Registrado satisfactoriamente", "Registrarse",
                        JOptionPane.INFORMATION_MESSAGE);
                generarTransaccion();
                coord.iniciarEPS(usuario, password);
                break;
            case 1:
                System.out.println("ya registrado");
                JOptionPane.showMessageDialog(null, "Usuario ya registrado ", "Registrarse",
                        JOptionPane.WARNING_MESSAGE);
                generarTransaccion();
                coord.iniciarEPS(usuario, password);
                break;
        }
    }

    public String[] seguridad(String usuario, String contra) {
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
        System.out.println("llegue hasta ac�");
        return hash;
    }

    public void iniciarlarPersistencia() {
        persistencia = new Persistencia();
    }

    public void ValidarCambios(List<String> vacunasUp, List<Integer> vacunas) {
        
        int cantA = Integer.valueOf(vacunasUp.get(0));
        int cantB = Integer.valueOf(vacunasUp.get(1));
        int cantC = Integer.valueOf(vacunasUp.get(2));

        int resta;
        if (cantA != vacunas.get(0)) {
            resta = Math.abs(cantA - vacunas.get(0));
            persistencia.restaurarVacunas(resta, "1");
        }
        if (cantA != vacunas.get(1)) {
            resta = Math.abs(cantB - vacunas.get(1));
            persistencia.restaurarVacunas(resta, "2");
        }
        if (cantA != vacunas.get(2)) {
            resta = Math.abs(cantC - vacunas.get(2));
            persistencia.restaurarVacunas(resta, "3");
        }
        this.vacunasFinales = new ArrayList<Integer>();
        this.vacunasFinales.add(0, cantA);
        this.vacunasFinales.add(1, cantB);
        this.vacunasFinales.add(2, cantC);

        System.err.println("valido Cambios");
        iniciarTransacción();
        
    }

    private void iniciarTransacción() {
        if(this.vacunasFinales != null) {
            System.err.println("entra al hilo");
            run();
        }else{
            System.out.println("no entra al hilo ");
        }
    }

    public EPS getModelo() {
        return modelo;
    }

    public void setModelo(EPS model) {
        modelo = model;
    }

    private void iniciarEPS(String usuario, String contra) {
        System.out.println("eps  no creada ");
        modelo = new EPS(usuario, contra);
        System.out.println("eps creada ");
    }

    @Override
    public void run() {
        System.out.println("ingreso a hilo");
        ArrayList<Integer> vacunas = new ArrayList<Integer>();
        if (modelo.connectServer(SERVER_HOST_NAME)) {
            vacunas = this.vacunasFinales;
            vacunas = this.generarTransaccion2(vacunas);
            if (vacunas == null) {
                System.out.println("que esta pasando");
            }
            if (validarTransaccion(vacunas)) {
                
                if (modelo.connectServer(SERVER_HOST_NAME2)) {
                    vacunas = this.generarTransaccion2(vacunas);
                    if (validarTransaccion(vacunas)) {
                        if (modelo.connectServer(SERVER_HOST_NAME3)) {
                            vacunas = this.generarTransaccion2(vacunas);
                        }
                    }
                }

            }else
            {
                System.out.println("retotno falso 1");
            }
        } else if (modelo.connectServer(SERVER_HOST_NAME2)) {
            System.out.println("segundo");
            vacunas = this.generarTransaccion2(null);
            if (validarTransaccion(vacunas)) {
                if (modelo.connectServer(SERVER_HOST_NAME)) {
                    vacunas = this.generarTransaccion2(vacunas);
                    if (validarTransaccion(vacunas)) {
                        if (modelo.connectServer(SERVER_HOST_NAME3)) {
                            vacunas = this.generarTransaccion2(vacunas);
                        }
                    }
                }

            }
        } else if (modelo.connectServer(SERVER_HOST_NAME3)) {
            System.out.println("tercero");
            vacunas = this.generarTransaccion2(null);
            if (validarTransaccion(vacunas)) {
                if (modelo.connectServer(SERVER_HOST_NAME)) {
                    vacunas = this.generarTransaccion2(vacunas);
                    if (validarTransaccion(vacunas)) {
                        if (modelo.connectServer(SERVER_HOST_NAME2)) {
                            vacunas = this.generarTransaccion2(vacunas);
                        }
                    }
                }

            }
        } else {
            System.out.println("No se pudo contectar a ningun servidor");
        }
    }

    private boolean validarTransaccion(ArrayList<Integer> vacunas) {
        System.err.println("revalidando vacunas");
        if (vacunas.get(0) != 0 || vacunas.get(1) != 0 || vacunas.get(2) != 0) {
            return true;
        }
        return false;
    }
}
