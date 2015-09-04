package com.lifeproject;

import com.lifeproject.data.*;
import com.lifeproject.ihm.ProfilePicture;
import me.nbeaussart.gui.JConsole;
import me.nbeaussart.gui.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by beaussan on 03/09/15.
 */
public class TerminalOutput {


    private static final SurnameReader sr= new SurnameReader("/com/lifeproject/res/humain_prenom");
    private static final NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");
    private ProfilePicture profilPic;
    private JFrame frameJc;
    private JFrame frameProfPic;

    public TerminalOutput(){

        JConsole jc = new JConsole(100,30);
        frameJc = Tools.showComponent(jc);
        frameJc.setLocationRelativeTo(null);
        frameJc.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);
            }
        });

        jc.setCursorVisible(true);
        jc.setCursorBlink(true);


        System.out.println(nr.getNom());

        Humain hum = new Humain("Nicolas","Paul",true);

        GeneratorUtil.randomizeNameOf(hum, nr, sr);
        hum.setHistory(History.getInstance().getHistory(hum));
        profilPic = new ProfilePicture(hum);
        frameProfPic = Tools.showComponent(profilPic);
        outHum(hum, jc);

        String in;
        final String restart = "Recommencer";
        final String save = "Sauvegarder";
        final String quit = "Quitter";
        final String changeName = "Changer le nom";
        final String changeRace = "Changer la race";
        do {
            in = jc.read(new String[]{restart, changeName, changeRace, save, quit});
            switch (in) {
                case restart:
                    jc.clearScreen();
                    GeneratorUtil.randomizeNameOf(hum, nr, sr);
                    hum.setHistory(History.getInstance().getHistory(hum));
                    outHum(hum, jc);
                    break;

                case save:
                    SavingUtil.writeToFile(hum);
                    break;

                case changeName:
                    jc.write("\nNom : ");
                    hum.setName(jc.read(15));
                    jc.write("\nPrénom : ");
                    hum.setSurname(jc.read(15));
                    hum.updateGender(sr);
                    hum.rewriteHistory();
                    jc.clearScreen();
                    outHum(hum, jc);
                    break;

                case changeRace:
                    jc.write("\nRace : ");
                    Race[] tabRa = Race.values();
                    String[] stAsk = new String[tabRa.length];
                    for (int i = 0; i < stAsk.length; i++) {
                        stAsk[i] = tabRa[i].name();
                    }
                    String inputRace = jc.read(stAsk);
                    for (int i = 0; i < stAsk.length; i++) {
                        if (stAsk[i].equals(inputRace)) {
                            hum.setRace(tabRa[i]);
                            break;
                        }
                    }
                    hum.rewriteHistory();
                    jc.clearScreen();
                    outHum(hum, jc);
                    break;
            }
        } while (!in.equals(quit));
        System.exit(0) ;

    }

    private void outHum(Humain hum, JConsole jc){

        if (hum.isMasculin()){
            jc.setForeground(new Color(0, 138, 178));
        } else {
            jc.setForeground(new Color(127, 0, 0));
        }
        jc.writeln(hum.getName() + " " + hum.getSurname());
        switch (hum.getRace()){
            case DWARF:
                jc.setForeground(new Color(0, 255, 140));
                break;
            case ELF:
                jc.setForeground(new Color(255, 191, 0));
                break;
            case GNOME:
                jc.setForeground(new Color(0, 64, 255));
                break;
            case HUMAIN:
                jc.setForeground(new Color(127, 127, 0));
                break;
            case MORTIS:
                jc.setForeground(new Color(178, 53, 0));
                break;
            case ORC:
                jc.setForeground(new Color(178, 0, 53));
                break;
        }
        jc.writeln(hum.getRace().name());
        jc.setForeground(new Color(0, 127, 0));
        jc.writeln(hum.getHistory());
        jc.setForeground(new Color(192, 192, 192));
        jc.writeln("____________");
        jc.setForeground(new Color(192, 192, 192));
        profilPic.setHum(hum);
        profilPic.repaint();
        frameProfPic.pack();
    }

}
