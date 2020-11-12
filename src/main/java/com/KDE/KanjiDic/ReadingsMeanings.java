package com.KDE.KanjiDic;

public class ReadingsMeanings {

    public Readings readings = new Readings();
    public Meanings meanings = new Meanings();

    public void setMeanings(Meanings meanings) {
        this.meanings = meanings;
    }

    public void setReadings(Readings readings) {
        this.readings = readings;
    }

    public Meanings getMeanings() {
        return meanings;
    }

    public Readings getReadings() {
        return readings;
    }
}
