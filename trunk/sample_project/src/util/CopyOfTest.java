package com.rapidus.urlread.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyOfTest {

/*	// Pattern for recognizing a URL, based off RFC 3986
	private static final Pattern urlPattern = Pattern.compile(
	        "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
	                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
	                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
	        Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);*/
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*//try catch example
		  for( int i=0; i<10; i++ ) {
			 try {
			  System.out.println( i );
			  throw new Exception( "boom!" );
			 } catch( Exception x ) {
			  System.out.println( x );
			  // execution still does not return into the try block, but
			  // the loop continues and a new try block is entered on the
			  // next pass.
			 }*/
		
		/*Matcher matcher = urlPattern.matcher("foo bar http://example.com baz");
		while (matcher.find()) {
		    int matchStart = matcher.start(1);
		    int matchEnd = matcher.end();
		    System.out.println(matchStart+" "+matchEnd);
		    // now you have the offsets of a URL match
		}
		*/
		
		//to get URL from string using REGEX
		/*String strTop = "\"hi\"";
		System.out.println(strTop);
		String strBot = strTop.replace("\"", "'");
		System.out.println(strBot);
		String str = "<a onclick='(new Image()).src='/rg/title-overview/primary/images/b.gif?link=%2Fmedia%2Frm2527113728%2Ftt0000001';' href='/media/rm2527113728/tt0000001'><img src='http://ia.media-imdb.com/images/M/MV5BMjAzNDEwMzk3OV5BMl5BanBnXkFtZTcwOTk4OTM5Ng@@._V1._SY317_CR8,0,214,317_.jpg' style='max-width:214px; max-height:317px;' height='317' alt='Carmencita Poster' title='Carmencita Poster' itemprop='image' /></a>";
		Pattern p = Pattern.compile("\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        boolean result = m.find();
        while (result) {
            for (int i = 1; i <= m.groupCount(); i++) {
                String url=m.group(i);
                System.out.println(url);
            }
            result = m.find();
        }
        String s = "asdasdasProduction Co: Edison Manufacturing Company See more »";
        System.out.println(s.endsWith("» "));*/
				
		//pattern matcher to get pid=1234
		String testHtmlOrig = "href=\"/m/jungle-book-8129106787/p/9788129106780?pid=9788129106780\">106780?pid=asdf<";
		String testHtml = testHtmlOrig.replace("\"", "'");
		//Pattern p = Pattern.compile("\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])");
		//@"\*\d*\.txt"
		String testHtml2 = "hi (22)";
		//Pattern p = Pattern.compile("pid=[\\d*|\\w*]*");
		Pattern p2 = Pattern.compile("\\(.+?\\)");
		Matcher m = p2.matcher(testHtml2);
		List<String> idList = new ArrayList<String>();
		String idFull;
		boolean result = m.find();
		while (result) {
                idFull =m.group(0);
                //String id = idFull.replaceAll("pid=", "");
                String id2 = idFull.replace("(", "").replace(")", "");
                idList.add(id2);
                result = m.find();
        }
		System.out.println(idList.toString());
		System.out.println(idList.get(0));
		
		
		//try to edit class in JVM
		/*try{
		for(int iCount=0; iCount<10; iCount++){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        System.out.print("Enter which method:");
	            int i = Integer.parseInt(br.readLine());
	            System.out.println(i);
	            if(i==1){
	            	Called.calledMethod1();
	            }
	            else{
	            	Called.calledMethod2();
	            }
	        }

		}catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		*/
		
		
		/*
		 * reflection api to load class dynamically using class.forname 
		 * */
		
		/*try{
			for(int iCount=0; iCount<10; iCount++){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter which class:");
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		String className = br.readLine();
		int results = compiler.run(null, null, null, className+".java");
		if(results == 0){

		    Class clazz = Class.forName(className+".class");
		    System.out.print("Compiled successfully.Enter which method:");
		    Object returnValue=clazz.getMethod(br.readLine()).invoke(null);
		    //D:\Users\pawan\workspace\ urlread\src\com\rapidus\ urlread\ util\Called
		}
		
			}
		}catch(Exception e){
            System.err.println("Exception : "+ e);
        }*/
		
		
		/*for (int j = 0 ; j<3; j++){
			
		
		long iTime = System.currentTimeMillis();
		String url = "http://www.flipkart.com/search/a/all?query=dell+vostro";
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)").timeout(20000).get();
		long jTime = System.currentTimeMillis();
		System.out.print("flipkart : ");
		System.out.println(jTime-iTime);
		
		url = "http://www.timtara.com/search.php?search_query=dell+vostro";
		Document doc2 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)").timeout(20000).get();
		long kTime = System.currentTimeMillis();
		System.out.print("timtara : ");
		System.out.println(kTime-jTime);
		
		
		
		url = "http://www.yebhi.com/searchAll.aspx?q=dell+vostro";
		Document doc3 = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)").timeout(20000).get();
		long lTime = System.currentTimeMillis();
		System.out.print("yebhi : ");
		System.out.println(lTime-kTime);
		
		
		//System.out.println(doc.getElementById("banner").html());
		}*/
		
			}
	}
	

