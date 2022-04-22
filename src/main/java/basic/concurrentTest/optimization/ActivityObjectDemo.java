package basic.concurrentTest.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class ActivityObjectDemo {
    // 每个活动对象都维护自己的
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Future<Integer> calculateInt(final int a, final int b) {
        return executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    System.out.println("starting " + a + " + " + b);
                    TimeUnit.MILLISECONDS.sleep(200);
                    return a + b;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ActivityObjectDemo activityObject = new ActivityObjectDemo();
        List<Future<Integer>> futures = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; i++) {
            int a = ThreadLocalRandom.current().nextInt(100);
            int b = ThreadLocalRandom.current().nextInt(100);
            Future<Integer> future = activityObject.calculateInt(a, b);
            futures.add(future);
        }
        for (Future<Integer> future : futures) {

            try {
                System.out.println(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            futures.remove(future);
        }

        activityObject.shutdown();
        Collections.synchronizedMap(new HashMap<>());
    }


}
