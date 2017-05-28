package ir.aut.ceit.logic;

/**
 * Created by Admin on 5/27/2017.
 */
public class Mousexy {
    private static Mousexy ourInstance = new Mousexy();

    public static Mousexy getInstance() {
        return ourInstance;
    }

    private Mousexy() {
    }
}
