/*
  Eric Stukenberg and Stephen Hyde-Donohue
  DeadWood Project
  WWU CSCI 345
  Spring 2017
*/
public class Set {

  private String name;
  private Role[] offCardRoles = new Role[4];
  private int shots;
  private Scene scenecard;
  private Set[] adjacentRooms = new Set[4];
  private int numofRoles;
  private int xCord;
  private int yCord;


  // Default constructor
  public Set (String setName) {
    name = setName;
  }
  public Set (String setName, int s, int n, int x, int y) {
    name = setName;
    shots = s;
    numofRoles = n;
    xCord = x;
    yCord = y;
    for (int i = 0; i < 4; i++) {
      offCardRoles[i] = new Role();
      //offCardRoles[i].setName("Temp");
    }
  }

  // Returns name
	public String getName() {
		return name;
	}

	// Returns offCardRoles array
	public Role[] getOffCardRoles() {
		return offCardRoles;
	}

  // Returns shots
	public int getShots() {
		return shots;
	}

  //sets shots
  public void setShots(int numShots) {
    shots = numShots;
  }
  // Returns shots
  public Scene getScenecard() {
    return scenecard;
  }

  // Flips scene card
  public void flipsSceneCard() {
    scenecard = Board.deck.drawCard();
    /* This requires the deck the board creates to be static
     * we might just want to let the deck do the flipping of cards
     * don't know if it matters
     */
  }
  public void removeScenecard() {
    scenecard = null;
  }

  // Sets the adjacent rooms
  public void setAdjacentRooms(Set room1, Set room2, Set room3, Set room4) {
    adjacentRooms[0]= room1;
    adjacentRooms[1]= room2;
    adjacentRooms[2]= room3;
    adjacentRooms[3]= room4;
  }

  // Returns adjacent rooms
  public Set[] getAdjacentRooms() {
    return adjacentRooms;
  }
  // returns number of roles
  public int getNumofRoles() {
    return numofRoles;
  }

  public void setNumofRoles(int n) {
    numofRoles = n;
  }

  public Role getRole(int n) {
    return offCardRoles[n];
  }

  public int getX() {
    return xCord;
  }

  public int getY() {
    return yCord;
  }

  public int countActiveRoles() {
    int count = 0;
    for (int i = 0; i < numofRoles; i++) {
      if (offCardRoles[i].getActor() != null) {
        count++;
      }
    }
    return count;
  }

  public Role[] getActiveRoles() {
    int count = this.countActiveRoles();
    Role[] active = new Role[count];
    int a = 0;
    for (int j = 0; j < numofRoles; j++) {
      if (offCardRoles[j].getActor() != null) {
        offCardRoles[j] = active[a];
        a++;
      }
    }
    return active;
  }
}
