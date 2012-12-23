/**
 * 
 */
package com.rapidus.urlread.util;

import java.io.IOException;

/**
 * @author pawan
 *
 */
public class RunCmdPrmt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec("cmd /c start test.bat John Kumar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
