package ir.aut.ceit.cards;

import ir.aut.ceit.Field;

/**
 * Created by Admin on 4/23/2017.
 */
public abstract class SpellCard extends Card {

    public SpellCard(String name, String description) {

    }


    abstract void turnEffect(Field ownerField, Field enemyFiled);

    abstract void destroyedEffect(Field ownerField, Field enemyFiled);
}
