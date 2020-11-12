import KanjiDic.Dictionary;
import KanjiDic.DBGen;

import java.io.File;

public class run {

    //First argument->xml kanjidic
    //Second argument->db path
    public static void main(String[] args) {

        File file  = new File("C:\\Users\\garza\\OneDrive\\Escritorio\\Kanjidic2\\kanjidic2.db");
        if(file.length() !=0)
        {
            System.err.println("FILE NOT EMPTY, HALTED!");
            return;
        }


        Dictionary dic = new Dictionary("C:\\Users\\garza\\OneDrive\\Escritorio\\Kanjidic2\\kanjidic2.xml");
        DBGen dbGen = new DBGen();
        dbGen.genFromKanjiDic(dic, DBGen.DBType.SQLite, "C:\\Users\\garza\\OneDrive\\Escritorio\\Kanjidic2\\kanjidic2.db", "");

    }
}
