package basic.concurrentTest.shareObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenChcker implements Runnable {

    private IntGenerator intGenerator;
    private int id;


    EvenChcker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        // 控制线程结束
        while (!intGenerator.isCanceled()) {
            int value = intGenerator.next();
            if (value % 2 != 0) {
                System.out.println("EvenChcker[" + id + "] get " + value + " that is not even");
                intGenerator.cancel();
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }

    public static void check(IntGenerator intGenerator, int threadNum) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < threadNum; i++) {
                executorService.execute(new EvenChcker(intGenerator, i));
            }
        } finally {
            executorService.shutdown();
        }

    }
}


abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract  int next();

    public void cancel() {
        this.canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}

// 偶数生成器
class EvenIntGenerator extends IntGenerator {
    private int curEvenValue = 0;
    private Lock lock = new ReentrantLock();

//    @Override
//    public synchronized int next() {
//        curEvenValue++;
//        curEvenValue++;
//        return curEvenValue;
//    }

    @Override
    public int next() {
        lock.lock();
        try {
            curEvenValue++;
            curEvenValue++;
            return curEvenValue;
        } finally {
            lock.unlock();
        }

    }



    public static void main(String[] args) {
        EvenChcker.check(new EvenIntGenerator(), 2);
    }
}


class AtomicEvenIntGenerator extends IntGenerator {

    private AtomicInteger value = new AtomicInteger(0);

    @Override
    public int next() {
        return value.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChcker.check(new AtomicEvenIntGenerator(), 2);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("aborting");
                System.exit(0);
            }
        }, 5000);
    }
}