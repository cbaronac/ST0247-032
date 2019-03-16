
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javafx.util.Pair; 

/**
 * Solucion 1er punto.
 * 
 * @author (Camila Barona - Felipe Sosa) 
 * @version (2018)
 */
public class Ejercicio1
{
    static int soluciones=0;

    private static void nReinas(int n, int[]tablero, int col,ArrayList<Pair<Integer,Integer>>huecos){
        if (col==n){
            soluciones++;
            imprimirTablero(tablero);
        } else {
            for (int i=0;i<n;i++){
                tablero[col]=i;
                if (noSeAtacanHastaIoNo(tablero,col, huecos)){
                    nReinas(n,tablero,col+1,huecos);
                } 
            }
        }
    }

    public static void nReinas(int n, ArrayList<Pair<Integer,Integer>> huecos){
        nReinas(n, new int[n],0,huecos);
        System.out.println("Las solucionrs son: "+soluciones);
    }

    private static boolean noSeAtacanHastaIoNo(int[] tablero,int c,ArrayList<Pair<Integer,Integer>>huecos){ 
        for (int i=0; i<c;i++){
            if(Math.abs(tablero[i]-tablero[c])==Math.abs(i-c) || tablero[i]==tablero[c]){
                for (Pair<Integer,Integer> pareja:huecos){
                    if (pareja.getKey()==tablero[c] && pareja.getValue()==c){
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

    public static void archivo (){
        final String Filename = "reinas.txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Filename));
            //Se almacenaran las coordenadas de las casillas malas
            ArrayList<Pair<Integer,Integer>> huecos=new ArrayList<>();
            String lineaActual=br.readLine();
            int nReinas=Integer.parseInt(lineaActual);
            
            //Condicion de parada en el archivo
            while (nReinas!=0){
                lineaActual=br.readLine();
                while(lineaActual.charAt(0) =='*' || lineaActual.charAt(0) =='.') {
                    for (int i=0; i<nReinas;i++){
                        for (int j=0;j<nReinas;j++){
                            if (lineaActual.charAt(j)=='*'){
                                huecos.add(new Pair(i,j));
                            }
                        }
                    }
                    lineaActual=br.readLine();
                }
            
            }
            System.out.println("Solucion: ");
            nReinas(nReinas,huecos);
            nReinas=Integer.parseInt(lineaActual);
        } catch (IOException ioe){
            System.out.println ("Error en la lectura");
        }
    }

    public static void main (String[] args){
        archivo();
    }

}

