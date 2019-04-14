/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Pipe Sosa y Camila Barona
 */
public class punto2{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        punto2 test =new punto2();
        test.reader("Archivo.txt");
    }
    
    public void reader(String fileName){
        try{
            BufferedReader read= new BufferedReader(new FileReader(fileName));
            int cont=0;
            String line= read.readLine();
            int routes=0;
            int duration=0;
            int rate=0;
            int [] morning=null;            
            int [] afternoon;
            while(!line.equals("0 0 0")){
                cont++;
                String [] sepInfo= line.split(" ");
                int [] sepInfoInt=new int[sepInfo.length];
                for(int i=0;i<sepInfo.length;++i){
                    sepInfoInt[i]=Integer.parseInt(sepInfo[i]);
                }
                if(cont==1&&sepInfoInt.length==3){
                    routes= sepInfoInt[0];
                    //System.out.print(routes+" ");
                    duration=sepInfoInt[1];
                    //System.out.print(duration+" ");
                    rate=sepInfoInt[2];
                    //System.out.print(rate+" ");
                    
                }else if(cont==2&&sepInfoInt.length==routes){
                    morning= sepInfoInt;
                }else if(cont==3&&sepInfoInt.length==routes){
                    afternoon= sepInfoInt;
                    minimumHoursValue(morning,afternoon,duration,rate);
                    cont=0;
                }
                line= read.readLine();
            }
            
        }catch(IOException ioe){
            System.err.println("Exception: "+ioe+" Saludos.");
            System.exit(1);
        }
    }
    
    public void minimumHoursValue(int [] morning, int [] afternoon,int duration, int rate){
        Arrays.sort(morning);
        Arrays.sort(afternoon);
        int add1= 0;
        int add2= 0;
        for(int i = 0, j = afternoon.length-1; i<morning.length;i++,j--){
            add1=morning[i]+afternoon[j];
            if(add1>duration){
                add2+=(add1-duration)*rate;
            }
        }
        System.out.println(add2);
    }
    
}
