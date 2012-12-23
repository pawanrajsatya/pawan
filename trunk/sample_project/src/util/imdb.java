package com.rapidus.urlread.util;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class imdb extends SeleneseTestCase {
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.imdb.com/title/tt0000001/");
		selenium.start();
	}

	@Test
	public void testImdb() throws Exception {
		selenium.open("/title/tt0000001/");
		String strTable = selenium.getTable("title-overview-widget-layout.0.1");
		String strTitle = selenium.getTitle();
		boolean title = selenium.isTextPresent("Carmencita (1894)");
		boolean Details = selenium.isTextPresent("1 min  -  Documentary | Short");
		boolean Rating = selenium.isTextPresent("5.7");
		boolean Rating_Details = selenium.isTextPresent("exact:Ratings: 5.7/10 from 667 users   \nReviews: 9 user | 1 critic");
		boolean Description = selenium.isTextPresent("Performing on what looks like a small wooden stage, wearing a dress with a hoop skirt and white high-heeled pumps, Carmencita does a dance with kicks and twirls, a smile always on her face.");
		System.out.println("strTable : "+strTable+" \nstrTitle : "+strTitle);
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
