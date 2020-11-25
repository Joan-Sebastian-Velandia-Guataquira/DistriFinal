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

    private ArrayList<Integer> generarTransaccion(ArrayList<Integer> vacunas) {

        try {
            return this.modelo.crearTransaccion(vacunas);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
 
 public void generarTransaccion(){
     ArrayList<Integer> vacunas = persistencia.leerVacunas(); 
     System.out.println("hello");
	 gui.Transaccion(vacunas);
	 
 }

    public void iniciarlarGUI() {
        this.gui = new GUI(this);
        gui.setVisible(true);
    }

    public void ingresarSistemas(String user, String pass) {
        String usuario = user;
        String contra = pass;
        String hash[] = new String[2];
        hash = this.coord.seguridad(usuario, contra);
        boolean estadoIngresoSistema = this.persistencia.IngresoSistema(hash);

        if (estadoIngresoSistema) {

            System.out.println("Ingreso Satisfactorio");
            JOptionPane.showMessageDialog(null, "Ingreso satisfactoriamente", "Iniciar Sesion",
                    JOptionPane.INFORMATION_MESSAGE);
                    coord.iniciarEPS(usuario, contra);
                    generarTransaccion();	

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

    public  void iniciarlarPersistencia() {
        persistencia = new Persistencia();
    }

    public void ValidarCambios(List<String> vacunasUp, List<Integer> vacunas) {
    	int cantA = Integer.valueOf(vacunasUp.get(0));
    	int cantB = Integer.valueOf(vacunasUp.get(1));
    	int cantC = Integer.valueOf(vacunasUp.get(2));
    	
    	int resta;
    	if(cantA != vacunas.get(0)) {
    		resta =  Math.abs( cantA - vacunas.get(0));
    		persistencia.restaurarVacunas(resta, "1");
    	}
    	if(cantA != vacunas.get(1)) {
    		resta =  Math.abs( cantB - vacunas.get(1));
    		persistencia.restaurarVacunas(resta, "1");
    	}
    	if(cantA != vacunas.get(2)) {
    		resta =  Math.abs( cantC - vacunas.get(2));
    		persistencia.restaurarVacunas(resta, "1");
    	}
    	this.vacunasFinales = new ArrayList<Integer>();
    	this.vacunasFinales.add(0,cantA);
    	this.vacunasFinales.add(1,cantB);
    	this.vacunasFinales.add(2,cantC);
    }

    public EPS getModelo() {
        return modelo;
    }

    public void setModelo(EPS model) {
        modelo = model;
    }

    private void iniciarEPS(String usuario, String contra) {
        modelo = new EPS(usuario, contra);
        run();
    }

	@Override
	public void run() {
        ArrayList<Integer> vacunas =  new ArrayList<Integer>(); 
        if(modelo.connectServer(SERVER_HOST_NAME)){
            vacunas = this.vacunasFinales;
            if(validarTransaccion(vacunas)){
                if(modelo.connectServer(SERVER_HOST_NAME2)){
                    vacunas = this.generarTransaccion(vacunas);
                    if(validarTransaccion(vacunas)){
                        if(modelo.connectServer(SERVER_HOST_NAME3)){
                            vacunas = this.generarTransaccion(vacunas);
                        }                
                    }
                }
                
            }
         }else if(modelo.connectServer(SERVER_HOST_NAME2)){
            vacunas = this.generarTransaccion(null);
            if(validarTransaccion(vacunas)){
                if(modelo.connectServer(SERVER_HOST_NAME)){
                    vacunas = this.generarTransaccion(vacunas);
                    if(validarTransaccion(vacunas)){
                        if(modelo.connectServer(SERVER_HOST_NAME3)){
                            vacunas = this.generarTransaccion(vacunas);
                        }                
                    }
                }
                
            }
         }else if(modelo.connectServer(SERVER_HOST_NAME3)){
            vacunas = this.generarTransaccion(null);
            if(validarTransaccion(vacunas)){
                if(modelo.connectServer(SERVER_HOST_NAME)){
                    vacunas = this.generarTransaccion(vacunas);
                    if(validarTransaccion(vacunas)){
                        if(modelo.connectServer(SERVER_HOST_NAME2)){
                            vacunas = this.generarTransaccion(vacunas);
                        }                
                    }
                }
                
            }
         }else{
             System.out.println("No se pudo contectar a ningun conectar");
         }
    }
	
	 private boolean validarTransaccion(ArrayList<Integer> vacunas) {
	        if(vacunas.get(0) != 0 || vacunas.get(1) != 0  || vacunas.get(2) != 0){
	            return true;
	        }
	        return false;
	    }
}
	
		
	

