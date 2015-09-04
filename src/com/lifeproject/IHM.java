package com.lifeproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	public IHM(final Humain hum,final JFrame frame){
		
		JPanel pan1 = new JPanel();
		JTextField tf1 = new JTextField(hum.getName());
		JTextField tf2 = new JTextField(hum.getSurname());
		JTextField tf3 = new JTextField(hum.getRace()+"");
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
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(12, 150, 440, 500);
		sp.setBackground(new Color(34,34,34));
		sp.setBorder(null);
		pan1.setLayout(null);
		tf1.setBounds(20, 30,400,50);
		tf1.setForeground(Color.WHITE);
		tf1.setBackground(new Color(34,34,34));
		tf1.setFont(new Font("Serif", Font.BOLD, 40));
		tf1.setBorder(null);
		tf2.setBounds(20, 75,400,50);
		tf2.setForeground(Color.WHITE);
		tf2.setBackground(new Color(34,34,34));
		tf2.setFont(new Font("Serif", Font.BOLD, 35));
		tf2.setBorder(null);
		tf3.setBounds(530, 540,150,50);
		tf3.setForeground(Color.WHITE);
		tf3.setBackground(new Color(34,34,34));
		tf3.setFont(new Font("Serif", Font.BOLD, 20));
		tf3.setBorder(null);
		pan2.setBounds(500,20,190,150);
		b1.setBounds(0, 0, 190, 50);
		b2.setBounds(0, 50, 190, 50);
		b2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				frame.repaint();
			 	SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
		        NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");
				GeneratorUtil.randomizeNameOf(hum, nr, sr);
		    	hum.setHistory(History.getInstance().getHistory(hum));
		    	new IHM(hum,frame);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

	});
		b3.setBounds(0, 100, 190, 50);
		b3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
				);
		pan2.repaint();
		pan2.setLayout(null);
		pan1.setBackground(new Color(34,34,34));
		
		pan1.add(tf1);
		pan1.add(tf2);
		pan1.add(tf3);
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
	    	   final JFrame frame = new JFrame("LifeGenerator");		
	         new IHM(hum,frame);
	   
       }
   });
 }
}
