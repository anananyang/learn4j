package basic.exceptions;

import java.util.concurrent.ThreadLocalRandom;

public class FinallyTest {

    void f() {
        int i = 0;
        while (true) {
            try {
                if (i++ == 1) {
                    throw new NullPointerException();
                }
                System.out.println("FinallyTest.f() no exception");
            } catch (NullPointerException e) {
                System.out.println("FinallyTest.f() exception");
            } finally {
                System.out.println("FinallyTest.f() finally i = " + i);
                if(i == 2) {
                    break;
                }
            }
        }

        System.out.println("FinallyTest.f() finish");
    }


    void h() {
        int i = ThreadLocalRandom.current().nextInt(3);
        try {
            System.out.println("return point 1");
            if(i == 1) {
                return;
            }
            System.out.println("return point 2");
            if(i == 2) {
                return;
            }
            System.out.println("return point 3");
            if(i == 3) {
                return;
            }
            System.out.println("return point 4");
            return;
        } finally {
            System.out.println("h() finally");
        }
    }


    void g() throws ImportantException {
        throw new ImportantException();
    }

    void cleanUp() throws TrivialException {
        throw new TrivialException();
    }


    int i() {
        int i = 1;
        try {
            return i;
        } finally {
            if(i == 1) {
                i = 2;
            }
            return i;
         }
    }

    public static void main(String[] args) {
//        FinallyTest finallyTest = new FinallyTest();
//        finallyTest.f();
//        finallyTest.h();

//        try {
//            FinallyTest finallyTest = new FinallyTest();
//            try {
//                finallyTest.g();
//            } finally {
//                finallyTest.cleanUp();
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        FinallyTest finallyTest = new FinallyTest();
        System.out.println("i = " + finallyTest.i());

    }
}


class ImportantException extends Exception {
    public String toString() {
        return "a very important exception";
    }
}

class TrivialException extends Exception {
    public String toString() {
        return "trivial exception";
    }
}

class ThreeException extends Exception {
    public String toString() {
        return "three exception";
    }
}

class Parent {
    void f() throws ImportantException, TrivialException {
    }

    void h() throws ImportantException, TrivialException {
    }

    void i() throws ImportantException, TrivialException {
    }

}

class Son extends Parent{
    // 不声明异常
    @Override
    void f() {}
    // 声明其中一个异常
    @Override
    void h() throws ImportantException {}
    // 声明父类方法中未声明的异常，编译器就会报错
//    @Override
//    void i() throws ThreeException {}
}