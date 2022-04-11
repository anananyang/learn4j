package basic.innerClass;

public class StaticInnerClassTest {
    static class StaticInnerClass {
        // 静态内部类可以定义静态变量
        static final Integer TEST_NUM = 1;
        // 静态内部类可以定义静态方法
        static final void print(String marker) {
            System.out.println(marker);
        }
        void f() {
            print("StaticInnerClass.f()");
        }
    }
}

class StaticInnerClassTestMain {
    public static void main(String[] args) {
        StaticInnerClassTest.StaticInnerClass staticInnerClass = new StaticInnerClassTest.StaticInnerClass();
        staticInnerClass.f();
    }
}


