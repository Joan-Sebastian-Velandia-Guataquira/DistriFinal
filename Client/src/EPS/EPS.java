package EPS;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import RMI.RMI;
import RMI.Transaccion;

public class EPS {

    private String username;
    private String password;

    Registry registry;
    RMI interfazRMI;

    private String IP;
    static final int PORT = 1099;

    public boolean connectServer(String server_host_name) {
        boolean transaccionRealizada = false;
        try {
            this.IP = "25.9.158.124";
            this.IP = "localhost";

            registry = LocateRegistry.getRegistry(IP, PORT);
            interfazRMI = (RMI) registry.lookup(server_host_name);

            int suma;
            suma = interfazRMI.suma(8, 5);

            System.out.println("La suma es: " + suma);
            transaccionRealizada = true;
        } catch (Exception e) {
            System.out.println("Error obteniendo IP: " + e.getMessage());
        }
        return transaccionRealizada;
    }

    public String obtenerIP() throws UnknownHostException {
        String localIpAddress = "";

        localIpAddress = InetAddress.getLocalHost().getHostAddress();

        return localIpAddress;
    }

    public ArrayList<Integer> crearTransaccion(ArrayList<Integer> vacunas) throws RemoteException {

        int typeA = vacunas.get(0);
        int typeB = vacunas.get(1);
        int typeC = vacunas.get(2);
        ArrayList<Integer> vacunasTransaccion = new ArrayList<Integer>();
        for (Integer integer : vacunas) {
            System.out.println("Vacunas para enviarTrsaccion " + integer);
        }

        if((typeA + typeB + typeC) >= 10){
            Transaccion nuevaTransaccion = new Transaccion(typeA, typeB, typeC);
            System.out.println("Transaccion enviada " + nuevaTransaccion.getVacA() + " " + nuevaTransaccion.getVacB() + " " + nuevaTransaccion.getVacC());
            vacunasTransaccion = (ArrayList<Integer>) interfazRMI.asignarVacuna(nuevaTransaccion);
            System.out.println("Transaccion llega " + vacunasTransaccion.get(0) + " " + vacunasTransaccion.get(1) + " " + vacunasTransaccion.get(2) );
        }
        return vacunasTransaccion;
	}

    public EPS(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
