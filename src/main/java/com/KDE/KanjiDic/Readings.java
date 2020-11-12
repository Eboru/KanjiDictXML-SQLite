package com.KDE.KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;

public class Readings {

    public HashMap<String, ArrayList<String>> readings = new HashMap<String, ArrayList<String>>();

    public HashMap<String, ArrayList<String>> getReadings() {
        return readings;
    }

    public void setReadings(HashMap<String, ArrayList<String>> readings) {
        this.readings = readings;
    }
}
