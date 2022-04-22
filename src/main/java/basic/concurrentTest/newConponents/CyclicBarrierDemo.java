package basic.concurrentTest.newConponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static void main(String[] arg) {
        HorseRace horseRace = new HorseRace(7, 200);
        horseRace.start();
    }
}


class Horse implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;
    // 记录马已经跑了多远
    private int strides;

    Horse(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getId() {
        return id;
    }

    // 有两个线程在操作 strides, 需要同步
    public synchronized int getStrides() {
        return strides;
    }

    public void tracks() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strides; i++) {
            sb.append("*");
        }
        sb.append(id);
        System.out.println(sb.toString());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 有两个线程在操作 strides，需要同步
                synchronized (this) {
                    this.strides += ThreadLocalRandom.current().nextInt(3);
                }
                // 等待
                System.out.println("horse " + id + " starting to wait");
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

class HorseRace {
    // 终点
    private static int FINISH_LINE = 75;
    // 赛马场有多少匹马在赛跑
    private List<Horse> horses = new ArrayList<>();

    private CyclicBarrier cyclicBarrier;
    // 用于赛马结束关闭线程池
    private ExecutorService executorService;
    // 暂停时间
    private int pause;

    class BarrierAction implements Runnable {

        private void printRunway() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                sb.append("=");
            }
            System.out.println(sb.toString());
        }

        private void tracks() {
            for (Horse horse : horses) {
                horse.tracks();
            }
        }

        private boolean isRaceFinished() {
            for (Horse horse : horses) {
                if (horse.getStrides() >= FINISH_LINE) {
                    return true;
                }
            }
            return false;
        }

        private void printWonHorse() {
            int id = this.getWonHorseId();
            System.out.println("horse " + id + " won!");
        }

        private int getWonHorseId() {
            int id = -1;
            int maxStrides = -1;
            for (Horse horse : horses) {
                int strides = horse.getStrides();
                if (strides >= FINISH_LINE && strides > maxStrides) {
                    id = horse.getId();
                }
            }
            return id;
        }

        @Override
        public void run() {
            // 先打印赛马跑道的长度
            printRunway();
            // 打印本次所有马跑的距离
            tracks();
            // 判断如果有马已经跑到终点，则关闭线程
            if (isRaceFinished()) {
                printWonHorse();
                // 这里一定使用 shutdownNow 关闭，否则线程池不会真正地关闭
                executorService.shutdownNow();
                return;
            }
            // 否则，睡眠
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("barrier-acton sleep interrupted");
            }
        }
    }

    HorseRace(int horseNum, int pause) {
        this.pause = pause;
        cyclicBarrier = new CyclicBarrier(horseNum, new BarrierAction());
        this.executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < horseNum; i++) {
            horses.add(new Horse(i, cyclicBarrier));
        }
    }

    public void start() {
        for (Horse horse : horses) {
            executorService.execute(horse);
        }
    }
}
