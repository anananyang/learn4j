package basic.concurrentTest.newConponents;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            executorService.execute(new WaittingTask(countDownLatch));
            for (int i = 0; i < 10; i++) {
                executorService.execute(new TaskPortion(countDownLatch));
            }
        }finally {
            executorService.shutdown();
        }

    }
}

class WaittingTask implements Runnable {

    private CountDownLatch countDownLatch;

    WaittingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println("latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    public String toString() {
        return Thread.currentThread().getName();
    }
}

class TaskPortion implements Runnable {

    private CountDownLatch countDownLatch;

    TaskPortion(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(this + " completed");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }

    }

    public String toString() {
        return Thread.currentThread().getName();
    }
}