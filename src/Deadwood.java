/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
  Main Driver program
*/
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Dimension;
import java.awt.event.*;




public class Deadwood {

  public Board gameBoard;
  private JFrame mainFrame;

  public static void main( String[] args ) {
    JFrame frame = new JFrame();
    JLayeredPane pane = new JLayeredPane();
    Board model = beginGame();
    View view = new View(model);
    view.getContentPane().setPreferredSize(new Dimension(750,200));
    view.setVisible(true);
    playGame(model);

  }
  /*
  * Start the entire game
  */
  private static Board beginGame() {
     boolean correct = false;
     int numPlayers = 0;
     while(correct != true) {
      System.out.print("How many people will play the game? ");
         Scanner scanner = new Scanner(System.in);
         numPlayers = scanner.nextInt();
         if(numPlayers > 1 && numPlayers < 8) {
            correct = true;
         } else {
            System.out.println("\nToo few or too many players, 2 to 8 players only.");
         }
      }
      Board board = new Board(numPlayers);
      return board;
  }

  /*
    Plays game
  */
  private static void playGame(Board model) {
    model.startGame();

  }
}
