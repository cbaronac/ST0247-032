/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio3;

/**
 *
 * @author Camila Barona, Felipe Sosa
 */
public class Edge {
    int ID1;
    int ID2;
    double distance;
    String name;
    
    public Edge(int ID1,int ID2,double distance,String name){
        this.ID1=ID1;
        this.ID2=ID2;
        this.distance=distance;
        this.name=name;        
    }
}

