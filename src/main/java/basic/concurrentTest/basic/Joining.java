package basic.concurrentTest.basic;

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);

        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);

        grumpy.interrupt();
    }
}


class Sleeper extends Thread {
    private int duration;

    Sleeper(String threadName, int duration) {
        super(threadName);
        this.duration = duration;
        start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(String.format("%s was interrupted. isInterrupted() is %b",
                    getName(),
                    isInterrupted()));
            return;
        }
        System.out.println(String.format("%s has awakened", getName()));
    }
}

class Joiner extends Thread {

    private Sleeper sleeper;

    Joiner(String threadName, Sleeper sleeper) {
        super(threadName);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s join completed", getName()));
    }
}