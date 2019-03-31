/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Camila Barona, Felipe Sosa
 */
public class Vertex {

    long ID;
    double x; // coordenada en el eje x
    double y; // coordenada en el eje y
    String name; // nombre del vértice

    public Vertex (long ID, double x, double y, String name) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.name = name;      
    }
     public Vertex (long ID, double x, double y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.name = "unknown";      
    }
}
