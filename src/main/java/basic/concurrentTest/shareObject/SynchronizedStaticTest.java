package basic.concurrentTest.shareObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStaticTest {

    synchronized static void f() {
        System.out.println("SynchronizedTest.f() start");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("f() has awakened");
        }

        System.out.println("SynchronizedTest.f() finish");
    }

    synchronized static void g() {
        System.out.println("SynchronizedTest.g() start");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("g() has awakened");
        }

        System.out.println("SynchronizedTest.g() finish");
    }


    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        TestA testA = new TestA(test);
        TestB testB = new TestB(test);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(testA);
        executorService.execute(testB);
    }
}

class TestStaticA implements Runnable {

    @Override
    public void run() {
        SynchronizedStaticTest.f();
    }
}

class TestStaticB implements Runnable {

    @Override
    public void run() {
        SynchronizedStaticTest.g();
    }
}