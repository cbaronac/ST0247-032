/**
 * Clase en la cual se implementan los metodos del Taller 8
 * 
 * @author Camila Barona, Felipe Sosa
 */
public class Taller8 {
    /**
     * Metodo que pretende implementar el funcionamiento del algoritmo MergeSort
     * de un conjunto de elementos
     * @param a un arreglo con elementos
     * 
     * para mas informacion ver
     * @see <a href="https://www.youtube.com/watch?v=JSceec-wEyw">
     *
     */
    public static void mergesort(int[] a, int left, int right) {
        if (left<right){
            int merge=(left+right)/2;
            mergesort(a,left,merge);
            mergesort(a,merge+1,right);
            merge(a,left,merge,right);
        } 
    }

    private static int[] merge (int [] a, int indiceIzq, int m, int indiceDer){
        int tam1= m-indiceIzq+1;
        int tam2=indiceDer-m;
        int aIzq[]=new int[tam1];
        int aDer[]=new int [tam2];

        for (int i=0; i<tam1;i++){
            aIzq[i]=a[indiceIzq+i];
        }

        for (int j=0; j<tam2;j++){
            aDer[j]=a[m+1+j];
        }

        int i=0; 
        int j=0;
        int k=indiceIzq;
        while (i<tam1 && j<tam2){
            if (aIzq[i]<=aDer[j]){
                a[k]=aIzq[i];
                i++;
            } else {
                a[k]=aDer[j];
                j++;
            }
            k++;
        }

        while (i<tam1){
            a[k]=aIzq[i];
            i++;
            k++;
        }

        while (j<tam2){
            a[k]=aDer[j];
            j++;
            k++;
        }

        return a;
    }

    /**
     * Metodo que pretende implementar el funcionamiento del algoritmo MergeSort
     * de un conjunto de elementos
     * @param a un arreglo con elementos
     * 
     * para mas informacion ver
     * @see <a href="https://www.youtube.com/watch?v=PgBzjlCcFvc">
     *
     */
    public static void quicksort(int[] a, int pequeño, int grande) {
        if (pequeño<grande){
            int p=particion(a,pequeño,grande);
            quicksort(a,pequeño,p-1);
            quicksort(a,p+1,grande);
        }
    }
    
    private static int particion(int a[], int pequeño, int grande){
        int pivot=a[grande];
        int i=(pequeño-1);
        
        for (int j=pequeño;j<=grande-1;j++){
            if (a[j]<=pivot){
                i++;
                swap(a[i],a[j]);               
            }
        }
        swap(a[i+1],a[grande]);
        return (i+1);
    }
    
    private static void swap(int a, int b){
        int aux=a;
        a=b;
        b=aux;
    }
    
    public static void main (String [] args) {
        int arr[]={0,4,7,1,3};
        System.out.println("Original: ");
        for (int i=0; i<arr.length;i++){
             System.out.print(arr[i]+",");
        }
        mergesort(arr,0,arr.length-1);
        System.out.println(" ");
        System.out.println("Merge sort: ");
         for (int i=0; i<arr.length;i++){
             System.out.print(arr[i]+",");
        }
         quicksort(arr,0,arr.length-1);
        System.out.println(" ");
        System.out.println("Quick sort: ");
         for (int i=0; i<arr.length;i++){
             System.out.print(arr[i]+",");
        }
    }
}
