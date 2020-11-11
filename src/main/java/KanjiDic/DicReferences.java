package KanjiDic;

import java.util.HashMap;

public class DicReferences {
    private HashMap<String, String> dictionaryReferences = new HashMap<>();

    public HashMap<String, String> getDictionaryReferences() {
        return dictionaryReferences;
    }

    public void setDictionaryReferences(HashMap<String, String> dictionaryReferences) {
        this.dictionaryReferences = dictionaryReferences;
    }
}
