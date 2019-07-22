/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSimulation;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author remyb
 */
public class Simulator {
Scanner input = new Scanner(System.in);
 
 
 
 
 
 
 
    public void getStopsFromUser() {
        
        boolean valid=false;
        
        
        System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");
        
        while(valid == false)
        try{
        
        int stops =input.nextInt();
        
        valid = true;
        
        }
        
        catch(Exception ex ) {
        
        System.out.println("Invalid input, try again.");
        
        }
       
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public File getInputFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Customer> checkFile(int i, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
}
