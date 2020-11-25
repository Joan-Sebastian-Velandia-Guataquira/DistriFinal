package EPS;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Persistencia {

    private static final String DELIMITER = "#";
    private static final int REGISTER = 0;
    private static final int REGISTERED = 1;
    private static final int UNREGISTER = -1;
    private int lector;

    public Persistencia() {
    }

    public int getLector() {
        return lector;
    }

    public void setLector(int lector) {
        this.lector = lector;
    }

    public List<Integer> inventarioIPS() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<Integer> inventario = new ArrayList<Integer>();
        String nuevalinea = "";
        String input = "";
        boolean encontrado = false;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(".\\Data\\Inventario.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(DELIMITER);
                String part1 = parts[3]; // 123
                if (!encontrado && (part1.equals(String.valueOf(this.lector)) || part1.equals("0"))) {
                    nuevalinea += parts[0] + DELIMITER + parts[1] + DELIMITER + parts[2] + DELIMITER
                            + String.valueOf(this.lector);
                    input += nuevalinea + "\r\n";
                    encontrado = true;
                } else {
                    input += linea + "\r\n";
                }
            }

            FileOutputStream fileOut = new FileOutputStream(".\\Data\\Inventario.txt");
            fileOut.write(input.getBytes());
            fileOut.close();

            StringTokenizer st = new StringTokenizer(nuevalinea, "#");

            while (st.hasMoreTokens()) {
                inventario.add(Integer.valueOf(st.nextToken()));
            }

        } catch (Exception e) {
            System.err.println("Error en inventarioIPS(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return inventario;

    }

    public boolean EscribirFicheroUsuarios(String data) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean registrado = false;
        try {
            fichero = new FileWriter(".\\Data\\Usuarios.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(data);
            registrado = true;

        } catch (Exception e) {
            registrado = false;
            System.err.println("Error creating usuario file " + e.getMessage());
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                System.err.println("Error closing contra file " + e2.getMessage());
            }
        }
        return registrado;
    }

    public int registrarse(String[] hash) {
        String username = hash[0];
        String password = hash[1];
        int success = 0;

        if (buscarEnFicheroUsuario(username) == 0) {
            if (EscribirFicheroUsuarios(username) && EscribirFicheroContra(password)) {
                success = REGISTER;
            } else {
                success = UNREGISTER;
            }
        } else {
            success = REGISTERED;
        }
        return success;
    }

    private int buscarEnFicheroUsuario(String username) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        int usuarioEncontrado = 0;

        archivo = new File(".\\Data\\Usuarios.txt");
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int cont = 0;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(username)) {
                    usuarioEncontrado = cont;
                    break;
                }
                cont++;
            }
        } catch (FileNotFoundException e) {
            usuarioEncontrado = -1;
            System.err.println("Error buscando usuario " + e.getMessage());
        } catch (IOException e) {
            usuarioEncontrado = -1;
            System.err.println("Error buscando usuario while " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                usuarioEncontrado = -1;
                System.err.println("Error cerrando arhcivo buscando usuario while " + e2.getMessage());
            }
        }
        return usuarioEncontrado;
    }

    private boolean EscribirFicheroContra(String password) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean registrado = false;
        try {
            fichero = new FileWriter(".\\Data\\Contra.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(password);
            registrado = true;
        } catch (Exception e) {
            System.err.println("Error creating contra file " + e.getMessage());
            registrado = false;
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                System.err.println("Error closing contra file " + e2.getMessage());
            }
        }
        return registrado;
    }

    public boolean IngresoSistema(String[] hash) {
        String username = hash[0];
        String password = hash[1];

        System.out.println("username " + username);
        System.out.println("password " + password);
        boolean success = true;

        int posusuario = buscarEnFicheroUsuario(username);
        System.out.println("posusuario " + posusuario);
        if (posusuario != -1) {
            System.out.println("posusuario " + posusuario);
            if (buscarEnFicheroContra(posusuario, password)) {
                success = true;
            } else {
                success = false;
            }
        } else {
            success = false;
        }
        return success;
    }

    private boolean buscarEnFicheroContra(int posusuario, String password) {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        boolean contraCorrect = false;

        archivo = new File(".\\Data\\Contra.txt");
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = "";

            for (int i = 0; i <= posusuario; i++) {
                linea = br.readLine();
            }
            //System.out.println("linea " + linea);
            if (password.equals(linea)) {
                contraCorrect = true;
            } else {
                contraCorrect = false;
            }
        } catch (FileNotFoundException e) {
            contraCorrect = false;
            System.err.println("Error buscando usuario " + e.getMessage());
        } catch (IOException e) {
            contraCorrect = false;
            System.err.println("Error buscando usuario while " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                contraCorrect = false;
                System.err.println("Error cerrando arhcivo buscando usuario while " + e2.getMessage());
            }
        }
        return contraCorrect;

    }

}
