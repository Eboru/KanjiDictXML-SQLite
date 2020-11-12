package com.KDE.run;

import com.KDE.KanjiDic.Dictionary;
import com.KDE.KanjiDic.DBGen;

import java.io.File;

public class run {

    //First argument->xml kanjidic
    //Second argument->db path
    public static void main(String[] args) {

        if(args.length<2)
        {
            System.err.println("NOT ENOUGH ARGUMENTS!");
            System.err.println("USE \"thisJar.jar <KanjiDictXMLFile> <Database output directory>\"");
            return;
        }
        
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
