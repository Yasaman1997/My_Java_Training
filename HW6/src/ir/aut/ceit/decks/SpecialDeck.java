package ir.aut.ceit.decks;

import ir.aut.ceit.cards.Card;

/**
 * Created by Admin on 4/23/2017.
 */
public class SpecialDeck extends ObjectDeck {


    public SpecialDeck(Card card)
    {
      super();
    }


    @Override
    public Object deal() {
        //return a card instead of an object
        return super.deal();
    }
}
