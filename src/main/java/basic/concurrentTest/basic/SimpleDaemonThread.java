package basic.concurrentTest.basic;

public class SimpleDaemonThread implements Runnable {

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
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SimpleDaemonThread());
            thread.setDaemon(true);
            thread.start();
        }

        printDaemon();
    }
}
