package basic.concurrentTest.terminateThread;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TerminalTest {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        interrupt(new SleepBlocked());
        interrupt(new IOBlocked(System.in));
        interrupt(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }

    private static void interrupt(Runnable runnable) throws InterruptedException {
        // 执行
        Future<?> future = exec.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("interrupting " + runnable.getClass().getSimpleName());
        // 中断线程
        future.cancel(true);
        System.out.println("interrup send to " + runnable.getClass().getSimpleName());
    }
}

class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("SleepBlock interrupted");
        }
        System.out.println("SleepBlock completed");
    }
}

class IOBlocked implements Runnable {

    private InputStream in;

    IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for read()");
            in.read();
        } catch (Exception e) {
            // 中断标记在异常捕获时被清除了，所以这里不会获取到的
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("IOBlocked interrupted");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("IOBlocked completed");
    }
}

class SynchronizedBlocked implements Runnable {

    private Lock lock = new ReentrantLock();

    SynchronizedBlocked() {
        //
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

//    public synchronized void f() {
//        // 无限循环，不释放锁
//        while(true) {
//            Thread.yield();
//        }
//    }

    public void f() {

        try {
            lock.lockInterruptibly();
            // 无限循环，不释放锁
            while (true) {
                Thread.yield();
            }
        } catch (InterruptedException e) {
            System.out.println("SynchronizedBlocked interrupted");
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("SynchronizedBlocked completed");
    }
}


class NIOBlocked implements Runnable {
    private final SocketChannel sc;

    NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for read() in " + this);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            sc.read(byteBuffer);
        } catch (ClosedByInterruptException e) {
            System.out.println("NIOBlocked ClosedByInterruptException");
        } catch (ClosedChannelException e) {
            System.out.println("NIOBlocked ClosedChannelException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("NIOBlocked completed");
    }


    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future futur = null;
        try {
            futur = executorService.submit(new NIOBlocked(sc1));
            executorService.execute(new NIOBlocked(sc1));
        }finally {
            executorService.shutdown();
            // 中断 sc1
            if(futur != null) {
                futur.cancel(true);
            }
            // 中断 sc2
            sc2.close();
        }
    }
}