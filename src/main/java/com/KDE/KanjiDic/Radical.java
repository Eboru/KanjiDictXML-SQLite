package com.KDE.KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;

/*The radical number, in the range 1 to 214. The particular
classification type is stated in the rad_type attribute.*/

public class Radical {
    HashMap<String, Integer> radType = new HashMap<String, Integer>();

    public void setRadType(HashMap<String, Integer> radType) {
        this.radType = radType;
    }

    public HashMap<String, Integer> getRadType(){
        return radType;
    }
}
