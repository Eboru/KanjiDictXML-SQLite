package KanjiDic;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//This is the header
public class Dictionary {
    private int fileVersion = -1;
    private String databaseVersion = "";
    private String dateOfCreation = "";
    private ArrayList<Kanji> kanji = new ArrayList<Kanji>();

    public Dictionary(String path)
    {
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fXmlFile);
            document.getElementsByTagName("header");
            fillThis(document);
            fillChars(document);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillThis(Document document)
    {
        Element header = (Element) document.getElementsByTagName("header").item(0);
        setFileVersion(Integer.parseInt(header.getElementsByTagName("file_version").item(0).getTextContent()));
        setDatabaseVersion(header.getElementsByTagName("database_version").item(0).getTextContent());
        setDateOfCreation(header.getElementsByTagName("date_of_creation").item(0).getTextContent());
    }

    private void fillChars(Document document)
    {
        NodeList nodes = document.getElementsByTagName("character");
        for(int i = 0; i<nodes.getLength(); i++)
        {
            Element echaracter = (Element)nodes.item(i);
            Kanji kanji = new Kanji();

            //Sets literal
            kanji.setLiteral(echaracter.getElementsByTagName("literal").item(0).getTextContent());

            //Sets codepoints
            Codepoints codepoints = new Codepoints();
            NodeList cp_values = ((Element)(echaracter.getElementsByTagName("codepoint").item(0))).getElementsByTagName("cp_value");
            for(int k = 0; k< cp_values.getLength(); k++)
                codepoints.getCpTypes().put(cp_values.item(k).getAttributes().getNamedItem("cp_type").getTextContent(), cp_values.item(k).getTextContent());
            kanji.setCodepoints(codepoints);

            //Sets radicals
            Radical radicals = new Radical();
            NodeList rads = ((Element)(echaracter.getElementsByTagName("radical").item(0))).getElementsByTagName("rad_value");
            for(int k = 0; k< rads.getLength(); k++)
                radicals.getRadType().put(rads.item(k).getAttributes().getNamedItem("rad_type").getTextContent(), Integer.parseInt(rads.item(k).getTextContent()));
            kanji.setRadicals(radicals);

            //Sets misc
            Misc misc = new Misc();
            Element ms = (Element)(echaracter.getElementsByTagName("misc").item(0));

            Element grade = (Element) ms.getElementsByTagName("grade").item(0);
            if(grade != null)
                misc.setGrade(Integer.parseInt(grade.getTextContent()));

            Element strokeCount = (Element) ms.getElementsByTagName("stroke_count").item(0);
            if(strokeCount != null)
                misc.setStrokeCount(Integer.parseInt(strokeCount.getTextContent()));

            Element freq = (Element) ms.getElementsByTagName("freq").item(0);
            if(freq != null)
                misc.setFrequency(Integer.parseInt(freq.getTextContent()));

            Element jlpt = (Element) ms.getElementsByTagName("jlpt").item(0);
            if(jlpt != null)
                misc.setJlpt(Integer.parseInt(jlpt.getTextContent()));

            NodeList variants = ms.getElementsByTagName("variant");
            for(int k = 0; k<variants.getLength(); k++)
                misc.getVariant().put(variants.item(k).getAttributes().getNamedItem("var_type").getTextContent(), variants.item(k).getTextContent());

            NodeList radNames = ms.getElementsByTagName("rad_name");
            for(int k=0; k<radNames.getLength(); k++)
                misc.getRadName().add(radNames.item(k).getTextContent());

            kanji.setMisc(misc);

            //Sets dick references
            if(echaracter.getElementsByTagName("dic_number").item(0)!=null) {
                DicReferences dictionaryRefs = new DicReferences();
                NodeList dicRef = ((Element) (echaracter.getElementsByTagName("dic_number").item(0))).getElementsByTagName("dic_ref");
                for (int k = 0; k < dicRef.getLength(); k++) {
                    StringBuilder builder = new StringBuilder();
                    String drType = dicRef.item(k).getAttributes().getNamedItem("dr_type").getTextContent();
                    builder.append(drType);
                    if (drType.equals("moro") && dicRef.item(k).getAttributes().getLength() == 3) {
                        builder.append("/m_vol=");
                        builder.append(dicRef.item(k).getAttributes().getNamedItem("m_vol").getTextContent());
                        builder.append("/m_page=");
                        builder.append(dicRef.item(k).getAttributes().getNamedItem("m_page").getTextContent());
                    }
                    dictionaryRefs.getDictionaryReferences().put(builder.toString(), dicRef.item(k).getTextContent());
                }
                kanji.setDicReferences(dictionaryRefs);
            }

            //Sets Query Code
            if(echaracter.getElementsByTagName("query_code").item(0)!=null) {
                QueryCodes queryCodes = new QueryCodes();
                NodeList qCodes = ((Element) (echaracter.getElementsByTagName("query_code").item(0))).getElementsByTagName("q_code");

                for (int k = 0; k < qCodes.getLength(); k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(qCodes.item(k).getAttributes().getNamedItem("qc_type").getTextContent());
                    if (qCodes.item(k).getAttributes().getLength() == 2) {
                        sb.append("/skip_misclass=");
                        sb.append(qCodes.item(k).getAttributes().getNamedItem("skip_misclass").getTextContent());
                    }
                    queryCodes.getQueryCodes().put(sb.toString(), qCodes.item(k).getTextContent());
                }
                kanji.setQueryCodes(queryCodes);
            }

            //Sets readingMeaning

            ReadingsMeanings rms = new ReadingsMeanings();

                //Readings
            if(echaracter.getElementsByTagName("reading_meaning").item(0) != null) {
                NodeList readings = ((Element) ((Element) (echaracter.getElementsByTagName("reading_meaning").item(0))).getElementsByTagName("rmgroup").item(0)).getElementsByTagName("reading");
                for (int k = 0; k < readings.getLength(); k++) {
                    if (!rms.getReadings().getReadings().containsKey(readings.item(k).getAttributes().getNamedItem("r_type").getTextContent()))
                        rms.getReadings().getReadings().put(readings.item(k).getAttributes().getNamedItem("r_type").getTextContent(), new ArrayList<String>());
                    rms.getReadings().getReadings().get(readings.item(k).getAttributes().getNamedItem("r_type").getTextContent()).add(readings.item(k).getTextContent());
                }

                //Meanings
                NodeList meanings = ((Element) ((Element) (echaracter.getElementsByTagName("reading_meaning").item(0))).getElementsByTagName("rmgroup").item(0)).getElementsByTagName("meaning");
                for (int k = 0; k < meanings.getLength(); k++) {
                    Node langNode = meanings.item(k).getAttributes().getNamedItem("m_lang");
                    if (langNode == null) {
                        if (!rms.getMeanings().getMeanings().containsKey("en"))
                            rms.getMeanings().getMeanings().put("en", new ArrayList<String>());
                        rms.getMeanings().getMeanings().get("en").add(meanings.item(k).getTextContent());
                        continue;
                    }
                    String lang = meanings.item(k).getAttributes().getNamedItem("m_lang").getTextContent();
                    switch (lang) {
                        case "fr":
                            if (!rms.getMeanings().getMeanings().containsKey("fr"))
                                rms.getMeanings().getMeanings().put("fr", new ArrayList<String>());
                            rms.getMeanings().getMeanings().get("fr").add(meanings.item(k).getTextContent());
                            break;
                        case "es":
                            if (!rms.getMeanings().getMeanings().containsKey("es"))
                                rms.getMeanings().getMeanings().put("es", new ArrayList<String>());
                            rms.getMeanings().getMeanings().get("es").add(meanings.item(k).getTextContent());
                            break;
                        case "pt":
                            if (!rms.getMeanings().getMeanings().containsKey("pt"))
                                rms.getMeanings().getMeanings().put("pt", new ArrayList<String>());
                            rms.getMeanings().getMeanings().get("pt").add(meanings.item(k).getTextContent());
                            break;
                    }
                }
                kanji.setReadingsMeanings(rms);
            }

            //Sets nanori
            if(echaracter.getElementsByTagName("reading_meaning").item(0)!=null) {
                NodeList nanori = ((Element) (echaracter.getElementsByTagName("reading_meaning").item(0))).getElementsByTagName("nanori");
                for (int k = 0; k < nanori.getLength(); k++) {
                    kanji.getNanori().add(nanori.item(k).getTextContent());
                }
            }
            getKanji().add(kanji);

        }
    }


    public int getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(int fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public ArrayList<Kanji> getKanji() {
        return kanji;
    }

    public void setKanji(ArrayList<Kanji> kanji) {
        this.kanji = kanji;
    }
}
