package com.rapidus.urlread.swing;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

 @SuppressWarnings("serial")
class Show extends JFrame {
    JPanel points;
    JTextField text;
    int maxPoints;

    public Show() {
        setBackground(Color.WHITE);
        setSize(400, 300);
        getContentPane().setLayout(null);
        setDefaultLookAndFeelDecorated(true);

        JLabel lab=new JLabel("Enter number of points: ");
        lab.setBounds(10,10,150,20);

        text = new JTextField();
        text.setBounds(150,10,100,20);

        this.add(lab);
        this.add(text);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            maxPoints = Integer.parseInt(text.getText()) * 2;
            points.removeAll();

            for (int i=0; i<maxPoints; i++) {
                JTextField textField = new JTextField();
                points.add(textField);
            }
            points.validate();
            points.repaint();
            
        }
        });
        submit.setBounds(10, 40, 100, 20);
        this.add(submit);

        points = new JPanel(new GridLayout(2,2));
        points.setBounds(10,70,265,60);
        this.add(points);
        Component[] a = points.getComponents();
        System.out.println(a.length);
        repaint();
    }
   }
public class RefreshJFrame {
        public static void main(String[] args) {
        Show show = new Show();
        show.setVisible(true);
        }
}