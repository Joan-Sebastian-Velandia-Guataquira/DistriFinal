package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMI extends Remote{

    public int suma(int a, int b) throws RemoteException;
    public List<Integer> asignarVacuna(Transaccion t )throws RemoteException;
    
}
