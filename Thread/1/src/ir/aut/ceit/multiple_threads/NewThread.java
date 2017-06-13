package ir.aut.ceit.multiple_threads;

/**
 * Created by Admin on 6/13/2017.
 */
public class NewThread implements  Runnable {

    String name ;
    Thread t ;


    NewThread(String threadname)
    {
        name =threadname ;
        t = new Thread(this,name);
        System.out.println("new thread:" +t );
        t.start();
    }


    @Override
    public void run() {
        for (int i = 5; i >0; i--) {
            System.out.println(name+ "exiting");

        }
    }



     static class MultiThreadDemo
    {
        public  static  void main (String args[])
        {
            new NewThread("one");
            new NewThread("two");
            new NewThread("three");


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("main thread interruoted");
            }
            System.out.println("main thread exiting");
        }
    }
}
