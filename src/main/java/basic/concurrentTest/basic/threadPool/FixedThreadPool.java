package basic.concurrentTest.basic.threadPool;

import basic.concurrentTest.basic.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {
        Runnable liftOff = new LiftOff();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            for(int i = 0; i < 5; i++) {
                executor.execute(new LiftOff());
            }
        }finally {
            executor.shutdown();
        }


        System.out.println("waitting for liftoff");
    }
}
