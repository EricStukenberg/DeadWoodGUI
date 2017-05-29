/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
  Main Driver program
*/
import java.util.*;
import java.io.*;

public class Deadwood {

  public static void main( String[] args ) {
    Board gameBoard = beginGame();
    playGame(gameBoard);
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
  private static void playGame(Board gameBoard) {
    gameBoard.startGame();

  }
}
