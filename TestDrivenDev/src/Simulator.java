/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve B.
 */
public class Simulator {

    Scanner input = new Scanner(System.in);
    boolean notvalidated = true;
    static Simulator sim = new Simulator();
   

    public static void main(String args[]) {

        int stops = sim.getStopsFromUser();
        File file = sim.getInputFile();
        ArrayList<Customer> custList = sim.checkFile(stops, file);
        sim.run(stops, custList);

    }


    
    public void run(int stops, ArrayList<Customer> custList) {
        Train OrionExpress = new Train(stops, custList);
        OrionExpress.simulate();
        OrionExpress.displayStops();

    }
    public int getStopsFromUser() {
        
        int stops=0;
        String buffer = null;
        System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");

        while (notvalidated) {
            try {
                buffer = input.nextLine();
                stops = Integer.parseInt(buffer);

                if (stops <= 0) {
                    throw new IllegalArgumentException();
                }
                notvalidated = false;
            } catch (NumberFormatException | NullPointerException nfe) {

                System.out.println("Invalid input, try again");
                System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");
            } catch (IllegalArgumentException x) {
               System.out.println("Invalid input, try again");
               System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");

            }
        }

        return stops;
    }

    public File getInputFile() {
        File file = new File("");
        notvalidated = true;
        
        System.out.println("Enter absolute path for data file or for default (C:/train/customer-data.txt) press Enter:");
        
        String path;
        
        try {
            
            if (input.hasNext()) {
                path = input.nextLine();
                file = new File(path);
            }
            if (!file.exists()) {
                int i = 0;
                while (!file.exists()) {
                    
                    System.out.println("File not found, try again.");
                    
                    System.out.println("Enter absolute path for data file or for default (C:/train/customer-data.txt) press Enter:");
                    if (input.hasNext()) {
                        path = input.nextLine();
                        file = new File(path);
                    }
                    
                    i++;                    
                    if (i == 5) {
                        break;
                    }
                    
                }
                
            }
            
        } catch (NoSuchElementException ex) {
            System.out.println("Please enter a file name before pressing enter. Try again  ");
            
        }
        
        return file;
    }
    
    public ArrayList<Customer> checkFile(int stops, File file) {
        Scanner Reader =null; 
        int linesProcessed = 0;
        String customerdata = " ";
        String[] dataArray;
        int[] parsedVals = new int[4];
        String delimiter = " ";
        ArrayList<Customer> custList = new ArrayList<Customer>();
        ArrayList idArray;
        idArray = new ArrayList();
      
        try {
         
          Reader =new Scanner(new FileReader(file));
            
        } catch (FileNotFoundException ex) {
            
            System.out.println("File not found, try again.");
            file = sim.getInputFile();
            sim.checkFile(stops, file);
        } 
        
        while (Reader.hasNextLine()) {
        
            
          
            customerdata = Reader.nextLine();
            dataArray = customerdata.split(delimiter);

            for (int i = 0; i < dataArray.length; i++) {
                try {

                    parsedVals[i] = Integer.parseInt(dataArray[i]);
                     if (parsedVals[i] <=0 )
                     {
                     throw new IllegalArgumentException();
                     
                     }
                    
                     if (i==2 | i==3   ){
                     
                     if(parsedVals[i]>stops){
                       throw new IllegalArgumentException();
                     
                     }
                     
                     }
                     

                } catch (NumberFormatException ex) {
                    if (linesProcessed == 0) {

                        System.out.println("Each line must have four integers. Try again.");

                    } else {
                        System.out.println(" Regular:Data in input file is not correct. Try again.");

                    }
                   file= sim.getInputFile();
                    custList =sim.checkFile(stops, file);
                }
                catch(IllegalArgumentException args){
                
                 System.out.println("Data in input file is not correct. Try again.");
                 file =sim.getInputFile();
                 custList = sim.checkFile(stops, file);
                 
                 
                }

            }
            
           

            try{
                
                 if(parsedVals[2]==parsedVals[3]) {
                 
                 
                 throw new IllegalArgumentException();
                 
                 }
                
                
                
                
                custList.add(new Customer(parsedVals[0], parsedVals[1], parsedVals[2], parsedVals[3]));}
          
            catch(IllegalArgumentException ex){
            
             System.out.println("Data in input file is not correct. Try again.");
              file =sim.getInputFile();
             custList = sim.checkFile(stops, file);
            
            
            
            
            }
            
            idArray.add(linesProcessed, parsedVals[0]);
            linesProcessed++;
            
            
         
        }
      
        boolean duplicates = false;
        for (int j = 0; j < idArray.size(); j++) {
            for (int k = j + 1; k < idArray.size(); k++) {
                if (k != j && idArray.get(k) == idArray.get(j)) {
                    duplicates = true;
                }
            }
        }
        
        
        
        if (duplicates) {
            System.out.println(" Duplicates: Data in input file is not correct. Try again.");

            File newfile = sim.getInputFile();
            sim.checkFile(stops, newfile);

        }

        return custList;
    }

}
