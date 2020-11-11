import KanjiDic.KanjiDic;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class run {

    public static void main(String[] args) {
        KanjiDic dic = new KanjiDic("C:\\Users\\garza\\OneDrive\\Escritorio\\Kanjidic2\\kanjidic2.xml");

    }
}
