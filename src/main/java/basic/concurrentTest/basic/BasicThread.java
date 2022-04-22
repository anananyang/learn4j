package basic.concurrentTest.basic;

public class BasicThread {
    public static void main(String[] args) {
        Runnable liftOff = new LiftOff();
        Thread thread = new Thread(liftOff);
        thread.start();
        System.out.println("waitting for liftoff");
    }
}