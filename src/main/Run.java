package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//код не доделанный  прошу извинить . много времени ушло на переделку алгоритма.
public class Run {

    public static void main(String [] args){
         String  rad;
         int r;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            rad = br.readLine(); //We read from user's input
           //123r = Integer.parseInt(rad); //We validate if "rad" is an integer (if so we skip catch call and continue on the next line, otherwise, we go to it (catch call))
            //*System.out.println(rad);

            Mathematics math = new Mathematics(rad);
            System.out.println("resultat operation " + math.getResult());
        }
        catch(Exception e){
            System.out.println("Write an integer number"); //This is what user will see if he/she write other thing that is not an integer

        }
    }
}
