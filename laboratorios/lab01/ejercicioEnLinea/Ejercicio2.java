
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

/**
 * Solución del ejercicio 2 del laboratorio 1
 * @author Camila Barona, Felipe Sosa
 * @version 1
 * 
 */
public class Ejercicio2
{
    public static boolean bicolorable(int [][] graph)
    {   
        //ArrayList <Integer> color= new ArrayList<Integer>();
        int[] color = new int[graph.length];
        int i = 0;
        while(i < graph.length)
        {
            color[i] = 4;
            i++;
        }

        if (auxBicolorable(graph,color)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean auxBicolorable(int [][] graph, int [] color)
    {
        Stack<Integer> cont =  new Stack<>();
        cont.push(0);
        while(cont.size() != 0)
        {
            System.out.println("Pila: "+cont);
            int actual = cont.pop();
            for(int i = 0; i < graph.length; i++)
            {
                if(graph[actual][i] == 1 && color[i] == 4)
                {
                    System.out.println("CUMPLE CONDICIÓN");
                    System.out.println("Color actual: "+color[actual]);
                    System.out.println("Color en la posición "+i+": "+color[i]);
                    color[i] = 1-color[actual];
                    System.out.println("Color adyacente: "+color[i]);
                    System.out.println("Valor ingresado en la pila: "+cont.push(i));
                    //round.push(i);
                }else if(graph[actual][i] == 1  && color[actual] == color[i]){
                    System.out.println("NO CUMPLE CONDICIÓN");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner consola=  new Scanner(System.in);
        System.out.println("Ingrese la cantidad de nodos: ");
        int nodos= consola.nextInt();
        System.out.println("Ingrese el número de arcos: ");
        int arcos = consola.nextInt();
        int [][] graph = new int[nodos][nodos];
        for (int i=0;i<arcos;i++)
        {
            System.out.println("Ingrese la coordenada de origen: ");
            int origen = consola.nextInt();
            System.out.println("Ingrese la coordenada de destino: ");
            int destino = consola.nextInt();
            graph[origen][destino] = 1;
        }

        if (bicolorable(graph)){
            System.out.println("BICOLORABLE");
        } else {
            System.out.println("NOT BICOLORABLE");
        }
    }
}
