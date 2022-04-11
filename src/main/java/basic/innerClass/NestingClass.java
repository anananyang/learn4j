package basic.innerClass;

public class NestingClass {
    private int test = 2;
    class A {
        String test = "just for testing";
        int TEST_NUM = 2;
        void print() {
            System.out.println("class A.print() " + test);
        }

        class B {
            void print() {
                System.out.println("class A.B.print() "  + test);
            }
            A getLastOutterClass() {
                return A.this;
            }
            NestingClass getOutterClass() {
                return NestingClass.this;
            }
        }
    }

    public void print() {
        System.out.println("class NestingClass.print() "  + test);
    }

    public static void main(String[] args) {
        NestingClass nestingClass = new NestingClass();
        NestingClass.A a = nestingClass.new A();
        a.print();
        NestingClass.A.B b = a.new B();
        b.print();

        NestingClass.A lastOutterClass = b.getLastOutterClass();
        lastOutterClass.print();
        NestingClass outterClass = b.getOutterClass();
        outterClass.print();
    }
}
