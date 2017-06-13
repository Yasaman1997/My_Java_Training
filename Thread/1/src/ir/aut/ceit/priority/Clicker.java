package ir.aut.ceit.priority;

/**
 * Created by Admin on 6/13/2017.
 */
 class Clicker implements Runnable {
    int click = 0;
    Thread t;
    private volatile boolean running = true;

    public Clicker(int p) {
        t = new Thread(this);
        t.setPriority(p);
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
        while (running) {
            click++;
        }
    }

    public void stop() {
        running = false;
    }


    public void start() {
        t.start();
    }
}


class HiLoPri {
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Clicker hi  = new Clicker(Thread.NORM_PRIORITY+2);
        Clicker lo = new Clicker(Thread.NORM_PRIORITY-2);


        lo.start();
        hi.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted");
        }


        lo.stop();
        hi.stop();






        //waiting for child to terminate


        try {
            hi.t.join();
            lo.t.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted exception caught");
        }

        System.out.println("low priority thread " +lo.click);
        System.out.println("high priority thread " +hi.click);
    }

}