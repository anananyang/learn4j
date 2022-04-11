package basic.reuseClass;

import java.util.ArrayList;
import java.util.List;

public class FinalClassTest {
    public final void f1() {
        System.out.println("FinalClassTest.f1()");
    }

    protected final void f2() {
        System.out.println("FinalClassTest.f2()");
    }

    private final void f3() {
        System.out.println("FinalClassTest.f3()");
    }
}

class Test extends FinalClassTest{

//    public final void f1() {
//        System.out.println("Test.f1()");
//    }
//
//    protected final void f2() {
//        System.out.println("Test.f2()");
//    }

    public void f3() {
        System.out.println("Test.f3()");
    }
}

class Value {
    private int i = 0;

    public void incr() {
        this.i++;
    }

    public int getValue() {
        return i;
    }
}

class Main {
    private final int j;
    private final Value value;

    public Main(int j) {
        this.j = j;
        value = new Value();
    }

    void f() {
        this.value.incr();
    }

    public static void main(String[] args) {

    }
}