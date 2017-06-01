


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import org.w3c.dom.Element;
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
  public Element set;


  // Constructor
  public Viewset(int i, Board model) {
    name = model.sets[i].getName();
    XMLParse xml = new XMLParse();

    //Grab the images to be used on each set
    ImageIcon cIcon =  new ImageIcon("../resources/cardback.png");
    ImageIcon sIcon =  new ImageIcon("../resources/shot.png");
    cardlabel = new JLabel();
    shotlabels = new JLabel[3]; //temp

    // Add a JLabel to the scene position on the set
    cardlabel = new JLabel();
    cardlabel.setIcon(cIcon);

    //Grab each set in the xml document and find its area element
    set = xml.getBoardElement(name);
    cardArea = (Element) set.getElementsByTagName("area").item(0);
    cardlabel.setBounds(Integer.parseInt(cardArea.getAttribute("x")),Integer.parseInt(cardArea.getAttribute("y")),205,115);
    cardlabel.setOpaque(true);
  }
}
