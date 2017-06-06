/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
*/
import java.util.*;
import java.io.*;
// import java.util.Random;
// import java.util.Arrays;

class Board {
   private final int maxPlayers = 8;
   private final int setNumber = 10;
   private int turn;
   private int days;
   private int numberOfPlayers;
   private int sceneCount;
   public Player currPlayer;
   public volatile Boolean endClick;
   String input;

   Player[] players = new Player[maxPlayers];
   Set[] sets = new Set[10];
   static Trailers trailers = new Trailers();
   static CastingOffice casting_office = new CastingOffice();
   static Deck deck = new Deck();
   Random rand = new Random();

   // Default constructor
   public Board (int numPlayers) {
      deck.shuffle();
      numberOfPlayers = numPlayers;
      endClick = false;
      createPlayers(numPlayers);
      createSets();
      if(numPlayers > 3) {
          days = 4;
      } else {
        days = 3;
      }
      turn = 0;

   }

   private int calculateScore(int playerNum) {
      return players[playerNum].getMoney() + players[playerNum].getCredits() + (players[playerNum].getRank() * 5);
   }
   private void wrapScene(Set set) {

      /* This whole method still needs to be tested
       * also still needs to add off card features
       */

      // Count and get on/off card actors
      Role[] onCardActors = set.getScenecard().getActiveRoles();
      Role[] offCardActors = set.getActiveRoles();
      int onCard_count = set.getScenecard().countActiveRoles();
      int offCard_count = set.countActiveRoles();

      if (onCard_count > 0) {
         // Roll Money die
         Integer[] rolls = new Integer[set.getScenecard().getBudget()];
         for (int k = 0; k < set.getScenecard().getBudget(); k++) {
            rolls[k] = rollDie();
         }
         Arrays.sort(rolls, Collections.reverseOrder());
         Arrays.sort(onCardActors, Collections.reverseOrder());

         // Give highest money die to highest ranked actors wrapping back to top
         for (int i = 0; i < rolls.length/onCard_count; i++) {
            for(int j = 0; j < onCard_count; j++) {
               onCardActors[j].getActor().updateMoney(rolls[i+j]);
            }
         }
         // Pay off card roles by role rank
         for (int l = 0; l < offCard_count; l++) {
           onCardActors[l].getActor().updateMoney(set.getRole(l).getRank());
         }

      }
      removeActors(offCardActors);
      set.removeScenecard();
      sceneCount--;
      if (sceneCount ==  1) {
        endDay();
      }
   }

   // starts the game for the player
   public void startGame() {
     while(true) {

      for(int i = 0; i < numberOfPlayers; i++) {
        endClick = false;
        currPlayer = players[i];
        Scanner scanner = new Scanner(System.in);
        System.out.println("*************************  " + "It is the " + currPlayer.getName() + " player's turn." + "  *************************");
        //input = "reading";
        while(endClick == false) {
          // if (scanner.Next() != \n) {
          //   //input = scanner.nextLine();
          // }
          // System.out.println(input);
          // if(endClick) {
          //   System.out.println("breaking");
          //   break;
          // }
          // input = "1";

        }
        currPlayer.canMove = true;
        currPlayer.canAct = true;
      }
    }
   }


  //  // begin game helper function
  //  public boolean readUserInput(Player currPlayer) {
  //    String[] words = new String[3];
  //    if (input != "reading") {
  //      System.out.println(input);
  //    }
  //    words = input.split("\\s+");
  //    int len = words.length;
  //    if(input.equals("who")) {
  //      who(currPlayer);
  //    } else if(input.equals("Where")) {
  //      where(currPlayer);
  //    } else if(words[0].equals("move") && len > 1 && currPlayer.canMove) {
  //      movePlayer(currPlayer, words, len);
   //
  //    } else if(words[0].equals("work") && len > 1) {
  //      work(currPlayer,  words, len);
   //
  //    } else if(words[0].equals("upgrade") && len == 3) {
  //       if(words[1].equals("cr")) {
  //         upgradeCredit(words, currPlayer);
  //       }
  //       if(words[1].equals("$")) {
  //         upgradeMoney(words, currPlayer);
  //       }
   //
  //    } else if(words[0].equals("Rehearse") && currPlayer.canAct) {
  //      rehearse(currPlayer);
  //    } else if(words[0].equals("act") && currPlayer.canAct) {
  //      act(currPlayer);
  //    } else if(input.equals("end")) {
  //      System.out.println("End turn is Selected should return false");
  //      return false;
  //    }
  //    input = "reading";
  //    return true;
  //   }

    // who helper function
    public String who(Player currPlayer) {
      Role currRole = currPlayer.getRole();
      String name = currPlayer.getName();
      int money = currPlayer.getMoney();
      int credits = currPlayer.getCredits();
      if(currRole == null) {
        return name + " ($" + money + ", " + credits + "cr) ";
      } else {
        currRole.getName();
        return name + " ($" + money + ", " + credits + "cr) " + "working " + currRole.getName() + ", " + "\"" + currRole.getLine() + "\"";
      }
    }

    //  where helper function
    private void where(Player currPlayer) {
      Role currRole = currPlayer.getRole();
      Set currLocation = currPlayer.getLocation();
      Scene currScene = currLocation.getScenecard();
      String setName = currLocation.getName();
      if(currScene == null ) {
        if(setName.equals("Trailers") || setName.equals("Casting Office")) {
          System.out.println(setName);
        } else {
          System.out.println(setName + " Wrapped");
        }
      } else if (currRole != null ) {
        String title = currScene.getTitle();
        int sceneNum = currScene.getSceneNum();
        System.out.println("in " + setName + " shooting " +
        title + " scene " + sceneNum);
      } else {
        String title = currScene.getTitle();
        int sceneNum = currScene.getSceneNum();
        System.out.println(setName + " shooting " +
        title + " scene " + sceneNum);
      }
    }

    // helper for move
    private void movePlayer(Player currPlayer, String[] words, int len) {
      if(len == 2) {
        String moveTo = words[1];
        currPlayer.move(moveTo);
      }
      if(len == 3) {
        String moveTo = words[1] + " " + words[2];
        System.out.println("Move to " + moveTo);
        currPlayer.move(moveTo);
      }
    }
    private void work(Player currPlayer, String[] words, int len) {
      Set currLocation = currPlayer.getLocation();
      String currRole;
      StringBuilder sb = new StringBuilder();
      if(currPlayer.getRole() == null) {
        for(int i = 1; i < len; i++) {
          sb.append(words[i]);
          if(i != len-1) {
            sb.append(" ");
          }
        }
        currRole = sb.toString();
        currRole = currRole.trim();
        System.out.println("The role the player tried to work on " + currRole);
        int offLen = currLocation.getNumofRoles();
        Scene currScene = currLocation.getScenecard();
        int onLen = currScene.getNumofRoles();
        Role[] offCardRoles = new Role[offLen];
        Role[] onCardRoles = new Role[onLen];
        offCardRoles = currLocation.getOffCardRoles();
        onCardRoles = currScene.getRoles();
        for(int i = 0; i < offLen; i++) {
          String roleName = offCardRoles[i].getName();
          System.out.println("Off card roles " + roleName);
          if(roleName.trim().equals(currRole)) {
            System.out.println(currRole + " = " + roleName);
            for(int j = 0; j < numberOfPlayers; j++) {
              Boolean taken = false;
              if(players[j].getRole() != null ) {
                if(players[j].getRole().getName().equals(roleName)) {
                  System.out.println(roleName + " was already taken by player " + players[j].getName());
                  taken = true;
                }
              }
              if((j == (numberOfPlayers -1)) && taken == false) {
                currPlayer.takeRole(offCardRoles[i]);
                offCardRoles[i].setActor(currPlayer);
              }
            }
          }
        }
        for(int i = 0; i < onLen; i++) {
          String roleName = onCardRoles[i].getName();
          System.out.println("On card roles " + roleName);
          if(roleName.trim().equals(currRole)) {
            System.out.println(currRole + " = " + roleName);

            for(int j = 0; j < numberOfPlayers; j++) {

              Boolean taken = false;
              if(players[j].getRole() != null ) {
                if(players[j].getRole().getName().equals(roleName)) {
                  System.out.println(roleName + " was already taken by player " + players[j].getName());
                  taken = true;
                }
              }
              if(j == numberOfPlayers -1 && taken == false) {
                currPlayer.takeRole(onCardRoles[i]);
                onCardRoles[i].setActor(currPlayer);

              }
            }
          }
        }

      } else {
        System.out.println("Player is already in a role");
      }
    }
    public void upgradeMoney(int num, Player currPlayer) {
      try {
        if (currPlayer.getLocation().getName().equals("Casting Office")) {
          int level = num;
          int[][] priceTable = casting_office.getUpgradePrices();
          if(level > 1 && level < 7) {
            int price = priceTable[level - 2][0];
            System.out.println(price);
            if (price > currPlayer.getMoney()) {
              System.out.println("Insufficient money for upgrade");
            }
            else if(currPlayer.getRank() >= level) {
              System.out.println("Player rank is higher than desired rank");
            }
            else {
              currPlayer.updateMoney(-price);
              currPlayer.upgradeRank(level);
            }
          }
          else {
            System.out.println("Must be a level between 2 and 6");
          }
        }
        else {
          System.out.println("Not in Casting Office");
        }
      }
      catch(NumberFormatException exception) {
      }
    }

    public void upgradeCredit(int num, Player currPlayer) {
      try {
        System.out.println("Location " +  currPlayer.getLocation().getName());
        if (currPlayer.getLocation().getName().equals("Casting Office")) {
          int level = num;
          System.out.println("Level trying to upgrade to " + num);
          int[][] priceTable = casting_office.getUpgradePrices();
          if(level > 1 && level < 7) {
            int price = priceTable[level - 2][1];
            System.out.println(price);
            if (price > currPlayer.getCredits()) {
              System.out.println("Insufficient credits for upgrade");
            }
            else if(currPlayer.getRank() >= level) {
              System.out.println("Player rank is higher than desired rank");
            }
            else {
              currPlayer.updatesCredits(0 - price);
              currPlayer.upgradeRank(level);
            }
          }
          else {
            System.out.println("Must be a level between 2 and 6");
          }
        }
        else {
          System.out.println("Not in Casting Office");
        }
      }
      catch(NumberFormatException exception) {
      }
    }

    private void rehearse(Player currPlayer) {
      if(currPlayer.getRole() != null) {

        if (currPlayer.getRole().getRank() == currPlayer.getRole().getPracticeChips()) {
          System.out.println("Already have gauranteed success.");
        }
        else {
          currPlayer.rehearse();
          System.out.println("One practice chip added " + currPlayer.getName() + " has " + currPlayer.getRole().getPracticeChips());

        }
      }
    }

    private void act(Player currPlayer) {
      if(currPlayer.getRole() != null) {
        currPlayer.canAct = false;
        int roll = rollDie();

        if ((roll + currPlayer.getRole().getPracticeChips()) >=  (currPlayer.getLocation().getScenecard().getBudget())) {
          if (currPlayer.getRole().getOnCard()) {
            System.out.println("Act succeeded you got 2cr");
            currPlayer.updatesCredits(2);
          }
          else {
            System.out.println("Act succeeded you got 1c$");
            currPlayer.updatesCredits(1);
            currPlayer.updateMoney(1);
          }
          currPlayer.getLocation().setShots(currPlayer.getLocation().getShots() - 1);
          if (currPlayer.getLocation().getShots() == 0) {
            wrapScene(currPlayer.getLocation());
          }
        }
        else {
          System.out.println("Act failed");
          if (!currPlayer.getRole().getOnCard()) {
            currPlayer.updateMoney(1);
          }
        }
    }
    }
   // ends the day for the game
   private void endDay() {
     createSets();
     for (int i = 0; i < numberOfPlayers; i++) {
       players[i].setLocation(trailers);
     }
     days--;
     if (days == 0) {
       endGame();
       System.exit(0);
     }
   }
   // ends the game for the player
   public void endGame() {
     for (int i = 0; i < numberOfPlayers; i++) {
       System.out.println("Player " + players[i].getName() + " ended with a score of " + calculateScore(i) + "!");
     }
   }

   // rolls the dice
   private int rollDie() {
      int  num = rand.nextInt(6) + 1;
      return num;
   }

   // creates all the players colors
   private void createPlayers(int numPlayers) {
      String[] colors = new String[maxPlayers];
      colors[0] = "blue";
      colors[1] = "cyan";
      colors[2] = "green";
      colors[3] = "orange";
      colors[4] = "pink";
      colors[5] = "red";
      colors[6] = "violet";
      colors[7] = "yellow";
      for(int i = 0; i < numPlayers; i++){
         players[i] = new Player(colors[i]);
         if(numPlayers > 4) {
           if(numPlayers > 5) {
             if(numPlayers > 6) {
               players[i].upgradeRank(2);
             } else {
               players[i].updatesCredits(4);
             }
           } else {
             players[i].updatesCredits(2);
           }
         }
      }
      currPlayer = players[0];
   }

   // creates all the sets
   private void createSets() {
     sets[0] = new Set("Main Street", 3, 4, 950, 80);
     sets[1] = new Set("Saloon", 2, 2, 632, 320);
     sets[2] = new Set("Ranch", 2, 3, 270, 560);
     sets[3] = new Set("Secret Hideout", 3, 4, 240, 760);
     sets[4] = new Set("Bank", 1, 2, 623, 515);
     sets[5] = new Set("Hotel", 3, 4, 969, 775);
     sets[6] = new Set("Church", 2, 2, 623, 775);
     sets[7] = new Set("Train Station", 3, 4, 15, 120);
     sets[8] = new Set("Jail", 1, 2, 281, 80);
     sets[9] = new Set("General Store", 2, 2, 370, 330);
     sceneCount = 10;
     createBoardLayout();

   }

   /* Clears both the actors' current role and the roles actor
    * Probably unnessesary to remove roles' actor as the scenecard shouldn't
    * ever be reached after this
    */
   private void removeActors(Role[] roles) {
      for(int j = 0; j < roles.length; j++) {
         roles[j].getActor().removeRole();
      }

      for(int i = 0; i < roles.length; i++) {
         roles[i].removeActor();
      }
   }

   //
   private void createBoardLayout() {
      sets[0].setAdjacentRooms(sets[8], sets[1], trailers, null);
      sets[1].setAdjacentRooms(sets[0], trailers, sets[4], sets[9]);
      sets[2].setAdjacentRooms(casting_office, sets[9], sets[4], sets[3]);
      sets[3].setAdjacentRooms(casting_office, sets[2], sets[6], null);
      sets[4].setAdjacentRooms(sets[2], sets[6], sets[5], sets[1]);
      sets[5].setAdjacentRooms(sets[6], trailers, sets[4], null);
      sets[6].setAdjacentRooms(sets[5], sets[3], sets[4], null);
      sets[7].setAdjacentRooms(casting_office, sets[8], sets[9], null);
      sets[8].setAdjacentRooms(sets[7], sets[9], sets[0], null);
      sets[9].setAdjacentRooms(sets[7], sets[8], sets[1], sets[2]);
      trailers.setAdjacentRooms(sets[1], sets[0], sets[5], null);
      casting_office.setAdjacentRooms(sets[3], sets[7], sets[2], null);
      for( int i = 0; i < 10; i++) {
        sets[i].flipsSceneCard();
      }
   }

   /* Reads Set roles from text file with format
    * role name/rank/line/role name/rank/line etc.
    */
   private void readSetRoles() {
     File file = new File("../resources/offcard.txt");
     try {
       Scanner scanlines = new Scanner(file);
       int count = 0;
       while(scanlines.hasNextLine()) {
         String line = scanlines.nextLine();
         Scanner scan = new Scanner(line);
         scan.useDelimiter("/");

         for (int i = 0; i < sets[count].getNumofRoles(); i++) {
           sets[count].getRole(i).setName(scan.next());

           sets[count].getRole(i).setRank(scan.nextInt());

           sets[count].getRole(i).setLine(scan.next());
         }
         count++;
       }
     }
     catch(FileNotFoundException exception) {
         System.out.println("The file " + file.getPath() + " doesn't exit.");
     }
   }

   public int getNumberOfPlayers() {
     return numberOfPlayers;
   }

  public Player getCurrPlayer() {
    return currPlayer;
  }
}
