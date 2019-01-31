
/**
 * Solución del ejercicio 1
 * 
 * @author (Camila Barona - Felipe Sosa) 
 * @version (2019)
 */
public class Ejercicio1
{
    /**
     * @param cadena se trata de una cadena de caracteres sobre la cual hallaremos las posibles combinaciones.
     *
     * El método conjuntoPotencia se define para que solo se tenga que pasar el parametro cadena y no la cadena 
     * vacía necesaria para el metodo reursivo conjuntoPotenciaAux. Este metodo no se modifica.
     * 
     */
    public static void conjuntoPotencia(String cadena) { 
        conjuntoPotenciaAux("", cadena); 
    }

    /**
     * @param esta, se utiliza como una variable auxiliar para guardar datos sobre el proceso.
     * @param falta se trata de una cadena de caracteres sobre la cual hallaremos las posibles combinaciones.
     *
     * El método conjuntoPotenciaAux se encarga de encontrar las posibles combinaciones en la cadena 
     * notese que el método es "private" de modo que solo se puede llamar desde el interior de la clase pues
     * el método que lo representa es conjuntoPotencia.

     */
    private static void conjuntoPotenciaAux(String esta, String falta) {  
        if (falta.isEmpty()){
            System.out.println (falta);
        } else {
            conjuntoPotenciaAux(esta+falta.charAt(0),falta.substring(1));
            conjuntoPotenciaAux(esta,falta.substring(1));
        } 
    }
}
