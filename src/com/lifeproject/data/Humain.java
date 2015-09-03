package com.lifeproject.data;

/**
 * Created by beaussan on 02/09/15.
 */
public class Humain {
    private boolean isMasculin;
    private String surname;
    private String name;
    private String history;
    private Race race;

    public Humain(boolean isMasculin, String surname, String name, String history, Race race) {
        this.isMasculin = isMasculin;
        this.surname = surname;
        this.name = name;
        this.history = history;
        this.race = race;
    }

    public Humain(String name, String surname, boolean isMasculin) {
        this.name = name;
        this.surname = surname;
        this.isMasculin = isMasculin;
    }



    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public boolean isMasculin() {
        return isMasculin;
    }

    public void setIsMasculin(boolean isMasculin) {
        this.isMasculin = isMasculin;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

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
