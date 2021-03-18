package com.Heba.alquran.Modules;

import java.util.ArrayList;

public class Data {
    ArrayList<Surahs> surahs;

    public Data(ArrayList<Surahs> surahsArrayList) {
        this.surahs = surahsArrayList;

    }

    public ArrayList<Surahs> getSurahs() {
        return surahs;
    }

    public void setSurahs(ArrayList<Surahs> surahs) {
        this.surahs = surahs;
    }

}
