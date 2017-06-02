/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
*/
public class Player {

  private String name;
  private int rank;
  private int credits;
  private int money;
  private Set location;
  private Role role;
  boolean canMove = true;
  boolean canAct = true;
  // Default constructor
  public Player (String color) {
    name = color;
    rank = 1;
    credits = 0;
    money = 0;
    location = Board.trailers;
  }

  // Returns name
  public String getName() {
    return name;
  }

  // Returns rank
  public int getRank() {
    return rank;
  }

  // Returns credits
  public int getCredits() {
    return credits;
  }

  // Updates player's credits
  public void updatesCredits(int newCredits) {
    credits += newCredits;
  }

  // Returns money
  public int getMoney() {
    return money;
  }

  public void updateMoney(int newMoney) {
    money += newMoney;
  }

  // Returns location
  public Set getLocation() {
    return location;
  }

  public void setLocation(Set s) {
    location = s;
  }

  // Returns players role
  public Role getRole() {
    return role;
  }


  public void removeRole() {
    role = null;
  }

  // Moves player
  public void move(String moveTo) {
    Set[] adjacentRooms = location.getAdjacentRooms();
    int i = 0;
    boolean moved = false;
    while(adjacentRooms[i] != null && i < 3) {
      if(moveTo.equals(adjacentRooms[i].getName())) {
        location = adjacentRooms[i];
        moved = true;
        canMove = false;
      }
      i++;
    }
    if( moved == false) {
      System.out.println("The set " + moveTo + " is not an adjacent Set.");
    }
  }

  // Upgrades player's rank
  public void upgradeRank(int newRank) {
    rank = newRank;
  }

  // Gives player a role
  public void takeRole(Role newRole) {
    role = newRole;
  }

  // Has player rehearse
  public void rehearse() {
    role.addPracticeChip();
    canAct = false;
  }
}
