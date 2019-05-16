/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Camila Barona y Felipe Sosa
 */
public class Edge implements Comparable{

    int ID1;
    int ID2;
    int distance;

    public Edge(int ID1, int ID2, int distance) {
        this.ID1 = ID1;
        this.ID2 = ID2;
        this.distance = distance;
    }
    
    public int getDistance(){
        return this.distance;
    }
    
    public boolean equals(Edge otro) {
        return this == otro;
    }

    public int compare(Edge e1,Edge e2) {
        return e1.compareTo(e2);
    }

    @Override
    public int compareTo(Object o) {
        Edge v = (Edge) o;
        if (this.equals(v)) {
            return 0;
        } else if ((int) this.getDistance() > (int) v.getDistance()) {
            return -1;
        } else {
            return 1;
        }

    }
}
