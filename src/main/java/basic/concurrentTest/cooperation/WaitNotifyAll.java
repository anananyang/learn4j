package basic.concurrentTest.cooperation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class WaitNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        // 容器
        Holder<Integer> holder = new Holder<>();
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Producter(1, holder));
        executor.execute(new Comsumer(2, holder));

        TimeUnit.SECONDS.sleep(10);

        executor.shutdownNow();
    }
}

// 生产者
class Producter implements Runnable {

    private Holder<Integer> holder;
    private long id;

    Producter(long id, Holder<Integer> holder) {
        this.id = id;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (holder) {
                    while (!holder.isEmpty()) {
                        holder.wait();
                    }
                    // 随机生成一个数字
                    Integer random = ThreadLocalRandom.current().nextInt(100);
                    System.out.println("Producter product an value: " + random);
                    holder.set(random);
                    holder.notify(); // 唤醒消费者
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Producter interrupted");
        }

        System.out.println("Producter completed");
    }
}

// 消费者
class Comsumer implements Runnable {

    private Holder<Integer> holder;
    private long id;

    Comsumer(long id, Holder<Integer> holder) {
        this.id = id;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (holder) {
                    while (holder.isEmpty()) {
                        holder.wait();
                    }
                    System.out.println("Comsumer get value: " + holder.get());
                    holder.notify();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Producter interrupted");
        }

        System.out.println("Producter completed");
    }
}

class Holder<T> {
    private T element;

    public void set(T t) {
        this.element = t;
    }

    public T get() {
        T t = element;
        element = null;
        return t;
    }

    public boolean isEmpty() {
        return element == null;
    }
}