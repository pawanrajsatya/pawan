package com.rapidus.urlread.util;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame 
{ 
public Main() 
{ 
setSize(600,600); 
JPanel panel = new JPanel(new GridLayout(2,2)); 
panel.setBackground(Color.BLACK); 
ImageIcon icon = new ImageIcon("name.jpeg"); 
JLabel label = new JLabel();
JLabel label1 = new JLabel();
label1.setText("Image:");
label.setIcon(icon); 
panel.add(label); 
this.getContentPane().add(panel); 
setVisible(true); 
} 


public void mainText(){

JFrame f=new JFrame();
JPanel panel;
JLabel label1,label2;
final JTextField text1,text2;
label1 = new JLabel();
label1.setText("Name:");
text1 = new JTextField(20);
label2 = new JLabel();
label2.setText("Address:");
text2 = new JTextField(20);
panel=new JPanel(new GridLayout(2,2));
panel.add(label1);
panel.add(text1);
panel.add(label2);
panel.add(text2);
f.add(panel); String driver= null, jdbcURL= null,username= null,password = null;
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
Class.forName(driver);
Connection conn = DriverManager.getConnection(jdbcURL, username, password);
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from city where cityid=1");
        while(rs.next()){
        text1.setText(rs.getString("stateid"));
        text2.setText(rs.getString("city"));
        }
}
catch(Exception e){}
f.pack();
f.setVisible(true);
}


public static void main (String[] args) // no args expected 
{ 
Main main = new Main(); 
main.mainText();
} // end main 
} // end class ImageDisplay