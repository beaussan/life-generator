package com.lifeproject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beaussan on 02/09/15.
 */
public class Humain {
    /** If true, the humain is male */
    private boolean isMasculin;
    /** the surname of the humain */
    private String surname;
    /** the name of the humain */
    private String name;
    /** the History of the humain */
    private String history;
    /** The race of the humain */
    private Race race;

    /** hbList History list of the history historic*/
    private List<HistoryByte> hbList;

    /**
     *
     * @param isMasculin if set to True, the humain is male
     * @param surname the surname of the humain
     * @param name the name of the humain
     * @param history the history of the humain
     * @param race the race of the humain
     */
    public Humain(boolean isMasculin, String surname, String name, String history, Race race) {
        this.isMasculin = isMasculin;
        this.surname = surname;
        this.name = name;
        this.history = history;
        this.race = race;
        resetHistoryBytes();
    }

    /**
     *
     * @param name the name of the humain
     * @param surname the surname of the humain
     * @param isMasculin if set to True, the humain is male
     */
    public Humain(String name, String surname, boolean isMasculin) {
        this.name = name;
        this.surname = surname;
        this.isMasculin = isMasculin;
        resetHistoryBytes();
    }

    /**
     * Reset the history of the character
     */
    public void resetHistoryBytes(){
        hbList = new ArrayList<>();
    }

    /**
     *
     * @param historyByte the history of the character
     * @return add a part of the history of the character to the known history
     */
    public boolean addHB(HistoryByte historyByte) {
        return hbList.add(historyByte);
    }

    /**
     * Rewrite the story of the human
     */
    public void rewriteHistory(){
        StringBuilder sb = new StringBuilder();
        for (HistoryByte hb : hbList){
            sb.append(hb.getStoryFor(this));
        }
        history = sb.toString();
    }

    /**
     * @return the race of the character
     */
    public Race getRace() {
        return race;
    }

    /**
     * Allow the user to change the race of the character
     * @param race the race of the character after the change
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * @return the history of the character
     */
    public String getHistory() {
        return history;
    }

    /**
     * Change the story of the character
     * @param history
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * @return true if the character's gender is male
     */
    public boolean isMasculin() {
        return isMasculin;
    }

    /**
     * define if the character's gender is male
     * @param isMasculin
     */
    public void setIsMasculin(boolean isMasculin) {
        this.isMasculin = isMasculin;
    }

    /**
     * @return return the surname of the character
     */
    public String getSurname() {
        return surname;
    }

    /**
     * update the surname of the character
     * @param surname the surname of the character after the change
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * allow the user to change the gender of his character
     * @param sr
     */
    public void updateGender(SurnameReader sr){
        setIsMasculin(sr.isMasculin(surname));
    }

    /**
     * @return the name of character
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the character
     * @param name the name of the character after the change
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Humain humain = (Humain) o;

        if (isMasculin != humain.isMasculin) return false;
        if (surname != null ? !surname.equals(humain.surname) : humain.surname != null) return false;
        if (name != null ? !name.equals(humain.name) : humain.name != null) return false;
        if (history != null ? !history.equals(humain.history) : humain.history != null) return false;
        return race == humain.race;

    }

    @Override
    public int hashCode() {
        int result = (isMasculin ? 1 : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Humain{" +
                "isMasculin=" + isMasculin +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", history='" + history + '\'' +
                ", race=" + race +
                '}';
    }
}
