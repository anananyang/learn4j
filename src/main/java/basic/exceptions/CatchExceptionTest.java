package basic.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CatchExceptionTest {


    void f() {
        System.out.println("CatchExceptionTest.f()");
        // 使用 try 块捕获异常
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("catch NullPointerException");
        } catch (RuntimeException e) {
            System.out.println("catch RuntimeException");
        } catch (Exception e) {
            System.out.println("catch Exception");
        }
    }


    void g() {
        int i = 0;
        while (i < 3) {
            try {
                if (i == 0) {
                    throw new NullPointerException();
                } else if (i == 1) {
                    throw new IllegalArgumentException();
                } else if (i == 2) {
                    throw new IndexOutOfBoundsException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException" + i);
            } catch (IllegalArgumentException e) {
                System.out.println("IllegalArgumentException" + i);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException" + i);
            }
            i++;
        }
    }

    void h() {
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        }
    }

    void I() {
        int i = 0;
        while (i < 3) {
            try {
                if (i == 0) {
                    throw new NullPointerException();
                } else if (i == 1) {
                    throw new IllegalArgumentException();
                } else if (i == 2) {
                    throw new IndexOutOfBoundsException();
                }
            } catch (Exception e) {
                System.out.println("Exception" + i);
            }
            i++;
        }
    }

    public void j() {
        throw new NullPointerException();
    }

    private void k() {
        j();
    }

    private void l() {
        k();
    }

    public static void main(String[] args) {
        CatchExceptionTest test = new CatchExceptionTest();
//        test.f();
//        test.g();
//        test.h();
//        test.I();

        try {
            test.l();
        }catch (NullPointerException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }
    }


}

