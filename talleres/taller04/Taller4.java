import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 4
 * 
 * @author Mauricio Toro, Andres Paez
 */
public class Taller4 {

    /**
     * Metodo auxiliar para llamar el metodo hayCaminoDFS posterior
     * @param g grafo dado 
     * @param o orgien vertices 
     * @param d destino vertice
     * @return true si hay camino, false de lo contrario
     */
    public static boolean hayCaminoDFS(Digraph g, int o, int d) {
        boolean[] visitados = new boolean[g.size()];
        return hayCaminoDFSAux(g, o, d, visitados);
    }

    /**
     * Metodo que recorre el grafo por medio de dfs 
     * @param g grafo dado 
     * @param o vertices origen
     * @param d vertice destino
     * @param visitados ayuda a tener un conteo acerca de que nodos han sido
     * o no visitados
     * @return true si hay camino, false de lo contrario
     */
    private static boolean hayCaminoDFSAux(Digraph g, int o, int d, boolean[] visitados) {
        visitados[0]=true;
        if (o==d) {
            return true;
        } else {
            ArrayList<Integer> hijos=g.getSuccessors(o);
            for (Integer hijo:hijos) {
                if (!visitados[hijo] && hayCaminoDFS(g,hijo,d)){
                    return true;
                } 
            }
            return false;
        }
    }

    /**
     * Metodo que recorre el grafo por medio de dfs teniendo en cuenta que
     * se quiere encontrar el de menor costo
     * @param g grafo dado 
     * @param inicio nodo desde el cual empieza el recorrido 
     * @param fin nodo donde termina el recorrido
     * @return cual es el costo que tiene ir desde inicio a fin
     */
    public static int costoMinimo(Digraph g, int inicio, int fin,boolean [] visitados) {
        //boolean[] visitados = new boolean[g.size()];
        visitados[inicio]=true;
        int costoMinimo=Integer.MAX_VALUE;
        int costoCamino=0;
        
        if (inicio==fin){
            System.out.println("Costo Mínimo: "+costoMinimo);
            return costoMinimo;
        } else {
            ArrayList<Integer> hijos=g.getSuccessors(inicio);
            for (Integer hijo:hijos) {
                if (!visitados[hijo]){
                    visitados[hijo]=true;
                    int recursion = costoMinimo(g,hijo,fin,visitados);
                    if (recursion == Integer.MAX_VALUE)
                        costoCamino = recursion;
                    else
                        costoCamino=g.getWeight(inicio,hijo) + recursion;
                    if (costoCamino<costoMinimo){
                        costoMinimo=costoCamino;
                    } 
                } 
            }
            return costoMinimo;
        }
    }

    /** 
     * Metodo auxiliar que llama al metodo recorrido posterior
     * con cada uno de los vertices
     * @param g grafo dado 
     * @return cual es el costo que tiene
     */
    public static int recorrido(Digraph g) {
        boolean[] visitados = new boolean[g.size()];
        return costoMinimo(g,1,2,visitados);
    }

     public static int caminoGrafoCompleto(Digraph g, int o){
        boolean[] visit = new boolean[g.size()];
        int vertices = 0;
        int[] costo = new int[1];
        costo[0] = Integer.MAX_VALUE;
        int tamaño = 0;
        caminoGrafoCompletoAux(g, o, o, visit, costo, tamaño, vertices);
        return costo[0] == Integer.MAX_VALUE ? 0 : costo[o];
    }
    
    public static boolean caminoGrafoCompletoAux(Digraph g, int o, int d, boolean[] visit, int[] costo, int tamaño, int vertices){
        
        if(o == d && g.size() == vertices){
            if(tamaño < costo[0]){
                costo[0] = tamaño;
            }
            visit[o] = false;
            return true;
        }
                
        visit[o] = true;
        vertices++;
        
        ArrayList<Integer> hijos=g.getSuccessors(o);
        for (Integer hijo:hijos) {
            if((!visit[hijo] && tamaño+g.getWeight(o,hijo)<costo[0])|| (hijo == d && vertices == g.size())){
                tamaño += g.getWeight(o, hijo);
                caminoGrafoCompletoAux(g, hijo, d, visit, costo, tamaño, vertices);
            }
        }
        
        visit[o] = false;
        return false;
    }
}



    