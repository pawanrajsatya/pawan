package com.rapidus.urlread.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SysoutToFile {
   public static void main(String[] args) throws FileNotFoundException {

    //File file  = new File("/home/sajid/sysout.log");
    File file  = new File("sysout.log");
    PrintStream printStream = new PrintStream(new FileOutputStream(file));
    PrintStream printStreamOrig = (System.out);
    System.out.println('1');

    System.setOut(printStream);
    System.out.println("2");
    System.setOut(printStreamOrig);
    System.out.println("3");
    System.setOut(printStream);
    System.out.println("4");
    

}
   public static void sysout2File(){
	   //check out the log file in location '<workspace>/sysout.log'
	   PrintStream printStream = null;
	   File file  = new File("sysout.log");
	    try {
			printStream = new PrintStream(new FileOutputStream(file, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		System.setOut(printStream);
   }
   public static void sysout2Console(){
	    //check out the remaining log file in console
	   	PrintStream printStream = System.out;
	   	System.setOut(printStream);
   }
}