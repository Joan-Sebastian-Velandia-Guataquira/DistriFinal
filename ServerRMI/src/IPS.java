import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class IPS extends UnicastRemoteObject implements RMI {
    protected IPS() throws RemoteException {
        super();
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "25.9.158.124");
            IPS obj = new IPS();
            /* ServerRMI stub = (ServerRMI) UnicastRemoteObject.exportObject(obj, 0); */

            System.out.println("Iniciando Server");
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("RemotoRMI", obj);

        } catch (Exception e) {
            System.err.println("Error en Server " + e.getMessage());
        }
    }

    @Override
    public int suma(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public ArrayList<Integer> asignarVacunas(Transaccion arg0) {
        return null;
    }
}
