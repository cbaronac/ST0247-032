/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller11;

/**
 * Clase en la cual se implementan los metodos del Taller 11
 *
 * @author Mauricio Toro, Andres Paez
 */
public class Taller11 {

    /**
     * Dado un grafo completo, hallen el costo mínimo del recorrido que pasa por
     * todos los vértices exactamente una vez y vuelve al nodo inicial
     * utilizando programación dinámica
     *
     * @param g grafo dado
     * @param y cadena de caracteres para mas informacion
     * @see
     * <a href="https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm">KMP</a>
     *
     */
    public static int heldKarp(Digraph g) {
        int n = g.size();// numero de vertices del grafo
        int[][] costos = new int[n][(int) Math.pow(2, n - 1)];
        int[][] anteriores = new int[n][(int) Math.pow(2, n - 1)];
        for (int i = 0; i < n; ++i) {
            costos[i][0] = g.getWeight(0, i);
            anteriores[i][0] = 0;
        }
        for (int j = 1; j < (int) Math.pow(2, n - 1); ++j) {
            for (int i = 1; i < n; ++i) {
                if (i == j) {
                    costos[i][j] = 0;
                } else {
                    int valor = (j - 1) - i;
                    if (j > n && valor > 0 && valor < n) {
                        costos[i][j] = 0;
                        costos[valor][j] = 0;
                    } else {
                        if (j < n) {
                            costos[i][j] = costos[j][0] + g.getWeight(j, i);
                        }else{
                            int valor2=j-i;
                            int valor3=j-valor2-1;
                            costos[i][j] = Math.max(costos[valor2][valor3]+g.getWeight(valor2,i),costos[valor3][valor2]+g.getWeight(valor3,i));
                        }
                    }
                }

            }
        }
        return costos[0][(int) Math.pow(2, n - 1)];
    }

}
