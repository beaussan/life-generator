package com.lifeproject;

import com.lifeproject.data.*;
import com.lifeproject.log.LogFormatter;
import com.lifeproject.log.UtilLog;

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
        LOG.log(Level.FINEST, "finest");*/

        Humain hum = new Humain("Nicolas","Paul",false);

        SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
        NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");

        for (int i =0; i < 200; i++){
            GeneratorUtil.randomizeNameOf(hum,nr,sr);
            hum.setHistory(History.getInstance().getHistory(hum));
            System.out.println(hum.getName() + " " + hum.getSurname() + " " + (hum.isMasculin()? "homme" : "femme"));
            System.out.println(hum.getHistory());
            System.out.println("____________");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }

}
