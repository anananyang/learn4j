package basic.innerClass;

import java.util.concurrent.ThreadLocalRandom;

public class OtherClass {

    public static void main(String[] args) {
        Parcel parcel = new Parcel(ThreadLocalRandom.current().nextInt());
        Parcel.Contents content = parcel.new Contents();
        System.out.println("value of contents is " + content.value());
        System.out.println("id of Parcel is " + content.readOutterId());
    }
}
