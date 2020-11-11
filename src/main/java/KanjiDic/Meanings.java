package KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;

public class Meanings {

    public HashMap<String, ArrayList<String>> meanings = new HashMap<String, ArrayList<String>>();

    public HashMap<String, ArrayList<String>> getMeanings() {
        return meanings;
    }

    public void setMeanings(HashMap<String, ArrayList<String>> meanings) {
        this.meanings = meanings;
    }
}

