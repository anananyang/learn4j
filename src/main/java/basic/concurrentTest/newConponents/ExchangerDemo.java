package basic.concurrentTest.newConponents;

import java.util.List;
import java.util.concurrent.*;

public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        List<Integer> productList = new CopyOnWriteArrayList<>();
        List<Integer> consumerList = new CopyOnWriteArrayList<>();

        ExchangerProductor productor = new ExchangerProductor(10, productList, exchanger);
        ExchangerConsumer consumer = new ExchangerConsumer(consumerList, exchanger);

        executorService.execute(consumer);
        executorService.execute(productor);

        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();

    }
}

class ExchangerProductor implements Runnable{

    private List<Integer> list;
    // 交换器
    private Exchanger<List<Integer>> exchanger;
    private int size;
    private int counter = 0;

    ExchangerProductor(int size, List<Integer> list, Exchanger<List<Integer>> exchanger) {
        this.size = size;
        this.list = list;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                for(int i = 0; i < size; i++) {
                    counter++;
                    System.out.println("ExchangerProductor product a num: " + counter);
                    list.add(counter);
                }
                list = exchanger.exchange(list);
            }
        }catch (InterruptedException e) {
            System.out.println("ExchangerProductor interrupted");
        }
        System.out.println("ExchangerProductor completed");
    }
}


class ExchangerConsumer implements Runnable{

    private List<Integer> list;
    // 交换器
    private Exchanger<List<Integer>> exchanger;

    ExchangerConsumer(List<Integer> list, Exchanger<List<Integer>> exchanger) {
        this.list = list;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                list = exchanger.exchange(list);
                for(Integer num : list) {
                    System.out.println("ExchangerConsumer consumer a number: " + num);
                    list.remove(num);
                }
            }
        }catch (InterruptedException e) {
            System.out.println("ExchangerConsumer interrupted");
        }
        System.out.println("ExchangerProductor completed");
    }
}

