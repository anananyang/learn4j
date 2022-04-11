package basic.interfaceTest;

public interface InterfaceTest {
    void f1();
}

interface InterfaceTest2 {
    void f1();

    void f2();
}

interface InterfaceTest3 extends InterfaceTest, InterfaceTest2 {
    void f3();
}

class InterfaceTestBaseImpl {
    public void f1() {
        System.out.println("InterfaceTestBaseImpl.f1");
    }
}

class InterfaceTestImpl extends InterfaceTestBaseImpl implements InterfaceTest, InterfaceTest2, InterfaceTest3 {
    @Override
    public void f1() {
        System.out.println("InterfaceTestBaseImpl.f1");
    }

    @Override
    public void f2() {
        System.out.println("InterfaceTestImpl.f2");
    }

    @Override
    public void f3() {
        System.out.println("InterfaceTestImpl.f3");
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("---------- InterfaceTest --------");
        InterfaceTest interfaceTest = new InterfaceTestImpl();
        interfaceTest.f1();
        System.out.println("---------- InterfaceTest2 --------");
        InterfaceTest2 interfaceTest1 = (InterfaceTest2)interfaceTest;
        interfaceTest1.f1();
        interfaceTest1.f2();
        System.out.println("---------- InterfaceTest3 --------");
        InterfaceTest3 interfaceTest2 = (InterfaceTest3)interfaceTest;
        interfaceTest2.f1();
        interfaceTest2.f2();
        interfaceTest2.f3();
    }
}