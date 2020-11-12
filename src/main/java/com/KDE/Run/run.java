package com.KDE.Run;

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
            System.err.println("USE \"thisJar.jar <KanjiDictXMLFile> <Database output file)>\"");
            return;
        }

        args[1]+="\\kanjidic2.db";
        File file  = new File(args[1]);
        if(file.length() !=0)
        {
            System.err.println("FILE NOT EMPTY, HALTED!");
            return;
        }


        System.out.println("STARTING BUILDING DATABASE...");

        Dictionary dic = new Dictionary(args[0]);
        DBGen dbGen = new DBGen();
        dbGen.genFromKanjiDic(dic, DBGen.DBType.SQLite, args[1], "");

        System.out.println("BUILDING DONE!");

    }
}
