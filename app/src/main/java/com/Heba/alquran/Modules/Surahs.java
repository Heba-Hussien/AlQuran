package com.Heba.alquran.Modules;

import java.util.ArrayList;

public class Surahs {
    int number;
    ArrayList<Ayahs> ayahs;

    public Surahs(int number, ArrayList<Ayahs> ayahsArrayList) {
        this.number = number;
        this.ayahs = ayahsArrayList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Ayahs> getAyahs() {
        return ayahs;
    }

    public void setAyahs(ArrayList<Ayahs> ayahs) {
        this.ayahs = ayahs;
    }
}
