import java.util.*;

/**
 * Clase en la cual se implementan los metodos del Taller 10
 * 
 * @author Camila Barona y Felipe Sosa
 */
public class Taller10 {
    /**

     * @param a cadena de caracteres
     * @param b cadena de caracteres
     * Solución punto 1 Taller 10
     */
    public static int lcs(String a, String b) {

        int [][] tabla = new int [a.length()+1][b.length()+1];

        for (int i=0; i<=a.length()-1;i++){
            tabla[i][0]=i;
        }
        
        for (int j=0; j<=b.length()-1;j++){
            tabla[0][j]=0;
        }

        for (int j=1;j<=b.length();j++){
            for (int i=1; i<=a.length();i++){
                if (a.charAt(i-1)==b.charAt(j-1)){
                    tabla[i][j]=tabla[i-1][j-1]+1;
                } else {
                    tabla[i][j]=Math.max(tabla[i][j-1],tabla[i-1][j]);
                }
            }
        }
        
        return tabla[a.length()][b.length()];
    }
}
