//svn checkout https://pawan.googlecode.com/svn/trunk/ pawan --username 88pawankumarjha@gmail.com
//machine code.google.com login 88pawankumarjha@gmail.com password cX2JR8fc4pm2
//add -N D:\Users\pawan\workspace\sample_project\src\toOffice\ParseCSVFile.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ParseCSVFile {

	public static void main(String[] args) {
		//csv file to be present in the root path 
		ArrayList<String> aList = ParseCSVFile.readFile("Book1.csv", ",");
		for (Iterator iterator = aList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);	
		}
	}

	//read csv file with parameters FileName and the strLine replace method 
	public static ArrayList<String> readFile(String strFile, String replaceVariable){
		try
		{   //create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine= "";
			StringTokenizer sToken = null;
			ArrayList<String> aList=new ArrayList<String>();
			//to skip the header
			strLine = br.readLine();
			//read comma separated file line by line
			while( (strLine = br.readLine()) != null){
				sToken = new StringTokenizer(strLine, replaceVariable);
				while(sToken.hasMoreTokens()){
					//to skip the 1st and 3rd columns
					sToken.nextToken();
					String nextToken = sToken.nextToken();
					aList.add(nextToken);
				}
			}
			return aList;
		}
		catch(Exception e){
			System.out.println("Exception while reading csv file: (csv file to be present in the root path) " + e);
			return null;	
		} 
	}
} 