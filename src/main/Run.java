package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//конечный вариант . тестирую
public class Run {

    public static void main(String [] args){
        String  rad;
        int r;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            rad = br.readLine(); //We read from user's input


            Mathematics math = new Mathematics(rad);
            System.out.println("resultat operation " + math.getResult());
        }
        catch(Exception e){
            System.out.println("Write an integer number"); //This is what user will see if he/she write other thing that is not an integer

        }
    }
}
