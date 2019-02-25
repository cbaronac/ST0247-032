 import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Solucion 2do punto.
 * 
 * @author (Camila Barona - Felipe Sosa) 
 * @version (2018)
 */
public class Ejercicio2
{
    static int soluciones=0;
    static ArrayList<Pair<Integer,Integer>> fallas;

    private static void nReinas(int n, int[]tablero, int col){
        if (col==n){
            soluciones++;
            imprimirTablero(tablero);
        } else {
            for (int i=0;i<n;i++){
                tablero[col]=i;
                if (noSeAtacanHastaIoNo(tablero,col)){
                    nReinas(n,tablero,col+1);
                } 
            }
        }
    }

    public static void nReinas(int n){
        nReinas(n, new int[n],0);
        System.out.println("Las soluciones son: "+soluciones);
    }

    private static boolean noSeAtacanHastaIoNo(int[] tablero,int c){ 
        for (int i=0; i<c;i++){
            if(Math.abs(tablero[i]-tablero[c])==Math.abs(i-c) || tablero[i]==tablero[c]){
                for (Pair<Integer,Integer> pareja:fallas){
                    if ((int)pareja.first==tablero[c] && (int)pareja.second==c){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void imprimirTablero(int[] tablero) {
        int n = tablero.length;
        System.out.print("    ");
        for (int i = 0; i < n; ++i)

            System.out.print(i + " ");
        System.out.println("\n");
        for (int i = 0; i < n; ++i) {
            System.out.print(i + "   ");
            for (int j = 0; j < n; ++j)

                System.out.print((tablero[i] == j ? "Q" : "#") + " ");
            System.out.println();
        }
        System.out.println();
    }
