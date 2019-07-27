/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.xml.internal.ws.util.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Steve B. 
 */
public class Simulator {
    
   Scanner input = new Scanner(System.in);
   boolean notvalidated=true;   
   static int  stops = 0;  
   static Simulator sim = new Simulator();
    
 public static void main(String args[]) {
     
     
 sim.getStopsFromUser();
 sim.getInputFile();
 try{
 sim.checkFile(stops, sim.getInputFile());
 }
 catch(Exception ex){
 sim.getStopsFromUser();
 sim.getInputFile();
 
 }
 
 }  
    


    public  int getStopsFromUser() {
        String buffer = null;
        int stops = 0;
        System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");

        while (notvalidated) {
            try {
                buffer = input.nextLine();
                stops = Integer.parseInt(buffer);

                
               if(stops < 0 )
               {throw  new IllegalArgumentException();}
                notvalidated = false;
            } catch (NumberFormatException | NullPointerException nfe) {

                System.out.println("Invalid input, try again.");

            }
            catch(IllegalArgumentException x){
            System.out.println("Number must be greater then zero. Try again ");
            
            }
        }

        return stops;
    }

    
    
    
    public  File getInputFile() {
        notvalidated = true;
        File file = null;
        System.out.println("Enter absolute path for data file or for default (C://train//customer-data.txt) press Enter:   ");

        String path = input.nextLine();
        try {
            file = new File(path);

            if (!file.exists()) {

                while (!file.exists()) {

                    System.out.println("File not found, try again. ");

                    path = input.nextLine();
                    file = new File(path);

                }

            }

        } catch (NullPointerException ex) {
            System.out.println("Please enter a file name before pressing enter. Try again  ");
            path = input.nextLine();
        }

        return file;
    }

     
    public ArrayList<Customer> checkFile(int i, File file) {

        ArrayList<Customer> x = new ArrayList<Customer>();
        try {
            input = new Scanner(file);
        } catch (Exception ex) {
            
         System.out.println("File not found, try again. ");    
         sim.getInputFile();
        }

        while (input.hasNextLine()) {
            System.out.println(input.nextLine());

        }

        return x;
    }

    

   
    
}
