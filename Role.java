/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
*/
public class Role implements Comparable<Role> {

  private String name;
  private boolean onCard = false;
  private String line;
  private Player actor;
  private int practiceChips;
  private int rank;


  @Override public int compareTo(Role that) {

    //this optimization is usually worthwhile, and can
    //always be added
    if (this == that) {
      return 0;
    }
    //primitive numbers follow this form
    if (this.rank < that.getRank()) {
      return -1;
    }
    if (this.rank > that.getRank()) {
      return 1;
    }

    return 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    name = n;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String l) {
    line = l;
  }

  public Player getActor() {
    return actor;
  }

  public void setActor(Player a) {
    actor = a;
  }

  // Probably unnessesary
  public void removeActor() {
    actor = null;
  }

  public int getPracticeChips() {
    return practiceChips;
  }

  public void addPracticeChip() {
    practiceChips++;
  }

  // Probably unnessesary
  public void removePracticeChip() {
    practiceChips = 0;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int r) {
    rank = r;
  }

  public boolean getOnCard() {
    return onCard;
  }
  public void setOnCard() {
    onCard = true;
  }
}
