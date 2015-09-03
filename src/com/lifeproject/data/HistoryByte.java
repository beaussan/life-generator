package com.lifeproject.data;

import java.util.List;

/**
 * Created by beaussan on 03/09/15.
 */
public class HistoryByte {
    private static int counter = 1;
    private final int id;

    private String story;

    private List<HistoryByte> possibleOutcome;

    public HistoryByte(String story){
        id = counter;
        counter++;
        this.story = story;
    }

    public String getStoryFor(Humain hum){
        String out = ""+story;
        out = out.replaceAll("\\{name\\}",hum.getSurname() + " " + hum.getName());
        return out;
        // TODO implementer les genres
    }






}
