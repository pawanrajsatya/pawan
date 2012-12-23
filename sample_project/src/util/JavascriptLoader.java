package com.rapidus.urlread.util;

//JavascriptLoader.java
//How to compile: javac JavascriptLoader.java
//How to run: java JavascriptLoader JS_FILENAME

import javax.script.*;
import java.io.*;

public class JavascriptLoader {
 public static void main(String args[])
 {
     if (args.length == 0) {
         System.out.println("Please provide a js file");
         return;
     }
     ScriptEngineManager mgr = new ScriptEngineManager();
     ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
     try {
         jsEngine.eval(new FileReader("JavaScript.js"));
     } catch (ScriptException ex) {
         System.out.println("ScriptException");
     } catch (FileNotFoundException ex) {
         System.out.println("FileNotFoundException");
     }
 }
}