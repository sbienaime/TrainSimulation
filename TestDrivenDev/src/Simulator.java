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
    static int stops = 0;
    static Simulator sim = new Simulator();
    static File file = null;
    static Scanner Reader;

    public static void main(String args[]) {

        sim.getStopsFromUser();
        sim.getInputFile();
        ArrayList<Customer> custList = sim.checkFile(stops, file);
        Train OrionExpress = new Train(stops, custList);
        OrionExpress.simulate();
       // try{OrionExpress.wait(200);}
       // catch(Exception ex){
        //System.out.println("Your system became unresponsive , starting simulatin over):  ");
       // }
        OrionExpress.displayStops();

    }

    public int getStopsFromUser() {
        String buffer = null;
        System.out.println("Enter number of stops the train has on its route (must be greater than 1):  ");

        while (notvalidated) {
            try {
                buffer = input.nextLine();
                stops = Integer.parseInt(buffer);

                if (stops < 0) {
                    throw new IllegalArgumentException();
                }
                notvalidated = false;
            } catch (NumberFormatException | NullPointerException nfe) {

                System.out.println("Invalid input, try again.");

            } catch (IllegalArgumentException x) {
                System.out.println("Number must be greater then zero. Try again ");

            }
        }

        return stops;
    }

    public File getInputFile() {
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

        } catch (NullPointerException ex) {
            System.out.println("Please enter a file name before pressing enter. Try again  ");
            path = input.nextLine();
        }

        return file;
    }

    public ArrayList<Customer> checkFile(int stops, File file) {
        int linesProcessed = 0;
        String customerdata = "";
        String[] dataArray;
        int[] parsedVals = new int[4];
        String delimiter = " ";
        ArrayList<Customer> custList = new ArrayList<Customer>();
        int[] idArray = new int[0];
        int lines = 0;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            while (reader.readLine() != null) {

                lines++;

            }
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }

            Reader = new Scanner(Simulator.file);

            idArray = new int[lines];
        } catch (FileNotFoundException ex) {

            System.out.println("File not found, try again. ");
            sim.getInputFile();
            sim.checkFile(stops, sim.getInputFile());
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (Reader.hasNextLine()) {

            customerdata = Reader.nextLine();
            dataArray = customerdata.split(delimiter);

            for (int i = 0; i < dataArray.length; i++) {
                try {

                    parsedVals[i] = Integer.parseInt(dataArray[i]);

                 

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

            custList.add(new Customer(parsedVals[0], parsedVals[1], parsedVals[2], parsedVals[3]));

            idArray[linesProcessed] = parsedVals[0];
            linesProcessed++;

        }

        boolean duplicates = false;
        for (int j = 0; j < idArray.length; j++) {
            for (int k = j + 1; k < idArray.length; k++) {
                if (k != j && idArray[k] == idArray[j]) {
                    duplicates = true;
                }
            }
        }
        if (duplicates) {
            System.out.println("Data in input file is not correct. Try again.");

            File newfile = sim.getInputFile();
            custList = sim.checkFile(stops, newfile);

        }

        return custList;
    }

}
