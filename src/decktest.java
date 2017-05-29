//Testing environment for deck and scene classes
public class decktest {
  public static void main(String [] args) {
    Deck deck = new Deck();
    //deck.shuffle();
    for (int j = 0; j < 40; j++) {
      Scene topcard = deck.drawCard();
     // System.out.println("The top card is \"" + topcard.getTitle() + "\", scene number " + topcard.getSceneNum() + ", with a budget of " + topcard.getBudget()
     // + " million. It has " + topcard.getNumofRoles() + " roles.");
     // System.out.println(topcard.getDescription());
      for (int i = 0; i < topcard.getNumofRoles(); i++) {
        //System.out.println(topcard.getRole(i).getName() + " with a rank of " + topcard.getRole(i).getRank() + ". Line: \"" + topcard.getRole(i).getLine() + "\"");
      }
    }
  }
}
