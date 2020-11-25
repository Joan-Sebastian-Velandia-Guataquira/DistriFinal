import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class IPS extends UnicastRemoteObject implements RMI.RMI, Runnable {

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
        this.setVacuansDisponibles1(vacuansDisponibles1);
        this.setVacuansDisponibles2(vacuansDisponibles2);
        this.setVacuansDisponibles3(vacuansDisponibles3);
    }

    public int getVacuansDisponibles2() {
        return vacuansDisponibles2;
    }

    public void setVacuansDisponibles2(int vacuansDisponibles2) {
        this.vacuansDisponibles2 = vacuansDisponibles2;
    }

    public int getVacuansDisponibles1() {
        return vacuansDisponibles1;
    }

    public void setVacuansDisponibles1(int vacuansDisponibles1) {
        this.vacuansDisponibles1 = vacuansDisponibles1;
    }

    public int getVacuansDisponibles3() {
        return vacuansDisponibles3;
    }

    public void setVacuansDisponibles3(int vacuansDisponibles3) {
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

    @Override
    public List<Integer> asignarVacuna(RMI.Transaccion arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
