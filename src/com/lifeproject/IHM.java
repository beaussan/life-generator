package com.lifeproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Scanner;


import javax.swing.*;

import sun.font.Font2D;

public class IHM {

	public IHM(String name){
		JFrame frame = new JFrame("LifeGenerator");		

		JPanel pan1 = new JPanel();
		JLabel lab1 = new JLabel(name);
		JPanel pan2 = new JPanel();
		JTextArea ta = new JTextArea();
		
		JButton b1= new JButton("Sauvergarder");
		JButton b2 = new JButton("Changer");
		JButton b3 = new JButton("Quitter");
		JScrollBar sp = new JScrollBar();
		ta.setBounds(20, 150, 440, 500);
		ta.setBackground(new Color(34,34,34));
		ta.setFont(new Font("Serif", Font.BOLD, 20));
		ta.setForeground(Color.WHITE);
		ta.setText("bonjour");
		
		sp.setBounds(460, 150, 20, 500);
		sp.setBackground(new Color(34,34,34));
		pan1.setLayout(null);
		lab1.setBounds(200, 10,150,50);
		lab1.setForeground(Color.WHITE);
		lab1.setFont(new Font("Serif", Font.BOLD, 40));
		pan2.setBounds(500,20,190,150);
		b1.setBounds(0, 0, 190, 50);
		b2.setBounds(0, 50, 190, 50);
		b3.setBounds(0, 100, 190, 50);
		pan2.repaint();
		pan2.setLayout(null);
		pan1.setBackground(new Color(34,34,34));

	
		pan1.add(lab1);
		pan1.add(pan2);
		pan1.add(sp);
		pan1.add(ta);
		pan2.add(b1);
		pan2.add(b2);
		pan2.add(b3);
		frame.pack();
		frame.setSize(new Dimension(700,700));
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	
		frame.getContentPane().add(pan1);
		frame.setResizable(false);
		
		frame.setVisible(true);
	}
	public static void main(String[] args) {
	     //Schedule a job for the event-dispatching thread:
	     //creating and showing this application’s GUI.
	     javax.swing.SwingUtilities.invokeLater(new Runnable() {
	       public void run() {
	         new IHM(null);
	   
       }
   });
 }
}
