/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 *
 * @author pipe_sosa
 */
public class QueueCreator {

    public static PriorityQueue<Edge> makeQ(HashMap<Integer, Pair<Vertex, ArrayList<Edge>>> map) {
        PriorityQueue<Edge> queue = new PriorityQueue();
        ArrayList<Edge> edges = map.get(1).getValue();
        for (int i = 0; i < edges.size(); i++) {
            queue.add(edges.get(i));
        }
        return queue;
    }

    public static void algorithm(PriorityQueue<Edge> queue, HashMap<Integer, Pair<Vertex, ArrayList<Edge>>> map, double p, String name) throws IOException {
        boolean[] visit = new boolean[queue.size() + 1];
        System.out.println(visit.length);
        ArrayList<int[]> resultado = new ArrayList();
        int imp = 0;
        while (!queue.isEmpty()) {
            ArrayList<Edge> list = new ArrayList();
            if (!visit[queue.peek().ID2]) {// si no ha sido visitado el del tope de la pila
                int[] car = new int[5];//cree un arreglo de personas que van en un carro
                int index = 1;//el indice de ese arreglo
                Edge driv = queue.poll(); //saque el primero de la cola (el mas lejano)
                double tiempoMax = driv.distance * p;
                //System.out.println(driv.ID2);
                visit[driv.ID2] = true;//ya habria sido visitado y almacenado en su propio carro
                car[0] = driv.ID2;
                int tiempoAcum = 0;
                while (index < 5 && !queue.isEmpty()) { //hasta que meta los 5
                    Edge actual = queue.peek();// mira el actual que seria el siguiente mas lejano
                    if (isSuccess(map, driv.ID2, actual.ID2) && Reader.getWeight(map, actual.ID2, 1) < Reader.getWeight(map, driv.ID2, 1) && tiempoAcum < tiempoMax) {
                        // si es alcanzable por el vertice mas lejano y si la distancia de el vertice actual a la universidad es menor que la del mas lejano, para no alejarse
                        tiempoAcum += Reader.getWeight(map, driv.ID2, actual.ID2);
                        driv = queue.poll();//si entra, pues sacas el actual, y lo conviertes en el "conductor"
                        car[index] = driv.ID2; //se ingresa en el carro
                        index++;//se aumenta el indice 
                        visit[driv.ID2] = true;//se toma como visitada a esa posicion

                    } else {
                        //meta a una lista auxiliar la conexion y meta todo lo de la arista a la cola normal si ya se quedo vacia
                        list.add(queue.poll());
                        if (queue.isEmpty()) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).ID2 != 1) {
                                    queue.add(list.get(i));
                                }
                            }
                            break;
                        }

                    }
                }

                int k = -1;//fin del arreglo
                for (int i = 0; i < car.length; i++) {// encuentre el 0
                    if (car[i] == 0) {
                        k = i;
                        break;
                    }
                }
                if (k == -1) {// si no encontro el 0 es porque el carro esta completo
                    k = car.length - 1;//el fin seria el ultimo elemento
                }

                if (car[0] == 1) {//si el carro lo maneja la "universidad eafit", eso no tiene sentido
                    continue;
                } else {
                    if (k == 1) { //si k que es el final del arreglo es 1 es porque el conductor se va solo
                        resultado.add(car);
                    } else { // de lo contrario
                        int cont = 0;
                        while (k > 0) { //recorra desde el final hasta donde el carro cumpla la condicion de no superar el p 
                            if (car[k] != 0 && car[k] != 1) { //si es distinto de 0 y de 1, se puede evaluar si se deja en el carro 
                                tiempoAcum += Reader.getWeight(map, 1, car[k]);
                                if (tiempoAcum < tiempoMax) {//si el tiempo que acumula ese vertice no supera al maximo posible, ya sabemos hasta que punto k el carro esta bien conformado
                                    break;
                                } else {// de lo contrario se aumenta el contador de cuantas eliminaciones hay que hacerle 
                                    tiempoAcum -= Reader.getWeight(map, 1, car[k]) + Reader.getWeight(map, car[k - 1], car[k]);
                                    cont++;
                                    k--;
                                }
                            } else {//se vuelve el uno en cero
                                car[k] = 0;
                                k--;
                            }
                        }

                        int retro = car.length - 1;
                        while (cont > 0) {//se eliminan los vertices que generen daños en el tiempo de recorrido
                            if (car[retro] != 0 && ((retro != car.length - 1 && car[retro + 1] == 0) || retro == car.length - 1)) {
                                queue.add(new Edge(1, car[retro], (int) Reader.getWeight(map, 1, car[retro])));
                                visit[car[retro]] = false;
                                car[retro] = 0;
                                retro--;
                                cont--;
                            } else {
                                retro--;
                            }
                        }
                        resultado.add(car);
                    }
                }
                String nameFile = "resultado.txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile, true))) {
                    if (imp == 0) {
                        writer.write(name + " y p= " + p + "\n");
                        imp++;
                    }
                    for (int j = 0; j < car.length; j++) {
                        writer.write(" " + car[j]);
                        //System.out.print(car[j] + " ");
                    }
                    //System.out.println();
                    writer.write("\n");
                }
            } else {
                queue.poll();
            }
        }

    }

    public static boolean isSuccess(HashMap<Integer, Pair<Vertex, ArrayList<Edge>>> grafo, int inicioID, int destinoID) {
        Pair<Vertex, ArrayList<Edge>> vertice_aristas = grafo.get(inicioID);
        ArrayList<Edge> Sucesores = vertice_aristas.getValue();
        for (int i = 0; i < Sucesores.size(); i++) {
            if (Sucesores.get(i).ID1 == inicioID && Sucesores.get(i).ID2 == destinoID) {
                return true;
            }
        }
        return false;
    }
}
