package basic.concurrentTest.shareObject;

public class SynchronizedDemo {

    public void g(){
        synchronized(this) {
            System.out.println("SynchronizedTest.f()");
        }
    }

    public synchronized void f(){
        System.out.println("SynchronizedTest.f()");
    }

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.g();
        synchronizedDemo.f();

    }
}
