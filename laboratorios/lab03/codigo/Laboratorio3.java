/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.util.Pair;
import java.util.ArrayDeque;

/**
 *
 * @author pipe_sosa
 */
public class Laboratorio3 {
    
    static ArrayDeque<Pair<Integer,Double>> cola = new ArrayDeque();

    private static double[] llenarInfinitos(int tam) {
        double[] a = new double[tam];
        Arrays.fill(a, Integer.MAX_VALUE);
        a[0] = 0;
        return a;
    }

    public static boolean punto1Aux(HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> grafo, int origenID, int destinoID) {

        boolean[] visitados = new boolean[grafo.size()];
        return punto1(grafo, origenID, destinoID, visitados);
        

    }

    public static boolean punto1(HashMap<Integer, Pair<Vertex, LinkedList<Edge>>> grafo, int origenID, int destinoID, boolean[] visitados) {
        
        Lector info = new Lector();
        visitados[origenID] = true;
         ArrayDeque<Pair<Integer,Double>> colaAux = new ArrayDeque();
        if (origenID == destinoID) {
            return true;
        } else {
            ArrayList<Integer> sucesores = info.getSuccessors(grafo, origenID);
            for (int i = 0; i < sucesores.size(); i++) {
                if (!visitados[i]) {
                    visitados[i]=true;
                    Pair pareja= new Pair(sucesores.get(i),info.getWeight(grafo,origenID,sucesores.get(i)));
                    cola.add(pareja);
                    if (punto1(grafo, sucesores.get(i),destinoID,visitados)) {
                        if(reemplazarCola(colaAux)){
                            return true;
                        }else{
                            colaAux.clear();
                        }
                    }
                }
            }

        }
        return false;
    }
    
    public static boolean reemplazarCola( ArrayDeque<Pair<Integer,Double>> colaAux ){
        if(cola.isEmpty()&& !colaAux.isEmpty()){
            cola=colaAux;
        }else {
            double distance=0.0;
            double distanceAux=0.0;
           
            while(!cola.isEmpty()){
                distance+=cola.poll().getValue();
            }
            while(!colaAux.isEmpty()){
                distanceAux+=colaAux.poll().getValue();
            }
            if(distanceAux<distance){
                cola=colaAux;
                return true;
            }else{
                colaAux.clear();
            }
        }
        
        return false;
    
    }
    
    

    public static void punto2(int n) {
        punto2(n, new int[n], 0);
    }

    public static boolean punto2(int n, int[] tablero, int col) {

        if (col == n) {
            imprimirTablero(tablero);
            return true;
        } else {
            for (int i = 0; i < n; i++) {
                tablero[col] = i;
                if (noSeAtacan(tablero, col)) {
                    if (punto2(n, tablero, col + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static void imprimirTablero(int[] tablero) {
        int n = tablero.length;
        System.out.print("    ");
        for (int i = 0; i < n; ++i) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < n; ++i) {
            System.out.print(i + "   ");
            for (int j = 0; j < n; ++j) {
                System.out.print((tablero[i] == j ? "Q" : "#") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean noSeAtacan(int[] tablero, int elI) {
        for (int i = 0; i < elI - 1; i++) {
            for (int j = i + 1; j < elI; j++) {
                if (Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j) || tablero[i] == tablero[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
