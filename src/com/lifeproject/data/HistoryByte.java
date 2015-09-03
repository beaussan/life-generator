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
        out = out.replaceAll("\\{race\\}",hum.getRace().toString());
        out = out.replaceAll("\\{surname\\}",hum.getSurname());
        if (hum.isMasculin()){
            out = out.replaceAll("(\\{)([^\\}]*)(\\\\)([^\\}]*)(\\})", "$2");
        } else {
            out = out.replaceAll("(\\{)([^\\}]*)(\\\\)([^\\}]*)(\\})", "$4");
        }
        return out;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryByte that = (HistoryByte) o;

        if (id != that.id) return false;
        if (story != null ? !story.equals(that.story) : that.story != null) return false;
        return !(possibleOutcome != null ? !possibleOutcome.equals(that.possibleOutcome) : that.possibleOutcome != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (story != null ? story.hashCode() : 0);
        result = 31 * result + (possibleOutcome != null ? possibleOutcome.hashCode() : 0);
        return result;
    }
}
