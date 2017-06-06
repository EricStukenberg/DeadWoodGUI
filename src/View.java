import java.awt.*;
import java.awt.Color;
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

  JButton bOnRole1;
  JButton bOnRole2;
  JButton bOnRole3;
  JButton bOnRole4;

  JButton upgradeButtons[];

  Boolean bR4Created;
  Viewset sets[];
  ImageIcon icon;
  Board model;
  boolean moved;
  boolean bRolesCreated;
  Boolean bRolesCreated1;
  Boolean bRolesCreated2;
  boolean bSceneCreated;
  Boolean bSceneCreated1;
  Boolean bSceneCreated2;
  Boolean playLabelCreated;
  // JLayered Pane
  JLayeredPane bPane;

  // Constructor
  public View(Board model1) {
    // Set the title of the JFrame
    super("Deadwood");
    model = model1;
    bR4Created = false;
    bRolesCreated = false;
    bRolesCreated1 = false;
    bRolesCreated2 = false;
    bSceneCreated = false;
    bSceneCreated1 = false;
    bSceneCreated2 = false;
    playLabelCreated = false;

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
    setSize(icon.getIconWidth() + 600,icon.getIconHeight()+200);

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
    showUpgradeButtons();

  }

  //Controller stuff needs to be moveed

  class boardMouseListener implements MouseListener{

    // Code for the different button clicks
    public void mouseClicked(MouseEvent e) {

      Player currPlayer = model.getCurrPlayer();
      Set[] rooms = currPlayer.getLocation().getAdjacentRooms();
      int i = 0;

       if (e.getSource()== bAct){
          System.out.println("Acting is Selected\n");

       }else if (e.getSource()== bRehearse){
          System.out.println("Rehearse is Selected\n");

       }else if (e.getSource()== bEnd){
             model.endClick = true;
             currPlayer = model.getCurrPlayer();
             model = model;
             moved = false;
             showPlayers(model.getNumberOfPlayers(), model);
             showPlayers(model.getNumberOfPlayers(), model);
             updateButtons();
             updateButtons(); // fixes bug

       } else if (e.getSource()== bRoom1){
          String roomName = rooms[0].getName();
          if(moved == false) {
              moved = true;
              currPlayer.move(roomName);
            for (int j = 0; i < 10; i++) {
              if (sets[i].name.equals(roomName)) {
                sets[i].visit(model);
              }
            }
            String newLoc = currPlayer.getLocation().getName();
            System.out.println("The player moved to " + newLoc);
            showPlayers(model.getNumberOfPlayers(), model);
            updateButtons();
          }

       } else if (e.getSource()== bRoom2){
           String roomName = rooms[1].getName();
           if(moved == false) {
               moved = true;
               currPlayer.move(roomName);
             for (int j = 0; i < 10; i++) {
               if (sets[i].name.equals(roomName)) {
                 sets[i].visit(model);
               }
             }
             String newLoc = currPlayer.getLocation().getName();
             System.out.println("The player moved to " + newLoc);
             showPlayers(model.getNumberOfPlayers(), model);
             updateButtons();
           }

       } else if (e.getSource()== bRoom3){
           String roomName = rooms[2].getName();

          if(moved == false) {
           moved = true;
           currPlayer.move(roomName);
           for (int j = 0; i < 10; i++) {
             if (sets[i].name.equals(roomName)) {
               sets[i].visit(model);
             }
           }
           String newLoc = currPlayer.getLocation().getName();
           System.out.println("The player moved to " + newLoc);
           showPlayers(model.getNumberOfPlayers(), model);
           updateButtons();
         }

       } else if (e.getSource()== bRoom4){
          if (rooms[3] != null) {
            if(moved == false) {
               String roomName = rooms[3].getName();
               System.out.println("The player moved to " + roomName);
               moved = true;
               currPlayer.move(roomName);
               for (int j = 0; i < 10; i++) {
                 if (sets[i].name.equals(roomName)) {
                   sets[i].visit(model);
                 }
               }
               String newLoc = currPlayer.getLocation().getName();
               System.out.println("The player moved to " + newLoc);
               showPlayers(model.getNumberOfPlayers(), model);
               updateButtons();
            }
           }
       } else if (e.getSource()== bRoom4){
          if (rooms[3] != null) {
             String roomName = rooms[3].getName();
             System.out.println("%%%%%% Moving to " + roomName);
             if(moved == false) {
              moved = true;
                currPlayer.move(roomName);
               for (int j = 0; i < 10; i++) {
                 if (sets[i].name.equals(roomName)) {
                   sets[i].visit(model);
                 }
               }
               String newLoc = currPlayer.getLocation().getName();
               System.out.println("The player moved to " + newLoc);
               showPlayers(model.getNumberOfPlayers(), model);
               updateButtons();
             }
          }
       } else if (e.getSource() == bRole1) {

       } else if (e.getSource() == bRole2) {

       }  else if (e.getSource() == bRole3) {

       }  else if (e.getSource() == bRole4) {

       }  else {
           int[][] prices = model.casting_office.getUpgradePrices();
           int cLevel = 2;
           int dLevel = 2;
           int xOffset = 0;
           for(int j = 0; j < 10; j++) {
             if(e.getSource() == upgradeButtons[j]) {
               if((j%2) == 0) {
                 model.upgradeMoney(dLevel, currPlayer);
                 break;
               } else {

                 model.upgradeCredit(cLevel, currPlayer);
                 break;
               }
             }
             if((j%2) == 0) {
               dLevel++;
             } else {
               cLevel++;
             }
           }
           showPlayers(model.getNumberOfPlayers(), model);
           showPlayers(model.getNumberOfPlayers(), model);
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
     Player currPlayer = model.getCurrPlayer();
     String playerName = currPlayer.getName();
     String who = model.who(currPlayer);
     showPlayerLabel( who, playerName);

     for(int i = 0; i < numPlayers; i++) {
       currPlayer = thePlayers[i];
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

  public void showPlayerLabel(String who, String name) {
    if(playLabelCreated == false) {
      playLabelCreated = true;
      playerInfo = new JLabel(who);
    } else {
      playerInfo.setText(who);
    }
    playerInfo.setBounds(icon.getIconWidth()+250,180,200,30);
    String firstLetter = name.substring(0,1);
    if(firstLetter.equals("b")) {
      playerInfo.setForeground(Color.blue);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("c")) {
      playerInfo.setForeground(Color.cyan);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("g")) {
      playerInfo.setForeground(Color.green);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("o")) {
      playerInfo.setForeground(Color.orange);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("p")) {
      playerInfo.setForeground(Color.pink);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("r")) {
      playerInfo.setForeground(Color.red);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("v")) {
      Color color = new Color(148,0,211);
      playerInfo.setForeground(color);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
    } else if (firstLetter.equals("y")) {
      playerInfo.setForeground(Color.yellow);
      playerInfo.setFont(new Font("Courier New", Font.ITALIC, 24));
      bPane.add(playerInfo,new Integer(2));
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
     bRoom1.addMouseListener(new boardMouseListener());


     bRoom2 = new JButton("Move to " + room2);
     bRoom2.setBackground(Color.white);
     bRoom2.setBounds(icon.getIconWidth()+10,120,200, 20);
     bPane.add(bRoom2, new Integer(2));
     bRoom2.addMouseListener(new boardMouseListener());

     bRoom3 = new JButton("Move to " + room3);
     bRoom3.setBackground(Color.white);
     bRoom3.setBounds(icon.getIconWidth()+10,150,200, 20);
     bPane.add(bRoom3, new Integer(2));
     bRoom3.addMouseListener(new boardMouseListener());

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

   //shows display upgrade buttons
   public void showUpgradeButtons() {
     upgradeButtons = new JButton[10];
     int xOffset = 0;
     int yOffset = 0;
     for(int i = 0; i < 10; i++) {
       upgradeButtons[i] = new JButton();
       upgradeButtons[i].setBackground(Color.white);
       upgradeButtons[i].setBounds(98 + xOffset,543 + yOffset,25, 21);
       upgradeButtons[i].addMouseListener(new boardMouseListener());
       upgradeButtons[i].setOpaque(false);
       bPane.add(upgradeButtons[i], new Integer(2));
       xOffset = xOffset + 45;
       if((i%2) != 0) {
         xOffset = 0;
         yOffset = yOffset + 21;
       }
     }


   }

  //updates the main action buttons
  public void updateButtons() {
    Player player = model.getCurrPlayer();
    Set location = player.getLocation();
    Set[] neighbors = location.getAdjacentRooms();
    int numNB = 0;
    for(int i = 0; i < 4; i++) {
      if(neighbors[i] != null) {
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
    if(numNB == 4 ) {
      room4 = neighbors[3].getName();
      room4 = "Move to " + room4;
    }

    bRoom4.setText(room4);
    bPane.add(bRoom4, new Integer(2));
    updateRoleButtons(location, player);
    updateSceneButtons(location, player);
  }

  //updates the taking a role button
  public void updateRoleButtons(Set location, Player player) {

    if(!(location.getName().equals("Trailers")) && !(location.getName().equals("Casting Office"))) {
      if (bRolesCreated == false) {
        bRolesCreated = true;

        lRoles = new JLabel("OFF CARD ROLES");
        lRoles.setBounds(icon.getIconWidth()+10, 240,200, 20);
        Color customColor = new Color(4, 106, 161);
        lRoles.setForeground(customColor);
        lRoles.setFont(new Font("Courier New", Font.BOLD, 18));
        bPane.add(lRoles,new Integer(2));

        int numRoles = location.getNumofRoles();

        Role role1 = location.getRole(0);
        String roleName1 = role1.getName();
        bRole1 = new JButton("Take " + roleName1);
        bRole1.setBackground(Color.lightGray);
        bRole1.setBounds(icon.getIconWidth()+10,270,200, 20);
        bPane.add(bRole1, new Integer(2));
        bRole1.addMouseListener(new boardMouseListener());

        Role role2 = location.getRole(1);
        String roleName2 = role2.getName();
        bRole2 = new JButton("Take " + roleName2);
        bRole2.setBackground(Color.lightGray);
        bRole2.setBounds(icon.getIconWidth()+230,270, 200, 20);
        bPane.add(bRole2, new Integer(2));
        bRole2.addMouseListener(new boardMouseListener());

        if (numRoles > 2) {

          Role role3 = location.getRole(2);
          String roleName3 = role3.getName();
          bRole3 = new JButton("Take " + roleName3);
          bRole3.setBackground(Color.lightGray);
          bRole3.setBounds(icon.getIconWidth()+10,300,200, 20);
          bPane.add(bRole3, new Integer(2));
          bRole3.addMouseListener(new boardMouseListener());
          if (numRoles > 3) {
            Role role4 = location.getRole(3);
            String roleName4 = role4.getName();
            bRole4 = new JButton("Take " + roleName4);
            bRole4.setBackground(Color.lightGray);
            bRole4.setBounds(icon.getIconWidth()+230,300,200, 20);
            bPane.add(bRole4, new Integer(2));
            bRole4.addMouseListener(new boardMouseListener());
          }
        }
      } else {
          int numRoles = location.getNumofRoles();
          Role role1 = location.getRole(0);
          String roleName1 = role1.getName();
          bRole1.setText("Take " + roleName1);
          bPane.add(bRole1, new Integer(2));

          Role role2 = location.getRole(1);
          String roleName2 = role2.getName();
          bRole2.setText("Take " + roleName2);
          bPane.add(bRole2, new Integer(2));


          if(numRoles > 2) {

            Role role3 = location.getRole(2);
            String roleName3 = role3.getName();
            if(bRolesCreated1 == false) {
              bRolesCreated1 = true;
              bRole3 = new JButton("Take " + roleName3);
              bRole3.setBackground(Color.lightGray);
              bRole3.setBounds(icon.getIconWidth()+10, 300, 200, 20);
              bPane.add(bRole3, new Integer(2));
              bRole3.addMouseListener(new boardMouseListener());
            } else {
              bRole3.setText("Take " + roleName3);
              bPane.add(bRole3, new Integer(2));
            }


            if(numRoles > 3) {
              Role role4 = location.getRole(3);
              String roleName4 = role4.getName();

              if (bRolesCreated2 == false) {
                bRolesCreated2 = true;
                bRole4 = new JButton("Take " + roleName4);
                bRole4.setBackground(Color.lightGray);
                bRole4.setBounds(icon.getIconWidth()+230, 300, 200, 20);
                bPane.add(bRole4, new Integer(2));
                bRole4.addMouseListener(new boardMouseListener());
              } else {

                bRole4.setText("Take " + roleName4);
                bPane.add(bRole4, new Integer(2));
              }
            } else if( bRolesCreated2 == true){
                bRole4.setText(" ");
                bPane.add(bRole4, new Integer(2));
            }
          } else {
            if (bRolesCreated1 == false) {
              bRolesCreated1 = true;
              bRole3 = new JButton(" ");
              bRole3.setBackground(Color.lightGray);
              bRole3.setBounds(icon.getIconWidth()+10, 300, 200, 20);
              bPane.add(bRole3, new Integer(2));
              bRole3.addMouseListener(new boardMouseListener());
            } else {
              bRole3.setText(" ");
              bPane.add(bRole3, new Integer(2));
            }

            if (bRolesCreated2 == false) {
              bRolesCreated2 = true;
              bRole4 = new JButton(" ");
              bRole4.setBackground(Color.lightGray);
              bRole4.setBounds(icon.getIconWidth()+230, 300, 200, 20);
              bPane.add(bRole4, new Integer(2));
              bRole4.addMouseListener(new boardMouseListener());
            } else {
              bRole4.setText(" ");
              bPane.add(bRole4, new Integer(2));
            }
          }
        }
      } else {
        if (bRolesCreated == true) {
          bRole1.setText(" ");
          bPane.add(bRole1, new Integer(2));
          bRole2.setText(" ");
          if(bRolesCreated1 == true) {
            bPane.add(bRole2, new Integer(2));
            bRole3.setText(" ");
            bPane.add(bRole3, new Integer(2));
            bRole4.setText(" ");
            bPane.add(bRole4, new Integer(2));
          }

        }
      }
  }

  public void updateSceneButtons(Set location, Player player) {

    if(!(location.getName().equals("Trailers")) && !(location.getName().equals("Casting Office"))) {
      Scene scene = location.getScenecard();
      int numRoles = scene.getNumofRoles();
      if (bSceneCreated == false) {
        bSceneCreated = true;

        lRoles = new JLabel("ON CARD ROLES");
        lRoles.setBounds(icon.getIconWidth()+10, 440, 400, 20);
        Color customColor = new Color(4, 106, 161);
        lRoles.setForeground(customColor);
        lRoles.setFont(new Font("Courier New", Font.BOLD, 18));
        bPane.add(lRoles,new Integer(2));


        Role role1 = scene.getRole(0);
        String roleName1 = role1.getName();
        bOnRole1 = new JButton("Take " + roleName1);
        bOnRole1.setBackground(Color.lightGray);
        bOnRole1.setBounds(icon.getIconWidth()+10, 470, 200, 20);
        bPane.add(bOnRole1, new Integer(2));
        bOnRole1.addMouseListener(new boardMouseListener());

        Role role2 = scene.getRole(1);
        String roleName2 = role2.getName();
        bOnRole2 = new JButton("Take " + roleName2);
        bOnRole2.setBackground(Color.lightGray);
        bOnRole2.setBounds(icon.getIconWidth()+230, 470, 200, 20);
        bPane.add(bOnRole2, new Integer(2));
        bOnRole2.addMouseListener(new boardMouseListener());

        if (numRoles > 2) {

          Role role3 = scene.getRole(2);
          String roleName3 = role3.getName();
          bOnRole3 = new JButton("Take " + roleName3);
          bOnRole3.setBackground(Color.lightGray);
          bOnRole3.setBounds(icon.getIconWidth()+10, 500, 200, 20);
          bPane.add(bOnRole3, new Integer(2));
          bOnRole3.addMouseListener(new boardMouseListener());
        }
        else {
          bOnRole3 = new JButton(" ");
          bOnRole3.setBackground(Color.lightGray);
          bOnRole3.setBounds(icon.getIconWidth()+10, 500, 200, 20);
          bPane.add(bOnRole3, new Integer(2));
          bOnRole3.addMouseListener(new boardMouseListener());
        }
      } else {
          Role role1 = scene.getRole(0);
          String roleName1 = role1.getName();
          bOnRole1.setText("Take " + roleName1);
          bPane.add(bOnRole1, new Integer(2));

          Role role2 = scene.getRole(1);
          String roleName2 = role2.getName();
          bOnRole2.setText("Take " + roleName2);
          bPane.add(bOnRole2, new Integer(2));


          if(numRoles > 2) {

            Role role3 = scene.getRole(2);
            String roleName3 = role3.getName();
            if(bSceneCreated1 == false) {
              bSceneCreated1 = true;
              bOnRole3 = new JButton("Take " + roleName3);
              bOnRole3.setBackground(Color.lightGray);
              bOnRole3.setBounds(icon.getIconWidth()+10,500,200, 20);
              bPane.add(bOnRole3, new Integer(2));
              bOnRole3.addMouseListener(new boardMouseListener());
            } else {
              bOnRole3.setText("Take " + roleName3);
              bPane.add(bOnRole3, new Integer(2));
            }
          }
          else {
            bOnRole3 = new JButton(" ");
            bOnRole3.setBackground(Color.lightGray);
            bOnRole3.setBounds(icon.getIconWidth()+10, 500, 200, 20);
            bPane.add(bOnRole3, new Integer(2));
            bOnRole3.addMouseListener(new boardMouseListener());
          }
        }
      } else {
        if (bSceneCreated == true) {
          bOnRole1.setText(" ");
          bPane.add(bOnRole1, new Integer(2));
          bOnRole2.setText(" ");
          bPane.add(bOnRole2, new Integer(2));
          bOnRole3.setText(" ");
          bPane.add(bOnRole3, new Integer(2));
        }
    }
  }
}
