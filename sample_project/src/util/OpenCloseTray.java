package com.rapidus.urlread.util;
import com.sun.jna.Library;
import com.sun.jna.Native;
 
interface WinMM extends Library { /* We'll make a new library */
 
    WinMM INSTANCE = (WinMM) Native.loadLibrary(("winmm"), WinMM.class); //Create the library from the winmm.dll file
    long mciSendStringA(String lpstrCommand, String lpstrReturnString, long uReturnLength, long hwndCallback); //Create the function we need to use to open the CD tray
}
/**
 *
 * @author 
 */

	public class OpenCloseTray {
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	    WinMM.INSTANCE.mciSendStringA("set CDAudio door open", null, 0, 0); //Open tray
	    WinMM.INSTANCE.mciSendStringA("set CDAudio door closed", null, 0, 0); //Close tray
	    }
	}
