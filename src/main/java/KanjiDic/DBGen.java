package KanjiDic;

import DBConnectors.DBConnection;
import DBConnectors.SQLiteConnection;
import KanjiDic.Dictionary;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBGen {

    public enum DBType
    {
        MySQL, SQLite
    }

    private DBConnection connection;

    private void prepareDB()
    {
        //Create tables
        try {
            Statement dbInfo = connection.getConnection().createStatement();
            String dbInfoTable = "CREATE TABLE KANJIDIC_INFO (" +
                    "file_version INT NOT NULL," +
                    "database_version STRING NOT NULL," +
                    "database_creation_date STRING NO NULL)";
            dbInfo.executeUpdate(dbInfoTable);
            dbInfo.close();

            Statement dbMisc = connection.getConnection().createStatement();
            String miscTable = "CREATE TABLE MISC (" +
                    "literal STRING NOT NULL PRIMARY KEY,"+
                    "grade INT," +
                    "stroke_count INT,"+
                    "frequency INT,"+
                    "jlpt INT)";
            dbMisc.executeUpdate(miscTable);
            dbMisc.close();

            Statement dbCP = connection.getConnection().createStatement();
            String codePointsTable = "CREATE TABLE CODE_POINTS (" +
                    "literal STRING NOT NULL PRIMARY KEY,"+
                    "ucs STRING,"+
                    "jis208 STRING," +
                    "jis212 STRING," +
                    "jis213 STRING)";
            dbCP.executeUpdate(codePointsTable);
            dbCP.close();

            Statement dbRad = connection.getConnection().createStatement();
            String radicalsTable = "CREATE TABLE RADICALS (" +
                    "literal STRING NOT NULL PRIMARY KEY," +
                    "classical INT, " +
                    "nelson_c INT)";
            dbRad.executeUpdate(radicalsTable);
            dbRad.close();

            Statement dbVariants = connection.getConnection().createStatement();
            String variantsTable = "CREATE TABLE VARIANTS (" +
                    "literal STRING NOT NULL PRIMARY KEY," +
                    "jis208 STRING," +
                    "jis212 STRING," +
                    "jis213 STRING," +
                    "deroo STRING," +
                    "njecd STRING," +
                    "s_h STRING," +
                    "oneill STRING," +
                    "ucs STRING," +
                    "nelson_c STRING)";
            dbVariants.executeUpdate(variantsTable);
            dbVariants.close();

            Statement dbReadings = connection.getConnection().createStatement();
            String readingsTable = "CREATE TABLE READINGS (" +
                    "literal STRING NOT NULL PRIMARY KEY," +
                    "pinyin STRING," +
                    "korean_r STRING," +
                    "korean_h STRING," +
                    "vietnam STRING," +
                    "ja_on STRING," +
                    "ja_kun STRING)";
            dbReadings.executeUpdate(readingsTable);
            dbVariants.close();


            Statement dbMeaning = connection.getConnection().createStatement();
            String meaningTable = "CREATE TABLE MEANINGS (" +
                    "literal STRING NOT NULL PRIMARY KEY," +
                    "en STRING," +
                    "fr STRING," +
                    "es STRING," +
                    "pt STRING)";
            dbMeaning.executeUpdate(meaningTable);
            dbMeaning.close();

            Statement dbNanori = connection.getConnection().createStatement();
            String nanoriTable = "CREATE TABLE NANORI (" +
                    "literal STRING NOT NULL PRIMARY KEY," +
                    "nanori STRING)";
            dbNanori.executeUpdate(nanoriTable);
            dbNanori.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void fillDB(Dictionary dictionary)
    {
        try {

            String dbInfo = "INSERT INTO KANJIDIC_INFO (file_version, database_version, database_creation_date) VALUES (?,?,?)";
            PreparedStatement infostm = connection.getConnection().prepareStatement(dbInfo);
            infostm.setInt(1, dictionary.getFileVersion());
            infostm.setString(2, dictionary.getDatabaseVersion());
            infostm.setString(3, dictionary.getDateOfCreation());
            infostm.executeUpdate();
            infostm.close();

            String dbCP = "INSERT INTO CODE_POINTS (literal, ucs, jis208, jis212, jis213) VALUES (?,?,?,?,?)";
            PreparedStatement cpstm = connection.getConnection().prepareStatement(dbCP);

            String dbMisc = "INSERT INTO MISC (literal, grade, stroke_count, frequency, jlpt) VALUES (?,?,?,?,?)";
            PreparedStatement miscstm = connection.getConnection().prepareStatement(dbMisc);

            String dbRadicals = "INSERT INTO RADICALS (literal, classical, nelson_c) VALUES (?,?,?)";
            PreparedStatement miscRadstm = connection.getConnection().prepareStatement(dbRadicals);

            String dbVariants = "INSERT INTO VARIANTS (literal, jis208, jis212, jis213, deroo, njecd, s_h, oneill, ucs, nelson_c) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement varstm = connection.getConnection().prepareStatement(dbVariants);

            String dbReadings = "INSERT INTO READINGS (literal, pinyin, korean_r, korean_h, vietnam, ja_on, ja_kun) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement readstm = connection.getConnection().prepareStatement(dbReadings);

            String dbMeanings = "INSERT INTO MEANINGS (literal, en, es, fr, pt) VALUES (?,?,?,?,?)";
            PreparedStatement meaningstm = connection.getConnection().prepareStatement(dbMeanings);

            String dbNanori = "INSERT INTO NANORI (literal, nanori) VALUES (?,?)";
            PreparedStatement nanoristm = connection.getConnection().prepareStatement(dbNanori);

            for(KanjiDic.Kanji c : dictionary.getKanji())
            {
                cpstm.setString(1, c.getLiteral());
                cpstm.setString(2, c.getCodepoints().getCpTypes().get("ucs"));
                cpstm.setString(3, c.getCodepoints().getCpTypes().get("jis208"));
                cpstm.setString(4, c.getCodepoints().getCpTypes().get("jis212"));
                cpstm.setString(5, c.getCodepoints().getCpTypes().get("jis213"));
                cpstm.executeUpdate();

                miscstm.setString(1, c.getLiteral());
                miscstm.setObject(2, c.getMisc().getGrade(), java.sql.Types.INTEGER);
                miscstm.setObject(3, c.getMisc().getStrokeCount(), java.sql.Types.INTEGER);
                miscstm.setObject(4, c.getMisc().getFrequency(), java.sql.Types.INTEGER);
                miscstm.setObject(5, c.getMisc().getJlpt(), java.sql.Types.INTEGER);
                miscstm.executeUpdate();

                miscRadstm.setString(1, c.getLiteral());
                miscRadstm.setObject(2, c.getRadicals().getRadType().get("classical"), java.sql.Types.INTEGER);
                miscRadstm.setObject(3, c.getRadicals().getRadType().get("nelson_c"), java.sql.Types.INTEGER);
                miscRadstm.executeUpdate();

                varstm.setString(1, c.getLiteral());
                varstm.setString(2, c.getMisc().getVariant().get("jis208"));
                varstm.setString(3, c.getMisc().getVariant().get("jis212"));
                varstm.setString(4, c.getMisc().getVariant().get("jis213"));
                varstm.setString(5, c.getMisc().getVariant().get("deroo"));
                varstm.setString(6, c.getMisc().getVariant().get("njecd"));
                varstm.setString(7, c.getMisc().getVariant().get("s_h"));
                varstm.setString(8, c.getMisc().getVariant().get("oneill"));
                varstm.setString(9, c.getMisc().getVariant().get("ucs"));
                varstm.setString(10, c.getMisc().getVariant().get("nelson_C"));
                varstm.executeUpdate();

                readstm.setString(1, c.getLiteral());
                StringBuilder lang = new StringBuilder();
                if(c.getReadingsMeanings().getReadings().getReadings().get("pinyin") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("pinyin")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    readstm.setString(2, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getReadings().getReadings().get("korean_r") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("korean_r")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    readstm.setString(3, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getReadings().getReadings().get("korean_h") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("korean_h")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    readstm.setString(4, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getReadings().getReadings().get("vietnam") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("vietnam")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                     readstm.setString(5, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getReadings().getReadings().get("ja_on") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("ja_on")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    readstm.setString(6, lang.toString());
                }
                lang = new StringBuilder();

                if(c.getReadingsMeanings().getReadings().getReadings().get("ja_kun") !=null) {
                    for (String s : c.getReadingsMeanings().getReadings().getReadings().get("ja_kun")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    readstm.setString(7, lang.toString());
                }
                readstm.executeUpdate();

                meaningstm.setString(1, c.getLiteral());

                lang = new StringBuilder();
                if(c.getReadingsMeanings().getMeanings().getMeanings().get("en") != null)
                {
                    for (String s : c.getReadingsMeanings().getMeanings().getMeanings().get("en")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    meaningstm.setString(2, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getMeanings().getMeanings().get("es") != null)
                {
                    for (String s : c.getReadingsMeanings().getMeanings().getMeanings().get("es")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    meaningstm.setString(3, lang.toString());
                }
                lang = new StringBuilder();
                if(c.getReadingsMeanings().getMeanings().getMeanings().get("fr") != null)
                {
                    for (String s : c.getReadingsMeanings().getMeanings().getMeanings().get("fr")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    meaningstm.setString(4, lang.toString());
                }

                lang = new StringBuilder();
                if(c.getReadingsMeanings().getMeanings().getMeanings().get("pt") != null)
                {
                    for (String s : c.getReadingsMeanings().getMeanings().getMeanings().get("pt")) {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    meaningstm.setString(5, lang.toString());
                }
                meaningstm.executeUpdate();

                lang = new StringBuilder();
                nanoristm.setString(1, c.getLiteral());
                if(c.getNanori() != null)
                {
                    for(String s : c.getNanori())
                    {
                        lang.append("(");
                        lang.append(s);
                        lang.append(")");
                    }
                    nanoristm.setString(2, lang.toString());
                }
                nanoristm.executeUpdate();


                cpstm.clearParameters();
                miscRadstm.clearParameters();
                miscstm.clearParameters();
                varstm.clearParameters();
                readstm.clearParameters();
                meaningstm.clearParameters();
                nanoristm.clearParameters();
            }
            cpstm.close();
            miscstm.close();
            miscRadstm.close();
            varstm.close();
            readstm.close();
            meaningstm.close();
            nanoristm.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        connection.closeConnection();
    }

    public void genFromKanjiDic(Dictionary dictionary, DBType dbType, String path, String password)
    {
        switch (dbType)
        {
            case MySQL:
                break;
            case SQLite:
                connection = new SQLiteConnection();
                connection.connect(path, password);
                prepareDB();
                fillDB(dictionary);
                break;
        }
    }
}
