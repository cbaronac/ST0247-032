/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller12;

import java.util.PriorityQueue;

/**
 *
 * @author
 */
public class Taller12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(HillClimb(8)));
        //Integer[]arr={2,4,1,3};
        //System.out.println(reinasAtacandose(arr));
        HillClimb(4);
    }

    public static int[] HillClimb(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {   //
            arr[i] = 1;                 //}esto llena la primera fila del tablero de reinas
        }                               //
        int a = 0;
        int cons = otroMetodo(arr, -1); // columna en la que ubicamos la primera reina
        System.out.println(cons);
        System.out.println("aqui: " + arr[0] + arr[1] + arr[2] + arr[3]);
        while(a<n){
          cons=otroMetodo(arr,cons);
         ++a;
        }
        return arr;

    }

    public static int otroMetodo(int[] arr, int cons) {
        PriorityQueue<Pair> queue = new PriorityQueue();         //cola para cada resultado en el desplazamiento sobre cada fila
        PriorityQueue<Pair> queue2 = new PriorityQueue();        //cola de los valores minimos obtenidos en la cola anterior
        int[] aux = new int[arr.length];
        int[] aux2 = {arr[0], arr[1], arr[2], arr[3]};
        for (int i = 0; i < arr.length && i != cons; i++) {
            for (int j = 1; j <= arr.length; j++) {
                aux2[i] = j;
                Pair<Pair<Integer,Integer>, Integer> agrup = new Pair(new Pair(i,j), reinasAtacandose(aux2));
                queue.add((Pair) agrup);
            }
            queue2.add(queue.peek());
            aux[i] = (int) (queue.poll()).second;
            System.out.println(aux[i]);
            queue = new PriorityQueue();
        }
        Pair<Integer,Integer> coordenadas=(Pair)(queue2.peek().first) ;
        
        int min = (int) (queue2.poll()).second;
        System.out.println("--->" + min);
        for (int i = 0; i < aux.length; i++) {
            if (aux[i] == min) {
                arr[i]=coordenadas.second;
                min = i;
                break;
            }
        }

        return 1209;
    }

    /**
     * Este metodo obtiene el numero de reinas que se atacan en el momento
     */
    public static int reinasAtacandose(int[] tablero) {
        int answ = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = i + 1; j < tablero.length; j++) {
                if (Math.abs(tablero[i] - tablero[j]) == Math.abs(i - j) || tablero[i] - tablero[j] == 0) {
                    ++answ;
                }

            }
        }
        return answ;
    }

}
