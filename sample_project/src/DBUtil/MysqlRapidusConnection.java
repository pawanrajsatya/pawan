package com.rapidus.urlread.DBUtil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlRapidusConnection {

	public static Connection getConnection(){
	
	try {
		String driver= null, jdbcURL= null,username= null,password = null;
		Properties prop = new Properties();
	        prop.load(new FileInputStream("src/ibatis.properties"));
	        driver  = prop.getProperty("driver");
	        jdbcURL = prop.getProperty("jdbcURL");
	        username= prop.getProperty("username");
	        password= prop.getProperty("password");
	        Class.forName(driver).newInstance();
	        Connection conn = DriverManager.getConnection(jdbcURL, username, password);
	        return conn;
	        
	} catch (Exception e) {return null;}
	
	
	}
	
	}
