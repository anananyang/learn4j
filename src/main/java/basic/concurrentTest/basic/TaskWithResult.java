package basic.concurrentTest.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskWithResult implements Callable<String> {

    private volatile Integer id;

    public TaskWithResult(Integer id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return "Result of taskWithResult " + id;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            List<Future<String>> resultList = startTask(executorService);
            printResult(resultList);
        } finally {
            executorService.shutdown();
        }


    }

    private static List<Future<String>> startTask(ExecutorService executorService) {
        List<Future<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TaskWithResult task = new TaskWithResult(i);
            Future<String> future = executorService.submit(task);
            resultList.add(future);
        }
        return resultList;
    }

    private static void printResult(List<Future<String>> resultList) {
       for(Future<String> future : resultList) {
           try {
               System.out.println(future.get());
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }
       }

    }
}
