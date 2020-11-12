package com.KDE.KanjiDic;

import java.util.ArrayList;
import java.util.HashMap;


/*The cpType attribute states the coding standard applying to the
        element. The values assigned so far are:
        jis208 - JIS X 0208-1997 - kuten coding (nn-nn)
        jis212 - JIS X 0212-1990 - kuten coding (nn-nn)
        jis213 - JIS X 0213-2000 - kuten coding (p-nn-nn)
        ucs - Unicode 4.0 - hex coding (4 or 5 hexadecimal digits)*/
public class Codepoints {
    HashMap<String, String> cpTypes = new HashMap<String, String>();

    public void setCpType(HashMap<String, String> cpTypes) {
        this.cpTypes = cpTypes;
    }

    public HashMap<String, String> getCpTypes(){
        return cpTypes;
    }
}
