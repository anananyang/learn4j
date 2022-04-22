package basic.concurrentTest.shareObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CriticalSection {

    private Lock lock = new ReentrantLock();

    private int x = 0;

    public int incr() {
        int i = -1;
        lock.lock();
        try {
            i = ++x;
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " get new value is " + i + " and new x is " + x);
        return i;
    }

    public static void main(String[] args) {
        final CriticalSection criticalSection = new CriticalSection();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    criticalSection.incr();
                }
            });
            if(i == 4) {
                executorService.shutdown();
            }
        }
//        executorService.shutdown();
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("aborting");
//                executorService.shutdown();
//                System.exit(0);
//            }
//        }, 5000);
    }

}



