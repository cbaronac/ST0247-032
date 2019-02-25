/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio2;

import java.util.HashMap;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 *
 * @author pipe_sosa
 */
public class Punto2 {

    public static void main(String[] args) {
        Lector lector = new Lector();
        Lector.makeMap();
        HashMap<Long, Pair<Vertex, LinkedList<Edge>>> grafo = Lector.hashMap;
        System.out.println(grafo.size());
        long origen = 10000;
        long destino = 10000;
        int verticesVisitados = 0;
        caminoCostoMinimo(grafo, origen, destino, verticesVisitados);

    }

    public static boolean caminoCostoMinimo(HashMap<Long, Pair<Vertex, LinkedList<Edge>>> grafo, long origen, long destino, int verticesVisitados) {
        double costoMinimo = Double.MAX_VALUE;
        int costo=0;
        if (origen == destino && grafo.size() == verticesVisitados) {
            System.out.println(costoMinimo);
            return true;
        } else {
            verticesVisitados++;
            Pair conjunto = grafo.get(origen);
            LinkedList<Edge> hijos = (LinkedList<Edge>) conjunto.getValue();

            for (Edge hijo : hijos) {
                if (hijo.distance < costoMinimo) {
                    costoMinimo = hijo.distance;
                    costo+=hijo.distance;
                    if (origen == hijo.ID1) {
                        caminoCostoMinimo(grafo, hijo.ID2, destino, verticesVisitados);
                    } else {
                        if (origen == hijo.ID2) {
                            caminoCostoMinimo(grafo, hijo.ID1, destino, verticesVisitados);
                        }
                    }
                }
            }
        }
        return false;
    }

}
