package basic.innerClass;

public class InterfaceInnerClassTest {

    static class InterfaceInnerClass {
        // 静态内部类可以定义静态变量
        static final Integer TEST_NUM = 1;
        // 静态内部类可以定义静态方法
        static final void print(String marker) {
            System.out.println(marker);
        }
        void f() {
            print("InterfaceInnerClass.f()");
        }
    }
}

class InterfaceInnerClassMain {
    public static void main(String[] args) {
        InterfaceInnerClassTest.InterfaceInnerClass interfaceInnerClass = new InterfaceInnerClassTest.InterfaceInnerClass();
        interfaceInnerClass.f();
    }
}
