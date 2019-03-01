/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller6;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Clase en la cual se implementan los metodos del Taller 6
 *
 * @author Mauricio Toro, Andres Paez
 */
public class Taller6 {

    /*public static void main(String[] args) {
        int[] denominaciones = {1000, 500, 200, 100, 50};
        int[] array = cambioGreedy(1200, denominaciones);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ,");
        }
    }*/

    /**
     * Metodo que dado un entero n y un conjunto de denominciones de dinero
     * busque la manera optima de dar el cambio
     *
     * @param n cantidad a devolver
     * @param denominaciones conjunto de denominaciones de dinero (monedas,
     * billetes)
     * @return un conjunto de unidades por denominacion
     */
    public static int[] cambioGreedy(int n, int[] denominaciones) {
        int[] respuesta = new int[denominaciones.length];
        for (int i = 0; i < denominaciones.length; i++) {
            int numMonedas = n / denominaciones[i];
            respuesta[i] = numMonedas;
            n = n % denominaciones[i];
        }
        return respuesta;
    }

    /**
     * Metodo que recorre todo el grafo con la intencion de buscar un camino que
     * represente el menor costo pasando por todos los vertices exactamente una
     * vez y vuelva al nodo inicial
     *
     * @param g grafo dado
     * @return cual es el costo que tiene
     */
    public static int recorrido(Digraph g) {
        
        int tam = g.size;
        int costoMinimo = Integer.MAX_VALUE;
        int respuesta = 0;
        int auxiliar=0;
        boolean [] visitados=new boolean[tam];
        int origen=0;
        while(origen!=tam){
            ArrayList<Integer> sucesores = g.getSuccessors(origen);
            auxiliar=0;
            for(int sucesor : sucesores){    
                if (visitados[sucesor]==false && g.getWeight(origen, sucesores.get(sucesor)) < costoMinimo) {                  
                    costoMinimo = g.getWeight(origen, sucesores.get(sucesor));
                    visitados[sucesor]=true;
                    auxiliar=sucesor;
                }
            }
            respuesta += g.getWeight(origen, sucesores.get(auxiliar));
            origen = sucesores.get(auxiliar);
        }
        return respuesta;
    }

}
