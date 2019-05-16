/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package proyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author Camila Barona y Felipe Sosa
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String nameFile1 = "DataSet11.txt";
        String nameFile2 = "DataSet205.txt";
        String[] names = {nameFile1, nameFile2};

        double p1 = 1.1;
        double p2 = 1.2;
        double p3 = 1.3;
        double[] ps = {p1, p2, p3};

        try {
           for (int i = 0; i < names.length; i++) {

                for (int j = 0; j < ps.length; j++) {
                    HashMap<Integer, Pair<Vertex, ArrayList<Edge>>> map = Reader.makeMap(ps[0], names[1]);
                    PriorityQueue<Edge> cola = QueueCreator.makeQ(map);
                    QueueCreator.algorithm(cola, map, ps[0], names[1]);
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
