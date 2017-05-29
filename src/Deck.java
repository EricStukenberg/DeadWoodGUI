// Deck of Scene Cards for Deadwood

import java.util.*;
import java.io.*;

class Deck {

  private final int deckSize = 40;
  private ArrayList<Scene> scenes = new ArrayList<Scene>(deckSize);

  // Default constructor
  public Deck () {
    for(int i = 0; i < deckSize; i++){
      scenes.add(i, new Scene());
    }
    readScenes();
    readDescriptions();
    readRoles();
  }

  // Draws a card from the top of the deck
  public Scene drawCard() {
    Scene temp = scenes.remove(0);
    return temp;
  }

  // Shuffles Scene Cards
  public void shuffle() {
    Collections.shuffle(scenes);
  }

  /* Reads Scene info from text file with format
   * title/scene number/budget/number of roles
   */
  private void readScenes() {
    File file = new File("scenes.txt");
    try {
      Scanner scanlines = new Scanner(file);
      int count = 0;
      while(scanlines.hasNextLine()) {
        String line = scanlines.nextLine();
        Scanner scan = new Scanner(line);
        scan.useDelimiter("/");
        scenes.get(count).setTitle(scan.next());
        scenes.get(count).setSceneNum(scan.nextInt());
        scenes.get(count).setBudget(scan.nextInt());
        scenes.get(count).setNumofRoles(scan.nextInt());
        count++;
      }
    }
    catch(FileNotFoundException exception) {
      System.out.println("The file " + file.getPath() + " doesn't exit.");
    }
  }

  // Reads text file for scene descriptions
  private void readDescriptions() {
    File file = new File("descriptions.txt");
    try {
      Scanner scan = new Scanner(file);
      int count = 0;
      while(scan.hasNextLine()) {
        scenes.get(count).setDescription(scan.nextLine());
        count++;
      }
    }
    catch(FileNotFoundException exception) {
      System.out.println("The file " + file.getPath() + " doesn't exit.");
    }
  }

  /* Reads Scene roles from text file with format
   * role name/rank/line/role name/rank/line etc.
   */
  private void readRoles() {
    File file = new File("oncard.txt");
    try {
      Scanner scanlines = new Scanner(file);
      int count = 0;
      while(scanlines.hasNextLine()) {
        String line = scanlines.nextLine();
        Scanner scan = new Scanner(line);
        scan.useDelimiter("/");

        for (int i = 0; i < scenes.get(count).getNumofRoles(); i++) {
          scenes.get(count).getRole(i).setName(scan.next());
         // System.out.println(scenes.get(count).roles[i].getName());
          scenes.get(count).getRole(i).setRank(scan.nextInt());
          //System.out.println(scenes.get(count).roles[i].getRank());
          scenes.get(count).getRole(i).setLine(scan.next());
          //System.out.println(scenes.get(count).roles[i].getLine());
          scenes.get(count).getRole(i).setOnCard();
        }
        count++;
      }
    }
    catch(FileNotFoundException exception) {
        System.out.println("The file " + file.getPath() + " doesn't exit.");
    }
  }
}
