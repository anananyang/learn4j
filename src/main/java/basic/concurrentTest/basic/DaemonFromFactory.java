package basic.concurrentTest.basic;

import java.util.concurrent.*;

public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        printDaemon();
    }

    public static void printDaemon() {
        System.out.println("Thread [ " + getThreadName() + " ] is daemon ? " + isDaemon());
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }

    private static boolean isDaemon() {
        return Thread.currentThread().isDaemon();
    }

    public static void main(String[] args) {

        ExecutorService executorService = null;
        try {
            DaemonThreadFactory daemonThreadFactory = new DaemonThreadFactory();
            executorService = Executors.newCachedThreadPool(daemonThreadFactory);
            for (int i = 0; i < 5; i++) {
                executorService.execute(new DaemonFromFactory());
            }
            printDaemon();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(executorService != null) {
                executorService.shutdown();
            }
        }



    }


//    @Override
//    public void run() {
//        try {
//            while(true) {
//                TimeUnit.MILLISECONDS.sleep(100);
//                System.out.println("Thread.currentThread() " + this);
//            }
//        }catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws InterruptedException{
//        DaemonThreadFactory daemonThreadFactory = new DaemonThreadFactory();
//        ExecutorService executorService = Executors.newCachedThreadPool(daemonThreadFactory);
////        for(int i = 0; i < 10; i++) {
////            executorService.execute(new DaemonFromFactory());
////        }
//        executorService.execute(new DaemonFromFactory());
//        System.out.println("All daemons started");
//        TimeUnit.MILLISECONDS.sleep(500);
//    }
}


class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    }
}

class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

    public DaemonThreadPoolExecutor() {
        super(0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new DaemonThreadFactory());
    }
}