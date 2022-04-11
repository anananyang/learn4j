package basic.generalType;

public class Tuple {
    public static void main(String[] args) {
        TwoTuple<String, Integer> twoTuple = new TwoTuple("just for fun", 1);
        System.out.println(twoTuple.toString());
        TwoTuple<Integer, String> twoTuple2 = new TwoTuple(1, "I'm happy now");
        System.out.println(twoTuple2.toString());

        ThreeTuple<String, Integer, Integer> threeTuple = new ThreeTuple("just for fun", 1, 2);
        System.out.println(threeTuple.toString());
        ThreeTuple<Integer, String, String> threeTuple2 = new ThreeTuple(1, "I'm happy now", "are you happy?");
        System.out.println(threeTuple2.toString());
    }

}

class TwoTuple<A, B> {
    protected A a;
    protected B b;

    TwoTuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public String  toString() {
        return "a = " + a + ", b = " + b;
    }
}

class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    protected C c;

    ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public String  toString() {
        return "a = " + a + ", b = " + b + ", c = " + c;
    }
}

