import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Persistencia {

    private static final String DELIMITER = "#";
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

    public void EscribirFicheroUsuarios(String data) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(".\\Data\\Usuarios.txt");
            pw = new PrintWriter(fichero);
            pw.println(data);

        } catch (Exception e) {
            System.err.println("Error creating usuario file " + e.getMessage());
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void registrarse(String[] hash) {
        String username = hash[0];
        String password = hash[1];
        String data = username + DELIMITER + password;
        EscribirFicheroUsuarios(data);
    }

}
