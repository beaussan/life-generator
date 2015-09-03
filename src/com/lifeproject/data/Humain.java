package com.lifeproject.data;

/**
 * Created by beaussan on 02/09/15.
 */
public class Humain {
    private boolean isMasculin;
    private String surname;
    private String name;

    public Humain(String name, String surname, boolean isMasculin) {
        this.name = name;
        this.surname = surname;
        this.isMasculin = isMasculin;
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
    public String toString() {
        return "Humain{" +
                "isMasculin=" + isMasculin +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Humain humain = (Humain) o;

        if (isMasculin != humain.isMasculin) return false;
        if (surname != null ? !surname.equals(humain.surname) : humain.surname != null) return false;
        return !(name != null ? !name.equals(humain.name) : humain.name != null);

    }

    @Override
    public int hashCode() {
        int result = (isMasculin ? 1 : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
