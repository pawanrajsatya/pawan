package com.rapidus.urlread.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class InsertPictureToMySql {
  public static void main(String[] args) throws Exception, IOException, SQLException {
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
   
      Class.forName(driver);
    Connection conn = DriverManager.getConnection(jdbcURL, username, password);
    String INSERT_PICTURE = "insert into pictures(description, photo) values (?, ?)";

    FileInputStream fis = null;
    PreparedStatement ps = null;
    try {
      conn.setAutoCommit(false);
      File file = new File("D:\\Users\\pawan\\Pictures\\2012-02\\beer.jpeg");
      fis = new FileInputStream(file);
      ps = conn.prepareStatement(INSERT_PICTURE);
      Date date = new Date();
      
      ps.setString(1, date.toString());
      ps.setBinaryStream(2, fis, (int) file.length());
      ps.executeUpdate();
      conn.commit();
    } finally {
      ps.close();
      fis.close();
    }
  }
}