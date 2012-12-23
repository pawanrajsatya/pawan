package com.rapidus.urlread.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingFrameComboSelect{
public static void main(String[] args) throws Exception{
	SwingFrameComboSelect sf=new SwingFrameComboSelect();
}
public SwingFrameComboSelect(){
JFrame f = new JFrame("Frame in Java Swing");
f.getContentPane().setLayout(null);
JLabel lbl1 = new JLabel("Name");
JTextField jt1=new JTextField(15);
JLabel lbl4=new JLabel("Branch");
final JComboBox jc=new JComboBox();
jc.addItem("Select");
jc.addItem("MCA");
jc.addItem("MBA");
jc.addItem("BE");
jc.addItem("BTech");
jc.addItem("Others");
lbl1.setBounds(100,120,70,20);
lbl4.setBounds(100,150,70,20);
jt1.setBounds(170,120,100,20);
jc.setBounds(170,150,100,20);
f.add(lbl1);
f.add(lbl4);
f.add(jt1);
f.add(jc);
f.setSize(1000,1000);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}