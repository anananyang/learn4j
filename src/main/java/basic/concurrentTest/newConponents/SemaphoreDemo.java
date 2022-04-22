package basic.concurrentTest.newConponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
}

class Pool<T> {
    // 被对象池管理的对象
    private List<T> items;
    // 对象池中对象的总数量
    private int size;
    // 与对象池长度一致，用于管理对象池中的对象
    private volatile boolean[] checkOut;
    // 计数信号量
    private Semaphore semaphore;

    Pool(Class<T> clazz, int size) {
        this.size = size;
        checkOut = new boolean[size];
        items = new ArrayList<>();
        semaphore = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取一个对象
    public T checkOut() throws InterruptedException {
        // 获取一个信号
        semaphore.acquire();
        return getItem();
    }

    // 可能有多个线程在同时操作 checkOut，需要同步
    private synchronized T getItem() {
        for(int i = 0; i < size; i++) {
            if(!checkOut[i]) {
                checkOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    // 返回一个item
    public void checkIn(T item) {
        if(releaseItem(item)) {
            semaphore.release();   // 释放信号
        }
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if(index == -1) {
            return false;
        }
        if(checkOut[index]) {
            checkOut[index] = false;
            return true;
        }
        return false;
    }
}