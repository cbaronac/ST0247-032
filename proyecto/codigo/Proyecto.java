/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author Camila Barona y Felipe Sosa
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Reader saveInfo = new Reader();

        HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> map = saveInfo.makeMap();

        //System.out.println(cont);
        System.out.println("information saved successfully");
        System.out.println("insert a vertex ID:");
        Scanner user = new Scanner(System.in);
        int vertexID = user.nextInt();//get the vertexID from the user information
        //Pair<Vertex, LinkedList<Edge>> pair = hashMap.get(vertexID);
        Vertex vertex = map.get(vertexID).getKey();
        System.out.println("x coordenate: " + vertex.x);
        System.out.println("y coordenate: " + vertex.y);
        System.out.println("name: " + vertex.name);

        LinkedList<Edge> list = (LinkedList<Edge>) map.get(vertexID).getValue();
        for (int i = 0; i < list.size(); i++) { // this loop is for print each edge 
            System.out.print("edge between ");
            System.out.print(list.get(i).ID1 + " and ");
            System.out.println(list.get(i).ID2);
            System.out.println("distance: " + list.get(i).distance);

        }
    }

}
