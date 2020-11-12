package KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;

public class Misc {
    private Integer grade = null;
    private Integer strokeCount = null;
    private Integer frequency = null;
    private Integer jlpt = null;
    private HashMap<String, String> variant = new HashMap<String, String>();
    private ArrayList<String> radName = new ArrayList<String>();

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(Integer strokeCount) {
        this.strokeCount = strokeCount;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getJlpt() {
        return jlpt;
    }

    public void setJlpt(Integer jlpt) {
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
