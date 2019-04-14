import java.util.*;

/**
 * Write a description of class Taller6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Punto1

{
    /**
     * Metodo que recorre todo el grafo con la intencion de buscar un
     * camino que represente el menor costo pasando por todos los vertices exactamente
     * una vez y vuelva al nodo inicial
     * @param g grafo dado 
     * @return cual es el costo que tiene
     */
    public static int recorrido(Digraph g) {
        int origen=0;
        int costoMinimo= Integer.MAX_VALUE;
        int respuesta=0;
        int j=0;
        boolean [] visitados=new boolean [g.size];

        while(j!=g.size-1){
            ArrayList<Integer> sucesores= g.getSuccessors(j);
            for(int i=0;i<sucesores.size();i++){
                if(visitados[i]==false && g.getWeight(j,sucesores.get(i))<costoMinimo){
                    costoMinimo=g.getWeight(j, sucesores.get(i));
                    j=sucesores.get(i);
                    visitados[i]=true;
                }
            } 
            respuesta+=g.getWeight(j, sucesores.get(j));
        }

        return respuesta;
    }
}
