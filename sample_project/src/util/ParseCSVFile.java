package com.rapidus.urlread.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class ParseCSVFile {
	public ArrayList<String> alist_return ;

	public ArrayList<String> getAlist_return() {
		return alist_return;
	}
	public void setAlist_return(ArrayList<String> alist_return) {
		this.alist_return = alist_return;
	} 

	public ParseCSVFile() {
		this.alist_return = null;
	}
	public ParseCSVFile(ArrayList<String> alist_return) {
		this.alist_return = alist_return; 
	}
	//read csv file with parameters FileName and the strLine replace method 
	public String[] readFile(String strFile, int replaceType){
		try
		{   //create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine,strLineReplace  = "";
			StringTokenizer st = null;
			int tokenNumber =0;
			ArrayList<String> alist=new ArrayList<String>();
			ArrayList<String> ar=new ArrayList<String>();

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null)
			{
				//read file1 in replace style 1
				if (replaceType == 1){
					//break comma separated line using "}"
					st = new StringTokenizer(strLine, "}");
				}
				else if (replaceType == 2){
					strLineReplace = strLine.replace("}","|");
					//break comma separated line using "|"
					st = new StringTokenizer(strLineReplace, "|");
				}
					while(st.hasMoreTokens())
					{
						//display csv values
						tokenNumber++;
						String nextToken = st.nextToken();
						alist.add(nextToken);
						if (replaceType == 1){
							ar.add(nextToken); 
						}else if (replaceType == 2){
							//pick every third column 
							if(tokenNumber%3==0){
							ar.add(nextToken);
						}
					}
					//reset token number
					tokenNumber = 0;
				}
			}
			//passing the alist object through constructor
			setAlist_return(alist);
			//System.out.println("set");
			String[] ar_list = new String[ar.size()];     
			ar_list = ar.toArray(ar_list);
			return ar_list;
		}
		catch(Exception e)
		{
			System.out.println("Exception while reading csv file: " + e);
			return null;	
		} 
	}
	
	//method to compare two arrayLists and return the list of mismatching IPs from alList2
	public List<String> compareArrays(ArrayList<String> alList1, ArrayList<String> alList2){
		List<String> lDiff = new ArrayList<String>();;
	    int iSize1 = alList1.size();
	    int iSize2 = alList2.size();
	    int iGetvar = 2;
	    for (int i=0; i<iSize1; i++){
	    	while( iGetvar<iSize2){
	    		if(!(alList1.contains(alList2.get(iGetvar)))){
	    			lDiff.add(alList2.get(iGetvar));
	    		}
	    		iGetvar = iGetvar+3;
	    	}
	    } 
	    return lDiff;
	} 
	
	//method returns arrayList object used for displaying the report
	public ArrayList<String> formArrayList(Iterator<String> it_list, List<String> diff_sevone){
		ArrayList<String> array_list_sevone = new ArrayList<String>();
		String s1= ""; String s2= ""; String s3 ="";
	    while(it_list.hasNext()){
	    	s1 = it_list.next().toString();
	    	if(it_list.hasNext()){
		    	s2 = it_list.next().toString();
		    	s3 = it_list.next().toString();
			    if(diff_sevone.contains(s3)){
					array_list_sevone.add(s1);
					array_list_sevone.add(s2);
					array_list_sevone.add(s3);
				}
	    	}
	    }
	    return array_list_sevone;
	}	
} 