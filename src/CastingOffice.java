public class CastingOffice extends Set {

  private String name;
  private int[][] upgradePrices = new int[5][2];
  private int xCord;
  private int yCord;

  // Default constructor
  public CastingOffice() {
    super("Casting Office");
    name = "Casting Office";
    xCord = 9;
    yCord = 459;
    createPriceTable();
  }

  // returns name
  public String getName() {
    return name;
  }

  // returns upgradePrices array
  public int[][] getUpgradePrices() {
    return upgradePrices;
  }

  // creates upGradePrice array the array has
  // dollar value followed by a
  public void createPriceTable() {
    int dlr = 4; /* dollar starting value */
    int crd = 5; /* credit starting value */
    int dlrAddVal = 6; /* add value to dollar */
    for(int r = 0; r < 5; r++) {
      int c = 0; /* column */
      upgradePrices[r][c] = dlr;
      upgradePrices[r][c+1] = crd;
      dlr += dlrAddVal;
      crd += 5;
      dlrAddVal += 2;
    }
  }
  public int getX() {
    return xCord;
  }

  public int getY() {
    return yCord;
  }
}
