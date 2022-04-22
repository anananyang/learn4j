package basic.enumTest;

import java.util.EnumSet;

public class EnumAbstractMethod {

    private EnumSet<Cycle> enumSet = EnumSet.noneOf(Cycle.class);

    public void add(Cycle cycle) {
        enumSet.add(cycle);
    }

    public void washCar() {
        for(Cycle cycle : enumSet) {
            cycle.action();
        }

    }

    public static void main(String[] args) {
//        EnumAbstractMethod enumAbstractMethod = new EnumAbstractMethod();
//        for(Cycle cycle : Cycle.values()) {
//            enumAbstractMethod.add(cycle);
//        }
//        enumAbstractMethod.washCar();


        for(OverrideConstantsSpecific o : OverrideConstantsSpecific.values()) {
            System.out.println("--------- " + o.toString() + "-----------");
            o.f();
        }

    }
}


enum Cycle {

    UNDERBODY {
        @Override
        void action() {
            System.out.println("Spraying the underbody");
        }
    },

    WHEELWASH {
        @Override
        void action() {
            System.out.println("Washing the the wheels");
        }
    },

    PREWASH {
        @Override
        void action() {
            System.out.println("Lossening the dirt");
        }
    },

    BASIC {
        @Override
        void action() {
            System.out.println("The basic wash");
        }
    },

    HOTWAX {
        @Override
        void action() {
            System.out.println("Applying hot wax");
        }
    },

    RINSE {
        @Override
        void action() {
            System.out.println("Rinsing");
        }
    },

    BLOWDRY {
        @Override
        void action() {
            System.out.println("Blowing dry");
        }
    };

    abstract void action();
}

enum OverrideConstantsSpecific {
    NUT,
    BOLT,
    WASHER {
        void f() {
            System.out.println("overridden behavior");
        }

        public String toString() {
            return "I'm is WASHER";
        }
    };

    void f() {
        System.out.println("default behavior");
    }
}