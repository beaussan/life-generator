package com.lifeproject.data;

import java.util.ArrayList;
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
        possibleOutcome = new ArrayList<>();
    }

    public List<HistoryByte> getPossibleOutcome() {
        return possibleOutcome;
    }

    public boolean add(HistoryByte historyByte) {
        return possibleOutcome.add(historyByte);
    }

    public String getStoryFor(Humain hum){
        String out = ""+story;
        out = out.replaceAll("\\{name\\}",hum.getName());
        out = out.replaceAll("\\{surname\\}",hum.getSurname());
        if (hum.isMasculin()){
            out = out.replaceAll("(\\{)(.*)(\\\\)(.*)(\\})", "$2");
        } else {
            out = out.replaceAll("(\\{)(.*)(\\\\)(.*)(\\})", "$4");
        }
        return out;
    }


    public int getId() {
        return id;
    }
}
