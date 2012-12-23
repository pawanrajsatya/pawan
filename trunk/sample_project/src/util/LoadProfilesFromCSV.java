package com.rapidus.urlread.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pawan
 *
 */
public class LoadProfilesFromCSV {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoadProfilesFromCSV lpcsv = new LoadProfilesFromCSV();
		List al = lpcsv.parseCSV();
		List cal = lpcsv.calculate(al);
		lpcsv.insert(cal);
	}

	public ArrayList parseCSV(){
		return null;
	}
	public ArrayList calculate(List al){
		return null;
	}
	public ArrayList insert(List cal){
		return null;
	}
}
