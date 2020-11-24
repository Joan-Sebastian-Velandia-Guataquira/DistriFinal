import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class IPS extends UnicastRemoteObject implements RMI, Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int vacuansDisponibles1;
    private int vacuansDisponibles2;
    private int vacuansDisponibles3;
    static final int PUERTO_RMI = 1099;
    static final String SERVER_HOST_NAME = "java.rmi.server.hostname";
    static final String SERVER_HOST_IP = "25.9.158.124";
    static final String RMI_NAME = "RemotoRMI";

    public IPS(int vacuansDisponibles1, int vacuansDisponibles2, int vacuansDisponibles3) throws RemoteException {
        this.vacuansDisponibles1 = vacuansDisponibles1;
        this.vacuansDisponibles2 = vacuansDisponibles2;
        this.vacuansDisponibles3 = vacuansDisponibles3;
    }

    public void iniciarServer(IPS IPS_Actual) {
        try {
            System.setProperty(SERVER_HOST_NAME, SERVER_HOST_IP);
            System.out.println("Iniciando Server.....");
            Registry registro = LocateRegistry.createRegistry(PUERTO_RMI);
            registro.rebind(RMI_NAME, IPS_Actual);

        } catch (Exception e) {
            System.err.println("Error en iniciarServer() " + e.getMessage());
        }
    }

    @Override
    public int suma(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public void run() // Hilossss
    {

    }

}
