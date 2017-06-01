import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParse {
  static NodeList boardList;
  static NodeList cardsList;
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

          boardList = docBoard.getElementsByTagName("set");
          cardsList = docBoard.getElementsByTagName("card");


      } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
      }

  }

  // takes in name of card and searches through list and returns
  // the Element
  public Element getCardElement(String name) {
    Element card = null;
    for(int i = 0; i < cardsList.getLength(); i++) {
      Node currNode = cardsList.item(i);
      if(currNode.getNodeType() == Node.ELEMENT_NODE) {
        Element currCard = (Element) currNode;
        String cardName = currCard.getAttribute("name");
        if(cardName.equals(name)) {
           card = currCard;
           break;
        }
      }
    }
    return card;
  }

  // takes in name of set and searches through list and returns
  // the Element
  public Element getBoardElement(String name) {
    Element set = null;
    for(int i = 0; i < boardList.getLength(); i++) {
      Node currNode = boardList.item(i);
      if(currNode.getNodeType() == Node.ELEMENT_NODE) {
        Element currSet= (Element) currNode;
        String setName = currSet.getAttribute("name");
        if(setName.equals(name)) {
           set = currSet;
           break;
        }
      }
    }
    return set;
  }
}
