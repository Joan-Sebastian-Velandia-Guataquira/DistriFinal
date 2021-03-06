import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Persistencia {

    private int lector;

    public Persistencia(int num) {
        this.lector = num;
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
        int contador = 0;
        String input="";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("ServerRMI\\Data\\Inventario.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split("#");
                String part1 = parts[3]; // 123
                if (part1.equals(String.valueOf(this.lector)) || part1.equals("0")) {
                    nuevalinea += parts[0] + "#" + parts[1] + "#" + parts[2] + "#" + String.valueOf(this.lector);
                    input +=nuevalinea+"\r\n";
                    break;
                }
                
                else
                    input += nuevalinea + "\r\n";
                contador++;
            }

            FileOutputStream fileOut = new FileOutputStream("ServerRMI\\Data\\Inventario.txt");
            fileOut.write(input.getBytes());
            fileOut.close();
            StringTokenizer st = new StringTokenizer(linea, "#");
            while (st.hasMoreTokens()) {
                inventario.add(Integer.valueOf(st.nextToken()));
            }
            System.out.println(linea);
        } catch (Exception e) {
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

    public void EscribirFichero() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("c:/prueba.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (Exception e) {
            e.printStackTrace();
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


}
