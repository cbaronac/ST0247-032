/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author pipe_sosa
 */
class Taller07 {

    // determina el camino mas corto desde inicio a todos los demas
    public void dijkstra(Grafo g, int inicio) {
        int[] tabla = llenarInfinitos(g.size());
        int actual = inicio;
        boolean[] visitados = new boolean[g.size()];
        for (int i = 0; i < g.size(); i++) {
            actual = caminoMasPequeño(g, visitados, tabla);
            visitados[actual] = true;

        }

    }

    private static int[] llenarInfinitos(int tam) {
        int[] a = new int[tam];
        Arrays.fill(a, Integer.MAX_VALUE);
        a[0] = 0;
        return a;
    }

    private static int caminoMasPequeño(Grafo g, boolean[] visitados, int[] tabla) {
        int aux = Integer.MAX_VALUE;
        for (int i = 0; i < tabla.length; i++) {
            if (!visitados[i] && tabla[i] < aux) {
                aux = tabla[i];
            }
        }
        return aux;
    }

    private static void actualizarPesos(Grafo g, int actual, int tabla[]) {
        if (tabla[actual] != Integer.MAX_VALUE) {
            ArrayList<Integer> sucesores = g.getSuccessors(actual);
            for (int i = 0; i < sucesores.size(); i++) {
                if (actual != i && g.getWeight(actual, i) != Integer.MAX_VALUE && g.getWeight(actual, i) + tabla[actual] < tabla[i]) {
                    tabla[i] = tabla[actual] + g.getWeight(actual, i);
                }

            }
        }

    }

    public static int[] prim(Grafo g, int v) {
        int [] respuesta=llenarInfinitos(g.size());
        HashMap <Integer,Pair<Integer,Integer>> mapaPrioridad=new HashMap();
        PriorityQueue <Integer> colaPrioridad=new PriorityQueue();
        int actual=v;
        boolean [] visitados=new boolean[g.size()];
        visitados[actual]=true;
        int cont=0;
        do{
            for (int sucesor : g.getSuccessors(actual)){
                Pair<Integer,Integer> vertices=new Pair(actual, sucesor);
                System.out.print("("+vertices.getKey()+", "+ vertices.getValue()+") .. ");
                //System.out.println(vertices.getValue());
                int peso= g.getWeight(vertices.getKey(), vertices.getValue());
                System.out.println(peso);
                //Pair<Pair<Integer,Integer>,Integer> verticesPeso=new Pair(vertices,peso);
                mapaPrioridad.put(peso,vertices);
                colaPrioridad.add(peso);
                
                
            }
            actual=mapaPrioridad.get(colaPrioridad.peek()).getValue();
            respuesta[actual]=colaPrioridad.poll();
            cont+=1;
        }while(cont!=g.size());
        
        return respuesta;
    }

    public static void main(String[] args) {
        GrafoAL g1 = new GrafoAL(5);
        g1.addArc(0, 1, 5);
        g1.addArc(0, 2, 4);
        g1.addArc(0, 3, 8);
        g1.addArc(1, 3, 2);
        g1.addArc(2, 3, 3);
        g1.addArc(3, 4, 3);
        g1.addArc(3, 5, 7);
        int[] a = prim(g1,0);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
        
    }
}
