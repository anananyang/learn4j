package basic.enumTest;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> enumMap = new EnumMap<>(AlarmPoints.class);
        enumMap.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("Kitchen fire!");
            }
        });

        enumMap.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("BATHROOM alert!");
            }
        });

        for(Map.Entry<AlarmPoints, Command> entry : enumMap.entrySet()) {
            entry.getValue().action();
        }
    }
}

enum AlarmPoints {
    STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY, KITCHEN;
}

interface Command {
    void action();
}

