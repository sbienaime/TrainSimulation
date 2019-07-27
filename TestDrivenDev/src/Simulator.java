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
   static File file = null;
   static Scanner Reader;
 public static void main(String args[]) {
     
   sim.getStopsFromUser();
  sim.getInputFile();
 sim.checkFile(stops, file);
 

 }  
    


    public  int getStopsFromUser() {
        String buffer = null;
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
            else{System.out.println("File is good");}

        } catch (NullPointerException ex) {
            System.out.println("Please enter a file name before pressing enter. Try again  ");
            path = input.nextLine();
        }

        return file;
    }

     
    public ArrayList<Customer> checkFile(int stops, File file) {
        int linesProcessed = 0;
        String customerdata = "";
        String[] tempArraystr;
        int[] tempArrayint = new int[4];
        String delimiter = " ";
        ArrayList<Customer> x = new ArrayList<Customer>();
        try {
            Reader = new Scanner(Simulator.file);
        } catch (FileNotFoundException ex) {

            System.out.println("File not found, try again. ");
            sim.getInputFile();
            sim.checkFile(stops, sim.getInputFile());
        }

        while (Reader.hasNextLine()) {

            customerdata = Reader.nextLine();
            tempArraystr = customerdata.split(delimiter);

            for (int i = 0; i < tempArraystr.length; i++) {
                try {

                    tempArrayint[i] = Integer.parseInt(tempArraystr[i]);

                } catch (NumberFormatException ex) {
                    if (linesProcessed == 0) {

                        System.out.println("Each line must have four integers. Try again.");

                    } else {
                        System.out.println("Data in input file is not correct. Try again.");

                    }
                    File newfile = sim.getInputFile();
                    sim.checkFile(stops, newfile);
                }

            }

            linesProcessed++;

        }

        return x;
    }

    

   
    
}
