


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class View extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel[];
  JLabel card2label;
  JLabel player1label;
  JLabel player2label;
  JLabel mLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;

  // JLayered Pane
  JLayeredPane bPane;

  // Constructor

  public View() {

    // Set the title of the JFrame
    super("Deadwood");
    // Set the exit option for the JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Create the JLayeredPane to hold the display, cards, role dice and buttons

    bPane = getLayeredPane();

    // Create the deadwood board
    boardlabel = new JLabel();
    ImageIcon icon =  new ImageIcon("../resources/board.jpg");
    boardlabel.setIcon(icon);
    boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

    // Add the board to the lower layer
    bPane.add(boardlabel, new Integer(0));

    // Set the size of the GUI
    setSize(icon.getIconWidth()+200,icon.getIconHeight());

    ImageIcon cIcon =  new ImageIcon("../resources/cardback.png");
    cardlabel = new JLabel[10];

    // Add a scene card to this room
    for (int i = 0; i < 10; i++) {
      cardlabel[i] = new JLabel();
      cardlabel[i].setIcon(cIcon);
      cardlabel[i].setBounds(21,69,cIcon.getIconWidth(),cIcon.getIconHeight());
      cardlabel[i].setOpaque(true);

      // Add the board to the lower layer
      bPane.add(cardlabel[i], new Integer(1));
    }



    // Add a dice to represent a player.
    // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
    player1label = new JLabel();
    player2label = new JLabel();
    ImageIcon p1Icon = new ImageIcon("../resources/r1.png");
    ImageIcon p2Icon = new ImageIcon("../resources/b1.png");
    player1label.setIcon(p1Icon);
    player2label.setIcon(p2Icon);
    //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
    player1label.setBounds(991,248,194,201);
    player2label.setBounds(981,248,194,201);
    bPane.add(player1label,new Integer(3));
    bPane.add(player2label,new Integer(3));

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

    bMove = new JButton("MOVE");
    bMove.setBackground(Color.white);
    bMove.setBounds(icon.getIconWidth()+10,90,100, 20);
    bMove.addMouseListener(new boardMouseListener());


    // Place the action buttons in the top layer
    bPane.add(bAct, new Integer(2));
    bPane.add(bRehearse, new Integer(2));
    bPane.add(bMove, new Integer(2));
  }

  // This class implements Mouse Events

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


  public static void main(String[] args) {
    View board = new View();
    board.getContentPane().setPreferredSize(new Dimension(750,200));
    board.setVisible(true);
  }
}
