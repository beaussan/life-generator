package com.lifeproject;

import com.lifeproject.data.*;
import com.lifeproject.log.LogFormatter;
import com.lifeproject.log.UtilLog;
import me.nbeaussart.gui.JConsole;
import me.nbeaussart.gui.Tools;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by beaussan on 02/09/15.
 */
public class Main {

    private static final Logger LOG = UtilLog.getLog(Main.class.getName());


    public static void main(String[] args) {/*
        UtilLog.setLevelGlobal(Level.ALL);
        UtilLog.setFormatter(new LogFormatter());

        LOG.log(Level.INFO, "info");
        LOG.log(Level.CONFIG, "cfg");
        LOG.log(Level.FINE, "fine");
        LOG.log(Level.FINER, "finer");
        LOG.log(Level.FINEST, "finest");



        JConsole jc = new JConsole(100,50);
        Tools.showComponent(jc);
        jc.setCursorVisible(true);
        jc.setCursorBlink(false);

        Humain hum = new Humain("Nicolas","Paul",false);

        SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
        NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");

        for (int i =0; i < 200; i++){
            GeneratorUtil.randomizeNameOf(hum,nr,sr);
            hum.setHistory(History.getInstance().getHistory(hum));
            jc.writeln(hum.getName() + " " + hum.getSurname() + " " + (hum.isMasculin()? "homme" : "femme"));
            jc.writeln(hum.getRace().name());
            jc.writeln(hum.getHistory());
            jc.writeln("____________");
            jc.setForeground(new Color(new Random().nextInt()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jc.clearScreen();

        }


        SavingUtil.writeToFile(hum);*/
        new TerminalOutput();


    }

}
