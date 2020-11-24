package GUI;

public class GUI {
    private Login login;
    private Registrar registrar;

    public Login getLoginPanel() {
        return login;
    }

    public void setLoginPanel(Login login) {
        this.login = login;
    }

    public Registrar getRegistrarPanel() {
        return registrar;
    }

    public void setRegistrarPanel(Registrar registrar) {
        this.registrar = registrar;
    }

    public GUI() {
        login = new Login();
        registrar = new Registrar();
    }
    
}
