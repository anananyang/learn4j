package basic.interfaceTest;

public class NestingInterface {

    class EImpl implements E {
        @Override
        public void g() {

        }
    }

    class GImpl implements E.G {
        @Override
        public void f1() {}
    }

    class HImpl implements E.H {
        @Override
        public void f1() {}
    }

    public static void main(String[] args) {
        A a = new A();
        a.getD();
        a.receive(a.getD());
    }
}

// 接口嵌套在接口中
interface E {
    interface G {
        void f1();
    }

    public interface H {
        void f1();
    }

    void g();
}


// 接口嵌套在类中
class A {

    private interface D {
        void f1();
    }

    public class DImple implements D {
        @Override
        public void f1() {
            System.out.println("DImple.f1()");
        }
    }

    class DImple2 implements D {
        @Override
        public void f1() {
          System.out.println("DImple2.f1()");
        }
    }

    private class DImple3 implements D {
        @Override
        public void f1() {
            System.out.println("DImple3.f1()");
        }
    }

    D getD() {
        return new DImple();
    }

    void receive(D d) {
        d.f1();
    }
}