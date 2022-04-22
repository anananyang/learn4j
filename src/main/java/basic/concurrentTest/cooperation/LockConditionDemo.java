package basic.concurrentTest.cooperation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 容器
        ConcurrentHolder<Integer> holder = new ConcurrentHolder<>(10);
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new LProductor(holder));
        executor.execute(new LConsumer(holder));

        TimeUnit.SECONDS.sleep(10);

        executor.shutdownNow();
    }
}


class LProductor implements Runnable {

    private ConcurrentHolder<Integer> holder;

    LProductor(ConcurrentHolder<Integer> holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer random = ThreadLocalRandom.current().nextInt(100);
                System.out.println("Producter product an value: " + random);
                holder.set(random);
                TimeUnit.MILLISECONDS.sleep(250);
            }
        } catch (InterruptedException e) {
            System.out.println("Producter interrupted");
        }
        System.out.println("Producter completed");
    }
}

class LConsumer implements Runnable {

    private ConcurrentHolder<Integer> holder;

    LConsumer(ConcurrentHolder<Integer> holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer value = holder.get();
                System.out.println("LConsumer get an value: " + value);
                TimeUnit.MILLISECONDS.sleep(250);
            }
        } catch (InterruptedException e) {
            System.out.println("LConsumer interrupted");
        }
        System.out.println("LConsumerv completed");
    }
}

class ConcurrentHolder<T> {
    // 存放元素的容器
    private List<T> elements = new LinkedList<>();
    // 锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    // 容器最多有多少个数据
    private int size;

    ConcurrentHolder(int size) {
        this.size = size;
    }

    public void set(T element) throws InterruptedException {
        lock.lock();
        try {
            while (elements.size() >= size) {
                condition.await();
            }
            elements.add(element);
            // 唤醒其他线程
            condition.signalAll();
            ;
        } finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (elements.size() == 0) {
                condition.await();
            }
            T element = elements.remove(0);
            // 唤醒其他线程
            condition.signalAll();
            return element;
        } finally {
            lock.unlock();
        }
    }

}