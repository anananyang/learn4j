package basic.interfaceTest;

public abstract class AbstractClassTest {
    abstract void f1();
}

class AbstractSonClassTest extends AbstractClassTest {
    @Override
    void f1() {
       System.out.println("AbstractSonClassTest.f1()");
    }
}
