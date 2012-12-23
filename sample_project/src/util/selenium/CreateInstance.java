//before running this code need to start rc server
//goto command prompt to the location where the selenium server jar is present 
//cd D:\Projects
//java -jar selenium-server-standalone-2.20.0.jar -interactive

package com.rapidus.urlread.util.selenium;
/**
 * Google.java
 * Open Google webpage and search for "Selenium RC".
 * @Author: Xuan Ngo
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rapidus.urlread.util.SwingTextAndImage;
import com.thoughtworks.selenium.DefaultSelenium;
 
public class CreateInstance
{
  public static void main(String[] args)
  {
	  
    final String sServerHost  = "localhost";
    final int iServerPort     = 4444;
    final String sBrowserType = "*firefox";
    final String sBaseUrl     = "http://www.google.ca/";
 
    DefaultSelenium oDefaultSelenium = new DefaultSelenium(sServerHost, iServerPort, sBrowserType, sBaseUrl);
    oDefaultSelenium.start();           // Start Selenium.
    oDefaultSelenium.setSpeed("1000");  // Wait 1 second for every instructions so that you can see what Selenium is doing.
    oDefaultSelenium.openWindow("", "selWindow");
    // Open the main google webpage.
    //oDefaultSelenium.open("http://www.google.ca/index.html"); 
    oDefaultSelenium.open("file:///D:/Users/pawan/Desktop/POC/country");
    // Type "Selenium RC" into the search input field.
    //oDefaultSelenium.type("name=q", "Selenium RC"); // Use name locator to identify the search input field.
 
    // Click on "Google Search" button
    //oDefaultSelenium.click("xpath=//input[@name='btnG']");
    //window.onhashchange = funcRef();
    /*<script>
    var oldLocation = location.href;
    setInterval(function() {
         if(location.href != oldLocation) {
              do your action
              oldLocation = location.href
         }
     }, 1000); // check every second
    </script>*/
    String strLocation = oDefaultSelenium.getLocation();
    JFrame f=new JFrame();
	JPanel panel=new JPanel();
	JTextField text1 = new JTextField();
	f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
	new SwingTextAndImage().mainImage("country", f, panel, text1);
    while(true){
    	String strNewLocation = oDefaultSelenium.getLocation();
    	//new SwingTextAndImage().mainImage("city");
    	if(strLocation.equals(strNewLocation)){
    		System.out.println("same location : "+strNewLocation);
    		
    	}else{
    		strLocation = oDefaultSelenium.getLocation();
    		String[] str = oDefaultSelenium.getLocation().split("/");
    		int length = str.length;
    		String tableName = str[length-1];
    		System.out.println("new location : "+strLocation);
    		new SwingTextAndImage().mainImage(tableName, f, panel, text1);
    	}    	
    }
    // Close the browser.
    //System.out.println("leaving");
    //oDefaultSelenium.stop();
  }
}