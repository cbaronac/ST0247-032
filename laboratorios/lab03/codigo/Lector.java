/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio3;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.util.Pair;

public class Lector {

    /**
     * This method was made for save all the information of a file named
     * "medellin_colombia-grande.txt" it has too a interactive part, where the
     * user give a vertex ID and the method deploys information about it.
     *
     * @return
     */
    public static HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> makeMap() {
        BufferedReader reader = null; // to initialize the buffered reader 
        HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> hashMap = new HashMap(); //initialize the 
        //hash map where everything
        String fileName = "Archivo.txt";                                                           // is going to be stored.
        int cont = 0;
        try {

            reader = new BufferedReader(new FileReader(fileName)); //reinitialize the bufferedreader but with the file we need
            String line = reader.readLine(); //this variable will be each line of the file 

            String vertexComponents[];
            String edgeComponents[];

            boolean change = false;//this variable will determinate if the process would be for a vertex or for a edge

            while (line != null) { // this loop is for roaming throw each line

                if (line.contains("Arcos.")) {// if that specific line contains "aristas" it has to change doing 
                    //vertex for doing edges 
                    change = true;
                    System.out.println("start creation of edges");
                    line = reader.readLine();//jump this line because it isn't going to be a information to store
                }

                if (line.contains("Vertices.")) {// if that specific line contains "vertices" it has to jump that line
                    System.out.println("start creation of vertex");
                    line = reader.readLine();
                }
                if (!change) {//if it hasn't change the way the vertex are being created

                    vertexComponents = line.split(" "); // we separete all data inside that line
                    Vertex vertex = null; // initialize the vertex we're making
                    String completeName = "";// this variable will store the complete name of the vertex, that's the last information on the list

                    if (vertexComponents.length > 3) { // if vertexComponents has more than 4 or equals 4 elements, we need to concatenate 
                        // the name, for make the vertex 

                        for (int i = 3; i < vertexComponents.length; i++) { //this loop concatenate the name information of the vertex
                            completeName += vertexComponents[i] + " ";
                        }

                        vertex = new Vertex(Integer.parseInt(vertexComponents[0]), Double.parseDouble(vertexComponents[1]),
                                Double.parseDouble(vertexComponents[2]), completeName);//create the vertex
                    }
                    if (vertexComponents.length == 3) {// if vertexComponents has 3 elements we have to put the name as unknown
                        vertex = new Vertex(Integer.parseInt(vertexComponents[0]), Double.parseDouble(vertexComponents[1]),
                                Double.parseDouble(vertexComponents[2]));//create the vertex
                    }

                    LinkedList<Edge> list = new LinkedList();//initialize the list of edges that would have each vertex
                    Pair<Vertex, LinkedList<Edge>> pair = new Pair(vertex, list);//make a couple of data between each vertex and his list
                    hashMap.put(vertex.ID, pair);// use the vertex ID for get access to the previous pair.
                    cont++;

                }
                if (change) { // if the method has change to make edges
                    //System.out.println("edgesss");
                    String completeName = "";
                    edgeComponents = line.split(" "); // to separate each component of the line
                    for (int i = 3; i < edgeComponents.length; i++) {// this loop concatenate the name information of the vertex
                        completeName += edgeComponents[i] + " ";
                    }

                    Edge edge = new Edge(Integer.parseInt(edgeComponents[0]), Integer.parseInt(edgeComponents[1]),
                            Double.parseDouble(edgeComponents[2]), completeName);// to create a edge with all the information that we have
                    hashMap.get(edge.ID1).getValue().add(edge);// add that edge to the vertex1 list
                    hashMap.get(edge.ID2).getValue().add(edge);// add that edge to the vertex2 list
                }

                line = reader.readLine();
            }
            return hashMap;

        } catch (IOException e) {
            System.out.println("nothing of that...");
            e.printStackTrace();
        } 
        return null;
    }
    
    public static ArrayList<Integer> getSuccessors(HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> grafo, int verticeID){
        ArrayList<Integer> lista=new ArrayList();
        Pair<Vertex, LinkedList<Edge>> vertice_aristas =grafo.get(verticeID);
        LinkedList <Edge> infoSucesores= vertice_aristas.getValue();
        for(int i=0;i<infoSucesores.size();i++){
            if(infoSucesores.get(i).ID1==verticeID){
                lista.add(infoSucesores.get(i).ID2);
            }else if(infoSucesores.get(i).ID2==verticeID){
                lista.add(infoSucesores.get(i).ID1);
            }
        }
        return lista;
    }
    
    public static double getWeight(HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> grafo,int inicioID, int destinoID){
        Pair<Vertex, LinkedList<Edge>> vertice_aristas =grafo.get(inicioID);
        LinkedList <Edge> infoSucesores= vertice_aristas.getValue();
        for(int i=0;i<infoSucesores.size();i++){
            if(infoSucesores.get(i).ID1==inicioID && infoSucesores.get(i).ID2==destinoID ){
                return infoSucesores.get(i).distance;
            }   
        }
        return 0;
        
    }
}
