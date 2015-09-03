package com.lifeproject.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by beaussan on 03/09/15.
 */
public final class GeneratorUtil {

    private GeneratorUtil(){}

    public static void randomizeNameOf(Humain hum, NameReader nr, SurnameReader sr){

        Random r = new Random();
        List<String> lsNoms = new ArrayList<>(nr.getNames());
        List<String> lsSurname;
        if (r.nextBoolean()){
            hum.setIsMasculin(true);
            lsSurname = new ArrayList<>(sr.getMasculin());
        } else {
            hum.setIsMasculin(false);
            lsSurname = new ArrayList<>(sr.getFeminun());
        }
        hum.setName(lsNoms.get(r.nextInt(lsNoms.size())));
        hum.setSurname(lsSurname.get(r.nextInt(lsSurname.size())));
    }
}
