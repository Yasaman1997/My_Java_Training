package ir.aut.ceit.wait_notify;

/**
 * Created by Admin on 6/13/2017.
 */
public class Q {
    int n;

    /**
     *
     */
    boolean valueSet = false;


    synchronized int get() {
        if (!valueSet)
            //  System.out.println("Got" + n);
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("interrupted exception caught");
            }
        System.out.println("Got" + n);

        valueSet = false;
        notify();
        return n;
    }


    synchronized void put(int n) {
        if (valueSet)
            // this.n = n;
            //  System.out.println("put" + n);
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("interrupted exception caught");
            }
        this.n = n;
        valueSet = true;
        System.out.println("put" + n);
        notify();

    }

}


class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
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
        int i = 0;
        while (true) {
            q.put(i++);
        }

    }
}

class Consumer implements Runnable {
    Q q;


    Consumer(Q q) {
        this.q = q;
        new Thread(this, "consumer").start();
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
        while (true) {
            q.get();
        }
    }
}


class PC {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

        System.out.println("press ctrl+c to stop");
    }
}