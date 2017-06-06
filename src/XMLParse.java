import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParse {
  //public static NodeList boardList;
//  public static NodeList cardsList;
  public void XMLParse() {
      try {
          File boardXML = new File("../resources/board.xml");
          File cardsXML = new File("../resources/cards.xml");
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document docBoard = db.parse(boardXML);
          Document docCards = db.parse(cardsXML);

          docBoard.getDocumentElement().normalize();
          docCards.getDocumentElement().normalize();

        //  boardList = docBoard.getElementsByTagName("set");
        //  cardsList = docCards.getElementsByTagName("card");


      } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
      }

  }

  // takes in name of card and searches through list and returns
  // the Element
  public Element getCardElement(String name) {
    Element card = null;
    try {
      File boardXML = new File("../resources/cards.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document docBoard = db.parse(boardXML);

      docBoard.getDocumentElement().normalize();

      NodeList boardList = docBoard.getElementsByTagName("card");
      for(int i = 0; i < boardList.getLength(); i++) {
        Node currNode = boardList.item(i);
        if(currNode.getNodeType() == Node.ELEMENT_NODE) {
          Element currCard= (Element) currNode;
          String cardName = currCard.getAttribute("name");
          if(cardName.equals(name)) {
             card = currCard;
             break;
          }
        }
      }
    } catch (Exception e){
          e.printStackTrace();
          System.exit(1);
    }
    return card;
  }


  // takes in name of card and searches through list and returns
  // the Element
  public Element getBoardElement(String name) {
    Element set = null;
    try {
      File boardXML = new File("../resources/board.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document docBoard = db.parse(boardXML);

      docBoard.getDocumentElement().normalize();

      NodeList boardList = docBoard.getElementsByTagName("set");
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
    } catch (Exception e){
          e.printStackTrace();
          System.exit(1);
    }
    return set;
  }
}
