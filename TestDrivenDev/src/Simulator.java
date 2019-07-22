/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author remyb
 */
public class Simulator {
Scanner input = new Scanner(System.in);
boolean valid=false;


    public int getStopsFromUser() {
        int stops=0;
        System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");
        
        while(valid == false){
        try{
        
        stops =input.nextInt();
        
        valid = true;
        
        }
        
        catch(Exception ex ) {
        
        System.out.println("Invalid input, try again.");
        stops=input.nextInt();
        }
        }
        
       return stops;
    }

    
    
    
    public File getInputFile() {
        valid = false;
        File file = null;
        while (valid == false) {

            System.out.println("Enter absolute path for data file or for default (C:/train/customer-data.txt) press Enter:   ");
            try {
                String path = input.nextLine();
                file = new File("path");

            
            
            
            
            
            } catch (Exception ex) {
                
              
              System.out.println("File not found, try again. ");
              input.nextLine();
            }

        }

        return file;
    }

     
     
    public ArrayList<Customer> checkFile(int i, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
}
