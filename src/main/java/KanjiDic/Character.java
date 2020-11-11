package KanjiDic;

import java.util.ArrayList;

public class Character {
    private String literal = "";
    private Codepoints codepoints = new Codepoints();
    private Radical radicals = new Radical();
    private Misc misc = new Misc();
    private DicReferences dicReferences = new DicReferences();
    private QueryCodes queryCodes = new QueryCodes();
    private ReadingsMeanings readingsMeanings = new ReadingsMeanings();
    private ArrayList<String> nanori = new ArrayList<>();


    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public Codepoints getCodepoints() {
        return codepoints;
    }

    public void setCodepoints(Codepoints codepoints) {
        this.codepoints = codepoints;
    }

    public Radical getRadicals() {
        return radicals;
    }

    public void setRadicals(Radical radicals) {
        this.radicals = radicals;
    }

    public Misc getMisc() {
        return misc;
    }

    public void setMisc(Misc misc) {
        this.misc = misc;
    }

    public DicReferences getDicReferences() {
        return dicReferences;
    }

    public void setDicReferences(DicReferences dicReferences) {
        this.dicReferences = dicReferences;
    }

    public QueryCodes getQueryCodes() {
        return queryCodes;
    }

    public void setQueryCodes(QueryCodes queryCodes) {
        this.queryCodes = queryCodes;
    }

    public ReadingsMeanings getReadingsMeanings() {
        return readingsMeanings;
    }

    public void setReadingsMeanings(ReadingsMeanings readingsMeanings) {
        this.readingsMeanings = readingsMeanings;
    }

    public ArrayList<String> getNanori() {
        return nanori;
    }

    public void setNanori(ArrayList<String> nanori) {
        this.nanori = nanori;
    }
}
