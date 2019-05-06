/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.util.Pair;

/**
 *
 * @author Camila Barona y Felipe Sosa
 */
public class Punto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Punto2 test = new Punto2();
        Pair<String[][], Pair<Integer, Integer>> pair = test.read();
        System.out.println("The shortest path has length " + test.research(pair.getKey(), pair.getValue()));
        // TODO code application logic here
    }

    public Pair<String[][], Pair<Integer, Integer>> read() {
        BufferedReader reader = null;
        String FileName = "Archivo.txt";
        Pair<String[][], Pair<Integer, Integer>> answ = null;
        String[][] matriz = null;
        try {
            reader = new BufferedReader(new FileReader(FileName));
            int escenarios = Integer.parseInt(reader.readLine());
            while (escenarios > 0) {
                String linea = reader.readLine();
                String[] dimensiones = linea.split(" ");
                int dimX = Integer.parseInt(dimensiones[0]), dimY = Integer.parseInt(dimensiones[1]);
                matriz = new String[dimY][dimX];
                String linea2 = reader.readLine();
                String[] coorKaro = linea2.split(" ");
                int coorX = Character.getNumericValue(coorKaro[0].charAt(0)), coorY = Character.getNumericValue(coorKaro[1].charAt(0));
                matriz[coorY - 1][coorX - 1] = "K";
                int numDesRad = Integer.parseInt(reader.readLine());
                while (numDesRad > 0) {
                    String linea3 = reader.readLine();
                    String[] coorRad = linea3.split(" ");
                    int radX = Character.getNumericValue(coorRad[0].charAt(0)), radY = Character.getNumericValue(coorRad[1].charAt(0));
                    matriz[radY - 1][radX - 1] = "R";
                    --numDesRad;
                }
                Pair<Integer, Integer> coordenadas = new Pair(coorY - 1, coorX - 1);
                answ = new Pair(matriz, coordenadas);
                --escenarios;
            }
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    if (matriz[i][j] == null) {
                        matriz[i][j] = "0";
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("nothing of that...");
            e.printStackTrace();
        }
        return answ;
    }

    public int research(String[][] matriz, Pair<Integer, Integer> coorKaro) {
        int cont = 0;
        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz[0].length; ++j) {
                if (!matriz[i][j].equals("K") && !matriz[i][j].equals("R")) {
                    int value = Math.abs(i - coorKaro.getKey()) + Math.abs(j - coorKaro.getValue());
                    matriz[i][j] = Integer.toString(value);
                } else {
                    if (!matriz[i][j].equals("K")) {
                        matriz[i][j] = "V";
                        int value = 0;
                        Pair<Integer, Integer> coorRad = new Pair(i, j);
                        if ((!matriz[i + 1][j].equals("0") || !matriz[i - 1][j].equals("0") || !matriz[i][j + 1].equals("0") || !matriz[i][j - 1].equals("0"))
                                && (i > 0 && i < matriz.length - 1 && i > 0 && i < matriz.length - 1)) {
                            value = Math.min(Integer.parseInt(matriz[i][j - 1]), Math.min(Integer.parseInt(matriz[i + 1][j]),
                                    Math.min(Integer.parseInt(matriz[i - 1][j]), Integer.parseInt(matriz[i][j + 1])))) + 1;
                        }
                        return research(matriz, coorRad) + value;
                    } else {
                        if (cont>0) {
                            return 0;
                        }
                        cont++;
                    }
                }

                /*if (matriz[i][j].length() == 2) {
                    System.out.print(matriz[i][j] + " ");
                } else {
                    System.out.print(matriz[i][j] + "  ");
                }*/
            }
            System.out.println();
        }
        return 0;
    }
}
