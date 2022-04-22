package basic.concurrentTest.optimization;

import basic.concurrentTest.shareObject.SynchronizedTest;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationComparisons {

    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest1 synchronizedTest = new SynchronizedTest1();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();

    static void test() {
        System.out.println("==============================");
        System.out.println(String.format("%-13s: %13d", "Cycles", Accoumulator.cycles));

        baseLine.timeTest();
        synchronizedTest.timeTest();
        lockTest.timeTest();
        atomicTest.timeTest();
        // 和基准测试比较
        Accoumulator.report(synchronizedTest, baseLine);
        Accoumulator.report(lockTest, baseLine);
        Accoumulator.report(atomicTest, baseLine);
        Accoumulator.report(synchronizedTest, lockTest);
        Accoumulator.report(synchronizedTest, atomicTest);
        Accoumulator.report(lockTest, atomicTest);
    }

    public static void main(String[] args) {
        System.out.println("warmup");
        // 提前调用一次，使得 Accoumulator 中静态部分初始化完成
        baseLine.timeTest();
        for (int i = 0; i < 5; i++) {
            test();
            Accoumulator.cycles =  Accoumulator.cycles * 2;
        }
        Accoumulator.exec.shutdown();
        List<Integer> list = new CopyOnWriteArrayList<>();
    }
}

// 累加器
abstract class Accoumulator {
    // 累加器ID
    protected String id = "error";
    // 循环次数
    public static long cycles = 50000L;
    // 运行时的 Modifier 和 Reader 的数量
    private static int N = 4;
    // 同步器
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(N * 2 + 1);
    public static ExecutorService exec = Executors.newCachedThreadPool();

    protected final static int SIZE = 100000;
    protected static int[] preloaded = new int[SIZE];
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected volatile long duration = 0;

    static {
        for (int i = 0; i < SIZE; i++) {
            preloaded[i] = ThreadLocalRandom.current().nextInt();
        }
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                accumulate();
            }
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void timeTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        // 等待 Modifier 和 Reader 完成
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 计算持续时间
        duration = System.nanoTime() - start;
        System.out.println(String.format("%-13s: %13d", id, duration));
    }

    public static void report(Accoumulator acc1, Accoumulator acc2) {
        System.out.println(String.format("%-22s: %.2f",
                acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration
        ));
    }
}

class BaseLine extends Accoumulator {
    {
        id = "BaseLine";
    }

    @Override
    public void accumulate() {
        int index = this.index;
        if (index >= SIZE) {
            index = 0;
        }
        value = value + preloaded[index];
        index++;
    }

    @Override
    public long read() {
        return value;
    }
}

class SynchronizedTest1 extends Accoumulator {
    {
        id = "Synchronized";
    }

    @Override
    public synchronized void accumulate() {
        value = value + preloaded[index];
        index++;
        if (index >= SIZE) {
            index = 0;
        }
    }

    @Override
    public synchronized long read() {
        return value;
    }
}


class LockTest extends Accoumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value = value + preloaded[index];
            index++;
            if (index >= SIZE) {
                index = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {

        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}


class AtomicTest extends Accoumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    @Override
    public void accumulate() {

        int idx = index.getAndIncrement();
        if(idx >= SIZE) {
            idx = 0;
            index.set(0);
        }
        value.getAndAdd(preloaded[idx]);
    }


    @Override
    public long read() {
        return value.get();

    }
}