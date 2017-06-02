


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


  JLabel mLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bRoom1;
  JButton bRoom2;
  JButton bRoom3;
  JButton bRoom4;
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



    // Create the Menu for action buttons

    mLabel = new JLabel("MENU");
    mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
    bPane.add(mLabel,new Integer(2));

    // Create Action buttons
    bAct = new JButton("ACT");
    bAct.setBackground(Color.white);
    bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
    bAct.addMouseListener(new boardMouseListener(model));
    bPane.add(bAct, new Integer(2));


    bRehearse = new JButton("REHEARSE");
    bRehearse.setBackground(Color.white);
    bRehearse.setBounds(icon.getIconWidth()+10,60,150, 20);
    bRehearse.addMouseListener(new boardMouseListener(model));
    bPane.add(bRehearse, new Integer(2));

    Player player = model.getCurrPlayer();
    Set location = player.getLocation();
    Set[] nieghbors = location.getAdjacentRooms();
    int numNB = 0;
    while(nieghbors[numNB] != null) {
      numNB++;
    }
    String room1 = nieghbors[0].getName();
    String room2 = nieghbors[1].getName();
    String room3 = nieghbors[2].getName();


    bRoom1 = new JButton("Move to " + room1);
    bRoom1.setBackground(Color.white);
    bRoom1.setBounds(icon.getIconWidth()+10,90,185, 20);
    bPane.add(bRoom1, new Integer(2));
    bRoom1.addMouseListener(new boardMouseListener(model));

    bRoom2 = new JButton("Move to " + room2);
    bRoom2.setBackground(Color.white);
    bRoom2.setBounds(icon.getIconWidth()+10,120,185, 20);
    bPane.add(bRoom2, new Integer(2));
    bRoom2.addMouseListener(new boardMouseListener(model));

    bRoom3 = new JButton("Move to " + room3);
    bRoom3.setBackground(Color.white);
    bRoom3.setBounds(icon.getIconWidth()+10,150,185, 20);
    bPane.add(bRoom3, new Integer(2));
    bRoom3.addMouseListener(new boardMouseListener(model));

    if(numNB == 4) {
      String room4 = nieghbors[3].getName();
      bRoom4 = new JButton("Move to " + room4);
      bRoom4.setBackground(Color.white);
      bRoom4.setBounds(icon.getIconWidth()+10,180,185, 20);
      bPane.add(bRoom3, new Integer(2));
      bRoom4.addMouseListener(new boardMouseListener(model));
    }


    // Plac
  }

  //Controller stuff needs to be moveed

  class boardMouseListener implements MouseListener{
    public Board model1;
    public boardMouseListener(Board model) {
      model1 = model;
    }
    // Code for the different button clicks
    public void mouseClicked(MouseEvent e) {

      Player currPlayer = model1.getCurrPlayer();
      Set[] rooms = currPlayer.getLocation().getAdjacentRooms();
       if (e.getSource()== bAct){
          System.out.println("Acting is Selected\n");
       }else if (e.getSource()== bRehearse){
          System.out.println("Rehearse is Selected\n");

       } else if (e.getSource()== bRoom1){
          String roomName = rooms[0].getName();
          currPlayer.move(roomName);
          showPlayers(model1.getNumberOfPlayers(), model1);

       } else if (e.getSource()== bRoom2){
         String roomName = rooms[1].getName();
         System.out.println("Selected Move to "  + roomName);
         currPlayer.move(roomName);
         showPlayers(model1.getNumberOfPlayers(), model1);

       } else if (e.getSource()== bRoom3){
         String roomName = rooms[2].getName();
         System.out.println("Selected Move to "  + roomName);
         currPlayer.move(roomName);
         showPlayers(model1.getNumberOfPlayers(), model1);

       } else if (e.getSource()== bRoom4){
         String roomName = rooms[3].getName();
         System.out.println("Selected Move to "  + roomName);
         currPlayer.move(roomName);
         showPlayers(model1.getNumberOfPlayers(), model1);

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
