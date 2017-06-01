package view;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// This singleton class loads the 7-segment images once and keeps track of them
// throughout the program's execution.
public class Resources {
  // This array actually holds the images, which are indexed by the displayed
  // number.  That is, big[1] is the 7-segment number 1.
  private ImageIcon[] deck;
  private ImageIcon board;
  static Resources instance;

  // This constructor creates the only instance of the class and reads in the
  // images from the files.  This is hard coded to look for the resources in a
  // specific directory.  This could be improved in two ways.  the path could be
  // more flexible and the images could be loaded on demand.
  private Resources() {
    deck = new ImageIcon[40];

    try {
      for (int i = 0; i < 40; ++i)
        deck[i] = new ImageIcon (
          ImageIO.read(
            new File(String.format("../resources/%d.png", i))));

      board = new ImageIcon (
          ImageIO.read(new File("../resources/board.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public ImageIcon getCard(int i) {
    return deck[i];
  }

  public ImageIcon getBoard(){
    return board;
  }

  public static Resources getInstance() {
    if (instance == null)
      instance = new Resources();
    return instance;
  }

}
