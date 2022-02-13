package innerClass.innerPackage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Parcel {
    private int id;

    public Parcel(int id) {
        this.id = id;
    }
    // 第一个内部类
    class Contents {
        private int i = 11;
        public int value() { return i;}
        int readOutterId() {
            return id;
        }
    }
    // 第二个内部类
    class Destination {
        private String label;
        Destination(String whereTo) {
            this.label = whereTo;
        }
        String readLabel() {
            return this.label;
        }
    }

    public Contents createContents() {
        return new Contents();
    }

    public Destination createDestination(String dest) {
        return new Destination(dest);
    }

    public void ship(String dest) {
        Contents contents = new Contents();
        System.out.println("value of contents is " + contents.value());
        System.out.println("id of outter " + contents.readOutterId());
        Destination destination = new Destination(dest);
        System.out.println("label of destination is " + destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel parcel = new Parcel(ThreadLocalRandom.current().nextInt());
        parcel.ship("hangzhou");
    }

}
