package com.rapidus.urlread.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.rapidus.urlread.DBUtil.DBquery;
import com.rapidus.urlread.DBUtil.Queries;

public class Service {
	
	private static final Logger logger = Logger.getLogger(Service.class.getName());
	
	public String clipURL(String fullURL){		
		String clippedURL = fullURL.substring(1, 3);
		return clippedURL;		
	}

	public List<String> evaluateURL(String URL){
		logger.info("url : " + URL);
		
		/*String clippedURL = clipURL(URL);
		logger.info("clipped url : " + clippedURL);*/
		List<String> lsf = fetchResult(URL);
		return lsf;
	}
	
	public List<String> fetchResult(String clippedURL){
		List<String> ls = null;
		String columnName = "state";
		String sql = Queries.stateFetch; 
		sql += " where s.countryid = "+clippedURL;
		logger.info("sql : "+sql);
		ls = DBquery.getStringforColumnName(sql, columnName);
		return ls;		
	}
	
	
	
}
