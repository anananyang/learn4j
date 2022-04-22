package basic.concurrentTest.newConponents;

import java.util.Queue;
import java.util.concurrent.*;

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<PrioritizedTask> queue = new PriorityBlockingQueue<>();
        PrioritiedTaskProducer producer = new PrioritiedTaskProducer(queue, executorService);
        PrioritiedTaskConsumer consumer = new PrioritiedTaskConsumer(queue);

        executorService.execute(producer);
        executorService.execute(consumer);
    }

}

// 优先任务的生产者
class PrioritiedTaskProducer implements Runnable {
    private Queue<PrioritizedTask> queue;
    private ExecutorService executorService;

    PrioritiedTaskProducer(Queue<PrioritizedTask> queue, ExecutorService executorService) {
        this.queue = queue;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        int id = 0;
        for(int i = 0; i < 10; i++, id++) {
            queue.add(new PrioritizedTask(id, ThreadLocalRandom.current().nextInt(10)));
            Thread.yield();
        }
        try {
            // 高优先级
            for(int i = 0; i < 10; i++, id++) {
                TimeUnit.NANOSECONDS.sleep(250);;
                queue.add(new PrioritizedTask(id, 10));
            }

            // 高优先级
            for(int i = 0; i < 10; i++, id++) {
                queue.add(new PrioritizedTask(id, i));
            }

            queue.add(new EndSentinelTask(id, id, executorService));
        }catch (InterruptedException e) {
            System.out.println("PrioritiedTaskProducer interrupted");
        }

        System.out.println("finished PrioritiedTaskProducer");

    }
}


// 优先任务的消费者
class PrioritiedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<PrioritizedTask> queue;

    PrioritiedTaskConsumer(PriorityBlockingQueue<PrioritizedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("PrioritiedTaskConsumer interrupted");
        }
        System.out.println("finished PrioritiedTaskProducer");
    }
}

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {

    private int id;
    private int priority;

    PrioritizedTask(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        if(priority > o.priority) {
            return -1;
        }
        if(priority < o.priority) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(toString());
    }

    public String toString() {
        return "task " + id + ": priority [ " + priority + " ] ";
    }
}

class EndSentinelTask extends PrioritizedTask {

    private ExecutorService executorService;

    EndSentinelTask(int id, int priority, ExecutorService executorService) {
        super(id, priority);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        System.out.println(this + " calling shutDownNow()");
        executorService.shutdownNow();
    }
}