package basic.concurrentTest.basic;

import java.util.concurrent.TimeUnit;

public class LiftOff implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    // 倒计时, 默认为 10
    private int countDown = 10;

    public LiftOff() {}
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    private String getStatus() {
        return "#" + id + "(" + (countDown--) + ")";
    }

    @Override
    public void run() {
       while(countDown != 0) {
           System.out.println(getStatus());
           try {
//               Thread.sleep(1000);
               TimeUnit.MILLISECONDS.sleep(1000);
           }catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
       System.out.println("#" + id + " LIFT OFF!");
    }


}