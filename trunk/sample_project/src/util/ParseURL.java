package com.rapidus.urlread.util;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ParseURL {
    public static void main(String[] args) throws Exception {
        //URL aURL = new URL("http://example.com:80/docs/books/tutorial"+ "/index.html?name=networking#DOWNLOADING");
    	URL aURL = new URL("http://docs.oracle.com/javase/tutorial/networking/urls/urlInfo.html");
        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
    }
    public Map<String, String> getParsedURL(URL aURL){
    	Map<String, String> tmURL = new TreeMap<String, String>();
    	tmURL.put("protocol",aURL.getProtocol());
    	tmURL.put("authority",aURL.getAuthority());
    	tmURL.put("host",aURL.getHost());
    	tmURL.put("port",String.valueOf(aURL.getPort()));
    	tmURL.put("path",aURL.getPath());
    	tmURL.put("query",aURL.getQuery());
    	tmURL.put("filename",aURL.getFile());
    	tmURL.put("ref",aURL.getRef());
    	SysoutToFile s2f = new SysoutToFile();
    	s2f.sysout2File();
    	System.out.println("");
    	System.out.println("You have accessed the following link on Date : "+new Date());
    	System.out.println(aURL.getProtocol()+"://"+aURL.getAuthority()+aURL.getFile());
    	System.out.println("");
    	System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
        s2f.sysout2Console();
		return tmURL;
    	
    }
}