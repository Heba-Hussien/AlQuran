package com.Heba.alquran.Modules;


public class Ayahs {
    int numberInSurah;
    String  audio, text;
    String[] audioSecondary;


    public Ayahs(int numberInSurah, String audio, String text, String[] audioSecondary) {
        this.numberInSurah = numberInSurah;
        this.audio = audio;
        this.text = text;
        this.audioSecondary = audioSecondary;
    }

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getAudioSecondary() {
        return audioSecondary;
    }

    public void setAudioSecondary(String[] audioSecondary) {
        this.audioSecondary = audioSecondary;
    }
}
