package com.lifeproject.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by beaussan on 03/09/15.
 */
public class SavingUtil {

    public static boolean writeToFile(Humain hum){
        return writeToFile(hum, hum.getName()+"_"+hum.getSurname());
    }
    public static boolean writeToFile(Humain hum, String fileName){

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName,"UTF-8");
            writer.write("Name : " + hum.getName());
            writer.write("\nSurname : " + hum.getSurname());
            writer.write("\nSexe : " + (hum.isMasculin() ? "Homme\n" : "Femme\n"));
            writer.write("Race : " + hum.getRace().name() + "\n");
            writer.write(hum.getHistory());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
