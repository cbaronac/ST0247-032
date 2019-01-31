/**
 *
 * @author pipe_sosa and Camila Barona la mejor
 * 
*/import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 2
 * 
 * @author Mauricio Toro, Camilo Paez
 */
public class Ejercicio2 {
	
	/**
	* Metodo auxiliar que llama al metodo combinations posterios
	* 
	* @param  list el conjunto sobre el cual se haran las combinaciones
	
	*/	
	public static void combinations(ArrayList list) {
		
	}

	/**
	* Metodo para obtener las posibles combinaciones que se pueden hacer
	* con los elementos dados
	* 
	* @param  index lleva la posicion para recorrer el conjunto 
	* @param list el conjunto que tiene todas las combinaciones
	* @param s la cadena actual
	* 
	*/	
	private static void combinations(String s, int index, ArrayList<String> list) {
		
	}

	/**
	* Metodo auxiliar que llama al metodo permutations posterios
	* 
	* @param  s la cadena a la cual se le haran las permutaciones
	* @return un ArrayList que contiene las permutaciones
	*/	
	public static ArrayList<String> permutations(String s) {
		ArrayList<String> list=new ArrayList();
                permutationsAux("",s,list);
                return list;
	}

	/**
	* Metodo para obtener las posibles permutaciones que se pueden hacer
	* con los caracteres de una cadena dada, recuerde que las letras no se 
	* repiten en este ejercicio
	* 
	* @param  pre parte de la cadena que empieza desde 0 a i
	* @param pos parte de cadena que empieza desde i a n
	* @param list el conjunto que tiene todas las permutaciones
	* 
	*/	
	private static void permutationsAux(String pre, String pos, ArrayList<String> list) {
            
            if(pos.length()==0){      
                list.add(pre);
            }else{
		for(int i=0;i<pos.length();i++)
                    permutationsAux(pre+pos.charAt(i),pos.substring(0,i)+pos.substring(i+1),list); 
            }     
                
        }

	/**
	* Metodoque imprime en pantalla como esta el tablero dado
	* 
	* @param  tablero es un arreglo con las posiciones de un tablero
	* de ajedrez
	* no se modifica.
	*/	
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
