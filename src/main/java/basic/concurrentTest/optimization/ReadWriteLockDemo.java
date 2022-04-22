package basic.concurrentTest.optimization;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException{
        Holder<Integer> holder = new Holder<>();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            exec.execute(new LReader(holder, countDownLatch));
        }
        for(int i = 0; i < 5; i++) {
            exec.execute(new LWriter(holder));
        }
        countDownLatch.await();
        System.out.println("exec.shutdown()");
        exec.shutdownNow();
    }
}

class LReader implements Runnable {

    private Holder<Integer> holder;
    private CountDownLatch countDownLatch;

    LReader(Holder<Integer> holder, CountDownLatch countDownLatch) {
        this.holder = holder;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 20; i++) {
                int value = ThreadLocalRandom.current().nextInt();
                System.out.println(Thread.currentThread().getName() + " generate an num: " + value);
                holder.set(value);
                TimeUnit.MILLISECONDS.sleep(200);
            }
            countDownLatch.countDown();
        }catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }

        System.out.println(Thread.currentThread().getName() + " completed");
    }
}

class LWriter implements Runnable {
    private Holder<Integer> holder;

    LWriter(Holder<Integer> holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Integer value = holder.get();
                System.out.println(Thread.currentThread().getName() + " get an num: " + value);
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " writer interrupted");
        }

    }
}

class Holder<T> {
    private List<T> list = new LinkedList<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void set(T element) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public T get() {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {

            return list.size() > 0 ? list.get(list.size() - 1) : null;
        } finally {
            readLock.unlock();
        }
    }
}