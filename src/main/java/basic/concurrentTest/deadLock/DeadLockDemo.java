package basic.concurrentTest.deadLock;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        Resuorce resuorce = new Resuorce();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                resuorce.f();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                resuorce.g();
            }
        };
        thread1.start();
        thread2.start();
    }
}

class Resuorce {

    private Object flock = new Object();
    private Object glock = new Object();

    public void f() {
        try {
            synchronized (flock) {
                System.out.println("Resuorce.f() trying to sleep " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(2000);
                g();
            }
        } catch (InterruptedException e) {
            System.out.println("Resuorce.f() interrupted");
        }


    }

    public  void g() {
        try {
            synchronized (glock) {
                System.out.println("Resuorce.g() trying to sleep " + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(250);
                f();
            }

        } catch (InterruptedException e) {
            System.out.println("Resuorce.g() sleeping");
        }
    }
}