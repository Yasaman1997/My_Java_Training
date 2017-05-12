package ir.aut.ceit.cards;

/**
 * Created by Admin on 4/23/2017.
 */
public abstract class Card {


    protected String name;
    protected String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Card() {
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }




    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesscription() {
        return description;
    }

    public void setDesscription(String desscription) {
        this.description = desscription;
    }
}
