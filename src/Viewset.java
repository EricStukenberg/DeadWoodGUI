


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Viewset extends JFrame {

  // Private Attributes

  // JLabels
  public JLabel cardlabel;
  public JLabel shotlabels[];
  public String name;
  public Element cardArea;
  public Element take;
  public Element set;
  public int takeCount;
  public boolean visited;
  public Scene scene;
  private XMLParse xml;


  // Constructor
  public Viewset(int i, Board model) {
    name = model.sets[i].getName();
    xml = new XMLParse();
    visited = false;

    // Grab our intial image for the scene cards
    ImageIcon cIcon =  new ImageIcon("../resources/cardback.png");
    cardlabel = new JLabel();

    // Add a JLabel to the scene position on the set
    cardlabel = new JLabel();
    cardlabel.setIcon(cIcon);

    //Grab each set in the xml document and find its card area element
    set = xml.getBoardElement(name);
    cardArea = (Element) set.getElementsByTagName("area").item(0);
    cardlabel.setBounds(Integer.parseInt(cardArea.getAttribute("x")),Integer.parseInt(cardArea.getAttribute("y")),205,115);
    cardlabel.setOpaque(true);

    // Shot counters
    ImageIcon sIcon =  new ImageIcon("../resources/shot.png");
    takeCount = model.sets[i].getShots();
    shotlabels = new JLabel[takeCount];


    for (int j = 0; j < takeCount; j++) {
      shotlabels[j] = new JLabel();
      shotlabels[j].setIcon(sIcon);
      take = (Element) set.getElementsByTagName("take").item(j);
      Element area = (Element) take.getElementsByTagName("area").item(0);
      shotlabels[j].setBounds(Integer.parseInt(area.getAttribute("x")),Integer.parseInt(area.getAttribute("y")),46,46);
      shotlabels[j].setOpaque(true);
    }
  }

  public void visit(Board model) {
    if (visited == false) {
      for (int i  = 0; i < 10; i++) {
        if (name.equals(model.sets[i].getName())) {
          scene = model.sets[i].getScenecard();
        }
      }
      
      Element cardxml = xml.getCardElement(scene.getTitle());
      System.out.println(scene.getTitle());
      System.out.println("../resources/" + cardxml.getTagName());
      ImageIcon cIcon =  new ImageIcon("../resources/" + cardxml.getAttribute("img"));
      cardlabel.setIcon(cIcon);
    }

  }
}
