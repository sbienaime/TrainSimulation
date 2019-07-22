


public class Customer {
int id;
int arrival;
int enter;
int exit;
int status;
public static final int CUST_NOT_PROCESSED = 0; 
public static final int CUST_ENTERED = 1; 
public static final int CUST_EXITED = 2; 

public Customer(int id, int arrival, int enter, int exit) 
{
this.id = id;
this.arrival = arrival;
this.enter = enter;
this.exit = exit;
this.status = CUST_NOT_PROCESSED;
}
public int getId()
{ 
return id;
}
public int getArrival()
{ 
return arrival;
}
public int getEnter()
{ 
return enter;
}
public int getExit()
{
return exit;
}
public int getStatus()
{ 
return status;
}
public void setStatus(int status)
{ 
this.status = status;
}
}



