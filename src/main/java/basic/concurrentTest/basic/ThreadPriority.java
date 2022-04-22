package basic.concurrentTest.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadPriority implements Runnable {

    @Override
    public void run() {
        Thread curThrad = Thread.currentThread();
        setRandomPriority(curThrad);
        printPriority(curThrad);
    }

    private static void setRandomPriority(Thread curThrad) {
        int randomPriority = ThreadLocalRandom.current().nextInt(10);
        curThrad.setPriority(randomPriority);
    }

    private static void printPriority(Thread curThrad) {
        String threadName = curThrad.getName();
        int priority = curThrad.getPriority();
        System.out.println("priority of thread [" + threadName + "] is " + priority);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            executorService.execute(new ThreadPriority());
        }
        Thread mainThread = Thread.currentThread();
        printPriority(mainThread);
    }
}
