package EPS;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        boolean transaccionRealizada= false;
        try {            
            this.IP = obtenerIP();
            
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

	public ArrayList<Integer> crearTransaccion(ArrayList<Integer> vacunas) {
        int vac1 = 0;
        int vac2 = 0;
        int vac3 = 0;
        ArrayList<Integer> vacunasTransaccion = new ArrayList<Integer>();
        for(int i = 0 ; i < vacunas.size(); i++){
            switch(vacunas.get(i)){
                case 1:
                    vac1++;
                break;
                case 2:
                    vac2++;
                break;
                case 3:
                    vac3++;
                break;
            }
        }
        if((vac1+vac2+vac3) <= 10){
            Transaccion nuevaTransaccion = new Transaccion(vac1,vac2,vac3);
         vacunasTransaccion = (ArrayList<Integer>) interfazRMI.asignarVacuna(nuevaTransaccion);
        }
        return vacunasTransaccion;
	}

    public EPS(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
