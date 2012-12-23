package com.rapidus.urlread.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupHtmlParser
{
	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
	    URL u = new URL(urlString); 
	    HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
	    huc.setRequestMethod("GET"); 
	    huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
	    huc.connect(); 
	    return huc.getResponseCode();
	}
	
	public static void main(String[] args)
	{
		String j = null; String strSub= null; String url = null;
				Document doc = null;
				for(Double i=1.0000119; i<=1.9999999; i+=0.0000001){
					j = i.toString().concat("H").replace('H', '0');
					strSub= j.substring(2,9);
					url = "http://www.imdb.com/title/tt"+strSub+"/";
					SysoutToFile.sysout2File();
					int response = 404;
					try{
						response = getResponseCode(url);
					}
					catch (Exception e) {
						// TODO: handle exception				
					}
					if(response==200){
						try {
							doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)").timeout(20000).get();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					
						//print url
						System.out.println("@URL: "+url.substring(7, url.length()));
						//to get image href
						Element checkEmptyImg = doc.getElementById("img_primary");
						if(checkEmptyImg!=null){
							String imgPrimary = doc.getElementById("img_primary").html();
							String imgPrimaryHref = imgPrimary.replace("\"", "'");
							Pattern p = Pattern.compile("\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])",Pattern.CASE_INSENSITIVE);
					        Matcher m = p.matcher(imgPrimaryHref);
					        boolean result = m.find();
					        while (result) {
					            for (int iTtemp = 1; iTtemp <= m.groupCount(); iTtemp++) {
					                String imageHref =m.group(iTtemp);
					                System.out.println("@HREF: "+imageHref );
					            }
					            result = m.find();
					        }
						}else{
							System.out.println("@HREF: ");
						}
						
				        //to get header title
						Elements checkEmptyHeader = doc.getElementsByClass("header");
						if(checkEmptyHeader!=null){
							String header = doc.getElementsByClass("header").first().text();
							String subHeader = header.substring(0, header.length()-6);
							System.out.println("@TITLE: "+subHeader);
							String year = header.substring(header.length()-5, header.length()-1);
							System.out.println("@YEAR: "+year);
						}else{
							System.out.println("@TITLE: \n@YEAR: ");
						}
						
						//movie info
						Elements checkEmptyInfobar = doc.getElementsByClass("infobar");
						if(checkEmptyInfobar!=null){
							String infobar = doc.getElementsByClass("infobar").text();
							System.out.println("@INFO: "+infobar);
						}else{
							System.out.println("@INFO: ");
						}
						
						//ratings
						Elements checkEmptySBDetails = doc.getElementsByClass("star-box-details");
						if(checkEmptySBDetails!=null){
							String star_box_details = doc.getElementsByClass("star-box-details").text();
							String[] starBoxSplit = star_box_details.split("users");
							String starBoxOne = starBoxSplit[0].concat("users");
							System.out.println("@"+starBoxOne);
						}else{
							System.out.println("@Ratings: ");
						}
						
						//para description
						Elements checkOverviewTop = doc.getElementsByClass("overview-top");
						if(checkOverviewTop!=null){
							Element overview_top = doc.getElementById("overview-top");	
							String desc;
							Elements eleP = overview_top.getElementsByTag("p");
							Iterator<Element> itP = eleP.iterator();
							while (itP.hasNext()){
								desc = itP.next().text();
								if(desc.length()!=0)
								System.out.println("@DESCRIPTION: "+desc);		
							}	
						}else{
							System.out.println("@DESCRIPTION: ");
						}
						
					}else{
					}
					
					//To get other details
					System.out.print("@Others: ");
					String desc;
					Elements txtBlocks = doc.getElementsByClass("txt-block");
					Iterator<Element> itTB = txtBlocks.iterator();
					while (itTB.hasNext()){
						desc = itTB.next().text();
						if(desc.length()!=0)
							if(!(desc.endsWith("»"))){
								System.out.print(desc+"; ");		
							}
								
					}
					System.out.println("");	
				}
				
	
				/*Element link = doc.select("a").first();
				String relHref = link.attr("href"); // == "/"
				String absHref = link.attr("abs:href"); // "http://jsoup.org/"
				*/ 
				//m.imdb
				/*
				Elements mainInfo = doc.getElementsByClass("mainInfo");
				Elements votes = doc.getElementsByClass("votes");
				Elements retina_capable = doc.getElementsByClass("retina-capable");
				Elements topCast_posters = doc.getElementsByClass("topCast");
				Elements topCrew = doc.getElementsByClass("topCrew");
				Elements details = doc.getElementsByClass("details");
				Elements reviews = doc.getElementsByClass("reviews");
				System.out.println("mainInfo:"+mainInfo.html()+"\nvotes:"+votes.text()+"\nretina_capable:"+retina_capable.outerHtml()+"\ntopCast_posters:"+topCast_posters.toString()+"\ntopCrew:"+topCrew.val()+"\ndetails:"+details.first().text()+"\n"+details.iterator().next().text()+"\nreviews:"+reviews.text());
				*/
				//doc.hasClass("infobar");
	}
}
