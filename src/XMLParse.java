import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParse {

  public static void main(String argv[]) {
      try {
          File boardXML = new File("board.xml");
          File cardsXML = new File("cards.xml");
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document docBoard = db.parse(boardXML);
          Document docCards = db.parse(cardsXML);

          docBoard.getDocumentElement().normalize();
          docCards.getDocumentElement().normalize();

          NodeList board = docBoard.getElementsByTagName("set");
          NodeList cards = docBoard.getElementsByTagName("card");


      } catch (Exception i){
        i.printStackTrace();
      }

  }

}
