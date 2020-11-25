package Server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import rmi.rmi;

public class IPS extends UnicastRemoteObject implements rmi, Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int vacuansDisponibles1;
    private int vacuansDisponibles2;
    private int vacuansDisponibles3;
    static final int PUERTO_RMI = 1099;
    static final String SERVER_HOST_NAME = "java.rmi.server.hostname";
    static final String SERVER_HOST_IP = "localhost";
    static final String RMI_NAME = "RemotoRMI";

    public IPS(int vacuansDisponibles1, int vacuansDisponibles2, int vacuansDisponibles3) throws RemoteException {
        this.vacuansDisponibles1 = vacuansDisponibles1;
        this.vacuansDisponibles2 = vacuansDisponibles2;
        this.vacuansDisponibles3 = vacuansDisponibles3;
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
    public void run() {

    }

    @Override
    public List<Integer> asignarVacuna(int a, int b, int c) throws RemoteException {
        System.out.println("nueva Transaccion");
        System.out.println("llego " + a + " " + b + " " + c);
        System.out.println("Hay " + this.vacuansDisponibles1 + " " + this.vacuansDisponibles2 + " " + this.vacuansDisponibles3);
        
        List<Integer> vacunasDadas = new ArrayList<Integer>();
        int resta;
        if (this.vacuansDisponibles1 >= a) {
            this.vacuansDisponibles1 -= a;
            vacunasDadas.add(0,0);
        } else {
            resta = a - this.vacuansDisponibles1;
            this.vacuansDisponibles1 = 0;
            vacunasDadas.add(0, resta);
        }
        if (this.vacuansDisponibles2 >= b ){
            this.vacuansDisponibles2 -= b;
            vacunasDadas.add(1,0);
        } else {
            resta = b - this.vacuansDisponibles2;
            this.vacuansDisponibles2 = 0;
            vacunasDadas.add(0, resta);
        }
        if (this.vacuansDisponibles3 >= c) {
            this.vacuansDisponibles3 -= c;
            vacunasDadas.add(2,0);
        } else {
            resta = c - this.vacuansDisponibles3;
            this.vacuansDisponibles3 = 0;
            vacunasDadas.add(0, resta);
        }
        System.out.println("se envia " + vacunasDadas.get(0) + " " + vacunasDadas.get(1) + " " + vacunasDadas.get(2));
        return vacunasDadas;
    }

}
