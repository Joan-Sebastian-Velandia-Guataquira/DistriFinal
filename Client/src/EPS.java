import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EPS {

    private String username;
    private String password;
    
    Registry registry;
    RMI interfazRMI;

    private String IP;
    static final int PORT = 1099;
    static final String SERVER_HOST_NAME = "RemotoRMI";

    public void connectServer() {
        try {
            
            this.IP = obtenerIP();
            
            registry = LocateRegistry.getRegistry(IP, PORT);
            interfazRMI = (RMI) registry.lookup(SERVER_HOST_NAME);

            int suma;
            suma = interfazRMI.suma(8, 5);
            
            System.out.println("La suma es: " + suma);

        } catch (Exception e) {
            System.out.println("Error obteniendo IP: " + e.getMessage());
        }
    }

    public String obtenerIP() throws UnknownHostException {
        String localIpAddress = "";

        localIpAddress = InetAddress.getLocalHost().getHostAddress();

        return localIpAddress;
    }
}
