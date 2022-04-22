package basic.enumTest;

import java.util.concurrent.ThreadLocalRandom;

public class TrafficLight {
    enum Signal {
        GRRREN, YELLOW, RED;

        public String toString() {
            return "I'am is " + this.name().toLowerCase();
        }

    }

    private Signal singal = Signal.RED;
    public void next() {
        Signal pre = singal;
        switch (singal) {
            case RED: singal = Signal.GRRREN; break;
            case YELLOW: singal = Signal.RED; break;
            case GRRREN: singal = Signal.YELLOW; break;
        }
        System.out.println(pre + " ----> " + singal);
    }

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        int count = ThreadLocalRandom.current().nextInt(10);
        for (int i = 0; i < count; i++) {
            trafficLight.next();
        }
    }

}
