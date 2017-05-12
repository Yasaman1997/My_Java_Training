package ir.aut.ceit;

import ir.aut.ceit.cards.MonsterCard;
import ir.aut.ceit.cards.SpellCard;

/**
 * Created by Admin on 4/23/2017.
 */
public class Field {

    private MonsterCard[] monsterCards;
    private SpellCard[] spellCards;


    public Field() {

    }

    public void cardTurnEffects(Field enemyField) {

    }


    public boolean addMonsterCard(MonsterCard card) {
        return true;
    }


    public boolean addSpellCard(SpellCard card) {
        return true;
    }


    public MonsterCard[] getMonsterCards() {
        return monsterCards;
    }

  /*  public void setMonsterCards(MonsterCard[] monsterCards) {
        this.monsterCards = monsterCards;
    }
*/
    public SpellCard[] getSpellCards() {
        return spellCards;
    }

  /*  public void setSpellCards(SpellCard[] spellCards) {
        this.spellCards = spellCards;
    }
    */
}
