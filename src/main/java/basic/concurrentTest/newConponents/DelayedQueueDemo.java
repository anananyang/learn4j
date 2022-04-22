package basic.concurrentTest.newConponents;

import java.util.List;
import java.util.concurrent.*;

public class DelayedQueueDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int delayTaskNum = 10;
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        for (int i = 0; i < delayTaskNum; i++) {
            delayQueue.put(new DelayedTask(i, ThreadLocalRandom.current().nextLong(5000)));
        }
        // 停止点
        delayQueue.put(new EndSentinel(delayTaskNum, 5000, executorService));
        executorService.execute(new DelayedTaskConsumer(delayQueue));
    }
}


class DelayedTask implements Delayed, Runnable {

    private long delta;   // 延迟时间
    private long triggle; // 实际执行时间, 单位纳秒
    private int id;

    DelayedTask(int id, long delta) {
        this.id = id;
        this.delta = delta;
        triggle = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
    }

    // 获取剩余延迟时间
    @Override
    public long getDelay(TimeUnit unit) {
        long delta = triggle - System.nanoTime();
        return unit.convert(delta, unit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (triggle < that.triggle) {
            return -1;
        }
        if (triggle > that.triggle) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(toString());
    }

    public String toString() {
        return "task " + id + ": delay [ " + delta + " ] ";
    }
}

class EndSentinel extends DelayedTask {

    private ExecutorService executorService;

    EndSentinel(int id, long delta, ExecutorService executorService) {
        super(id, delta);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        System.out.println(this + " calling shutDownNow()");
        executorService.shutdownNow();
    }
}


// 延迟任务消费者
class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> delayQueue;

    DelayedTaskConsumer(DelayQueue<DelayedTask> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        // 从延迟队列中取出任务消费
        try {
            while (!Thread.interrupted()) {
                delayQueue.take().run();   // 直接在本线程执行
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished DelayTaskConsumer");
    }
}