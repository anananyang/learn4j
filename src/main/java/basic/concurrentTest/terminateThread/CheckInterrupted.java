package basic.concurrentTest.terminateThread;

import java.util.concurrent.TimeUnit;

public class CheckInterrupted implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup n1 = new NeedsCleanup(1);
                // point 1
                try {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // point 2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("calculating");
                        double value = 0;
                        for(int i = 1; i < 25000; i++) {
                           value = value + (Math.PI + Math.E) / value;
                        }
                        System.out.println("finished to calculat");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("exiting via while() test");

        } catch (InterruptedException e) {
            System.out.println("exiting via InterruptedException");
        }
    }


    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new CheckInterrupted());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        // 中断
        t.interrupt();
    }
}


class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("clean up " + id);
    }
}