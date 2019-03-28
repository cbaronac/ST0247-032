import java.util.*;

/**
 * Clase en la cual se implementan los metodos del Taller 9
 * 
 * @author Camila Barona y Felipe Sosa
 */
public class Taller9 {
    /**
     * Metodo que pretende implementar el funcionamiento del algoritmo levenshtein
     * de dos cadenas a y b
     * @param a cadena de caracteres
     * @param b cadena de caracteres
     * para mas informacion ver
     * @see <a href="https://people.cs.pitt.edu/~kirk/cs1501/Pruhs/Spring2006/assignments/editdistance/Levenshtein%20Distance.htm">
     *
     */
    public static int levenshtein(String a, String b) {

        int [][] tabla = new int [a.length()+1][b.length()+1];
        tabla=putZero(tabla);

        for (int i=0; i<=a.length()-1;i++){
            tabla[i][0]=i;
        }

        for (int j=0; j<=b.length()-1;j++){
            tabla[0][j]=j;
        }

        for (int j=1;j<=b.length();j++){
            for (int i=1; i<=a.length();i++){
                if (a.charAt(i-1)==b.charAt(j-1)){
                    tabla[i][j]=tabla[i-1][j-1];
                } else {
                    int aux=(Math.min(tabla[i-1][j]+1,tabla[i][j-1]+1));
                    tabla[i][j]=Math.min(aux,tabla[i-1][j-1]+1);
                }
            }
        }
        return tabla[a.length()][b.length()];
    }

    public static int [][] putZero(int [][] tabla){
        for (int i=0; i<tabla.length;i++){
            for (int j=0; j<tabla[0].length;j++){
                tabla[i][j]=0;
            }
        }
        return tabla;
    }

    public static void main (String [] args) {
        System.out.println( levenshtein("horse","ros"));
    }
}
