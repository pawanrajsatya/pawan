////def sdf = new java.text.SimpleDateFormat("yyyy-MM-dd")
//def sdf = ReachableSoapTest.getName("Pawan")
//log.info("ret value " + sdf) // returns the current testCase handle
////return sdf.format( new Date())


import java.io.*;
import java.net.*;

public class ReachableSoapTest
{
public static void main(String args[])
{
try
{
InetAddress address = InetAddress.getByName("173.194.32.104");
System.out.println("Reach: " + address.isReachable(3000));
}//try
catch (UnknownHostException e)
{
System.err.println("Unable to connect");
}//catch-1
catch (IOException e)
{
System.err.println("Unable to connect");
}//catch-2
}//main()


public static String getName(String x)
{
x = "My Name is "+x;
return x;
}

}//class