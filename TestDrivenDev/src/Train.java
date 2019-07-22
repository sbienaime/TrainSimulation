


import java.util.ArrayList;

public class Train {

int stops; 
int madeStops; 
int currTime; 
ArrayList<Customer> custList;

public Train(int stops, ArrayList<Customer> list) 
{
this.stops = stops;
this.custList = list;
this.madeStops = 0;
this.currTime = 0;
}
public void simulate(){ 
int currStop = 1; 
int time = 1; 
boolean loopAgain = true;
while (loopAgain)
{
loopAgain = false; 
boolean printed = false; 
for (int i=0; i < custList.size(); i++) 
{
Customer cust = custList.get(i); 
if (cust.getStatus() != Customer.CUST_EXITED) 
{
loopAgain = true;
}

if (cust.getStatus() == Customer.CUST_NOT_PROCESSED
&& cust.getArrival() <= time && cust.getEnter() == currStop)
{
if (!printed)
System.out.println("Current Time=" + time + " Current Stop=" + currStop);
System.out.println(" Customer enters train: id="+cust.getId());
cust.setStatus(Customer.CUST_ENTERED);
custList.set(i, cust); 
printed = true;
}

if (cust.getStatus() == Customer.CUST_ENTERED && cust.getExit() == currStop)
{
if (!printed)
System.out.println("Current Time=" + time + " Current Stop=" + currStop);
System.out.println(" Customer exits train: id="+cust.getId());
cust.setStatus(Customer.CUST_EXITED);
custList.set(i, cust); 
printed = true;
}
} 
if (currStop == stops)
currStop = 1; 
else
currStop++; 
if (printed) 
{
madeStops++; 
currTime = time; 
}
time++; 
} 
}
public void displayStops(){ 
System.out.println("Train made " + madeStops + " stops and it took " + currTime + " time units to process all customers");
}
}