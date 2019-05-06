/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 * Clase en la cual se implementan los metodos del Taller 11
 *
 * @author Camila Barona y Felipe Sosa
 */
public class Punto1 {

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
        int[][] costos = new int[n][(int) Math.pow(2, n - 1)];//hasta 2^n-1 porque el numero de subconjuntos de 4 elementos 
                                                              //es en realidad de 3 elementos, osea 8 subconjuntos.
        int[][] anteriores = new int[n][(int) Math.pow(2, n - 1)];//los anteriores a la visita
        for (int i = 0; i < n; ++i) { //llenar la primera fila de la matriz con los numeros de los vertices
            costos[i][0] = g.getWeight(0, i);
            anteriores[i][0] = 0;
        }
        for (int j = 1; j < (int) Math.pow(2, n - 1); ++j) {//ciclo que va iterando columna por columna, pues los datos
                                                            // que trata el algoritmo necesita de datos en columnas previas
            for (int i = 1; i < n; ++i) {
                if (i == j) {
                    costos[i][j] = 0;// si son iguales, seria como ir de 0 a 1 pasando por 1, lo cual no se tiene en cuenta
                } else {
                    int valor = (j - 1) - i;
                    if (j > n && valor > 0 && valor < n) {//tampoco se tiene en cuenta cuando se trata de ir desde 0 hasta 1 
                                                          //pasando por 1 y por 2.
                        costos[i][j] = 0;
                        costos[valor][j] = 0;
                    } else {
                        if (j < n) {
                            costos[i][j] = costos[j][0] + g.getWeight(j, i);// aqui es cuando se llena la tabla con valores 
                                                                            //distintos de cero
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
