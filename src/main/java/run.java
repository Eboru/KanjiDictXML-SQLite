import KanjiDic.Dictionary;
import KanjiDic.DBGen;

import java.io.File;

public class run {

    //First argument->xml kanjidic
    //Second argument->db path
    public static void main(String[] args) {

        File file  = new File(args[1]);
        if(file.length() !=0)
        {
            System.err.println("FILE NOT EMPTY, HALTED!");
            return;
        }


        Dictionary dic = new Dictionary(args[1]);
        DBGen dbGen = new DBGen();
        dbGen.genFromKanjiDic(dic, DBGen.DBType.SQLite, args[2], "");

    }
}
