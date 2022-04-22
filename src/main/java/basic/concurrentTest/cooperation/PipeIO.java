package basic.concurrentTest.cooperation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipeIO {
    public static void main(String[] args) throws IOException{
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);

        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            executor.execute(sender);
            executor.execute(receiver);
        }finally {
            executor.shutdown();
        }

    }
}

class Sender implements Runnable {

    private PipedWriter writer = new PipedWriter();

    public PipedWriter getWriter() {
        return writer;
    }

    @Override
    public void run() {
        try {
            for (int i = 'A'; i <= 'z'; i++) {
                writer.write(i);
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sender interrupted");
        } catch (IOException e) {
            System.out.println("Sender IOException");
        }
    }
}

class Receiver implements Runnable {

    private PipedReader reader;

    Receiver(Sender sender) throws IOException {
        this.reader = new PipedReader(sender.getWriter());
    }

    @Override
    public void run() {
        try {
            for (int i = 'A'; i < 'z'; i++) {
                char value = (char) reader.read();
                System.out.println("receiver receive " + value);
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sender interrupted");
        } catch (IOException e) {
            System.out.println("Sender IOException");
        }
    }
}