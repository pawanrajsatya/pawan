//before running this code need to start rc server
//goto command prompt to the location where the selenium server jar is present 
//cd D:\Projects
//java -jar selenium-server-standalone-2.20.0.jar -interactive


package com.rapidus.urlread.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.thoughtworks.selenium.DefaultSelenium;

public class Flipkart {

	/**
	 * @param args
	 */
	public static void ali(String s, ArrayList<String> ali, String url){
		StringTokenizer st = new StringTokenizer(s, "\n");
		int line = 0;
		ali = new ArrayList<String>();
		while(st.hasMoreTokens()) { 
			
			String key = st.nextToken();
			line++;
			if(line==7){
				StringTokenizer stSub = new StringTokenizer(key, "(");
				String nxtStSub = stSub.nextToken();
				ali.add(" Name: "+nxtStSub);
			}
			if(line==16){
				ali.add(" Description: "+key);
			}
			if(line==11||line==18||line==19){
				ali.add(key);
			}
			
		}
		int size = ali.size();
		System.out.println("");
		System.out.println("");
		for(int j = 0; j<size; j++){
			if(j!=size-1){
				System.out.println(ali.get(j)+"|");
			}else{
				System.out.println(ali.get(j)+"|"+url);
			}
		}
	}
	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
	    URL u = new URL(urlString); 
	    HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
	    huc.setRequestMethod("GET"); 
	    huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
	    huc.connect(); 
	    return huc.getResponseCode();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.jabong.com/");
		selenium.start();
		selenium.setTimeout("120000");
		
		String url = "http://www.jabong.com/";
		selenium.open(url);
		String[] s = selenium.getAllLinks();
		
			System.out.println("getAllLinks : "+Arrays.toString(s));	
			System.out.println("getAllFields : "+Arrays.toString(selenium.getAllFields()));
		
		
						
		selenium.stop();		
	}
	

}
