package basic.concurrentTest.basic;

public class ThreadException implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        MyUncaughtExceptionHandler handler = new MyUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
        Thread thread = new Thread(new ThreadException());
        thread.start();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String message = String.format("%s catch an exception", t.getName(), e.getMessage());
        System.out.println(message);
    }
}