package com.rapidus.urlread.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

public class PowerMP3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strFile = "skin_240x320.xml";
		String strLine = "";
		String urlInsert = "";
		StringTokenizer st = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader( new FileReader(strFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("output : \n\n");
	}

}
