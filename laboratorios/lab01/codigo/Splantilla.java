/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio1;

/**
 * Esta clase debe de contener la solucion al problema planteado en el punto 1
 * del laboratorio#1 del curso de estructura de datos y algoritmos II
 *
 * @author Felipe Sosa, Camila Barona
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
//import jdk.internal.net.http.common.Pair;
import javafx.util.Pair;

public class Splantilla {

    /**
     * This method was made for save all the information of a file named "medellin_colombia-grande.txt"
     * it has too a interactive part, where the user give a vertex ID and the method deploys information about it. 
     *
     */
    public static void makeMap() { 
        BufferedReader reader = null; // to initialize the buffered reader 
        HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> hashMap = new HashMap<Integer, Pair<Vertex, LinkedList<Edge>>>(); //initialize the 
                                                                                                                        //hash map where everything
            String fileName = "medellin_colombia-grande.txt";                                                           // is going to be stored.
        try {
            
            reader = new BufferedReader(new FileReader(fileName)); //reinitialize the bufferedreader but with the file we need
            String line = reader.readLine(); //this variable will be each line of the file 

            String vertexComponents[]; 
            String edgeComponents[];
            
            boolean change = false;//this variable will determinate if the process would be for a vertex or for a edge
            
            while (line != null) { // this loop is for roaming throw each line

                if (line.contains("Aristas")) {// if that specific line contains "aristas" it has to change doing vertex for doing edges 
                    change = true;
                    line = reader.readLine();//jump this line because it isn't going to be a information to store
                }
                if (line.contains("Vertices")) {// if that specific line contains "vertices" it has to jump that line
                    line = reader.readLine();
                } else {
                    if (!change) {//if it hasn't change the way the vertex are being created

                        vertexComponents = line.split(" "); // we separete all data inside that line
                        Vertex vertex = null; // initialize the vertex we're making
                        String completeName = "";// this variable will store the complete name of the vertex, that's the last information on the list
                        
                        if (vertexComponents.length > 3) { // if vertexComponents has more than 4 or equals 4 elements, we need to concatenate 
                                                           // the name, for make the vertex 

                            for (int i = 3; i < vertexComponents.length; i++) { //this loop concatenate the name information of the vertex
                                completeName += vertexComponents[i] + " ";
                            }
                            
                            vertex = new Vertex(Integer.valueOf(vertexComponents[0]), Double.parseDouble(vertexComponents[1]),
                                    Double.parseDouble(vertexComponents[2]), completeName);//create the vertex
                        }
                        if (vertexComponents.length == 3) {// if vertexComponents has 3 elements we have to put the name as unknown
                            vertex = new Vertex(Integer.valueOf(vertexComponents[0]), Double.parseDouble(vertexComponents[1]),
                                    Double.parseDouble(vertexComponents[2]));//create the vertex
                        }

                        LinkedList<Edge> list = new LinkedList();//initialize the list of edges that would have each vertex
                        Pair<Vertex, LinkedList<Edge>> pair = new Pair(vertex, list);//make a couple of data between each vertex and his list
                        hashMap.put(vertex.ID, pair);// use the vertex ID for get access to the previous pair.

                    }
                }
                if (change) { // if the method has change to make edges
                    
                    String completeName = ""; 
                    edgeComponents = line.split(" "); // to separate each component of the line
                    for (int i = 3; i < edgeComponents.length; i++) {// this loop concatenate the name information of the vertex
                        completeName += edgeComponents[i] + " ";
                    }

                    Edge edge = new Edge(Integer.valueOf(edgeComponents[0]), Integer.valueOf(edgeComponents[1]),
                            Double.parseDouble(edgeComponents[2]), completeName);// to create a edge with all the information that we have
                    hashMap.get(edge.ID1).getValue().add(edge);// add that edge to the vertex1 list
                    hashMap.get(edge.ID2).getValue().add(edge);// add that edge to the vertex2 list
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("nothing of that...");
            e.printStackTrace();
        } finally {
            try {
                reader.close();//close the buffered reader
                System.out.println("information saved successfully");
                System.out.println("insert a vertex ID:");
                Scanner user=new Scanner(System.in);
                int vertexID=user.nextInt();//get the vertexID from the user information
                Pair pair=hashMap.get(vertexID);
                Vertex vertex=(Vertex)pair.getKey();
                System.out.println("x coordenate: "+vertex.x);
                System.out.println("y coordenate: "+vertex.y);
                System.out.println("name: "+vertex.name);
                
                LinkedList<Edge> list= (LinkedList<Edge>)pair.getValue();
                for(int i=0; i<list.size();i++){ // this loop is for print each edge 
                    System.out.print("edge between ");
                    System.out.print(list.get(i).ID1+" and ");
                    System.out.println(list.get(i).ID2);
                    System.out.println("distance: "+list.get(i).distance);
                    System.out.println("name: "+list.get(i).name);
                }
                
            } catch (IOException e) {
                //System.out.println("nada de eso...");
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo principal del programa
     *
     * @param args un array de argumentos
     */
    public static void main(String[] args) {
        makeMap();
    }
}
