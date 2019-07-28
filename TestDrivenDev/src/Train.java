


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
	public void simulate()
	{
		int currStop = 1;   // current stop starts at 1
		int time = 1;  // current time
		boolean loopAgain = true;  

		while (loopAgain)
		{
			loopAgain = false;   // assume all customers are done
			boolean printed = false;  // controls printing headings
			
			for (int i=0; i < custList.size(); i++)  // process every customer
			{
				Customer cust = custList.get(i);  // get current customer
				if (cust.getStatus() != Customer.CUST_EXITED)  // at least one more customer to process
				{
					loopAgain = true;
				}
				
				// check if current customer already arrived at train and wants to enter at current stop
				if (cust.getStatus() == Customer.CUST_NOT_PROCESSED 
						&& cust.getArrival() <= time && cust.getEnter() == currStop)
				{
					if (!printed)
					     System.out.println("Current Time=" + time + " Current Stop=" + currStop);
					System.out.println("       Customer enters train: id="+cust.getId());
					cust.setStatus(Customer.CUST_ENTERED);
					custList.set(i, cust);  // update customer list with changed status
					printed = true;
				}
				
				// check if the current customer is in train and wants to exit at current stop
				if (cust.getStatus() == Customer.CUST_ENTERED && cust.getExit() == currStop)
				{
					if (!printed)
					    System.out.println("Current Time=" + time + " Current Stop=" + currStop);
					System.out.println("       Customer exits train: id="+cust.getId());
					cust.setStatus(Customer.CUST_EXITED); 
					custList.set(i, cust);  // update customer list with changed status
					printed = true;
				}
				
			}  // loop on customers
			
			if (currStop == stops){ 
				currStop = 1; } // if reached last stop, reset to first stop}
                        else{
				currStop++; 
                        
                        
                        }   // go to next stop
			
			if (printed)  // if there was something printed, we made a stop
			{
				madeStops++;  // update the number of stops made
				currTime = time;  // update time to process all customers
			}
			
			time++;  // increment current time
			
		}  // while more to process
	}

public void displayStops(){ 
System.out.println("Train made " + madeStops + " stops and it took " + currTime + " time units to process all customers");
}
}