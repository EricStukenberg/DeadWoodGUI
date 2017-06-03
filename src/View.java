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
import java.io.FileOutputStream;
import java.io.FileWriter;


public class View extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel[];
  JLabel card2label;
  JLabel playerlabel[];
  JLabel playerInfo;
  JLabel lRoles;


  JLabel mLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bEnd;
  JButton bRoom1;
  JButton bRoom2;
  JButton bRoom3;
  JButton bRoom4;

  JButton bRole1;
  JButton bRole2;
  JButton bRole3;
  JButton bRole4;

  Boolean bR4Created;
  Viewset sets[];
  ImageIcon icon;
  Board model;
  boolean moved;
  Boolean bRolesCreated;
  // JLayered Pane
  JLayeredPane bPane;

  // Constructor

  public View(Board model1) {
    // Set the title of the JFrame
    super("Deadwood");
    model = model1;
    bR4Created = false;
    bRolesCreated = false;

    // Set the exit option for the JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    moved = false;

    // Create the JLayeredPane to hold the display, cards, role dice and buttons

    bPane = getLayeredPane();
    XMLParse xml = new XMLParse();

    // Create the deadwood board
    boardlabel = new JLabel();
    icon =  new ImageIcon("../resources/board.jpg");
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

    buttons();

  }

  //Controller stuff needs to be moveed

  class boardMouseListener implements MouseListener{

    // Code for the different button clicks
    public void mouseClicked(MouseEvent e) {

      Player currPlayer = model.getCurrPlayer();
      Set[] rooms = currPlayer.getLocation().getAdjacentRooms();
       if (e.getSource()== bAct){
          System.out.println("Acting is Selected\n");

       }else if (e.getSource()== bRehearse){
          System.out.println("Rehearse is Selected\n");

       }else if (e.getSource()== bEnd){
             //System.out.println("End turn is Selected\n");
             model.endClick = false;
             model.currPlayer = currPlayer;
             model.input = "end";
             currPlayer = model.getCurrPlayer();
             updateButtons();
             //model.readUserInput("end", currPlayer);

       } else if (e.getSource()== bRoom1){
          String roomName = rooms[0].getName();
          // model.readUserInput("move " + roomName, currPlayer);
          model.currPlayer = currPlayer;
          model.input = "move " + roomName;
          moved = true;
          String newLoc = currPlayer.getLocation().getName();
          System.out.println("The player moved to " + newLoc);
          showPlayers(model.getNumberOfPlayers(), model);
          updateButtons();


       } else if (e.getSource()== bRoom2){
           String roomName = rooms[1].getName();
           System.out.println("Selected Move to "  + roomName);
           currPlayer.move(roomName);
           model.currPlayer = currPlayer;
           model.input = "move " + roomName;
           moved = true;
           showPlayers(model.getNumberOfPlayers(), model);
           updateButtons();


       } else if (e.getSource()== bRoom3){
           String roomName = rooms[2].getName();
           System.out.println("Selected Move to "  + roomName);
           currPlayer.move(roomName);
           model.currPlayer = currPlayer;
           model.input = "move " + roomName;
           moved = true;
           showPlayers(model.getNumberOfPlayers(), model);
           updateButtons();


       } else if (e.getSource()== bRoom4){
           String roomName = rooms[3].getName();
           System.out.println("Selected Move to "  + roomName);
           currPlayer.move(roomName);
           moved = true;
           showPlayers(model.getNumberOfPlayers(), model);
           updateButtons();
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
       ImageIcon p1Icon = new ImageIcon("../resources/"+ firstLetter + rank +".png");
       playerlabel[i].setIcon(p1Icon);
       playerlabel[i].setBounds(x,y,194,201);
       bPane.add(playerlabel[i],new Integer(3));
       offSet = offSet + 20;
     }
   }


   public void buttons() {
     if(model == null) {
       System.out.println("Error");
     }
     int numPlayers = model.getNumberOfPlayers();
     playerlabel = new JLabel[numPlayers];
     for(int j = 0; j < numPlayers; j++) {
       playerlabel[j] = new JLabel();
     }
     showPlayers(numPlayers, model);



     // Create the Menu for action buttons

     mLabel = new JLabel("MENU");
     mLabel.setBounds(icon.getIconWidth()+40,5,200,20);
     Color customColor = new Color(153,76,2);
     mLabel.setForeground(customColor);
     mLabel.setFont(new Font("Courier New", Font.BOLD, 18));
     bPane.add(mLabel,new Integer(2));

     // Create Action buttons
     bAct = new JButton("ACT");
     bAct.setBackground(Color.white);
     bAct.setBounds(icon.getIconWidth()+10, 30,200, 20);
     bAct.addMouseListener(new boardMouseListener());
     bPane.add(bAct, new Integer(2));


     bRehearse = new JButton("REHEARSE");
     bRehearse.setBackground(Color.white);
     bRehearse.setBounds(icon.getIconWidth()+10,60,200, 20);
     bRehearse.addMouseListener(new boardMouseListener());
     bPane.add(bRehearse, new Integer(2));

     Player player = model.getCurrPlayer();
     Set location = player.getLocation();
     Set[] neighbors = location.getAdjacentRooms();
     int numNB = 0;
     for(int i = 0; i < 4; i++) {
       if(neighbors[i] != null) {
         System.out.println("Num rooms " + numNB);
         numNB++;
       }
     }
     String room1 = neighbors[0].getName();
     String room2 = neighbors[1].getName();
     String room3 = neighbors[2].getName();


     bRoom1 = new JButton("Move to " + room1);
     bRoom1.setBackground(Color.white);
     bRoom1.setBounds(icon.getIconWidth()+10,90,200, 20);
     bPane.add(bRoom1, new Integer(2));
     if(moved == false ) {
       bRoom1.addMouseListener(new boardMouseListener());
     }

     bRoom2 = new JButton("Move to " + room2);
     bRoom2.setBackground(Color.white);
     bRoom2.setBounds(icon.getIconWidth()+10,120,200, 20);
     bPane.add(bRoom2, new Integer(2));
     if(moved == false ) {
       bRoom2.addMouseListener(new boardMouseListener());
     }

     bRoom3 = new JButton("Move to " + room3);
     bRoom3.setBackground(Color.white);
     bRoom3.setBounds(icon.getIconWidth()+10,150,200, 20);
     bPane.add(bRoom3, new Integer(2));
     if(moved == false ) {
       bRoom3.addMouseListener(new boardMouseListener());
     }
     //String room4 = neighbors[3].getName();
     bRoom4 = new JButton(" ");
     bRoom4.setBackground(Color.white);
     bRoom4.setBounds(icon.getIconWidth()+10,180,200, 20);
     bPane.add(bRoom4, new Integer(2));
     bRoom4.addMouseListener(new boardMouseListener());


     bEnd = new JButton("END TURN");
     bEnd.setBackground(Color.white);
     bEnd.setBounds(icon.getIconWidth()+10,210,200, 20);
     bEnd.addMouseListener(new boardMouseListener());
     bPane.add(bEnd, new Integer(2));

   }

  public void updateButtons() {
    Player player = model.getCurrPlayer();
    Set location = player.getLocation();
    Set[] neighbors = location.getAdjacentRooms();
    int numNB = 0;
    for(int i = 0; i < 4; i++) {
      if(neighbors[i] != null) {
        System.out.println("Num rooms " + numNB);
        numNB++;
      }
    }
    String room1 = neighbors[0].getName();
    String room2 = neighbors[1].getName();
    String room3 = neighbors[2].getName();
    bRoom1.setText("Move to " + room1);
    bPane.add(bRoom1, new Integer(2));
    bRoom2.setText("Move to " + room2);
    bPane.add(bRoom2, new Integer(2));
    bRoom3.setText("Move to " + room3);
    bPane.add(bRoom3, new Integer(2));

    String room4 = "  ";
    System.out.println("Num rooms " + numNB);
    if(numNB == 4 ) {
      room4 = neighbors[3].getName();
      room4 = "Move to " + room4;
    }

    bRoom4.setText(room4);
    bPane.add(bRoom4, new Integer(2));

    if(location.getRole(0) != null) {
      if(bRolesCreated == false) {
        bRolesCreated = true;

        int numRoles = location.getNumofRoles();

        Role role1 = location.getRole(0);
        String roleName1 = role1.getName();
        bRole1 = new JButton("Take " + roleName1);
        bRole1.setBackground(Color.white);
        bRole1.setBounds(icon.getIconWidth()+10,240,200, 20);
        bPane.add(bRole1, new Integer(2));
        bRole1.addMouseListener(new boardMouseListener());

        Role role2 = location.getRole(1);
        String roleName2 = role2.getName();
        bRole1 = new JButton("Take " + roleName2);
        bRole1.setBackground(Color.white);
        bRole1.setBounds(icon.getIconWidth()+230,240, 200, 20);
        bPane.add(bRole1, new Integer(2));
        bRole1.addMouseListener(new boardMouseListener());

        if(numRoles > 2) {
          Role role3 = location.getRole(2);
          String roleName3 = role3.getName();
          bRoom3 = new JButton("Take " + roleName3);
          bRoom3.setBackground(Color.white);
          bRoom3.setBounds(icon.getIconWidth()+10,270,200, 20);
          bPane.add(bRoom3, new Integer(2));
          if(moved == false ) {
            bRoom3.addMouseListener(new boardMouseListener());
          }
          if(numRoles > 2) {
            Role role4 = location.getRole(3);
            String roleName4 = role4.getName();
            bRoom4 = new JButton("Take " + roleName4);
            bRoom4.setBackground(Color.white);
            bRoom4.setBounds(icon.getIconWidth()+230,270,200, 20);
            bPane.add(bRoom4, new Integer(2));
            bRoom4.addMouseListener(new boardMouseListener());
          }
        }
      } else {
        
      }
    }

  }

}
