package com.rapidus.urlread.util;

public class ThreadExample implements Runnable
{
   public static void main(String args[]) throws Throwable  
   {
      ThreadExample obj1 = new ThreadExample();
      ThreadExample obj2 = new ThreadExample();
      new Thread(obj1).start();
      new Thread(obj2).start();
      // main thread is ending here,
      // Thread-0 and Thread-1 continue to run.
   }

   public void run()
   {
      try {
         for (int i=0; i<5; i++) {
            System.out.println("thread "
               +Thread.currentThread().getName()+" step "+i);
            Thread.sleep(500);
         }
      } catch (Throwable t) { }
   }
}