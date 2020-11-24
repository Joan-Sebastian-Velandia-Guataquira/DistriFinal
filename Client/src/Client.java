import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectServer();
    }

    private void connectServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
            RMI inter = (RMI) registry.lookup("RemotoRMI");
            int suma;
            suma = inter.suma(8, 5);
            System.out.println("La suma es: " + suma);
            
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }
}
