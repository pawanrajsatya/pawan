package com.rapidus.urlread.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

public class ReadBlobPicture {
  public static void main(String[] args) throws Exception {
	  
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

    String sql = "SELECT id, description, photo FROM pictures";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet resultSet = stmt.executeQuery();
    String name = "name.jpeg";
    while (resultSet.next()) {
      int id = Integer.parseInt(resultSet.getString(1));
      String description = resultSet.getString(2);
      
      File image = new File(name);
      name+="name.jpeg";
      FileOutputStream fos = new FileOutputStream(image);

      byte[] buffer = new byte[1];
      InputStream is = resultSet.getBinaryStream(3);
      while (is.read(buffer) > 0) {
        fos.write(buffer);
      }
      fos.close();
    }
    conn.close();
  }
}