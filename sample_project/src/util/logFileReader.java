/**
 * 
 */
package com.rapidus.urlread.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.rapidus.urlread.DBUtil.MysqlRapidusConnection;


/**
 * @author pawan
 *
 */
public class logFileReader {

	
	public static String[] readFile(String strFile){
		
		
		
		return null;		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{ 
			String strFile = "imdbLog.txt";
			String strLine = "";
			String urlInsert = "";
			StringTokenizer st = null;
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			Connection conn = MysqlRapidusConnection.getConnection();
			PreparedStatement stmt = null;
			while( (strLine = br.readLine()) != null)
			{
				st = new StringTokenizer(strLine, "~~~~");
				while(st.hasMoreTokens())
				{
					String strto = st.nextToken();
					int s = Integer.parseInt(strto); 
				switch (s) {
				case 1:
					stmt = conn.prepareStatement("insert into imdb(URL) values (?)");
					urlInsert = st.nextToken();
					stmt.setString(1, urlInsert);
					System.out.println("inserting url : "+urlInsert);
					stmt.executeUpdate();
					break;
				case 2:
					stmt = conn.prepareStatement("update imdb set IMAGE_LINK=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 3:
					stmt = conn.prepareStatement("update imdb set TITLE=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 4:
					stmt = conn.prepareStatement("update imdb set YEAR=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 5:
					stmt = conn.prepareStatement("update imdb set INFO=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 6:
					stmt = conn.prepareStatement("update imdb set RATINGS=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 7:
					stmt = conn.prepareStatement("update imdb set DESCRIPTION=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				case 8:
					stmt = conn.prepareStatement("update imdb set OTHER=? WHERE url=?");
					stmt.setString(1, st.nextToken());
					stmt.setString(2, urlInsert);
					stmt.executeUpdate();
					break;
				default:
					break;
				}
				
				}
			}
			stmt.close();
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception while reading file: " + e);
		} 
	}

}
