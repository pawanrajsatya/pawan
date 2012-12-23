package com.rapidus.urlread.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.lang.Runnable;

import javax.imageio.ImageIO;

import com.sun.jna.examples.win32.GDI32;
import com.sun.jna.examples.win32.User32;
import com.sun.jna.examples.win32.User32.WINDOWINFO;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/** The Window Manager checks the top level windows periodically and creates the
*  appropriate manager class instance if a new window is detected. */
public class WindowManager implements Runnable {
   
   private static User32 user32 = User32.INSTANCE;
protected WINDOWINFO arg1;

   private boolean getWindowList() {

      user32.EnumWindows( new User32.WNDENUMPROC() {
         public boolean callback( W32API.HWND hWnd, Pointer userData ) {
            byte[] buffer = new byte[ 2048 ];
            user32.GetWindowText( hWnd, buffer, 1024 );
            String title = Native.toString( buffer );
            //System.out.println("GetWindowTextLength : "+ user32.GetWindowTextLength(hWnd));
            //System.out.println("WINDOWs INFO : "+ user32.GetWindowInfo(hWnd, arg1));
            //System.out.println("hwnd : "+hWnd.toString()); 
            //String url = Native.getWebStartLibraryPath(METHOD_NAME);
            //System.out.println("u : "+url);
            //IntByReference out = null;
            //user32.GetWindowThreadProcessId(hWnd, out);
            //System.out.println("out : "+out);
			//user32.GetWindowThreadProcessId(hWnd, out processID);
            
   
            GDI32.RECT rect = new GDI32.RECT();
            user32.GetWindowRect( hWnd, rect );
            Rectangle rectangle = rect.toRectangle();
   
            if( title.length() > 1 ) {
               System.out.println( "Found window '" + title + "' ( "
                      + rectangle.width + "x" + rectangle.height + " @ "
                      + rectangle.x + "/" + rectangle.y + ")" );
               
               if( title.contains( "Selenium Remote Control" ) ) {
                  try {
                	  String fullTitle = title;
                	  user32.FindWindow(null, fullTitle );
                	  
                     Robot robot = new Robot();
                      BufferedImage image = robot.createScreenCapture( rectangle );
                      File file = new File( "screencapture.png" );
                      ImageIO.write( image, "png", file );
                      System.out.println( "... Capturing into " + file.getAbsolutePath() );
                  } catch( AWTException ae ) {
                     System.err.println( "AWTException" );
                     return false;
                  } catch( IOException ie ) {
                     System.err.println( "IOExcpetion" );
                     return false;
                  }
               }
            }
            return true;
         }
      }, null );

      return true;
   }      
   
   public void run() {
      System.setProperty( "jna.encoding", "UTF-16LE" );

      for( int counter = 0; counter < 1; counter++ ) {
         if( ! getWindowList() ) {
        	 
            break;
         }System.out.println("counter : "+counter);
      }
    }
   public static void main(String args[]){
		WindowManager wm = new WindowManager();
		wm.run();
	}
}