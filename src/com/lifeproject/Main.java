package com.lifeproject;

import com.lifeproject.log.LogFormatter;
import com.lifeproject.log.UtilLog;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by beaussan on 02/09/15.
 */
public class Main {

    private static final Logger LOG = UtilLog.getLog(Main.class.getName());


    public static void main(String[] args) {
        UtilLog.setLevelGlobal(Level.ALL);
        UtilLog.setFormatter(new LogFormatter());

        LOG.log(Level.INFO, "info");
        LOG.log(Level.CONFIG, "cfg");
        LOG.log(Level.FINE, "fine");
        LOG.log(Level.FINER, "finer");
        LOG.log(Level.FINEST, "finest");

    }

}
