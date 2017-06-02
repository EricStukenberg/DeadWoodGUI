/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
*/
public class Trailers extends Set {

  private String name;
  private int xCord;
  private int yCord;

  // Default constructor
  public Trailers() {
    super("Trailers");
    name = "Trailers";
    xCord = 991;
    yCord = 248;
  }

  // Returns name
  public String getName() {
   return name;
  }

  public int getX() {
    return xCord;
  }

  public int getY() {
    return yCord;
  }
}
