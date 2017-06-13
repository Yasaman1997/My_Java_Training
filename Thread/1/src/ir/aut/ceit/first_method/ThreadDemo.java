package ir.aut.ceit.first_method;

/**
 * Created by Admin on 6/12/2017.
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        new NewThread();

        try {


            for (int i = 5; i > 0; i--) {


                System.out.println("main thread" + i);
                Thread.sleep(1000);

            }

        }
        catch (InterruptedException e )
        {
            System.out.println("main thred interupted");
        }

        System.out.println("main thread exiting");


    }
}