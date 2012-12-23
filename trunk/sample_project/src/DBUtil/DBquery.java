package com.rapidus.urlread.DBUtil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBquery {

	private static final Logger logger = Logger.getLogger(DBquery.class.getName());
	
	public static List<String> getStringforColumnName(String sql, String columnName)
	{ 
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 String driver= null, jdbcURL= null,username= null,password = null;
		  Properties prop = new Properties();
	      try {
	              prop.load(new FileInputStream("src/ibatis.properties"));
	              driver  = prop.getProperty("driver");
	              jdbcURL = prop.getProperty("jdbcURL");
	              username= prop.getProperty("username");
	              password= prop.getProperty("password");
	              
	      } catch (Exception e) {
	      }
		try{
			
	        Class.forName (driver).newInstance ();
	        connection = DriverManager.getConnection (jdbcURL, username, password);
	        logger.info("Database connection established");	
				
				stmt = connection.prepareStatement(sql);
				rs=stmt.executeQuery();
				List<String> ar=new ArrayList<String>();
				
				while(rs.next())
				{
				    String column = rs.getString(columnName);
				    ar.add(column);
				}
				return ar;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(rs);
			close(stmt);
			close(connection);
		}
		return null;
	}
	
	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			// ignore
		}
	}

	public static void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			// ignore
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// ignore
		}
	}
}
