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

import com.lifeproject.data.GeneratorUtil;
import com.lifeproject.data.History;
import com.lifeproject.data.Humain;
import com.lifeproject.data.NameReader;
import com.lifeproject.data.SurnameReader;
import com.lifeproject.ihm.ProfilePicture;

import sun.font.Font2D;

public class IHM {

	public IHM(Humain hum){
		JFrame frame = new JFrame("LifeGenerator");		
		JPanel pan1 = new JPanel();
		JLabel lab1 = new JLabel(hum.getName());
		JLabel lab2 = new JLabel(hum.getSurname());
		JPanel pan2 = new JPanel();
		JTextArea ta = new JTextArea();
		JButton b1= new JButton("Sauvergarder");
		JButton b2 = new JButton("Changer");
		JButton b3 = new JButton("Quitter");
		ProfilePicture pp = new ProfilePicture(hum);
		pp.setHum(hum);
		pp.setBounds(454,284,246,246);
		ta.setBackground(new Color(34,34,34));
		ta.setFont(new Font("Serif", Font.BOLD, 20));
		ta.setForeground(Color.WHITE);
		ta.setLineWrap(true);
		ta.setTabSize(32);
		ta.setText(hum.getHistory());
		JScrollPane sp = new JScrollPane(ta);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBounds(12, 150, 440, 500);
		sp.setBackground(new Color(34,34,34));
		pan1.setLayout(null);
		lab1.setBounds(60, 30,400,50);
		lab1.setForeground(Color.WHITE);
		lab1.setFont(new Font("Serif", Font.BOLD, 40));
		lab2.setBounds(60, 70,400,50);
		lab2.setForeground(Color.WHITE);
		lab2.setFont(new Font("Serif", Font.BOLD, 40));
		pan2.setBounds(500,20,190,150);
		b1.setBounds(0, 0, 190, 50);
		b2.setBounds(0, 50, 190, 50);
		b3.setBounds(0, 100, 190, 50);
		pan2.repaint();
		pan2.setLayout(null);
		pan1.setBackground(new Color(34,34,34));
		
		pan1.add(lab1);
		pan1.add(lab2);
		pan1.add(pan2);
		pan1.add(sp);
		pan1.add(pp);
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
	     javax.swing.SwingUtilities.invokeLater(new Runnable() {
	       public void run() {
	    	   Humain hum = new Humain(false,null,null,null,null);
	    	   SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
	           NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");
	    	   GeneratorUtil.randomizeNameOf(hum, nr, sr);
	    	   hum.setHistory(History.getInstance().getHistory(hum));
	         new IHM(hum);
	   
       }
   });
 }
}
