package com.rapidus.urlread.swing;

import java.awt.*; import java.awt.event.*; import javax.swing.*;
public class Frame1 extends JFrame
{
  JPanel pane=new JPanel();
  
  Frame1() // the frame constructor method
  {
    super("My Simple Frame"); setBounds(100,100,300,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container con=this.getContentPane(); // inherit main frame
    con.add(pane); // add the panel to frame
    JOptionPane.showMessageDialog(null,"This is just a message","Message Dialog",JOptionPane.PLAIN_MESSAGE);
    int pressed = JOptionPane.showConfirmDialog(null,"Everything aok");
    if (pressed==JOptionPane.YES_OPTION)
       {
    	System.out.println(pressed + " is pressed");
       // do the action for confirmation
       }
    // customize panel here
    // pane.add(someWidget);
    setVisible(true); // display this frame
  }
  public static void main(String args[]) {new Frame1();}
}