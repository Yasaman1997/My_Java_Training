package ir.aut.ceit.second_method;

/**
 * Created by Admin on 6/12/2017.
 */
public class NewThread extends Thread {

    NewThread() {
        super("demo thread");
        System.out.println("child thread" + this);
        start();  //start the thread
    }


    public void run() {

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("child thread" + i);

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("child interrupted");
        }
        System.out.println("exiting child thread");

    }
}


class ExtendThread {
    public static void main(String[] args) {
        new NewThread();


        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("main thread" + i);
                Thread.sleep(500);

            }
        } catch (InterruptedException e) {
            System.out.println("main interrupted");
        }
        System.out.println("exiting main thread");

    }
}

