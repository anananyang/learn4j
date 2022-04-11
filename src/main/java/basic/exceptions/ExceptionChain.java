package basic.exceptions;

public class ExceptionChain {

    void f() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            throw e;  // 重新抛出
        }
    }

    void g() {
        throw new NullPointerException();
    }

    void h() {
        try {
            g();
        } catch (NullPointerException e) {
            System.out.println("ExceptionChain.h() catch a NullPointerException");
            e.printStackTrace();
            throw (NullPointerException) e.fillInStackTrace();
        }
    }

    void i() {
        try {
            h();
        } catch (NullPointerException e) {
            System.out.println("ExceptionChain.i() catch a NullPointerException");
            e.printStackTrace();
            throw (NullPointerException) e.fillInStackTrace();
        }
    }

    void l() {
        try {
          g();
        }catch (NullPointerException e) {
            System.out.println("ExceptionChain.h() catch a NullPointerException");
            RuntimeException runtimeException = new RuntimeException(e);
            runtimeException.initCause(e);
            throw runtimeException;
        }
    }



    public static void main(String[] args) {
        ExceptionChain exceptionChain = new ExceptionChain();
//        exceptionChain.f();
//        exceptionChain.i();

        try {
            exceptionChain.l();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class MyException extends RuntimeException {

    MyException(String message) {
        super(message);
    }
}
