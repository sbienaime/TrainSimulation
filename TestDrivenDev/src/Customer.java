


public class Customer {
private int id;
private int arrival;
private int enter;
private int exit;
private int status;
public static final int CUST_NOT_PROCESSED = 0; 
public static final int CUST_ENTERED = 1; 
public static final int CUST_EXITED = 2; 

public Customer(int id, int arrival, int enter, int exit) 
{
    
    
    
    
    if(id<=0| arrival<=0 | enter<=0 | exit <=0){
    
    throw new  IllegalArgumentException(" All customer attributes must be greater than or equal to zero: Try again. ");
    
    
    
    }
    
    
   
this.id = id;
this.arrival = arrival;
this.enter = enter;
this.exit = exit;
this.status = CUST_NOT_PROCESSED;








}
public int getId()
{ 
    
    
return this.id;
}
public int getArrival()         
{ 
return this.arrival;
}
public int getEnter()
{ 
return this.enter;
}
public int getExit()
{
return this.exit;
}
public int getStatus()
{ 
return this.status;
}






public void setStatus(int status)
{ 
    
if(status !=0 & status !=1 & status !=2){

 throw new  IllegalArgumentException(" Status must be integer value of 1,2, or 3; no other values are acceptable. ");

}
this.status = status;
}
}



