


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class TrainSimulator {
	
static final String FILE_PATH = "customer-data.txt";
public static void main(String args[])
{
Scanner keyboard = new Scanner(System.in);
Scanner fileReader = null;
String filePath = "";
int numStops = 0;
ArrayList<Customer> customers = new ArrayList<Customer>();
while(true)
{
System.out.print("Enter number of stops the train has on its route (must be greater than 1): ");
numStops = keyboard.nextInt();
if(numStops < 1)
System.out.println("Invalid input, try again.");
else
break;
}

while(true)
{
System.out.print("Enter absolute path for data file or for default (C:/train/customer-data.txt) press Enter:");
filePath = keyboard.nextLine();


try {
if(filePath.equals(""))
fileReader = new Scanner(new File(FILE_PATH));
else fileReader = new Scanner(new File(filePath));
} catch (FileNotFoundException e) {
System.out.println("File not found, try again. ");
continue;
}
break;
}
while(fileReader.hasNextLine())
{

String line = fileReader.nextLine();
String tokens[] = line.split("\\s+");
customers.add(new Customer(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3])));
}
fileReader.close();
Train train = new Train(numStops, customers);
train.simulate();
train.displayStops();
}
}
