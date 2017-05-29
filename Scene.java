// Scene Cards



class Scene {
  private final int maxRoles = 3;

  Role[] roles = new Role[maxRoles];
  private int budget;
  private int sceneNum;
  private int numofRoles;
  private String description;
  private String title;

  // Default constructor
  public Scene () {
    budget = 0;
    numofRoles = 0;
    sceneNum = 0;
    description = "Temp";
    title = "Temp";
    for (int i = 0; i < 3; i++) {
      roles[i] = new Role();
    }
  }
  public Role[] getRoles() {
    return roles;
  }
  public Role getRole(int n) {
    return roles[n];
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int b) {
    budget = b;
  }

  public int getSceneNum() {
    return sceneNum;
  }

  public void setSceneNum(int s) {
    sceneNum = s;
  }

  public int getNumofRoles() {
    return numofRoles;
  }

  public void setNumofRoles(int n) {
    numofRoles = n;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String d) {
    description = d;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String t) {
    title = t;
  }

  public int countActiveRoles() {
    int count = 0;
    for (int i = 0; i < numofRoles; i++) {
      if (roles[i].getActor() != null) {
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
      if (roles[j].getActor() != null) {
        roles[j] = active[a];
        a++;
      }
    }
    return active;
  }

}
