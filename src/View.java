


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


public class View extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel[];
  JLabel card2label;
  JLabel playerlabel[];
  JLabel player2label;
  JLabel player3label;
  JLabel player4label;

  JLabel mLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  Viewset sets[];
  // JLayered Pane
  JLayeredPane bPane;

  // Constructor

  public View(Board model) {
    // Set the title of the JFrame
    super("Deadwood");
    // Set the exit option for the JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Create the JLayeredPane to hold the display, cards, role dice and buttons

    bPane = getLayeredPane();
    XMLParse xml = new XMLParse();

    // Create the deadwood board
    boardlabel = new JLabel();
    ImageIcon icon =  new ImageIcon("../resources/board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Add the board to the lower layer
    bPane.add(boardlabel, new Integer(0));

    // Set the size of the GUI
    setSize(icon.getIconWidth()+200,icon.getIconHeight());

    //Grab the images to be used on each set
    sets = new Viewset[10];

    // Add a JLabel to the scene positions on each set
    for (int i = 0; i < 10; i++) {
      sets[i] = new Viewset(i, model);
      // Add the cards to the lower layer
      bPane.add(sets[i].cardlabel, new Integer(1));
      for (int j = 0; j < sets[i].takeCount; j++) {
        bPane.add(sets[i].shotlabels[j], new Integer(1));
      }

    }

    int numPlayers = model.getNumberOfPlayers();
    playerlabel = new JLabel[numPlayers];
    for(int j = 0; j < numPlayers; j++) {
      playerlabel[j] = new JLabel();
    }
    showPlayers(numPlayers, model);
    // Add a dice to represent a player.
    // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
    //player1label = new JLabel();
    //player2label = new JLabel();
    //ImageIcon p1Icon = new ImageIcon("../resources/r1.png");
  //  ImageIcon p2Icon = new ImageIcon("../resources/b1.png");
  //  player1label.setIcon(p1Icon);
  //  player2label.setIcon(p2Icon);
    //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
  //  player1label.setBounds(991,248,194,201);
  //  player2label.setBounds(981,248,194,201);
  //  bPane.add(player1label,new Integer(3));
  //  bPane.add(player2label,new Integer(3));

    // Create the Menu for action buttons
    mLabel = new JLabel("MENU");
    mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    bAct = new JButton("ACT");
    bAct.setBackground(Color.white);
    bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
    bAct.addMouseListener(new boardMouseListener());

    bRehearse = new JButton("REHEARSE");
    bRehearse.setBackground(Color.white);
    bRehearse.setBounds(icon.getIconWidth()+10,60,100, 20);
    bRehearse.addMouseListener(new boardMouseListener());

    bMove = new JButton("MOVE ROOMS");
    bMove.setBackground(Color.white);
    bMove.setBounds(icon.getIconWidth()+10,90,150, 20);
    bMove.addMouseListener(new boardMouseListener());


    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));
  }

  //Controller stuff needs to be moveed

  class boardMouseListener implements MouseListener{

    // Code for the different button clicks
    public void mouseClicked(MouseEvent e) {

       if (e.getSource()== bAct){
          System.out.println("Acting is Selected\n");
       }
       else if (e.getSource()== bRehearse){
          System.out.println("Rehearse is Selected\n");
       }
       else if (e.getSource()== bMove){
          System.out.println("Move is Selected\n");
       }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
   }

   public void showPlayers(int numPlayers, Board model) {
     Player[] thePlayers = model.players;
     int offSet = 5;
     for(int i = 0; i < numPlayers; i++) {
       Player currPlayer = thePlayers[i];
       int rank = currPlayer.getRank();
       Set location = currPlayer.getLocation();
       int x = location.getX() + offSet;
       int y = location.getY();
       String firstLetter = currPlayer.getName();
       firstLetter = firstLetter.substring(0,1);
       System.out.println(firstLetter);
       ImageIcon p1Icon = new ImageIcon("../resources/"+ firstLetter + rank +".png");
       playerlabel[i].setIcon(p1Icon);
       playerlabel[i].setBounds(x,y,194,201);
       bPane.add(playerlabel[i],new Integer(3));
       offSet = offSet + 20;
     }
   }

}
