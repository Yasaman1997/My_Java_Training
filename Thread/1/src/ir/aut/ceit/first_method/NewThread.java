package ir.aut.ceit.first_method;

/**
 * Created by Yasaman on 6/12/2017.
 */

public class NewThread implements Runnable {
    Thread t1;

    NewThread() {
        t1 = new Thread(this, "demo thread");
        System.out.println("child thread" + t1);
        t1.start();
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
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

