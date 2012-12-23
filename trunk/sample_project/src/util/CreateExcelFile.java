package com.rapidus.urlread.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CreateExcelFile{
    public static void main(String[]args){
    	try{
        	String sql = "Select * from employee";
        	String tableName = "employee";
    		String filename="data.xls" ;
    		HSSFWorkbook hwb=new HSSFWorkbook();
    		HSSFSheet sheet =  hwb.createSheet("new sheet");
    		
    		HSSFRow rowhead=   sheet.createRow((short)0);
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
    		Statement st=con.createStatement();
    		//to get column names in oracle : Select COLUMN_NAME from user_tab_columns where table_name='EMP' 
    		ResultSet rs1=st.executeQuery("select column_name from information_schema.columns where table_name='"+tableName+"'");
    		int headerLength=0;
    		while (rs1.next()){
    			rowhead.createCell((short) headerLength).setCellValue(rs1.getString(1));
    			headerLength++;
    		}
    		
    		ResultSet rs=st.executeQuery(sql);
    		int i=1;
    		while(rs.next()){
    			HSSFRow row=   sheet.createRow((short)i);
    			for(int headerSubLength =1; headerSubLength < headerLength+1; headerSubLength++){
    				row.createCell((short) headerSubLength-1).setCellValue(rs.getString(headerSubLength));
    			}
    			i++;
    		}
    		FileOutputStream fileOut =  new FileOutputStream(filename);
    		hwb.write(fileOut);
    		fileOut.close();
    		System.out.println("Your excel file has been generated!");
    		rs.close();
    		st.close();
    		con.close();
    		} catch ( Exception ex ) {
    		    System.out.println(ex);
    		
    		}
    }
    

    public void writeXlsFromQuery(String sql, String tableName){
		try{
		String filename="data.xls" ;
		HSSFWorkbook hwb=new HSSFWorkbook();
		HSSFSheet sheet =  hwb.createSheet("new sheet");
		
		HSSFRow rowhead=   sheet.createRow((short)0);
		Class.forName("com.mysql.jdbc.Driver");
		//to get oracle connection: place a ojdbc14 jar and chenge driverURL to 
		//jdbc:oracle:thin:@server:1521:db
		//oracle.jdbc.OracleDriver
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		Statement st=con.createStatement();
		//to get column names in oracle : Select COLUMN_NAME from user_tab_columns where table_name='EMP' 
		ResultSet rs1=st.executeQuery("select column_name from information_schema.columns where table_name='"+tableName+"'");
		int headerLength=0;
		while (rs1.next()){
			rowhead.createCell((short) headerLength).setCellValue(rs1.getString(1));
			headerLength++;
		}
		
		ResultSet rs=st.executeQuery(sql);
		int i=1;
		while(rs.next()){
			HSSFRow row=   sheet.createRow((short)i);
			for(int headerSubLength =1; headerSubLength < headerLength+1; headerSubLength++){
				row.createCell((short) headerSubLength-1).setCellValue(rs.getString(headerSubLength));
			}
			i++;
		}
		FileOutputStream fileOut =  new FileOutputStream(filename);
		hwb.write(fileOut);
		fileOut.close();
		System.out.println("Your excel file has been generated!");
		rs.close();
		st.close();
		con.close();
		} catch ( Exception ex ) {
		    System.out.println(ex);
		
		}
    }
}