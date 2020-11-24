public class ControladorEPS {

    private EPS modelo;
    private Persistencia persistencia;
    private static ControladorEPS coord;

    public static void main(String[] args) throws Exception {
        coord = new ControladorEPS();
        coord.iniciarlarPersistencia();
        coord.iniciarEPS();
    }

    private void iniciarlarPersistencia() {
        persistencia = new Persistencia();
    }

    public void analizarSolicitudes() {
        System.out.println("");

    }
    
    public EPS getModelo() {
        return modelo;
    }

    public void setModelo(EPS model) {
        modelo = model;
    }

    private void iniciarEPS() {
        modelo = new EPS();
        modelo.connectServer();
    }


}
