package ir.aut.ceit.cards;

/**
 * Created by Admin on 4/23/2017.
 */
public class MonsterCard extends Card {

    private int power;  //current attack
    private int basePower;  //the initial attack power of the monster when it was created.
    private boolean canAttack;   // whether or not the monster can attack on a given turn.


    public MonsterCard() {
        super();
    }

    public MonsterCard(String name, String description, int power, boolean canAttack) {

    }


    public MonsterCard(String name, String description, int power) {
        setCanAttack(false);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getBasePower() {
        return basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public boolean getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
}


