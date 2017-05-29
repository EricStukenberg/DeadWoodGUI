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
  private ImageIcon[] big;
  private ImageIcon background;
  static Resources instance;

  // This constructor creates the only instance of the class and reads in the
  // images from the files.  This is hard coded to look for the resources in a
  // specific directory.  This could be improved in two ways.  the path could be
  // more flexible and the images could be loaded on demand.
  private Resources() {
    big = new ImageIcon[10];

    try {
      for (int i = 0; i < 10; ++i)
        big[i] = new ImageIcon (
          ImageIO.read(
            new File(String.format("../resources/%d.png", i))));

      background = new ImageIcon (
          ImageIO.read(new File("../resources/bg.jpeg")));
    }catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public ImageIcon getIcon(int i) {
    return big[i];
  }

  public ImageIcon getBG(){
    return background;
  }

  public static Resources getInstance() {
    if (instance == null)
      instance = new Resources();
    return instance;
  }

}
