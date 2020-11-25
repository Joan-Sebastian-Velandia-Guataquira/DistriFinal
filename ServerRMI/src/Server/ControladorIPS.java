package Server;
import java.util.List;

public class ControladorIPS {

    private IPS modelo;
    private Persistencia persistencia;
    private static ControladorIPS controlador;
    static final int MAXIMO_ID = 2147483647;
    static final int MINIMO_ID = 1;
    private static final int ID = 1;

    public static void main(String[] args) {
        controlador = new ControladorIPS();
        //ID = controlador.crearID();
        controlador.inicializarPersistencia(ID);
    }

    public IPS getModelo() {
        return modelo;
    }

    public void setModelo(IPS modelo) {
        this.modelo = modelo;
    }

    private void inicializarIPS(int cantVacuna1, int cantVacuna2, int cantVacuna3) {
        try {
            this.modelo = new IPS(cantVacuna1, cantVacuna2, cantVacuna3);
        } catch (Exception e) {
            System.err.println("Error inicializando IPS " + e.getMessage());
        }
    }

    public void inicializarPersistencia(int ID) {
        try {
            this.persistencia = new Persistencia(ID);
            List<Integer> disponibilidad = persistencia.inventarioIPS();
            for (Integer integer : disponibilidad) {
                System.out.print(integer + " ");
            }
            controlador.inicializarIPS(disponibilidad.get(0), disponibilidad.get(1), disponibilidad.get(2));
        } catch (Exception e) {
            System.err.println("Error inciializando Persistencia " + e.getMessage());
        }
    }

    public ControladorIPS() {

    }

    public Integer crearID() {

        double num = Math.random() * MAXIMO_ID + MINIMO_ID;
        return (int) num;
    }

}
