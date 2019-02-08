import java.lang.Math;
/**
 * Solucion 1er punto.
 * 
 * @author (Camila Barona - Felipe Sosa) 
 * @version (2018)
 */
public class Ejercicio1
{
    private static boolean nReinas(int n, int[]tablero, int col){
        if (col==n){
            imprimirTablero(tablero);
            return true;
        } else {
            for (int i=0;i<n;i++){
                tablero[col]=i;
                if (noSeAtacanHastaIoNo(tablero,col)){                    
                    if(nReinas(n,tablero,col+1)){
                        return true;
                    } 
                } 
            }
            return false;
        }
    }

    public static void nReinas(int n){
        nReinas(n, new int[n],0);
    }

    private static boolean noSeAtacanHastaIoNo(int[] tablero,int elI){
        for (int i=0; i<elI-1;i++){
            for (int j=i+1;j<elI;j++){
                if(Math.abs(tablero[i]-tablero[j])==Math.abs(i-j) || tablero[i]==tablero[j]){
                    return false;
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
}
