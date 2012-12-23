package com.rapidus.urlread.util;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SwingTextAndImage extends JFrame 
{ 

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
f.add(panel);
try{
Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root" );
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

public void mainImage(){
	JFrame f=new JFrame();
	JPanel panel;
	JLabel label1,label2,lPhoto,label3;
	final JTextField text1,text2;
	label1 = new JLabel();
	label1.setText("Name:");
	text1 = new JTextField(20);
	label2 = new JLabel();
	label2.setText("Address:");
	text2 = new JTextField(20);
	label3 = new JLabel();
	label3.setText("Image:");
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root" );
		        Statement st=conn.createStatement();
		        ResultSet rs=st.executeQuery("select * from city where cityid=1");
		        while(rs.next()){
		        text1.setText(rs.getString("stateid"));
		        text2.setText(rs.getString("city"));
		        }
		}
		catch(Exception e){}
	try
    {
    Class.forName("com.mysql.jdbc.Driver");
 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rapidus","root","root");  
 Statement st=con.createStatement();   
 ResultSet rs = st.executeQuery( "select  photo from pictures where id='1'") ;
 while(rs.next()) 
 {
 byte[] imagedata = rs.getBytes("photo") ;
 Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
 ImageIcon icon =new ImageIcon(img);
 lPhoto = new JLabel(icon) ;
 setLayout(null);                              // BYTES TO IMAGE                                                                      
 
 panel=new JPanel();

 panel.add(label1);
	panel.add(text1);
	panel.add(label2);
	panel.add(text2);
	panel.add(label3);
	panel.add(lPhoto) ;
	
	
	
 //lPhoto.setBounds(301,41,150,200);
 f.add(panel);
 f.setSize(600, 600); 
 //f.pack();
 f.setVisible(true);
}
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public void updatetext2(String tableName,JFrame f, JPanel panel,JTextField text2){
	
try{
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root" );
	        Statement st=conn.createStatement();
	        	ResultSet rs=st.executeQuery("select * from "+tableName+" limit 1");
		        while(rs.next()){
		        //text11.setText(rs.getString("stateid"));
		        text2.setText(rs.getString(tableName));
		        }	
	}
	catch(Exception e){}
}
public void updatetext1(String tableName,JFrame f, JPanel panel,JTextField text1){
	
try{
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root" );
	        Statement st=conn.createStatement();
	        	ResultSet rs=st.executeQuery("select * from "+tableName+" limit 1");
		        while(rs.next()){
		        text1.setText(rs.getString(tableName+"id"));
		        }	
	}
	catch(Exception e){}
}
public void mainImage(String tableName, JFrame f, JPanel panel, JTextField text1){
	//JFrame f=new JFrame();
	//JPanel panel=new JPanel();
	JLabel label1,label2,lPhoto,label3;
	final JTextField text2;
	label1 = new JLabel();
	label1.setText("Name:");
	text1 = new JTextField(20);
	label2 = new JLabel();
	label2.setText("Address:");
	text2 = new JTextField(20);
	label3 = new JLabel();
	label3.setText("Image:");
	lPhoto = new JLabel() ;
	updatetext1(tableName,f, panel, text1);
	updatetext2(tableName,f, panel, text2);
	try
    {
    Class.forName("com.mysql.jdbc.Driver");
 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rapidus","root","root");  
 Statement st=con.createStatement();   
 ResultSet rs = st.executeQuery( "select  photo from pictures where id='1'") ;
 while(rs.next()) 
 {
 byte[] imagedata = rs.getBytes("photo") ;
 Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
 ImageIcon icon =new ImageIcon(img);
 lPhoto = new JLabel(icon) ;
 setLayout(null);                              // BYTES TO IMAGE       
 }
    }
 catch(Exception e){e.printStackTrace();}
 try{ 

	panel.removeAll();
	panel.add(label1);
	panel.add(text1);
	panel.add(label2);
	panel.add(text2);
	//panel.add(label3);
	//panel.add(lPhoto) ;
	
    panel.validate();
    panel.repaint();
    
 //lPhoto.setBounds(301,41,150,200);
 f.add(panel);
 f.setSize(600, 600); 
 //f.pack();
 f.setVisible(true);
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}


public static void main (String[] args) // no args expected 
{ 
SwingTextAndImage main = new SwingTextAndImage(); 
main.mainImage();
//main.mainText();
} // end main 
} // end class ImageDisplay