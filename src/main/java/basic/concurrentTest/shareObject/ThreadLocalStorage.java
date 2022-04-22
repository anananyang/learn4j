package basic.concurrentTest.shareObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalStorage {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public int incr() {
        Integer value = threadLocal.get();
        if (value == null) {
            value = 0;
        }
        value++;
        threadLocal.set(value);

        return value;
    }

    public int get() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        final ThreadLocalStorage threadLocalStorage = new ThreadLocalStorage();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 5; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        while(!Thread.currentThread().isInterrupted()) {
                            threadLocalStorage.incr();
                            System.out.println(Thread.currentThread().getName() + " get value " + threadLocalStorage.get());
                        }
                    }
                });
            }

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          executorService.shutdown();
        }
    }
}
