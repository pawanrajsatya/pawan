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
import java.util.StringTokenizer;

import com.thoughtworks.selenium.DefaultSelenium;

public class Mimdb_test {

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
				String nxtStSub2 = stSub.nextToken();
				ali.add("Name:"+nxtStSub);
				if(nxtStSub2.length()>=4){
					ali.add("Year:"+nxtStSub2.substring(0,4));	
				}else{
					
				}				
			}
			if(line==16){
				ali.add("Description:"+key);
			}
			if(line==11||line==18||line==19){
				ali.add(key);
			}
			
		}
		int size = ali.size();
		for(int j = 0; j<size; j++){
			if(j!=size-1){
				System.out.println(ali.get(j));
			}else{
				System.out.println(ali.get(j)+"\nURL:"+url);
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
		DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://m.imdb.com/");
		selenium.start();
		selenium.setTimeout("220000");
		for(Double i=1.0000001; i<=1.9999999; i+=0.0000001){
			String strSub= i.toString().substring(2,9);
			String url = "http://m.imdb.com/title/tt"+strSub+"/";
			//SysoutToFile.sysout2File();
			int response = 404;
			try{
				response = getResponseCode(url);
			}
			catch (Exception e) {
				// TODO: handle exception				
			}
			//TODO
				if(response==200){
					selenium.open(url);
					String title=selenium.getTitle();
		    		System.out.println(title);
		    		//JSoupHtmlParser
					String ratingUsers=selenium.getText("ratingUsers");
		    		System.out.println(ratingUsers);
		    		String s2=selenium.getText("details");
		    		System.out.println(s2);
		    		ArrayList<String> ali = new ArrayList<String>();
					//ali(s, ali, url);
				}
				else{
				}
	    }
		selenium.stop();		
	}
}