/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio2;

/**
 *
 * @author pipe_sosa
 */
public class Edge {
    long ID1;
    long ID2;
    double distance;
    String name;
    
    public Edge(long ID1,long ID2,double distance,String name){
        this.ID1=ID1;
        this.ID2=ID2;
        this.distance=distance;
        this.name=name;        
    }
}