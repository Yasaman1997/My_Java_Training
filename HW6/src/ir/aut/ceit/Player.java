package ir.aut.ceit;

import ir.aut.ceit.cards.Card;
import ir.aut.ceit.decks.CardDeck;
import ir.aut.ceit.decks.SpecialDeck;

/**
 * Created by Admin on 4/23/2017.
 */
public class Player {


    private CardDeck mainDeck;
    private SpecialDeck specialDeck;

    private Card[] hand;
    private Special nextSpecial;
    private int lifePoints;

    public Player(CardDeck playerDeck, SpecialDeck playerSpecials, int lifePoints) {

    }


    public Player(CardDeck playerDeck, SpecialDeck playerSpecials) {
        this.lifePoints = 5000;
    }


    public boolean draw(int count) {
        return true;
    }


    public void drawSpecialCard() {

    }


    public void nextTurnPrep() {

    }


    public boolean playCardFromHand(int whichCard, Field myField) {
        return true;
    }


    public boolean playSpecial(Field myField) {
        return true;
    }


    public void changeLifePoints(int change) {

    }


    public boolean isDefeated() {
        return true;
    }


    public boolean cardTurnEffects() {
        return true;
    }


    void playCardFromHand() {

    }

    public Special getNextSpecial() {

        return null;
    }


    void changeLifePoints() {

    }


    /*  public CardDeck getMainDeck() {
          return mainDeck;
      }

      public void setMainDeck(CardDeck mainDeck) {
          this.mainDeck = mainDeck;
      }

    public SpecialDeck getSpecialDeck() {
        return specialDeck;
    }

    public void setSpecialDeck(SpecialDeck specialDeck) {
        this.specialDeck = specialDeck;
    }
*/

    public void setNextSpecial(Special nextSpecial) {
        this.nextSpecial = nextSpecial;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public Card[] getHand() {
        return hand;
    }
}

