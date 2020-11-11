package KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;

public class Misc {
    private int grade = -1;
    private int strokeCount = -1;
    private int frequency = -1;
    private int jlpt = -1;
    private HashMap<String, String> variant = new HashMap<String, String>();
    private ArrayList<String> radName = new ArrayList<String>();

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getJlpt() {
        return jlpt;
    }

    public void setJlpt(int jlpt) {
        this.jlpt = jlpt;
    }

    public HashMap<String, String> getVariant() {
        return variant;
    }

    public void setVariant(HashMap<String, String> variant) {
        this.variant = variant;
    }

    public ArrayList<String> getRadName() {
        return radName;
    }

    public void setRadName(ArrayList<String> radName) {
        this.radName = radName;
    }
}
