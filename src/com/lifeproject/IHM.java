package com.lifeproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
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

	private final static SurnameReader sr = new SurnameReader("/com/lifeproject/res/humain_prenom");
	private final static NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");

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
	public IHM(final Humain hum,final JFrame frame){

		JPanel pan1 = new JPanel();
		final JTextField tfName = new JTextField(hum.getName());
		final JTextField tfSurname = new JTextField(hum.getSurname());
		final JTextField tfRace = new JTextField(hum.getRace()+"");
		JPanel pan2 = new JPanel();
		final JTextArea ta = new JTextArea();
		JButton bSave= new JButton("Sauvergarder");
		JButton bChange = new JButton("Changer");
		JButton bQuit = new JButton("Quitter");
		final ProfilePicture pp = new ProfilePicture(hum);
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
		tfName.setBounds(20, 30, 400, 50);
		tfName.setForeground(Color.WHITE);
		tfName.setBackground(new Color(34, 34, 34));
		tfName.setFont(new Font("Serif", Font.BOLD, 40));
		tfName.setBorder(null);
		tfSurname.setBounds(20, 75, 400, 50);
		tfSurname.setForeground(Color.WHITE);
		tfSurname.setBackground(new Color(34, 34, 34));
		tfSurname.setFont(new Font("Serif", Font.BOLD, 35));
		tfSurname.setBorder(null);
		tfRace.setBounds(530, 540, 150, 50);
        if (hum.isMasculin()){
            tfRace.setForeground(new Color(0, 138, 178));
        } else {
            tfRace.setForeground(new Color(200, 0, 0));
        }
		tfRace.setBackground(new Color(34, 34, 34));
		tfRace.setFont(new Font("Serif", Font.BOLD, 20));
		tfRace.setBorder(null);
		pan2.setBounds(500,20,190,150);
		bSave.setBounds(0, 0, 190, 50);
		bChange.setBounds(0, 50, 190, 50);
		bChange.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.repaint();
				GeneratorUtil.randomizeNameOf(hum, nr, sr);
				hum.setHistory(History.getInstance().getHistory(hum));
                tfName.setText(hum.getName());
                tfSurname.setText(hum.getSurname());
                if (hum.isMasculin()){
                    tfRace.setForeground(new Color(0, 138, 178));
                } else {
                    tfRace.setForeground(new Color(200, 0, 0));
                }
                tfRace.setText(hum.getRace().toString());
                ta.setText(hum.getHistory());
                pp.setHum(hum);
                pp.repaint();
                frame.revalidate();
                frame.repaint();
                				//new IHM(hum, frame);
			}

		});
		bQuit.setBounds(0, 100, 190, 50);
		bQuit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.dispose();
            }


        });
		pan2.repaint();
		pan2.setLayout(null);
		pan1.setBackground(new Color(34,34,34));

		pan1.add(tfName);
		pan1.add(tfSurname);
		pan1.add(tfRace);
		pan1.add(pan2);
		pan1.add(sp);
		pan1.add(pp);
		pan2.add(bSave);
		pan2.add(bChange);
		pan2.add(bQuit);

		frame.pack();
		frame.setSize(new Dimension(700,700));
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
		frame.getContentPane().add(pan1);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
